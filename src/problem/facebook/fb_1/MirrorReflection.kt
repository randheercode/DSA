package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:00 am
 */
class MirrorReflection {
    fun mirrorReflection(p: Int, q: Int): Int {
        var p = p
        var q = q
        val g = gcd(p, q)
        p /= g
        p %= 2
        q /= g
        q %= 2
        if (p == 1 && q == 1) return 1
        return if (p == 1) 0 else 2
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (a == 0) b else gcd(b % a, a)
    }
}