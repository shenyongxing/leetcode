package com.shenxing.test.linklist;

import java.util.*;
public class LeetcodeInterview0202 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 删除无序链表中的重复节点
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }

        // 此处如果改用map应该会有更好的性能。 
        List<Integer> list = new ArrayList<Integer>();
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            if(list.contains(cur.val)) {
                prev.next = cur.next;
            } else {
                prev = cur;
                list.add(cur.val);
            }
            cur = cur.next;
        }

        return head;
    }


    /**
     * 删除无序链表中的重复节点
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes2(ListNode head) {
        if (head == null) {
            return head;
        }

        // 此处比list集合的查找要快，因为hashmap查找复杂度是O(1) 
        // 经过leetcode判题，改动后时间为10ms。 之前为923ms。map与list集合的查找性能差别还是很大的。
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            if(map.containsKey(cur.val)) {
                prev.next = cur.next;
            } else {
                prev = cur;
                map.put(cur.val, 1);    // 此处value不重要，故随便写。 
            }
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(3);
        ListNode e = new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        LeetcodeInterview0202 test = new LeetcodeInterview0202();
        ListNode h = test.removeDuplicateNodes(a);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}