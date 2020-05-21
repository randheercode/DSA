/**
 * Created by randheercode
 * Date: 21/5/20
 * Time: 4:42 pm
 */
fun printArray(image: Array<IntArray>, prefix: String = "") {
    println(prefix + image.joinToString(separator = ",", prefix = "[", postfix = "]") { it.joinToString(separator = ",", prefix = "[", postfix = "]") })
}

fun generateIntArray(data: String): Array<IntArray> {
    return data.split("],[").map {
        it.replace("[", "").replace("]", "")
                .split(",").map { it.toInt() }.toIntArray()
    }.toTypedArray()
}