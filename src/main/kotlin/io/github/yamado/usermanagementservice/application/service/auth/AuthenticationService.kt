package io.github.yamado.usermanagementservice.application.service.auth

import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth.AuthenticationRequest
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.auth.AuthenticationResponse

interface AuthenticationService {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse
}