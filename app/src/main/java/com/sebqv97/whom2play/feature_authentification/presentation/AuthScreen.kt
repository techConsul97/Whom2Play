package com.sebqv97.whom2play.feature_authentification.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthScreen(modifier: Modifier = Modifier, viewModel: AuthViewModel = viewModel()) {
    viewModel.registerUser("sebastian.leonti@gmail.com","Afdsf123s")
    val authState by viewModel.authState
}