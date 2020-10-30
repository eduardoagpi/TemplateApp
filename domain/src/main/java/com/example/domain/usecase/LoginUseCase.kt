package com.example.domain.usecase

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend fun signIn(user: String, password: String) = accountRepository.signIn(user, password)
}
