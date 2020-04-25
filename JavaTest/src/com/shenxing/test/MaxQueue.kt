package com.shenxing.test

import java.util.*

class MaxQueue {

    var maxQueue = ArrayDeque<Int>()
    var queue = ArrayDeque<Int>()

    fun max_value(): Int {
        if (maxQueue.isEmpty()) {
            return -1
        }

        return maxQueue.peek()
    }

    fun push_back(value: Int) {

        queue.add(value)

        var tmp = maxQueue.peek()
        while (!maxQueue.isEmpty() && maxQueue.last < value) {
            maxQueue.removeLast()
        }

        maxQueue.add(value)
    }

    fun pop_front(): Int {
        if (queue.isEmpty()) {
            return -1
        }

        var target = queue.poll()
        if (target == maxQueue.peek()) {
            maxQueue.poll()
        }

        return target
    }
}