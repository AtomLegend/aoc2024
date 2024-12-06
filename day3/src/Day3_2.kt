import java.io.File
import java.math.BigDecimal
import java.nio.file.Paths

fun main() {
    val path = Paths.get("day3/src/input.txt")
    val content = File(path.toAbsolutePath().toString()).readText()

    val regex = "mul\\(\\d+,\\d+\\)"

    val doRange = Regex("do\\(\\)").findAll(content).map { it.range }.toList()
    val dontRange = Regex("don't\\(\\)").findAll(content).map { it.range }.toList()
    val results = Regex(regex).findAll(content)

    var isEnabled = true
    var index = 0
    var total = BigDecimal("0")
    while (index < content.length - 1) {
        if (isEnabled) {
            val nextDont = dontRange.firstOrNull { it.first > index }
            val safeZone = if (nextDont == null) {
                println("reached the end")
                index..<content.length
            } else {
                index..nextDont.last
            }
            val filteredResults = results.filter { it.range.first in safeZone }.map { it.value }.toList()
            total = total.add(addResults(filteredResults))
            isEnabled = false
            index = safeZone.last
        } else {
            val nextDo = doRange.first { it.first > index }
            index = nextDo.first
            isEnabled = true
        }
    }

    print("result is $total")

}

private fun addResults(results: List<String>): BigDecimal {
    var total = BigDecimal("0")
    results.forEach {
        val numbers = it
            .replace("mul(", "")
            .replace(")", "")
            .split(",")

        val left = BigDecimal(numbers.first())
        val right = BigDecimal(numbers[1])
        total = total.add(left.times(right))
    }
    return total
}
