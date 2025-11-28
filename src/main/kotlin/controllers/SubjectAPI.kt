package ie.setu.controllers
import ie.setu.models.Subject
import ie.setu.persistence.XMLSerializer
import ie.setu.persistence.Serializer
import ie.setu.utils.isValidListIndex

class SubjectAPI(private val serializer: Serializer) {

    private var subjects = ArrayList<Subject>()

    fun addSubject(subject: Subject): Boolean {
        return subjects.add(subject)
    }

    fun listAllSubjects(): String {
        return if (subjects.isEmpty()) {
            "No subjects stored."
        } else {
            subjects.joinToString(separator = "\n") { subject ->
                "${subjects.indexOf(subject)}: $subject"
            }
        }
    }

    fun deleteSubject(index: Int): Subject? {
        return if (isValidListIndex(index, subjects)) {
            subjects.removeAt(index)
        } else null
    }

    fun numberOfSubjects(): Int = subjects.size

    fun save(): Boolean {
        return try {
            serializer.write(subjects)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun load(): Boolean {
        return try {
            val loaded = serializer.read() as? MutableList<Subject>
            if (loaded != null) subjects.addAll(loaded)
            true
        } catch (ex: Exception) {
            false
        }
    }


}


