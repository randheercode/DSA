package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 8:23 am
 */
class SimplifyPath {
    fun simplifyPath(path: String): String {
        val pathComponent = path.split("/")
        val pathStack = Stack<String>()

        for (p in pathComponent) {

            if (pathStack.isEmpty() && p == "..") continue

            if (p == "." || p.isEmpty()) continue

            if (p == "..") {
                pathStack.pop()
                continue
            }

            pathStack.push(p)

        }

        val resultBuilder = StringBuilder()

        while (pathStack.isNotEmpty()) {
            if (resultBuilder.isNotEmpty()) resultBuilder.insert(0, "/")
            resultBuilder.insert(0, pathStack.pop())
        }

        resultBuilder.insert(0, "/")
        return resultBuilder.toString()
    }
}