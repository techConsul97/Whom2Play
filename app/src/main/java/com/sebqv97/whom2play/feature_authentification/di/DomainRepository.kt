package com.sebqv97.whom2play.feature_authentification.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sebqv97.whom2play.feature_authentification.data.repository.AuthRepositoryImpl
import com.sebqv97.whom2play.feature_authentification.domain.repository.AuthRepository
import com.sebqv97.whom2play.feature_authentification.domain.use_case.LoginUser
import com.sebqv97.whom2play.feature_authentification.domain.use_case.RegisterUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainRepository {

    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth,firestore: FirebaseFirestore):AuthRepository = AuthRepositoryImpl(firebaseAuth,firestore)

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository):LoginUser = LoginUser(authRepository)

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository) = RegisterUser(authRepository)

}