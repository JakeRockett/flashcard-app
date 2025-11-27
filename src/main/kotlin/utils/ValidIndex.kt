package ie.setu.utils

fun isValidListIndex(indexToCheck: Int, list: List<Any>): Boolean {
    for (i in list.indices) {
        if (i == indexToCheck) {
            return true
        }
    }
    return false
}
