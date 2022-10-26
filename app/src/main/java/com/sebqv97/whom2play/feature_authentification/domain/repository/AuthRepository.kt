package com.sebqv97.whom2play.feature_authentification.domain.repository

import com.sebqv97.whom2play.common.util.Resource
import com.sebqv97.whom2play.feature_authentification.data.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun createUser(auth: User): Flow<Resource<String>>

    suspend fun loginUser(auth: User): Flow<Resource<String>>


}