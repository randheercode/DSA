package problem

import kotlin.math.log

// https://medium.com/techie-delight/bit-manipulation-interview-questions-and-practice-problems-27c0e71412e7
// http://www.techiedelight.com/bit-hacks-part-1-basic/
class BitManipulation {

    // region Basic
    fun isEven(n: Int): Boolean {
        return (n and 1) == 0
    }

    fun oppositeSign(a: Int, b: Int): Boolean {
        return (a xor b) < 0
    }

    fun addOne(n: Int): Int {
        return -(n.inv())
    }

    fun swap(f: Int, s: Int) {
        var a = f
        var b = s
        if (a != b) {
            a = a xor b
            b = a xor b
            a = a xor b
        }
        println("$a $b")
    }

    // endregion

    // region Playing with Kth bit: n shl (k - 1)
    fun turnOffKthBit(n: Int, k: Int): Int {
        return n and (1 shl (k - 1)).inv()
    }

    fun turnOnKthBit(n: Int, k: Int): Int {
        return n or (1 shl (k - 1))
    }

    fun isKthSet(n: Int, k: Int): Boolean {
        return (n or (1 shl (k - 1))) > 0
    }

    fun toggleKthBit(n: Int, k: Int): Int {
        return n xor (1 shl (k - 1))
    }

    // endregion

    // region Playing with Rightmost bit
    fun isPowerOfTwo(n: Int): Boolean {
        return (n and (n - 1)) == 0
    }

    fun rightMostSetBitPosition(n: Int): Int {
        if (n and 1 == 1) return 1
        val x = n xor (n and (n - 1))
        var pos = 0
        while (x > 0) {
            x shl 1
            pos++
        }
        return pos
    }

    fun rightMostSetBitPositionOther(n: Int): Int {
        if (n and 1 == 1) return 1
        return log(2.0, (n and -n).toDouble()).toInt() + 1
    }

    fun setBitPos(n: Int): Int {
        if ((n and (n - 1)) > 0) {
            return 1
        }
        return log(2.0, n.toDouble()).toInt() + 1
    }

    fun findParity(x: Int): Boolean {
        var n = x
        var parity = false
        // run till n is zero
        while (n != 0) {
            // invert the parity flag
            if (n and 1 == 1) {
                parity = !parity
            }
            // right shift n by 1 (divide by 2)
            n = n shr 1
        }
        return parity
    }

    fun findParityRightMost(x: Int): Boolean {
        var n = x
        var parity = false
        // run till n is zero
        while (n != 0) {
            // invert the parity flag
            parity = !parity
            // turn off rightmost set bit
            n = n and n - 1
        }
        return parity
    }

    // endregion

    // region Playing with Letter of English Alphabets
    fun lowerToUpper(char: Char): Char {
        return (char.toInt() or ' '.toInt()).toChar()
    }

    fun upperToLower(char: Char): Char {
        return (char.toInt() and '_'.toInt()).toChar()
    }

    fun invert(char: Char): Char {
        return (char.toInt() xor ' '.toInt()).toChar()
    }

    fun letterPosition(char: Char): Int {
        return char.toInt() and 31
    }
    // endregion

    // region Find absolute value of integer

    fun absolute(n: Int): Int {
        val mask = n shr 31
        return (n + mask) and mask
    }

    // endregion

    // region Problems
    // Find number of bits needed to be flipped to convert x to y
    fun findBits(x: Int, y: Int): Int {
        // take XOR of x and y and store in n
        var n = x xor y
        // count stores the total bits set in n
        var count = 0
        while (n != 0) {
            n = n and n - 1 // clear the least significant bit set
            count++
        }
        return count
    }

    // return true if binary representation of n is a palindrome
    fun isPalindrome(n: Int): Boolean {
        // rev stores reverse of binary representation of n
        var rev = 0
        // do till all bits of n are processed
        var k = n
        while (k > 0) {
            // add rightmost bit to rev
            rev = rev shl 1 or (k and 1)
            k = k shr 1 // drop last bit
        }
        // return true if reverse(n) is same as n
        return n == rev
    }
    //endregion

}

fun main() {
    val result = BitManipulation().swap(5, 6)
    println(result)
}
