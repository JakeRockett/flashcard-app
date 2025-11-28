package ie.setu

import ie.setu.persistence.Serializer
import ie.setu.models.Subject
import ie.setu.controllers.SubjectAPI
import ie.setu.controllers.FlashCardAPI
import ie.setu.models.FlashCard
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextLine
import ie.setu.persistence.XMLSerializer
import java.io.File
import kotlin.system.exitProcess

private val flashCardAPI = FlashCardAPI(XMLSerializer(File("flashcards.xml")))
private val subjectAPI = SubjectAPI(XMLSerializer(File("subjects.xml")))

fun main() {
    runMenu()
}

fun flashCardMenu(): Int {
    print(
        """
        -----------------------------------------
        |           FLASHCARD APP               |
        -----------------------------------------
        | Flashcard Options                     |
        -----------------------------------------
        | 1) Add Flashcard                      |
        | 2) List All Flashcards                |
        | 3) Update Flashcard                   |
        | 4) Delete Flashcard                   |
        | 5) List Flashcards by Subject ID      |
        | 6) Find Flashcard by ID               |
        -----------------------------------------
        | Subject Options                       |
        -----------------------------------------
        | 7) Add Subject                        |
        | 8) Study Flashcard by Subject         |                                       |
        | 9) List All Subjects                  |
        | 10) Delete Subject                     |
        -----------------------------------------
        | Persistence                           |
        -----------------------------------------
        | 20) Save All                          |
        | 21) Load All                          |
        -----------------------------------------
        | 0) Exit                                |
        -----------------------------------------
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
            5 -> listFlashcardsBySubject()
            6 -> findFlashcardById()

            7 -> addSubject()
            8 -> studyFlashcardsBySubject()
            9 -> listSubjects()
            10 -> deleteSubject()

            20 -> saveAll()
            21 -> loadAll()

            0 -> exitApp()
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

fun addSubject() {
    println("---- ADD SUBJECT ----")

    val subjectTitle = readNextLine("Enter subject title: ")
    val category = readNextLine("Enter subject category: ")
    val grade = readNextLine("Enter subject grade: ")
    val cost = readNextInt("Enter subject cost: ").toDouble()
    val isIncludedInFinalAward = readNextLine("Included in final award? (yes/no): ").lowercase() == "yes"

    val newSubject = Subject(
        subjectId = 0,
        subjectTitle = subjectTitle,
        category = category,
        grade = grade,
        cost = cost,
        isIncludedInFinalAward = isIncludedInFinalAward
    )

    val added = subjectAPI.addSubject(newSubject)

    println(if (added) "Subject added successfully!" else "Failed to add subject.")
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

fun listSubjects() {
    println(subjectAPI.listAllSubjects())
}

fun deleteSubject() {
    listSubjects()
    if (subjectAPI.numberOfSubjects() == 0) return

    val index = readNextInt("Enter index to delete: ")
    val removed = subjectAPI.deleteSubject(index)

    println(if (removed != null) "Subject deleted." else "Delete failed.")
}

fun listFlashcardsBySubject() {
    val id = readNextInt("Enter Subject ID: ")
    println(flashCardAPI.listFlashcardsBySubject(id))
}

fun findFlashcardById() {
    val id = readNextInt("Enter Flashcard ID: ")
    println(flashCardAPI.findFlashcardById(id))
}

fun studyFlashcardsBySubject() {
    listSubjects()
    if (subjectAPI.numberOfSubjects() == 0) {
        println("No subjects available to study.")
        return
    }

    val subjectId = readNextInt("Enter the Subject ID you want to study: ")

    val flashcardsToStudy = flashCardAPI.getFlashcardsBySubject(subjectId)
    if (flashcardsToStudy.isEmpty()) {
        println("No flashcards for this subject.")
        return
    }

    println("---- STUDYING FLASHCARDS ----")
    for (flashcard in flashcardsToStudy) {
        println("Question: ${flashcard.question}")
        readNextLine("Press Enter to see the answer...")
        println("Answer: ${flashcard.answer}")

        val correct = readNextLine("Did you answer correctly? (yes/no): ").lowercase() == "yes"
        if (correct) flashcard.numTimesRight++
        flashcard.numTimesStudied++
        println("Times studied: ${flashcard.numTimesStudied}, Times correct: ${flashcard.numTimesRight}")
        println("--------------------------------")
    }

    println("You have finished studying subject ID $subjectId!")
}


fun saveAll() {
    val fcSaved = flashCardAPI.save()
    val subjectSaved = subjectAPI.save()

    if (fcSaved && subjectSaved) println("All data saved successfully!")
    else if (!fcSaved && !subjectSaved) println("Failed to save Flashcards and Subjects.")
    else if (!fcSaved) println("Failed to save Flashcards.")
    else println("Failed to save Subjects.")
}

fun loadAll() {
    val fcLoaded = flashCardAPI.load()
    val subjectLoaded = subjectAPI.load()

    if (fcLoaded && subjectLoaded) println("All data loaded successfully!")
    else if (!fcLoaded && !subjectLoaded) println("Failed to load Flashcards and Subjects.")
    else if (!fcLoaded) println("Failed to load Flashcards.")
    else println("Failed to load Subjects.")
}


fun exitApp() {
    println("Thank you for using the Flashcard App")
    kotlin.system.exitProcess(0)
}







