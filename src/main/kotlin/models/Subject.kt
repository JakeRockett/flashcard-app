package ie.setu.models

data class Subject (
    val subjectId: Int,
    var subjectTitle: String,
    var category: String,
    var grade: String,
    var cost: Double,
    var isIncludedInFinalAward: Boolean
)