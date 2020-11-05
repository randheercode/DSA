package problem.facebook.fb_1

import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 2:06 pm
 */

internal class DSU {
    var parent: IntArray = IntArray(10001)

    fun find(x: Int): Int {
        if (parent[x] != x) parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        parent[find(x)] = find(y)
    }

    init {
        for (i in 0..10000) parent[i] = i
    }
}

class AccountsMerge {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val emailToName: MutableMap<String, String> = mutableMapOf()
        val graph: MutableMap<String, MutableList<String>> = mutableMapOf()
        for (account in accounts) {
            val name = account[0]
            val firstEmail = account[1]
            graph.computeIfAbsent(firstEmail) { ArrayList() }.add(firstEmail)
            emailToName[firstEmail] = name
            for (i in 2 until account.size) {
                val email = account[i]
                graph.computeIfAbsent(email) { ArrayList() }.add(firstEmail)
                graph.computeIfAbsent(firstEmail) { ArrayList() }.add(email)
                emailToName[email] = name
            }
        }
        val seen: MutableSet<String> = mutableSetOf()
        val ans: MutableList<List<String>> = mutableListOf()
        for (email in graph.keys) {
            if (!seen.contains(email)) {
                seen.add(email)
                val stack: Stack<String> = Stack()
                stack.push(email)
                val component: MutableList<String> = mutableListOf()
                while (!stack.empty()) {
                    val node = stack.pop()
                    component.add(node)
                    for (nei in graph[node]!!) {
                        if (!seen.contains(nei)) {
                            seen.add(nei)
                            stack.push(nei)
                        }
                    }
                }
                component.sort()
                component.add(0, emailToName[email]!!)
                ans.add(component)
            }
        }
        return ans
    }

    fun accountsMergeDSU(accounts: List<List<String>>): List<List<String>> {
        val dsu = DSU()
        val emailToName: MutableMap<String, String> = mutableMapOf()
        val emailToID: MutableMap<String, Int> = mutableMapOf()
        var id = 0
        for (account in accounts) {
            var name = ""
            for (email in account) {
                if (name === "") {
                    name = email
                    continue
                }
                emailToName[email] = name
                if (!emailToID.containsKey(email)) {
                    emailToID[email] = id++
                }
                dsu.union(emailToID[account[1]]!!, emailToID[email]!!)
            }
        }
        val ans: MutableMap<Int, MutableList<String>> = mutableMapOf()
        for (email in emailToName.keys) {
            val index = dsu.find(emailToID[email]!!)
            ans.computeIfAbsent(index) { ArrayList() }.add(email)
        }
        for (component in ans.values) {
            component.sort()
            component.add(0, emailToName[component[0]]!!)
        }
        return ArrayList(ans.values)
    }
}