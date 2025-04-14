package io.github.yamado.usermanagementservice.infrastructure.adapter.dto

import io.github.yamado.usermanagementservice.infrastructure.persistence.entity.RoleEntity

data class UserResponse(
    val id: Long,
    val name: String,
    val lastName: String,
    val email: String,
    var roles: Set<RoleEntity>
)
