package com.michigang.michibot.commands

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DingCommand() : SlashCommand {

    private val logger = LoggerFactory.getLogger(DingCommand::class.java)

    override val name: String = "ding"
    override fun handle(event: ChatInputInteractionEvent): Mono<Void> =
            with(event) {
                logger.info("Sending a Dong!")
                reply().withContent("Dong!")
            }

}