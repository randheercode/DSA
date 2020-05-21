package problems

import generateIntArray
import printArray

/**
 * Created by randheercode
 * Date: 21/5/20
 * Time: 4:49 pm
 * Problem Statement: Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 */
class CountSquare {
    fun countSquares(matrix: Array<IntArray>): Int {
        var count = 0
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (i > 0 && j > 0 && matrix[i][j] > 0)
                    matrix[i][j] = minOf(matrix[i - 1][j - 1], matrix[i][j - 1], matrix[i - 1][j]) + 1
                count += matrix[i][j]
            }
        }
        return count
    }
}

fun main() {
    test1()
    test2()
}

private fun test1() {
    val inputArray = generateIntArray("[[0,1,1,1],[1,1,1,1],[0,1,1,1]]")
    printArray(inputArray, "${Thread.currentThread().stackTrace[1].methodName}: Input: ")
    println("${Thread.currentThread().stackTrace[1].methodName}: Output: ${CountSquare().countSquares(inputArray)}")
}

private fun test2() {
    val inputArray = generateIntArray("[[1,0,1],[1,1,0],[1,1,0]]")
    printArray(inputArray, "${Thread.currentThread().stackTrace[1].methodName}: Input: ")
    println("${Thread.currentThread().stackTrace[1].methodName}: Output: ${CountSquare().countSquares(inputArray)}")
}


