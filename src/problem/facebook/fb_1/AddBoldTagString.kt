package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 12/11/20
 * Time: 11:53 am
 */
class AddBoldTagString {
    fun addBoldTag(s: String, dict: Array<String>): String? {
        val bold = BooleanArray(s.length)
        var j = 0
        var end = 0
        while (j < s.length) {
            for (word in dict) {
                if (s.startsWith(word, j)) {
                    end = maxOf(end, j + word.length)
                }
            }
            bold[j] = end > j
            j++
        }
        val result = StringBuilder()
        var i = 0
        while (i < s.length) {
            if (!bold[i]) {
                result.append(s[i])
                i++
                continue
            }
            var j = i
            while (j < s.length && bold[j]) j++
            result.append("<b>" + s.substring(i, j) + "</b>")
            i = j - 1
            i++
        }
        return result.toString()
    }
}