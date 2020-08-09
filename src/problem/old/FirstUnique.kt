package problem.old


/**
 * Created by randheercode
 * Date: 28/4/20
 * Time: 9:42 pm
 * Statement: You have a queue of integers, you need to retrieve the first unique integer in the queue.
 * Implement the FirstUnique class:
 *
 * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 * int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
 * void add(int value) insert value to the queue.
 *
 * Hint:
 * Use doubly Linked list with hashmap of pointers to linked list nodes. add unique number to the linked list. When add is called check if the added number is unique then it have to be added to the linked list and if it is repeated remove it from the linked list if exists. When showFirstUnique is called retrieve the head of the linked list.
 * Use queue and check that first element of the queue is always unique.
 * Use set or heap to make running time of each function O(logn).
 *
 */
class FirstUnique(nums: IntArray) {

    private val uniqueNumber = mutableMapOf<Int, Int>()
    private val duplicateNumber = mutableMapOf<Int, Int>()

    init {
        nums.forEach { add(it) }
    }

    fun showFirstUnique(): Int {
        return uniqueNumber.values.firstOrNull() ?: -1
    }

    fun add(value: Int) {
        if (duplicateNumber.contains(value)) return
        if (uniqueNumber.contains(value)) {
            uniqueNumber.remove(value)
            duplicateNumber[value] = value
        } else {
            uniqueNumber[value] = value
        }
    }

}

fun main() {
    val firstUnique = FirstUnique(intArrayOf(7, 7, 7, 7, 7, 7))
    println(firstUnique.showFirstUnique()) // return -1
    firstUnique.add(7)            // the queue is now [7,7,7,7,7,7,7]
    firstUnique.add(3)            // the queue is now [7,7,7,7,7,7,7,3]
    firstUnique.add(3)           // the queue is now [7,7,7,7,7,7,7,3,3]
    firstUnique.add(7)          // the queue is now [7,7,7,7,7,7,7,3,3,7]
    firstUnique.add(17)          // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
    println(firstUnique.showFirstUnique()) // return 17

    val firstUnique1 = FirstUnique(intArrayOf(2,3,5))
    println(firstUnique1.showFirstUnique()) // return 2
    firstUnique1.add(5);            // the queue is now [2,3,5,5]
    println(firstUnique1.showFirstUnique()) // return 2
    firstUnique1.add(2);            // the queue is now [2,3,5,5,2]
    println(firstUnique1.showFirstUnique()) // return 3
    firstUnique1.add(3);            // the queue is now [2,3,5,5,2,3]
    println(firstUnique1.showFirstUnique()) // return -1
}