package ie.setu.controllers

import ie.setu.models.FlashCard

class FlashCardAPI {
    private val flashcards = mutableListOf<FlashCard>()

    fun addFlashCard() {
        println("Enter Subject ID: ")
        val subjectId = readlnOrNull()?.toIntOrNull()
        if (subjectId == null) {
            println("The Subject ID is invalid.")
            return
        }


    }

    fun viewAllFlashCards() {
        if (flashcards.isEmpty()) {
            println("You don't have any flashcards.")
            return
        }
        for (flashcard in flashcards) {
            println("ID: ${flashcard.flashCardId} Subject ID: " +
                    "${flashcard.subjectId} Question: ${flashcard.question}" +
                    "Answer: ${flashcard.answer}")
        }
    }
}