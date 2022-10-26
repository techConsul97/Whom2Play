package com.sebqv97.whom2play.feature_authentification.presentation

data class AuthState(
    val isLoading: Boolean? = null,
    val isSuccessful:Boolean? = null,
    val error:String? = null
)
