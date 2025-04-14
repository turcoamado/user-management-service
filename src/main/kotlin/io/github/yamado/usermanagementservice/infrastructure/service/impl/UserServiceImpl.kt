package io.github.yamado.usermanagementservice.infrastructure.service.impl

import io.github.yamado.usermanagementservice.application.service.UserService
import io.github.yamado.usermanagementservice.domain.model.RoleType
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.CreateUserRequest
import io.github.yamado.usermanagementservice.infrastructure.adapter.dto.UserResponse
import io.github.yamado.usermanagementservice.infrastructure.persistence.mapper.UserMapper
import io.github.yamado.usermanagementservice.infrastructure.persistence.repository.RoleRepository
import io.github.yamado.usermanagementservice.infrastructure.persistence.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val roleRepository: RoleRepository,
    private val encoder: PasswordEncoder
) : UserService {

    override fun createUser(request: CreateUserRequest): UserResponse {
        if (userRepository.findByEmail(request.email) != null) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered.") //FIXME error handling

        val userEntity = userMapper.toEntity(userMapper.toDomain(request)) // Convert to DTO -> Domain -> Entity
        val defaultRole = roleRepository.findByName(RoleType.USER_READ) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found") //FIXME error handling
        userEntity.password = encoder.encode(userEntity.password)
        userEntity.creationDate = LocalDateTime.now()
        userEntity.roles = setOf(defaultRole)

        val savedUser = userRepository.save(userEntity)
        return userMapper.toResponse(userMapper.toDomain(savedUser)) // Entity -> Domain -> Response
    }

    override fun getUserById(id: Long): UserResponse? {
        val userEntity = userRepository.findById(id).orElse(null)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") //FIXME error handling
        val user = userMapper.toDomain(userEntity)
        return userMapper.toResponse(user)
    }

    override fun getUserByEmail(email: String): UserResponse? {
        val userEntity = userRepository.findByEmail(email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") //FIXME error handling
        return userMapper.toResponse(userMapper.toDomain(userEntity))
    }
}