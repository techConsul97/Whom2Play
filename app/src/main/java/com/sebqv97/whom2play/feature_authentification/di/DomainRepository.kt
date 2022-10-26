package com.sebqv97.whom2play.feature_authentification.di

import com.google.firebase.auth.FirebaseAuth
import com.sebqv97.whom2play.feature_authentification.data.repository.AuthRepositoryImpl
import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import com.sebqv97.whom2play.feature_authentification.domain.use_case.LoginUserUseCase
import com.sebqv97.whom2play.feature_authentification.domain.use_case.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainRepository {

    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth):AuthRepository = AuthRepositoryImpl(firebaseAuth)

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository):LoginUserUseCase = LoginUserUseCase(authRepository)

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository) = RegisterUserUseCase(authRepository)

}