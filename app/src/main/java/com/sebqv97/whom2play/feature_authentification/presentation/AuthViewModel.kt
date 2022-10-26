package com.sebqv97.whom2play.feature_authentification.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.whom2play.common.util.Resource
import com.sebqv97.whom2play.feature_authentification.domain.use_case.LoginUser
import com.sebqv97.whom2play.feature_authentification.domain.use_case.RegisterUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUser,
    private val loginUserUseCase: LoginUser,
):ViewModel(){


    private val _authState:MutableState<AuthState> = mutableStateOf(AuthState())
    val authState:State<AuthState> get() = _authState
    
    
    fun signUserIn(email:String?, password:String?){
        viewModelScope.launch { 
            loginUserUseCase(email, password).collect{
                when(it){
                    is Resource.Loading -> _authState.value = AuthState(isLoading = true)
                    is Resource.Error -> _authState.value = AuthState(error = it.message)
                    is Resource.Success -> _authState.value = AuthState(isSuccessful = true)
                }
            }
        }
    }

    fun registerUser(email: String?,password: String?){
        viewModelScope.launch {
            registerUserUseCase(email, password).collect{
                when(it){
                    is Resource.Loading -> _authState.value = AuthState(isLoading = true)
                    is Resource.Error -> _authState.value = AuthState(error = it.message)
                    is Resource.Success -> signUserIn(email, password)
                }
            }
        }
    }


}