package com.shenxing.test


/**
 * 约瑟夫环问题
 */
class Jose {


    fun ysf(n: Int, k: Int): Int {
        var index = 0

        var list = ArrayList<Int>()
        for (i in 0 until n) {
            list.add(i)
        }

        while (list.size > 1) {
            index = ((index + k - 1) % list.size)
            println("删除序号： $index")
            list.removeAt(index)
        }

        println("最后剩下的是${list.get(0)}")
        return list.get(0)
    }

    companion object {
        @JvmStatic
        fun main(args:Array<String>) {
            var a = Jose()
            println(a.ysf(10, 4))
        }
    }
}