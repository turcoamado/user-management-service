package io.github.yamado.usermanagementservice.domain.model

import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val roles: Set<Role>?,
    val creationDate: LocalDateTime? = null,
    val updateDate: LocalDateTime? = null
)
