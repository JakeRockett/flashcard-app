package ie.setu

import ie.setu.controllers.FlashCardAPI
import ie.setu.controllers.SubjectAPI
import ie.setu.models.FlashCard
import ie.setu.models.Subject

private val flashCardAPI = FlashCardAPI()


fun main() {
    runMenu()
}

fun flashCardMenu(): Int {
    print("""
          ----------------------------------
          |        FLASHCARD APP           |
          ----------------------------------
          | FLASHCARD MENU                 |
          |   1) Add a Flashcard           |
          |   2) View All Flashcards       |
          |   3) Study a Flashcard         |
          |   4) Edit a Flashcard          |
          ----------------------------------
          |   0) Exit                      |
          ----------------------------------
          ==>> """)
    return readlnOrNull()?.toIntOrNull() ?: -1
}



fun runMenu() {

    val flashCardAPI = FlashCardAPI()
    val subjectAPI = SubjectAPI()
    do {
        val num = flashCardMenu()
        when (num) {
            1 -> flashCardAPI.addFlashCard()
            2 -> flashCardAPI.viewAllFlashCards()
            else -> println("The number you chose was invalid. Try again: ")
        }

    } while (true)
}

fun addFlashCard() {

}