package com.michigang.michibot.commands

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Mono

@Component
class OwnCommand : SlashCommand {

    private val CAT_API_URL = "https://api.thecatapi.com/v1/images/search"

    private val logger = LoggerFactory.getLogger(DingCommand::class.java)

    override val name: String = "own"
    override fun handle(event: ChatInputInteractionEvent): Mono<Void> =
            with(event) {
                logger.info("Sending a Cat")
                reply()
                        .withContent(
                                RestTemplate().getForEntity(CAT_API_URL, Array<CatAPIResponse>::class.java)
                                        .body
                                        ?.get(0)
                                        ?.url
                                        ?: "😿"
                        )

            }

    data class CatAPIResponse(val id: String, val url: String)

}