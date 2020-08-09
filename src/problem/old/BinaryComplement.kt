package problem.old

/**
 * Created by randheercode
 * Date: 5/5/20
 * Time: 8:32 am
 * Statement: Find binary complement of given number.
 */
class BinaryComplement {
    fun getBinaryComplement(N: Int): Int {
        val bitCount = Math.floor(Math.log(N.toDouble()) / Math.log(2.0)).toInt() + 1
        return ((1 shl bitCount).minus(1)).xor(N)
    }
}

fun main() {
    println(BinaryComplement().getBinaryComplement(5))
}