package io.github.yamado.usermanagementservice.infrastructure.persistence.repository

import io.github.yamado.usermanagementservice.infrastructure.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun save(userEntity: UserEntity) : UserEntity
    fun findByEmail(email: String): UserEntity?
}