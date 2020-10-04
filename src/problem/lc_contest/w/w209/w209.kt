package problem.lc_contest.w.w209

import utils.TreeNode

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun specialArray(nums: IntArray): Int {
        val array = IntArray(nums.size + 1)
        for (n in nums) {
            val idx = minOf(n, nums.size)
            array[idx] = array[idx] + 1
        }

        var last = 0
        for (i in array.lastIndex downTo 0) {
            if (array[i] != 0) {
                array[i] = array[i] + last
            } else {
                array[i] = last
            }
            last = array[i]
        }

        for (i in array.lastIndex downTo 1) {
            if (array[i] == i) return i
        }
        return -1
    }
}

private fun test1() {
    println(Prob1().specialArray(intArrayOf(3, 5)))
    println(Prob1().specialArray(intArrayOf(0, 0)))
    println(Prob1().specialArray(intArrayOf(0, 4, 3, 0, 4)))
    println(Prob1().specialArray(intArrayOf(3, 6, 7, 7, 0)))
}

private class Prob2 {
    fun isEvenOddTree(root: TreeNode?): Boolean {
        fun height(root: TreeNode?): Int {
            return if (root == null) 0 else (1 + maxOf(height(root.left), height(root.right)))
        }

        val height = height(root)
        val result = mutableListOf<MutableList<Int>>()
        repeat(height) { result.add(mutableListOf()) }

        fun traverse(root: TreeNode?, label: Int) {
            if (root == null) return
            result[label].add(root.`val`)
            traverse(root.left, label + 1)
            traverse(root.right, label + 1)
        }
        traverse(root, 0)

        fun isOk(list: List<Int>, idx: Int): Boolean {
            val even = idx % 2 == 0
            if (even && list[0] % 2 == 0) return false
            if (!even && list[0] % 2 != 0) return false
            for (i in 1..list.lastIndex) {
                if (even && (list[i - 1] > list[i] || list[i] % 2 == 0)) return false
                if (!even && (list[i - 1] < list[i] || list[i] % 2 != 0)) return false
            }
            return true
        }

        var idx = 0
        while (idx < result.size) {
            if (!isOk(result[idx], idx)) return false
            idx++
        }
        return true
    }
}

private fun test2() {
    println(Prob2())
}

private class Prob3 {

}

private fun test3() {
    println(Prob3())
}

private class Prob4 {

}

private fun test4() {
    println(Prob4())
}

fun main() {
    when (1) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
