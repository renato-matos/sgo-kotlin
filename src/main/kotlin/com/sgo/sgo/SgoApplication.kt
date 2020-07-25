package com.sgo.sgo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SgoApplication

fun main(args: Array<String>) {
	runApplication<SgoApplication>(*args)
}
