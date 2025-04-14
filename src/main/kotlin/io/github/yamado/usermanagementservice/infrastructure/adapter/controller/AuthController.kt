package io.github.yamado.usermanagementservice.infrastructure.adapter.controller

import io.github.yamado.usermanagementservice.application.service.auth.AuthenticationService
import io.github.yamado.usermanagementservice.application.service.auth.TokenService
import io.github.yamado.usermanagementservice.application.service.config.JwtProperties
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth.AuthenticationRequest
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth.AuthenticationResponse
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth.RefreshRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse {
        return authenticationService.authentication(authRequest)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequest): ResponseEntity<AuthenticationResponse> {
        val refreshToken = request.refreshToken
        val userDetails = tokenService.validateRefreshToken(refreshToken)

        val newAccessToken = tokenService.generate(
            userDetails = userDetails,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )

        return ResponseEntity.ok(AuthenticationResponse(accessToken = newAccessToken, refreshToken = refreshToken))
    }

}