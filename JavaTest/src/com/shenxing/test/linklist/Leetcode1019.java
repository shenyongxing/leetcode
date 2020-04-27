package com.shenxing.test.linklist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Leetcode1019 {

    /**
     * 链表中的下一个更大节点。 暴力破解法,速度在点慢，8个节点的链表，用时822ms。
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[] {};
        }

        List<Integer> res = new ArrayList<>();
        ListNode cur = head;
        
        while (cur != null) {
            ListNode tmp = cur.next;
            if (tmp != null) {
                while (tmp != null) {
                    if (cur.val < tmp.val) {
                        // 找到第一个大的元素就停止。 (不是找大于cur的最大的节点)
                        break;
                    }
                    tmp = tmp.next;
                }
                res.add(tmp != null ? tmp.val : 0);
            } else {
                // 没有下一个更大的节点了
                res.add(0);
            }

            cur = cur.next;
        }

        int[] a = new int[res.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = res.get(i);
        }
        return a;
    }

    /**
     * 优化版本，使用单调栈。 用时24ms，比暴力方法快了近40倍
     * @param head
     * @return
     */
    public int[] nextLargerNodes2(ListNode head) {
        if (head == null) {
            return new int[] {};
        }

        List<Integer> res = new ArrayList<>();
        // 单调栈，用来存放链表的索引值
        Deque<Integer> stack = new ArrayDeque<>();
        // 存放链表的节点值。由于链表事先不知道长度，故一般用list集合来存储
        List<Integer> data = new ArrayList<>();

        int index = 0;
        while (head != null) {
            // 第一个值暂定为0，后面会按照规则更新
            res.add(0);  
            data.add(head.val);  
            // 当前元素比stack顶部索引所指处要大 
            while (!stack.isEmpty() && head.val > data.get(stack.peek())) {
                res.set(stack.pop(), head.val);
            }

            stack.push(index++);
            head = head.next;
        }

        int[] a = new int[res.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = res.get(i);
        }
        return a;
    }


    /**
     * 优化版本，使用单调栈。 
     * @param head
     * @return
     */
    public int[] nextLargerNodes3(ListNode head) {
        if (head == null) {
            return new int[] {};
        }

        List<Integer> res = new ArrayList<>();
        // 单调栈，用来存放链表的索引值
        Deque<Integer> stack = new ArrayDeque<>();
        // 存放链表的节点值。由于链表事先不知道长度，故一般用list集合来存储
        List<Integer> data = new ArrayList<>();

        int index = 0;
        while (head != null) {
            // 第一个值暂定为0，后面会按照规则更新
            res.add(0);  
            data.add(head.val);  
            // 当前元素比stack顶部索引所指处要大 
            while (!stack.isEmpty() && head.val > data.get(stack.peek())) {
                res.set(stack.pop(), head.val);
            }

            stack.push(index++);
            head = head.next;
        }

        int[] a = new int[res.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = res.get(i);
        }
        return a;
    }

    public static void main(String[] args) {
        Leetcode1019 test = new Leetcode1019();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(7);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(9);
        ListNode f = new ListNode(2);
        ListNode g = new ListNode(5);
        ListNode h = new ListNode(1);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;

        int[] tt = test.nextLargerNodes(a);
        for (int i = 0; i < tt.length; i++) {
            System.out.println(tt[i]);
        }
    }
}