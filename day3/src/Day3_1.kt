import java.io.File
import java.math.BigDecimal
import java.nio.file.Paths

fun main() {
    val path = Paths.get("day3/src/input.txt")
    val content = File(path.toAbsolutePath().toString()).readText()

    val regex = "mul\\(\\d+,\\d+\\)"

    val results = Regex(regex).findAll(content).map { it.value }.toList()

    var total = BigDecimal(0)
    results.forEach {
        val numbers = it
            .replace("mul(", "")
            .replace(")", "")
            .split(",")

        val left = BigDecimal(numbers.first())
        val right = BigDecimal(numbers[1])
        total = total.add(left.times(right))
    }

    print("result is $total")

}