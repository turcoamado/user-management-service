package io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth

data class AuthenticationRequest(
    val email: String,
    val password: String
)
