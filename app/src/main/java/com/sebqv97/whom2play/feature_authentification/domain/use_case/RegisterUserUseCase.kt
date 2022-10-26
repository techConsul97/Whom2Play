package com.sebqv97.whom2play.feature_authentification.domain.use_case

import com.sebqv97.whom2play.common.util.Resource
import com.sebqv97.whom2play.feature_authentification.data.model.User
import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import com.sebqv97.whom2play.feature_authentification.util.AuthDataValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun registerUser(email: String?, password: String?): Flow<Resource<String>> = flow {
        if (AuthDataValidator.isEmailValid(email) && AuthDataValidator.isValidPassword(password)) {
            val user = User(email!!, password!!)

            authRepository.createUser(user).collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading())
                    is Resource.Error -> emit(Resource.Error(it.message!!))
                    is Resource.Success -> emit(Resource.Success(it.data!!))
                }

            }

       } else emit(Resource.Error(message = "Check your credentials"))


    }


}