package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 10/11/20
 * Time: 10:01 am
 */
class GroupShiftedStrings {

    private fun generateKey(str: String): String {
        val key = StringBuilder()
        key.append("L#").append(str.length)
        if (str.length < 2) return key.toString()
        for (i in 1 until str.length) {
            var diff = str[i] - str[i - 1]
            if (diff < 0) diff += 26
            key.append(diff)
        }
        return key.toString()
    }

    fun groupStrings(strings: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()
        for (str in strings) {
            val key = generateKey(str)
            map[key] = map.getOrDefault(key, mutableListOf()).apply { add(str) }
        }
        return map.values.toList()
    }
}