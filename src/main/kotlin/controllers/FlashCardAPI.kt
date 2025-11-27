package ie.setu.controllers

import ie.setu.models.FlashCard
import ie.setu.persistence.Serializer
import ie.setu.utils.isValidListIndex

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

    fun updatedFlashCard(index: Int, updatedCard: FlashCard): Boolean {
        val exists = findFlashCard(index)
        if (exists != null) {
            exists.subjectId = updatedCard.subjectId
            exists.subArea = updatedCard.subArea
            exists.difficultyLevel = updatedCard.difficultyLevel
            exists.subjectArea = updatedCard.subjectArea
            exists.isExaminable = updatedCard.isExaminable
            exists.question = updatedCard.question
            exists.answer = updatedCard.answer
            return true

        }
        return false
    }

    fun deleteFlashCard(index: Int): FlashCard? {
        return if (isValidListIndex(index, flashcards)) { // pass the list, not size
            flashcards.removeAt(index)
        } else {
            null
        }
    }


    fun findFlashCard(index: Int): FlashCard? =
        if (isValidListIndex(index, flashcards)) flashcards[index] else null

    fun numberOfFlashCards() = flashcards.size




    private fun formatListString(cards: List<FlashCard>): String =
        cards.joinToString("\n") { card ->
            "${flashcards.indexOf(card)}: $card"
        }



}