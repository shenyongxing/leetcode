package com.shenxing.test

class Leetcode121 {



    fun maxProfit(prices: IntArray): Int {

        if (prices == null || prices.isEmpty()) {
            return 0
        }

        var maxProfit = 0
        for (i in 0 until prices.size) {
            for (j in i + 1 until prices.size) {
                var tmp = prices[j] - prices[i]
                if (tmp > 0 && tmp > maxProfit) {
                    maxProfit = tmp
                }
            }
        }

        return maxProfit
    }

    companion object {
        @JvmStatic
        fun main(agrs: Array<String>) {
            var a = BinaryTree()
//            var b = intArrayOf(1, 5, 7, 18, 4, 2, 3)
//            var b = intArrayOf(1, 5, 7, 18, 4, 2, 3)
            var b = intArrayOf(1, 2, 5)


            println(a.coinChange(b, 11))
        }
    }
}