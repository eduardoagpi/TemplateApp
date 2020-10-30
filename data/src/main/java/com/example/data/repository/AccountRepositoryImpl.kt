package com.example.data.repository

import com.example.domain.entity.SignUpData
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(): AccountRepository {

    override suspend fun signUp(signUpData: SignUpData) {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(user: String, password: String) {
        TODO("Not yet implemented")
    }
}
