package problems


/**
 * Created by randheercode
 * Date: 20/6/20
 * Time: 7:32 pm
 */
class PermutationSequence {
    fun getPermutation(n: Int, k: Int): String {
        var y = k
        val factorials = IntArray(n)
        val nums: MutableList<Int> = mutableListOf(1)
        factorials[0] = 1
        for (i in 1 until n) {
            factorials[i] = factorials[i - 1] * i
            nums.add(i + 1)
        }
        --y
        println("Factorials: " + factorials.toList())
        println("nums: " + nums.toList())
        val sb = StringBuilder()
        for (i in n - 1 downTo 0) {
            val idx = y / factorials[i]
            print("idx: $idx\t")
            y -= idx * factorials[i]
            print("y: $y\t")
            sb.append(nums[idx])
            nums.removeAt(idx)
            print("sb: $sb\t")
            print("num: ${nums.toList()}\t")
            println()
        }
        return sb.toString()
    }
}

fun main() {
    println(PermutationSequence().getPermutation(4, 9))
}