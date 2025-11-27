package ie.setu

import ie.setu.controllers.FlashCardAPI
import ie.setu.models.FlashCard
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextLine
import ie.setu.persistence.XMLSerializer
import java.io.File
import kotlin.system.exitProcess

private val flashCardAPI = FlashCardAPI(XMLSerializer(File("flashcards.xml")))

fun main() {
    runMenu()
}

fun flashCardMenu(): Int {
    print(
        """
          ----------------------------------
          |        FLASHCARD APP           |
          ----------------------------------
          |   1) Add a Flashcard           |
          |   2) List All Flashcards       |
          |   3) Update a Flashcard        |
          |   4) Delete a Flashcard        |
          |   5) List Flashcards by Subject|
          |   6) Find Flashcard by ID      |
          ----------------------------------
          |   20) Save Flashcards          |
          |   21) Load Flashcards          |
          ----------------------------------
          |   0) Exit                      |
          ----------------------------------
        ==>> 
        """.trimIndent()
    )
    return readlnOrNull()?.toIntOrNull() ?: -1
}

fun runMenu() {
    do {
        when (val option = flashCardMenu()) {
            1 -> addFlashCard()
            2 -> listAllFlashcards()
            3 -> updatedFlashCard()
            4 -> deleteFlashCard()
            20 -> saveFlashcards()
            21 -> loadFlashcards()
            else -> println("Invalid option: $option")
        }
    } while (true)
}

fun addFlashCard() {
    println("---- ADD FLASHCARD ----")

    val subjectId = readNextInt("Enter Subject ID: ")
    val subArea = readNextLine("Enter Sub-Area: ")
    val difficultyLevel = readNextLine("Enter Difficulty Level: ")
    val subjectArea = readNextLine("Enter Subject Area: ")
    val isExaminable = readNextLine("Is this examinable? (yes/no): ").lowercase() == "yes"
    val question = readNextLine("Enter the question: ")
    val answer = readNextLine("Enter the answer: ")

    val newFlashCard = FlashCard(
        flashCardId = 0,
        subjectId = subjectId,
        subArea = subArea,
        difficultyLevel = difficultyLevel,
        numTimesStudied = 0,
        subjectArea = subjectArea,
        isExaminable = isExaminable,
        numTimesRight = 0,
        question = question,
        answer = answer
    )

    val added = flashCardAPI.addFlashCard(newFlashCard)

    if (added) {
        println("Your flashcard has been added successfully!")
    } else {
        println("Failed to add your flashcard.")
    }
}

fun listAllFlashcards() {
    println(flashCardAPI.listAllFlashCards())
}

fun updatedFlashCard() {
    listAllFlashcards()

    if (flashCardAPI.numberOfFlashCards() > 0) {
        val index = readNextInt("Enter the index you want to update: ")
        val subjectId = readNextInt("Enter the new subject ID: ")
        val subArea = readNextLine("Enter the new sub-area: ")
        val difficulty = readNextLine("Enter the new difficulty: ")
        val subjectArea = readNextLine("Enter the new subject area: ")
        val examinable = readNextLine("Is examinable (yes/no): ").lowercase() == "yes"
        val question = readNextLine("Enter the new question: ")
        val answer = readNextLine("Enter the new answer: ")

        val updatedCard = FlashCard(
            flashCardId = 0,
            subjectId = subjectId,
            subArea = subArea,
            difficultyLevel = difficulty,
            numTimesStudied = 0,
            subjectArea = subjectArea,
            isExaminable = examinable,
            numTimesRight = 0,
            question = question,
            answer = answer
        )

        val success = flashCardAPI.updatedFlashCard(index, updatedCard)

        if (success) {
            println("Flashcard updated successfully!")
        } else {
            println("Update failed. Invalid index.")
        }
    } else {
        println("No flashcards to update.")
    }
}

fun deleteFlashCard() {
    println("---- DELETE FLASHCARD ----")

    println(flashCardAPI.listAllFlashCards())

    if (flashCardAPI.numberOfFlashCards() > 0) {
        val index = readNextInt("Enter the index of the flashcard to delete: ")

        val deleted = flashCardAPI.deleteFlashCard(index)

        if (deleted != null) {
            println("Flashcard deleted successfully! ID: ${deleted.flashCardId}")
        } else {
            println("Delete failed. Invalid index.")
        }
    } else {
        println("No flashcards to delete.")
    }
}

fun saveFlashcards() {
    if (flashCardAPI.save()) {
        println("Flashcards saved successfully!")
    } else {
        println("Failed to save flashcards.")
    }
}

fun loadFlashcards() {
    if (flashCardAPI.load()) {
        println("Flashcards loaded successfully!")
    } else {
        println("Failed to load flashcards.")
    }
}






