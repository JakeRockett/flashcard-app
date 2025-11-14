package ie.setu

fun mainMenu() : Int {
    println("")
    println("--------------------")
    println("FLASHCARD STUDY APP")
    println("--------------------")
    println("FLASHCARD MENU")
    println("   1) ")
    println("   2) ")
    println("   3) ")
    println("   4) ")
    println("   5) ")
    println("--------------------")
    println("   0) Exit ")
    println("--------------------")
    print("==>> ")
    return readlnOrNull()?.toIntOrNull() ?: -1
}