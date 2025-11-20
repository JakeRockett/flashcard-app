package ie.setu

import java.lang.System.exit

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
    do {
        val num = flashCardMenu()
        when (num) {
            1 -> addFlashCard()
            2 -> viewFlashCard()
            3 -> studyFlashCard()
            4 -> editFlashCard()
            0 -> exitApp()
            else -> println("The number you chose was invalid. Try again: ")
        }

    } while (true)
}

fun addFlashCard() {

}

fun viewFlashCard() {

}

fun studyFlashCard() {

}

fun editFlashCard() {

}

fun exitApp() {
    println("Goodbye for now.")
    exit(0)
}