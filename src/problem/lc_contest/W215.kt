package problem.lc_contest


class W215 {

    class OrderedStream(val n: Int) {

        val data = Array<String>(n + 1) { "" }
        var ptr = 1

        fun insert(id: Int, value: String): List<String> {
            data[id] = value
            val result = mutableListOf<String>()
            var idx = ptr
            while (idx < n + 1 && data[idx].isNotEmpty()) {
                result.add(data[idx])
                idx++
            }
            ptr = idx
            return result
        }

    }

}
