package io.github.yamado.usermanagementservice.infrastructure.security

import io.github.yamado.usermanagementservice.infrastructure.persistence.entity.RoleEntity
import io.github.yamado.usermanagementservice.infrastructure.persistence.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

typealias ApplicationUser = io.github.yamado.usermanagementservice.infrastructure.persistence.entity.UserEntity

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found!")


    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .authorities(this.roles.toAuthorities())
            .build()

    private fun Set<RoleEntity>?.toAuthorities(): List<GrantedAuthority> =
        this?.map { SimpleGrantedAuthority("ROLE_${it.name}") } ?: emptyList()
}