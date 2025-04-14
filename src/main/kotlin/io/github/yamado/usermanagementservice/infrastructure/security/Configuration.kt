package io.github.yamado.usermanagementservice.infrastructure.security

import io.github.yamado.usermanagementservice.application.service.config.JwtProperties
import io.github.yamado.usermanagementservice.infrastructure.persistence.repository.UserRepository
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Configuration {

    @Bean
    fun userDetailsService(userRepository: UserRepository, passwordEncoder: PasswordEncoder) : UserDetailsService =
        CustomUserDetailsService(userRepository, passwordEncoder)

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(userRepository: UserRepository, passwordEncoder: PasswordEncoder): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService(userRepository, passwordEncoder))
                it.setPasswordEncoder(encoder())
                it.setPasswordEncoder(passwordEncoder)
            }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}