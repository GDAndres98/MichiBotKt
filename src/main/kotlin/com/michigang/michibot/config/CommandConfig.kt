package com.michigang.michibot.config

import discord4j.common.JacksonResources
import discord4j.discordjson.json.ApplicationCommandRequest
import discord4j.rest.RestClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Configuration
import org.slf4j.LoggerFactory
import org.springframework.core.io.support.PathMatchingResourcePatternResolver


@Configuration
class CommandConfig : ApplicationRunner {

    private val logger = LoggerFactory.getLogger(CommandConfig::class.java)

    @Autowired
    private lateinit var client: RestClient


    override fun run(args: ApplicationArguments?) {
        val d4jMapper = JacksonResources.create()

        val matcher = PathMatchingResourcePatternResolver()
        val applicationId = client.applicationId.block() ?: -1L

        val commands = matcher.getResources("commands/*.json")
                .map { resource ->
                    d4jMapper.objectMapper.readValue(resource.inputStream, ApplicationCommandRequest::class.java)
                }

        client.applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
                .doOnNext { logger.info("Successfully registered Global Commands") }
                .doOnError { e -> logger.error("Failed to register global commands", e) }
                .subscribe()

    }
}
