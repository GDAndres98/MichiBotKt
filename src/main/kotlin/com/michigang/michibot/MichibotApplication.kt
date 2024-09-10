package com.michigang.michibot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MichibotApplication

fun main(args: Array<String>) {
	runApplication<MichibotApplication>(*args)
}
