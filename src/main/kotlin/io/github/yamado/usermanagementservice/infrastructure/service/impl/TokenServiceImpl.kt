package io.github.yamado.usermanagementservice.infrastructure.service.impl

import io.github.yamado.usermanagementservice.application.service.auth.TokenService
import io.github.yamado.usermanagementservice.application.service.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenServiceImpl(
    private val jwtProperties: JwtProperties,
    private val userDetailsService: UserDetailsService
): TokenService {

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    override fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(emptyMap())
            .and()
            .signWith(secretKey)
            .compact()

    override fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    override fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    override fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)

        return userDetails.username == email && !isExpired(token)
    }

    private fun getAllClaims(token:String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }

    override fun validateRefreshToken(token: String): UserDetails {
        val username = extractEmail(token)
        val userDetails = userDetailsService.loadUserByUsername(username)

        if (!isValid(token, userDetails)) {
            throw RuntimeException("Invalid refresh token")
        }

        return userDetails
    }
}