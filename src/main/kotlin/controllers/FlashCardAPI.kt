package ie.setu.controllers

import ie.setu.models.FlashCard
import ie.setu.persistence.Serializer

class FlashCardAPI (serializerType: Serializer) {

    private val flashcards = mutableListOf<FlashCard>()
    private var nextFlashCardId = 1
    private val serializer: Serializer = serializerType

    fun addFlashCard(flashCard: FlashCard): Boolean {
        flashCard.flashCardId = nextFlashCardId++
        return flashcards.add(flashCard)
    }

    fun listAllFlashCards(): String =
        if (flashcards.isEmpty()) "You don't have any flashcards stored"
        else formatListString(flashcards)


    private fun formatListString(cards: List<FlashCard>): String =
        cards.joinToString("\n") { card ->
            "${flashcards.indexOf(card)}: $card"
        }



}