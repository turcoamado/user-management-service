package io.github.yamado.usermanagementservice.application.service.auth

import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface TokenService {
    fun generate(userDetails: UserDetails, expirationDate: Date): String
    fun extractEmail(token: String): String?
    fun isExpired(token: String): Boolean
    fun isValid(token: String, userDetails: UserDetails): Boolean
    fun validateRefreshToken(token: String): UserDetails
}