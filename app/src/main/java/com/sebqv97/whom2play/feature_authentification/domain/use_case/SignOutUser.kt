package com.sebqv97.whom2play.feature_authentification.domain.use_case

import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUser @Inject constructor(
    private val authRepository: AuthRepository
){
   suspend  operator fun invoke() = authRepository.logoutUser()
}