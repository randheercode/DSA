/**
 * Created by randheercode
 * Date: 21/5/20
 * Time: 4:42 pm
 */
fun printArray(image: Array<IntArray>, prefix: String = "") {
    println(prefix)
    println(image.joinToString(separator = "\n") { it.joinToString(separator = ",\t") })
    println()
}

fun printCharArray(image: Array<CharArray>, prefix: String = "") {
    println(prefix)
    println(image.joinToString(separator = "\n") { it.joinToString(separator = ",\t") })
    println()
}

fun generateIntArray(data: String): Array<IntArray> {
    return data.split("],[").map {
        it.replace("[", "").replace("]", "")
                .split(",").map { it.toInt() }.toIntArray()
    }.toTypedArray()
}

fun generateCharArray(data: String): Array<CharArray> {
    return data.split("],[").map {
        it.replace("[", "").replace("]", "")
                .split(",").map { it[0] }.toCharArray()
    }.toTypedArray()
}