package io.github.yamado.usermanagementservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserManagementApplication

fun main(args: Array<String>) {
	runApplication<UserManagementApplication>(*args)
}
