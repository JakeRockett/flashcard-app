package ie.setu.controllers

import ie.setu.models.FlashCard

class FlashCardAPI {
    private val flashcards = mutableListOf<FlashCard>()
    private var nextFlashCardId = 1

    fun addFlashCard() {
        println("ADDING A NEW FLASHCARD: ")

        println("Enter Flashcard ID: ")
        val flashCardId = readlnOrNull()?.toIntOrNull()
        if (flashCardId == null) {
            println("The Flashcard ID is invalid.")
            return
        }

        println("Enter Subject ID: ")
        val subjectId = readlnOrNull()?.toIntOrNull()
        if (subjectId == null) {
            println("The Subject ID is invalid. ")
            return
        }

        println("Enter Sub-Area: ")
        val subArea = readlnOrNull() ?: ""

        println("Enter difficulty level: ")
        val difficultyLevel = readlnOrNull() ?: ""

        val numTimesStudied = 0

        println("Enter Subject Area: ")
        val subjectArea = readlnOrNull() ?: ""

        println("Is this examinable? (yes/no): ")
        val isExaminable = when (readlnOrNull()?.lowercase()) {
            "yes", "y" -> true
            else -> false
        }

        val numTimesRight = 0

        println("Enter the question: ")
        val question = readlnOrNull() ?: ""

        println("Enter the answer: ")
        val answer = readlnOrNull() ?: ""

        val newFlashCard = FlashCard(
            flashCardId = nextFlashCardId++,
            subjectId = subjectId,
            subArea = subArea,
            difficultyLevel = difficultyLevel,
            numTimesStudied = numTimesStudied,
            subjectArea = subjectArea,
            isExaminable = isExaminable,
            numTimesRight = numTimesRight,
            question = question,
            answer = answer
        )

        flashcards.add(newFlashCard)
        println("NEW FLASHCARD ADDED: FLASHCARD ID: ${newFlashCard.flashCardId}")


    }

    fun viewAllFlashCards() {
        if (flashcards.isEmpty()) {
            println("You don't have any flashcards.")
            return
        }
        println("Flashcards List:")
        flashcards.forEach { card ->
            println(
                """
                ID: ${card.flashCardId}
                Subject ID: ${card.subjectId}
                Subject Area: ${card.subjectArea}
                Sub Area: ${card.subArea}
                Difficulty Level: ${card.difficultyLevel}
                Question: ${card.question}
                Answer: ${card.answer}
                Times Studied: ${card.numTimesStudied}
                Times Correct: ${card.numTimesRight}
                Examinable: ${card.isExaminable}
                """.trimIndent()
            )
            println("---------")
        }
    }
}