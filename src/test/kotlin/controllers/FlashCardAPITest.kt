package ie.setu.controllers

import ie.setu.models.FlashCard
import ie.setu.persistence.XMLSerializer
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class FlashCardAPITest {

    private val testFile = File("test-flashcards.xml")
    private var flashCardAPI: FlashCardAPI = FlashCardAPI(XMLSerializer(testFile))
    private val card1 = FlashCard(0, 1, "Algebra", "Easy", 0, "Math", true, 0, "2+2=?", "4")
    private val card2 = FlashCard(0, 2, "Physics", "Medium", 0, "Science", false, 0, "Force formula?", "F=ma")
    private val card3 = FlashCard(0, 1, "Geometry", "Hard", 0, "Math", true, 0, "Area of circle?", "πr²")

    @BeforeEach
    fun setup() {
        if (testFile.exists()) testFile.delete()
        flashCardAPI = FlashCardAPI(XMLSerializer(testFile))
        flashCardAPI.addFlashCard(card1)
        flashCardAPI.addFlashCard(card2)
        flashCardAPI.addFlashCard(card3)
    }

    @AfterEach
    fun tearDown() {
        if (testFile.exists()) testFile.delete()
    }

    @Nested
    inner class AddFlashCards {

        @Test
        fun `adding a flashcard increases number of flashcards`() {
            val card = FlashCard(0, 3, "Chemistry", "Medium", 0, "Science", false, 0, "Water formula?", "H2O")
            assertEquals(3, flashCardAPI.numberOfFlashCards())
            assertTrue(flashCardAPI.addFlashCard(card))
            assertEquals(4, flashCardAPI.numberOfFlashCards())
            assertEquals(card, flashCardAPI.findFlashCard(3))
        }
    }

}
