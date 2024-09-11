package com.michigang.michibot.config

import discord4j.core.DiscordClientBuilder
import discord4j.core.GatewayDiscordClient
import discord4j.core.`object`.presence.ClientActivity
import discord4j.core.`object`.presence.ClientPresence
import discord4j.rest.RestClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "bot")
class BotConfig {

    lateinit var token: String

    @Bean
    fun gatewayDiscordClient(): GatewayDiscordClient? =
            DiscordClientBuilder.create(token)
                    .build()
                    .gateway()
                    .setInitialPresence { ClientPresence.online(ClientActivity.listening("tu corazón uwu")) }
                    .login()
                    .block()

    @Bean
    fun discordRestClient(client: GatewayDiscordClient): RestClient = client.restClient

}