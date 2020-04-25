package com.shenxing.test.linklist;

import java.util.HashMap;
import java.util.Map;

public class Leetcode817 {

    /**
     * 链表组件  本质上还是对链表的遍历
     * @param head
     * @param G
     * @return
     */
    public int numComponents(ListNode head, int[] G) {
        if (head == null) {
            return 0;
        }

        // 将数组存放在map，提升查找速度
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : G) {
            map.put(i, 0);
        }

        // 由于题目中规定G的大小在10000以内，故可以采用桶排序的思想，定义一个10000大小的数组。
        // 用G的值当作索引下标，存放在这个大小为10000的数组中。 如a[G[i]] = 1;
        // 后面链表比对的时候则可以a[cur.val] == 1 查看是否在G中。 
        // 这个做法是评论区的做法，虽然时间在2ms左右，但是这样会牺牲空间。
        // 个人认为采用hashmap达到了一定的折衷。

        int total = 0;
        ListNode cur = head;
        boolean hited = false;  // 是否命中
        while (cur != null) {
            if (map.containsKey(cur.val)) {
                hited = true;
            } else {
                if (hited) {
                    total++;
                }
                hited = false;
            }
            cur = cur.next;
        }

        // 处理当hited是true时的情形，可能的情形是最后一个节点存在于map中，此时while已经结束循环，故此时要加上1。
        return hited ? total + 1 : total;
    }


}