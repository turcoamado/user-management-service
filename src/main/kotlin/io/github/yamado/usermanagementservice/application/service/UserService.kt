package io.github.yamado.usermanagementservice.application.service

import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.CreateUserRequest
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.UserResponse

interface UserService {
    fun createUser(request: CreateUserRequest): UserResponse
    fun getUserById(id: Long): UserResponse?
    fun getUserByEmail(email: String): UserResponse?
}