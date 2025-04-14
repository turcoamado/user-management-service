package io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth

data class AuthenticationResponse (
    val accessToken: String,
    val refreshToken: String
)