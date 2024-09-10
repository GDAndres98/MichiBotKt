package com.michigang.michibot.listener

import com.michigang.michibot.commands.SlashCommand
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class SlashCommandListener(
        private val slashCommands: Collection<SlashCommand>,
        client: GatewayDiscordClient) {

    init {
        client.on(ChatInputInteractionEvent::class.java) { handle(it) }.subscribe()
    }

    fun handle(event: ChatInputInteractionEvent): Mono<Void> =
            Flux.fromIterable(slashCommands)
                    .filter { it.name == event.commandName }
                    .next()
                    .flatMap { it.handle(event) }

}