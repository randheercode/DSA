package problem.facebook

import java.util.*


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
}

fun main() {
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2))
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 0))
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'B', 'B', 'C', 'D', 'D', 'D', 'E', 'E', 'F', 'G'), 2))
}