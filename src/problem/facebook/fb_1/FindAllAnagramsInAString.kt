package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 5:12 pm
 */
class FindAllAnagramsInAString {

    fun findAnagrams(s: String, p: String): List<Int> {
        val result = mutableListOf<Int>()

        if (s.length < p.length) return result

        val target = mutableMapOf<Char, Int>()
        for (c in p) target[c] = target.getOrDefault(c, 0) + 1

        val source = mutableMapOf<Char, Int>()
        for (i in p.indices) source[s[i]] = source.getOrDefault(s[i], 0) + 1

        if (anagram(target, source)) result.add(0)

        for (i in p.length until s.length) {

            val outChar = s[i - p.length]
            source[outChar] = source[outChar]!! - 1
            if (source[outChar] == 0) source.remove(outChar)

            source[s[i]] = source.getOrDefault(s[i], 0) + 1

            if (anagram(target, source)) {
                result.add(i - p.length + 1)
            }

        }

        return result
    }

    private fun anagram(target: MutableMap<Char, Int>, source: MutableMap<Char, Int>): Boolean {
        return if (target.size != source.size) false
        else target.all { it.value == source[it.key] }
    }
}