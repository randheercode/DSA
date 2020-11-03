package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 10:44 am
 */

open class Reader4 {
    fun read4(buf4: CharArray): Int {
        return 0
    }
}

class Reader4Impl : Reader4() {
    var buffPtr = 0
    var currPtr = 0
    var commonBuff = CharArray(4)

    fun read(buf: CharArray, n: Int): Int {
        var readDone = 0
        while (readDone < n) {
            if (buffPtr == 0) {
                buffPtr = read4(commonBuff)
                currPtr = 0
            }
            if (buffPtr == 0) return readDone
            buf[readDone++] = commonBuff[currPtr++]
            buffPtr--
        }
        return readDone
    }
}