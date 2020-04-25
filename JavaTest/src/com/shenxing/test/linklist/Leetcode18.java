package com.shenxing.test.linklist;

public class Leetcode18 {

    /**
     * 删除链表的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 加入哑节点，方便处理删除第一个元素的情况。 
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                // 由于题目中说明链表的值各不相同，故找到删除的节点后可以退出循环。
                break;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}