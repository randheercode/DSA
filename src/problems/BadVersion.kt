package problems

/**
 * Created by randheer.kumar
 * Date: 1/5/20
 * Time: 11:30 pm
 */
class BadVersion(private val firstBadVersion: Int) {

    private fun isBadVersion(version: Int) = version >= firstBadVersion

    fun firstBadVersion(n: Int): Int {
        var start = 1
        var end = n
        while (start < end) {
            val mid = start.plus(end.minus(start).div(2))
            if (!isBadVersion(mid)) start = mid.plus(1) else end = mid
        }
        return start
    }

}

fun main() {
    //println(BadVersion(11).firstBadVersion(23))
    println(BadVersion(12).firstBadVersion(222))
}