package io.github.yamado.usermanagementservice.infrastructure.persistence.entity

import io.github.yamado.usermanagementservice.domain.model.RoleType
import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class RoleEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    val name: RoleType
)

