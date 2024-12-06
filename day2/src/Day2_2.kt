import java.io.File
import java.nio.file.Paths

fun main() {

    val path = Paths.get("day2/src/input.txt")
    val inputStream = File(path.toAbsolutePath().toString()).inputStream()

    var numberOfSafeReports = 0
    inputStream.bufferedReader().forEachLine {
        val numbers = it.split(" ").map { number -> number.toInt() }

        // Edge Cases -.-
        //val numbers = listOf(55 ,56 ,53 ,52 ,51 ,50)
        //val numbers = listOf(5 ,1 ,2, 3, 4, 5)

        var counterInc = 0
        var counterDec = 0
        numbers.forEachIndexed { index, i ->
            if (index + 1 == numbers.size) {
                return@forEachIndexed
            }
            val diff = i - numbers[index + 1]
            if (diff < 0) {
                counterInc++
            } else {
                counterDec++
            }
        }

        val type = when (counterInc > counterDec) {
            true -> LevelType.INCREASING
            false -> LevelType.DECREASING
        }

        // test sort order
        when (type) {
            LevelType.INCREASING -> {
                val isValid = iterateAndDetermineSafe(numbers, -3, -1)

                if (isValid) {
                    numberOfSafeReports++
                } else {
                    val isValidNow = removeNumbersAndTryAgain(numbers, -3, -1)
                    if (isValidNow) {
                        numberOfSafeReports++
                    }
                }
            }

            LevelType.DECREASING -> {

                val isValid = iterateAndDetermineSafe(numbers, 1, 3)

                if (isValid) {
                    numberOfSafeReports++
                } else {
                    val isValidNow = removeNumbersAndTryAgain(numbers, 1, 3)
                    if (isValidNow) {
                        numberOfSafeReports++
                    }
                }
            }
        }
    }

    println("number of reports safe $numberOfSafeReports")
}

fun removeNumbersAndTryAgain(numbers: List<Int>, lowerBounds: Int, upperBounds: Int): Boolean {
    // test removing numbers
    val hasMore = true
    var index = 0
    while (hasMore) {
        val numbersStripped = numbers.filterIndexed { filterIndex, _ -> index != filterIndex }
        val nowValid = iterateAndDetermineSafe(numbersStripped, lowerBounds, upperBounds)
        if (nowValid) {
            return true
        }
        if (index + 1 == numbers.size) {
            return false
        }
        index++
    }

    return false
}

fun iterateAndDetermineSafe(numbers: List<Int>, lowerBounds: Int, upperBounds: Int): Boolean {
    var isValid = true
    // test each individual number
    numbers.forEachIndexed { index, number ->
        // end of line
        if (index + 1 == numbers.size) {
            return@forEachIndexed
        }
        val numberPlusOne = numbers[index + 1]
        val subtractedResult = number - numberPlusOne
        if (subtractedResult !in lowerBounds..upperBounds) {
            isValid = false
            return@forEachIndexed
        }
    }

    return isValid
}
