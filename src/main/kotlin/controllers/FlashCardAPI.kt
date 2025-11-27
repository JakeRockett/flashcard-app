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

}