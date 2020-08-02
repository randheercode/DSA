package problems

/**
 * Created by randheercode
 * Date: 2/8/20
 * Time: 11:40 am
 */
class Logger() {

    /** Initialize your data structure here. */
    val messageTime = mutableMapOf<String, Int>()


    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
    If this method returns false, the message will not be printed.
    The timestamp is in seconds granularity. */
    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
        synchronized(this) {
            var result = true
            if (messageTime.containsKey(message)) {
                result = (timestamp - messageTime[message]!!) > 10
            }
            if (result) {
                messageTime[message] = timestamp
            }
            return result
        }
    }

}

fun main() {
    val obj = Logger()
    println(obj.shouldPrintMessage(1, "foo"))
    println(obj.shouldPrintMessage(2, "bar"))
    println(obj.shouldPrintMessage(3, "foo"))
    println(obj.shouldPrintMessage(8, "foo"))
    println(obj.shouldPrintMessage(10, "foo"))
    println(obj.shouldPrintMessage(11, "foo"))
}
