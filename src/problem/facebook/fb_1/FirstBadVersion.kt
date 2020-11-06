package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 6/11/20
 * Time: 3:04 pm
 */
abstract class VersionControl {
    fun isBadVersion(version: Int): Boolean {
        return version > 12
    }

    abstract fun firstBadVersion(n: Int): Int
}

class FirstBadVersion : VersionControl() {
    override fun firstBadVersion(n: Int): Int {
        var start = 1
        var end = n

        while (start <= end) {
            val mid = start + (end - start) / 2
            if (isBadVersion(mid)) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        return start
    }
}