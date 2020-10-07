package problem.facebook

import problem.old.ListNode
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
        var fMax = 0
        for (f in frequencies) {
            fMax = maxOf(fMax, f)
        }

        // count the most frequent tasks
        var nMax = 0
        for (f in frequencies) {
            if (f == fMax) nMax++
        }

        return maxOf(tasks.size, (fMax - 1) * (n + 1) + nMax)
    }

    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null || k == 0) return head

        // Count Nodes
        var nodeCount = 1
        var tailNode = head
        while (tailNode?.next != null) {
            tailNode = tailNode.next
            nodeCount++
        }

        val rr = k % nodeCount

        if (rr == nodeCount || rr == 0) return head

        // With 5 node, 7 rotation is overall 2 rotation
        var r = 1

        var current = head

        while (r < nodeCount - rr) {
            current = current?.next
            r++
        }

        val newHead = current?.next
        current?.next = null
        current = newHead
        while (current?.next != null) {
            current = current.next
        }
        current?.next = head

        return newHead
    }
}

fun main() {
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2))
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 0))
    println(TaskScheduler().leastInterval(charArrayOf('A', 'A', 'B', 'B', 'C', 'D', 'D', 'D', 'E', 'E', 'F', 'G'), 2))
    println(TaskScheduler().leastIntervalMath(charArrayOf('A', 'A', 'B', 'B', 'C', 'D', 'D', 'D', 'E', 'E', 'F', 'G'), 2))
}