package problem.lc_contest

class W213 {

    private fun findArrayWithElement(pieces: Array<IntArray>, element: Int): IntArray? {
        return pieces.find { it.indexOf(element) == 0 }
    }

    fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
        var arrIdx = 0

        while (arrIdx < arr.size) {
            val piece = findArrayWithElement(pieces, arr[arrIdx]) ?: return false
            var pieceIdx = 0
            while (arrIdx < arr.size && pieceIdx < piece.size) {
                if (arr[arrIdx] == piece[pieceIdx]) {
                    arrIdx++
                    pieceIdx++
                } else {
                    return false
                }
            }
            if (pieceIdx != piece.size) return false
        }

        return arrIdx == arr.size
    }

}

