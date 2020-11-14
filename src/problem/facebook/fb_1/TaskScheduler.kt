package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 14/11/20
 * Time: 11:43 am
 */
class TaskScheduler {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val frequencies = IntArray(26)
        for (t in tasks) {
            frequencies[t.toInt() - 'A'.toInt()]++
        }
        Arrays.sort(frequencies)
        val fMax = frequencies[25]
        var idleTime = (fMax - 1) * n
        var i = frequencies.size - 2
        while (i >= 0 && idleTime > 0) {
            idleTime -= minOf(fMax - 1, frequencies[i])
            --i
        }
        idleTime = maxOf(0, idleTime)
        return idleTime + tasks.size
    }
}