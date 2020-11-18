package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 8:05 am
 */
class ValidPalindrome3 {

    fun isValidPalindrome(s: String, k: Int): Boolean {
        val lenS = s.length
        val memo = Array(lenS){ Array<Int?>(lenS) { null }}

        return lenS - dfs(0, lenS - 1, s, memo) <= k
    }

    private fun dfs(lo: Int, hi: Int, str: String, memo: Array<Array<Int?>>): Int{
        if(lo > hi) return 0
        if(lo == hi) return 1

        memo[lo][hi]?.let{ return it }

        memo[lo][hi] = if(str[lo] == str[hi]){
            dfs(lo + 1, hi - 1, str, memo) + 2
        }else{
            maxOf(dfs(lo + 1, hi, str, memo), dfs(lo, hi - 1, str, memo))
        }

        return memo[lo][hi]!!
    }
}