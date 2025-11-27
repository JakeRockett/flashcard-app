package ie.setu.models

data class FlashCard (
    var flashCardId: Int,
    var subjectId: Int,
    var subArea: String,
    var difficultyLevel: String,
    var numTimesStudied: Int,
    var subjectArea: String,
    var isExaminable: Boolean,
    var numTimesRight: Int,
    var question: String,
    var answer: String,
)