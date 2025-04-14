package io.github.yamado.usermanagementservice.infrastructure.persistence.repository

import io.github.yamado.usermanagementservice.domain.model.RoleType
import io.github.yamado.usermanagementservice.infrastructure.persistence.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<RoleEntity, Long> {
    fun findByName(name: RoleType): RoleEntity?
}