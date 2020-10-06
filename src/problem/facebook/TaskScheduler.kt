package problem.facebook

import java.util.*

// https://leetcode.com/problems/task-scheduler/solution/
class TaskScheduler {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val f = IntArray(26)
        for (task in tasks) f[task - 'A']++
        Arrays.sort(f)
        val chunk = f[25] - 1
        var idleSpots = chunk * n

        for (i in 24 downTo 0) {
            idleSpots -= minOf(chunk, f[i])
        }
        return if (idleSpots < 0) tasks.size else idleSpots + tasks.size
    }

    fun leastIntervalMath(tasks: CharArray, n: Int): Int {
        // frequencies of the tasks
        val frequencies = IntArray(26)
        for (t in tasks) {
            frequencies[t.toInt() - 'A'.toInt()]++
        }

        // max frequency
        var f_max = 0
        for (f in frequencies) {
            f_max = maxOf(f_max, f)
        }

        // count the most frequent tasks
        var n_max = 0
        for (f in frequencies) {
            if (f == f_max) n_max++
        }

        return maxOf(tasks.size, (f_max - 1) * (n + 1) + n_max)
    }
}

fun main() {
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2))
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 0))
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'B', 'B', 'C', 'D', 'D', 'D', 'E', 'E', 'F', 'G'), 2))
}