package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:42 am
 */
class MinimumAddMakeParenthesesValid {
    fun minAddToMakeValid(S: String): Int {
        var ans = 0
        var bal = 0
        for (element in S) {
            bal += if (element == '(') 1 else -1
            // It is guaranteed bal >= -1
            if (bal == -1) {
                ans++
                bal++
            }
        }
        return ans + bal
    }
}