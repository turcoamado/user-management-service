package io.github.yamado.usermanagementservice.infrastructure.adapter.dto

data class CreateUserRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String
)
