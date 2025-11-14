package ie.setu.models

data class FlashCard (
    val flashCardId: Int,
    val subjectId: Int,
    var subArea: String,
    var difficultyLevel: String,
    var numTimesStudied: Int,
    var subjectArea: String,
    val isExaminable: Boolean,
    var numTimesRight: Int,
    var question: String,
    var answer: String,
)