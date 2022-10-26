package com.sebqv97.whom2play.feature_authentification.domain.use_case

import com.sebqv97.whom2play.common.util.Resource
import com.sebqv97.whom2play.feature_authentification.data.model.User
import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import com.sebqv97.whom2play.feature_authentification.util.AuthDataValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val loginUserUseCase: LoginUserUseCase
) {
    fun registerUser(email: String?, password: String?): Flow<Resource<User>> = flow {
      //  if (AuthDataValidator.isEmailValid(email) && AuthDataValidator.isValidPassword(password)) {
            val user = User(email!!, password!!)
            authRepository.createUser(user).onEach {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading())
                    is Resource.Error -> emit(Resource.Error(it.message!!))
                    is Resource.Success -> loginUserUseCase.loginUser(email,password)
                }
            }
  //      } else emit(Resource.Error(message = "Check your credentials"))


    }




}