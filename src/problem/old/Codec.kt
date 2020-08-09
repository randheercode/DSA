package problem.old


/**
 * Created by randheercode
 * Date: 3/6/20
 * Time: 7:45 pm
 */
class Codec {
    // Encodes a list of strings to a single string.
    fun encode(strs: List<String>): String {
        if (strs.isEmpty()) return 300.toChar().toString()
        return strs.joinToString(299.toChar().toString())
    }

    // Decodes a single string to a list of strings.
    fun decode(s: String): List<String> {
        if (s == 300.toChar().toString()) return listOf()
        return s.split(299.toChar().toString())
    }
}

class Codec1 {
    // Encodes string length to bytes string
    fun intToString(s: String): String {
        val x = s.length
        val bytes = CharArray(4)
        for (i in 3 downTo -1 + 1) {
            bytes[3 - i] = (x shr i * 8 and 0xff).toChar()
        }
        return String(bytes)
    }

    // Encodes a list of strings to a single string.
    fun encode(strs: List<String>): String {
        val sb = StringBuilder()
        for (s in strs) {
            sb.append(intToString(s))
            sb.append(s)
        }
        return sb.toString()
    }

    // Decodes bytes string to integer
    fun stringToInt(bytesStr: String): Int {
        var result = 0
        for (b in bytesStr.toCharArray()) result = (result shl 8) + b.toInt()
        return result
    }

    // Decodes a single string to a list of strings.
    fun decode(s: String): List<String?> {
        var i = 0
        val n = s.length
        val output: MutableList<String> = mutableListOf()
        while (i < n) {
            val length = stringToInt(s.substring(i, i + 4))
            i += 4
            output.add(s.substring(i, i + length))
            i += length
        }
        return output
    }

}

fun main() {
    val input = listOf("Abcde", "B", "C")
    println("Input $input")
    val encoded = Codec1().encode(input)
    println("Encoded $encoded")
    val decoded = Codec1().decode(encoded)
    println("Decoded: $decoded")
}