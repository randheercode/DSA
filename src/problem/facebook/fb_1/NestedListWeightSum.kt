package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 9/11/20
 * Time: 7:04 pm
 */

interface NestedInteger {

    fun isInteger(): Boolean


    fun getInteger(): Int?


    fun setInteger(value: Int): Unit


    fun add(ni: NestedInteger): Unit

    fun getList(): List<NestedInteger>?

}

class NestedListWeightSum {
    var sum = 0

    private fun compute(nestedList: List<NestedInteger>, label: Int) {

        for (nl in nestedList) {
            if (nl.isInteger()) {
                sum += (label * nl.getInteger()!!)
            } else {
                compute(nl.getList()!!, label + 1)
            }
        }

    }

    fun depthSum(nestedList: List<NestedInteger>): Int {
        compute(nestedList, 1)
        return sum
    }
}