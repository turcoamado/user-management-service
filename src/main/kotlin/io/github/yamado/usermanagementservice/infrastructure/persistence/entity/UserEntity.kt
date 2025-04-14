package io.github.yamado.usermanagementservice.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    var lastName: String,
    var email: String,
    var password: String,
    @CreationTimestamp
    @Column(updatable = false)
    var creationDate: LocalDateTime? = LocalDateTime.now(),
    var updateDate: LocalDateTime? = null,
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity::class)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: Set<RoleEntity>? = emptySet()
)
