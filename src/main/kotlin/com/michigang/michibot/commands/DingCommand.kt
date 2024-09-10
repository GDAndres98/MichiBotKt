package com.michigang.michibot.commands

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DingCommand() : SlashCommand {

    override val name: String = "ding"
    override fun handle(event: ChatInputInteractionEvent): Mono<Void> =
            event.reply()
                    .withContent("Dong!")


}