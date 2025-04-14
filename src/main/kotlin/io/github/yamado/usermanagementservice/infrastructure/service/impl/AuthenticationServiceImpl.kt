package io.github.yamado.usermanagementservice.infrastructure.service.impl

import io.github.yamado.usermanagementservice.application.service.auth.AuthenticationService
import io.github.yamado.usermanagementservice.application.service.auth.TokenService
import io.github.yamado.usermanagementservice.application.service.config.JwtProperties
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth.AuthenticationRequest
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth.AuthenticationResponse
import io.github.yamado.usermanagementservice.infrastructure.security.CustomUserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
): AuthenticationService {

    override fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        try {
            val user = userDetailsService.loadUserByUsername(authRequest.email)
            val accessToken = tokenService.generate(
                userDetails = user,
                expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
            )

            val refreshToken = tokenService.generate(
                userDetails = user,
                expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
            )

            return AuthenticationResponse(
                accessToken = accessToken,
                refreshToken = refreshToken
            )

        } catch (e: Exception) {
            println("Authentication error: ${e.message}")
            throw e
        }
    }
}