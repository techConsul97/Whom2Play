package com.sebqv97.whom2play.feature_authentification.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.sebqv97.whom2play.common.util.Resource
import com.sebqv97.whom2play.feature_authentification.data.model.User
import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,

    ): AuthRepository{


    override suspend fun createUser(auth: User): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading<String>())

        firebaseAuth.createUserWithEmailAndPassword(auth.email,auth.password).addOnCompleteListener{
            if(it.isSuccessful) {
                trySend(Resource.Success("User Created Successfully"))
                Log.d("Logged: ", "Current UserID: ${firebaseAuth.currentUser?.uid}")
            }
        }.addOnFailureListener {
            trySend(Resource.Error<String>("Login Failed"))
        }
        awaitClose{ close() }

    }


    override suspend fun loginUser(auth: User): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading<String>())
        firebaseAuth.signInWithEmailAndPassword(auth.email,auth.password)
            .addOnCompleteListener {
                trySend(Resource.Success<String>("Login successful"))
            }.addOnFailureListener {
                trySend(Resource.Error<String>("Login Failed"))
            }
        awaitClose{
            close()
        }

    }

}