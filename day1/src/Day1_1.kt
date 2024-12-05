import java.io.File
import java.nio.file.Paths
import kotlin.math.absoluteValue

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

    var totalDifference = 0

    // Sum all
    leftNumbers.forEachIndexed { index, leftNumber ->
        val rightNumber = rightNumbers[index]
        val difference = leftNumber - rightNumber

        // could be negative
        totalDifference += difference.absoluteValue
    }

    println("Total difference between all numbers is $totalDifference")
}