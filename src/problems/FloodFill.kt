package problems

/**
 * Created by randheercode
 * Date: 11/5/20
 * Time: 4:54 pm
 *  An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 *  Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 *  To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 *  At the end, return the modified image.
 */
class FloodFill {
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        fun fillImage(image: Array<IntArray>, sr: Int, sc: Int, color: Any, newColor: Int) {
            if (image[sr][sc] == color) {
                image[sr][sc] = newColor
                if (sr >= 1) fillImage(image, sr - 1, sc, color, newColor)
                if (sr < image.lastIndex) fillImage(image, sr + 1, sc, color, newColor)
                if (sc >= 1) fillImage(image, sr, sc - 1, color, newColor)
                if (sc < image[sr].lastIndex) fillImage(image, sr, sc + 1, color, newColor)
            }
        }
        if (image[sr][sc] != newColor) fillImage(image, sr, sc, image[sr][sc], newColor)
        return image
    }
}

fun main() {
    val array = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 1))
    printArray(array)
    printArray(FloodFill().floodFill(array, 1, 1, 1))
}

private fun printArray(image: Array<IntArray>) {
    println(image.joinToString(separator = " ", prefix = "[", postfix = "]") { it.joinToString(separator = ", ", prefix = "[", postfix = "]") })
}
