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

    @Nested
    inner class ListFlashCards {

        @Test
        fun `list all flashcards returns cards`() {
            val allCards = flashCardAPI.listAllFlashCards().lowercase()
            assertTrue(allCards.contains("algebra"))
            assertTrue(allCards.contains("physics"))
            assertTrue(allCards.contains("geometry"))
        }

        @Test
        fun `listAllFlashCards returns message when empty`() {
            val emptyAPI = FlashCardAPI(XMLSerializer(File("empty-flashcards.xml")))
            val result = emptyAPI.listAllFlashCards().lowercase()
            assertTrue(result.contains("don't have any flashcards"))
        }
    }

    @Nested
    inner class UpdateFlashCards {

        @Test
        fun `updating existing flashcard changes data`() {
            val updatedCard = FlashCard(0, 1, "Algebra", "Hard", 0, "Math", false, 0, "2+3=?", "5")
            assertTrue(flashCardAPI.updatedFlashCard(0, updatedCard))
            val loaded = flashCardAPI.findFlashCard(0)
            assertEquals("Hard", loaded!!.difficultyLevel)
            assertEquals("5", loaded.answer)
        }

        @Test
        fun `updating non-existing flashcard returns false`() {
            val updatedCard = FlashCard(0, 1, "Algebra", "Hard", 0, "Math", false, 0, "2+3=?", "5")
            assertFalse(flashCardAPI.updatedFlashCard(10, updatedCard))
            assertFalse(flashCardAPI.updatedFlashCard(-1, updatedCard))
        }
    }

    @Nested
    inner class DeleteFlashCards {

        @Test
        fun `deleting existing flashcard returns deleted object`() {
            val deleted = flashCardAPI.deleteFlashCard(1)
            assertEquals(card2, deleted)
            assertEquals(2, flashCardAPI.numberOfFlashCards())
        }

        @Test
        fun `deleting non-existing flashcard returns null`() {
            assertNull(flashCardAPI.deleteFlashCard(-1))
            assertNull(flashCardAPI.deleteFlashCard(5))
        }
    }
}
