package com.sebqv97.whom2play.feature_authentification.domain.repository

import com.sebqv97.whom2play.common.util.Resource
import com.sebqv97.whom2play.feature_authentification.data.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun createUser(auth: User): Flow<Resource<Boolean>>

    suspend fun loginUser(auth: User): Flow<Resource<Boolean>>

    suspend fun logoutUser():Flow<Resource<Boolean>>

     fun isUserLoggedIn():Boolean

    fun getFirebaseAuthState():Flow<Boolean>

     fun addUserIntoDb(user:User):Boolean

}