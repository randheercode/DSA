package codeforces

import java.util.*

fun main() {
    fun canDivide(weight: Int): String {
        if (weight <= 3) return "NO"
        if (weight.rem(2) == 0) return "YES"
        return "NO"
    }
    val sc = Scanner(System.`in`)
    val weight = sc.nextInt()
    sc.close()
    println(canDivide(weight))
}