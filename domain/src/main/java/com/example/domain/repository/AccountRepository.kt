package com.example.domain.repository

import com.example.domain.entity.SignUpData

interface AccountRepository {

    suspend fun signUp(signUpData: SignUpData)

    suspend fun signIn(user: String, password: String)
}
