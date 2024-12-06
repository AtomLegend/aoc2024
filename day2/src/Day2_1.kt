import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("day2/src/input.txt")
    val inputStream = File(path.toAbsolutePath().toString()).inputStream()

    var numberOfSafeReports = 0
    inputStream.bufferedReader().forEachLine {
        val numbers = it.split(" ").map { number -> number.toInt() }

        // determine increase or decrease
        val type = when (numbers.first() - numbers[1] > 0) {
            true -> LevelType.DECREASING
            false -> LevelType.INCREASING
        }

        // test sort order
        when (type) {
            LevelType.INCREASING -> {
                // test order
                val numbersSorted = numbers.sorted()
                if (numbersSorted != numbers) {
                    return@forEachLine
                }

                // test each individual number
                numbers.forEachIndexed { index, number ->
                    // end of line
                    if (index + 1 == numbers.size) {
                        return@forEachIndexed
                    }
                    val numberPlusOne = numbers[index + 1]
                    val subtractedResult = number - numberPlusOne
                    if (subtractedResult !in -3..-1) {
                        return@forEachLine
                    }
                }

                numberOfSafeReports++
            }

            LevelType.DECREASING -> {
                // test order
                val numbersSorted = numbers.sortedDescending()
                if (numbersSorted != numbers) {
                    return@forEachLine
                }

                // test each individual number
                numbers.forEachIndexed { index, number ->
                    // end of line
                    if (index + 1 == numbers.size) {
                        return@forEachIndexed
                    }
                    val numberPlusOne = numbers[index + 1]
                    val subtractedResult = number - numberPlusOne
                    if (subtractedResult !in 1..3) {
                        return@forEachLine
                    }
                }

                numberOfSafeReports++
            }
        }
    }

    println("number of reports safe $numberOfSafeReports")
}

enum class LevelType {
    INCREASING,
    DECREASING
}
