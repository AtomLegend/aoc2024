import java.io.File
import java.nio.file.Paths

fun main() {

    // Read file
    val path = Paths.get("").toAbsolutePath().toString()
    val inputStream = File("$path/day1/src/numbers.txt").inputStream()

    // Split and convert to integers
    val leftNumbers: MutableList<Int> = mutableListOf()
    val rightNumbers: MutableList<Int> = mutableListOf()
    inputStream.bufferedReader().forEachLine {
        val numbers = it.split("   ")

        leftNumbers.add(numbers.first().toInt())
        rightNumbers.add(numbers[1].toInt())
    }

    // Sort to make comparison easier
    leftNumbers.sort()
    rightNumbers.sort()

    var totalSimilarity = 0

    var indexRight: Int

    // Calc similarity
    leftNumbers.forEach { leftNumber ->

        val firstIndex = rightNumbers.indexOfFirst { it == leftNumber }
        if (firstIndex < 0) {
            return@forEach
        }
        indexRight = firstIndex

        var numberOfAppearance = 0

        while (rightNumbers[indexRight] == leftNumber) {
            numberOfAppearance++
            indexRight++
        }

        val similarity = leftNumber * numberOfAppearance

        totalSimilarity += similarity
    }

    println("Similarity score $totalSimilarity")
}