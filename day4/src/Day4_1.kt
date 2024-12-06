import java.io.File
import java.nio.file.Paths

fun main() {
    val path = Paths.get("day4/src/input.txt")
    val content = File(path.toAbsolutePath().toString()).readText()

    val arrayContent = content.lines().map {
        it.map { line -> line.toString() }.toTypedArray()
    }

    val xmas = "XMAS"
    val samx = "SAMX"

    var numberOfResults = 0

    numberOfResults += searchHorizontal(arrayContent, xmas, samx)
    numberOfResults += searchVertical(arrayContent, xmas, samx)
    numberOfResults += searchDiagonal(arrayContent, xmas, samx)
    numberOfResults += searchReverseDiagonal(arrayContent, xmas, samx)


    print("number of results = $numberOfResults")

    // 2534
}

private fun searchHorizontal(arrayContent: List<Array<String>>, xmas: String, samx: String): Int {
    val regular = listOf(0, 1, 2, 3)
    var numberOfResults = 0

    for (element in arrayContent) {
        // regular
        for (j in 0..arrayContent.first().size - 4) {
            if (element[j] == "S" || element[j] == "X") {

                var mutableString = ""
                regular.forEach {
                    mutableString += element[it + j]
                }

                if (mutableString == xmas || mutableString == samx) {
                    numberOfResults++
                }
            }
        }
    }

    return numberOfResults
}

private fun searchVertical(arrayContent: List<Array<String>>, xmas: String, samx: String): Int {
    var numberOfResults = 0

    val vertical: List<Pair<Int, Int>> = listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(3, 0))

    val arrayDimen = arrayContent.first().size

    // y
    for (i in 0..arrayDimen - 4) {
        // x
        for (j in 0..<arrayDimen) {
            if (arrayContent[i][j] == "S" || arrayContent[i][j] == "X") {
                var mutableString = ""
                vertical.forEach {
                    mutableString += arrayContent[i + it.first][it.second + j]
                }

                if (mutableString == xmas || mutableString == samx) {
                    numberOfResults++
                }
            }
        }
    }

    return numberOfResults
}

private fun searchDiagonal(arrayContent: List<Array<String>>, xmas: String, samx: String): Int {
    var numberOfResults = 0

    val vertical: List<Pair<Int, Int>> = listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2), Pair(3, 3))

    val arrayDimen = arrayContent.first().size

    // y
    for (y in 0..arrayDimen - 4) {
        // x
        for (x in 0..arrayDimen - 4) {
            if (arrayContent[y][x] == "S" || arrayContent[y][x] == "X") {
                var mutableString = ""
                vertical.forEach {
                    mutableString += arrayContent[y + it.first][it.second + x]
                }

                if (mutableString == xmas || mutableString == samx) {
                    numberOfResults++
                }
            }
        }
    }

    return numberOfResults
}

private fun searchReverseDiagonal(arrayContent: List<Array<String>>, xmas: String, samx: String): Int {
    var numberOfResults = 0

    val vertical: List<Pair<Int, Int>> = listOf(Pair(0, 0), Pair(1, -1), Pair(2, -2), Pair(3, -3))

    val arrayDimen = arrayContent.first().size

    for (y in 0..<arrayDimen - 3) {
        // regular
        for (x in 3..<arrayDimen) {
            if (arrayContent[y][x] == "S" || arrayContent[y][x] == "X") {
                var mutableString = ""
                vertical.forEach {
                    mutableString += arrayContent[y + it.first][it.second + x]
                }

                if (mutableString == xmas || mutableString == samx) {
                    numberOfResults++
                }

            }
        }
    }

    return numberOfResults
}

