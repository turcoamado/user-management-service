package io.github.yamado.usermanagementservice.infrastructure.persistence.mapper

import io.github.yamado.usermanagementservice.domain.model.User
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.CreateUserRequest
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.UserResponse
import io.github.yamado.usermanagementservice.infrastructure.persistence.entity.UserEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toDomain(request: CreateUserRequest): User
    fun toEntity(user: User): UserEntity
    fun toDomain(userEntity: UserEntity): User
    fun toResponse(user: User): UserResponse
}