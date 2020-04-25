package com.shenxing.test

import java.util.*

class Leetcode695 {


    // 最大岛屿数量
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {

        var result = 0
        for (i in 0 until grid.size) {
            for (j in 0 until grid[0].size) {
                if (grid[i][j] != 0) {
                    var tmp = getIslandSize(grid, i, j)
                    if (tmp > result) {
                        result = tmp
                    }
                }
            }
        }

        return result

    }

    fun getIslandSize(grid: Array<IntArray>, startX: Int, startY: Int):Int {
        if (grid == null || grid.isEmpty()) {
            return 0
        }

        var row = grid.size
        var col = grid[0].size

        var que = ArrayDeque<Point>()
        // 标记是否已访问
        var book = Array(grid.size) { Array(grid[0].size) { 0 } }
        // 方向数组
        val next = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))

        var startPoint = Point(startX, startY)
        book[startX][startY] = 1
        que.add(startPoint)
        var count = 1   // 起点是一个

        while (!que.isEmpty()) {
            var tx = 0
            var ty = 0

            var point = que.poll()
            for (i in 0..3) {
                tx = point.x + next[i][0]
                ty = point.y + next[i][1]

                if (tx < 0 || tx >= row || ty < 0 || ty >= col) {
                    continue
                }

                if (grid[tx][ty] > 0 && book[tx][ty] == 0) {
                    book[tx][ty] = 1
                    que.push(Point(tx, ty))
                    count++
                }
            }
        }

        return count
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var array2D = Array(3) {Array(3) {0} }
        }



    }


    data class Point(var x: Int, var y: Int)

}