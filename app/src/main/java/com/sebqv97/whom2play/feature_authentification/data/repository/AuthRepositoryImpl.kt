package com.sebqv97.whom2play.feature_authentification.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

import com.sebqv97.whom2play.common.util.Resource
import com.sebqv97.whom2play.feature_authentification.data.model.User
import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore

) : AuthRepository {


    override suspend fun createUser(auth: User): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        firebaseAuth.createUserWithEmailAndPassword(auth.email, auth.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                   if(addUserIntoDb(user = auth)) {
                       trySend(Resource.Success(true))
                       Log.d("Logged: ", "Current UserID: ${firebaseAuth.currentUser?.uid}")
                   }
                }
            }.addOnFailureListener {
            trySend(Resource.Error(message = it.localizedMessage ?: "Registration Failed"))
        }
        awaitClose { close() }

    }


    override suspend fun loginUser(auth: User): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        firebaseAuth.signInWithEmailAndPassword(auth.email, auth.password)
            .addOnCompleteListener {

                trySend(Resource.Success(true))
            }.addOnFailureListener {
                trySend(Resource.Error(message = it.message ?: "Login Failed"))
            }
        awaitClose {
            close()
        }

    }

    override suspend fun logoutUser(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            firebaseAuth.signOut()
            emit(Resource.Success(true))
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(message = e.message ?: "Unknown Error Occurred"))
        }
    }

    override fun isUserLoggedIn() = firebaseAuth.currentUser != null

    override fun getFirebaseAuthState() = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }

    }

    override  fun addUserIntoDb(user: User):Boolean {
        var status = false
        fireStore.collection("Users")
            .add(user.getDetailsIntoHashMap())
            .addOnSuccessListener {
             status = true

            }
            .addOnFailureListener {
                status = false
            }
        return status
        }

}