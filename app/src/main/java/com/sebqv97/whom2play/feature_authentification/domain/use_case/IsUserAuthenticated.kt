package com.sebqv97.whom2play.feature_authentification.domain.use_case

import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.isUserLoggedIn()
}