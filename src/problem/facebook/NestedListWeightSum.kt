package problem.facebook

// https://leetcode.com/problems/nested-list-weight-sum/
class NestedListWeightSum {
    fun depthSum(nestedList: List<NestedInteger>): Int {
        return dfs(nestedList, 1)
    }

    private fun dfs(list: List<NestedInteger>, depth: Int): Int {
        var sum = 0
        for (n in list) {
            sum += if (n.isInteger()) {
                n.getInteger()!! * depth
            } else {
                dfs(n.getList()!!, depth + 1)
            }
        }
        return sum
    }
}

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    fun getInteger(): Int?

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int): Unit

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger): Unit

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger>?
}