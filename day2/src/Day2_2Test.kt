fun main() {

    val listOfListsDown = listOf(
        listOf(14, 11, 8, 9, 8, 6),
    )

    listOfListsDown.forEach { list ->
        val result = removeNumbersAndTryAgain(list, 1, 3)
        if (!result) {
            println("ohoh")
        }
    }

    val listOfListsUp = listOf(
        listOf(48, 46, 47, 49, 51, 54, 56),
        listOf(1, 1, 2, 3, 4, 5),
        listOf(1, 2, 3, 4, 5, 5),
        listOf(1, 2, 3, 4, 5, 5),

        )

    listOfListsUp.forEach { list ->
        val result = removeNumbersAndTryAgain(list, -3, -1)
        if (!result) {
            println("ohoh")
        }
    }

}