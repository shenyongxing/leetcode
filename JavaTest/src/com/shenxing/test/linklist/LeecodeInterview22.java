package com.shenxing.test.linklist;

public class LeecodeInterview22 {

    /**
     * 链表中倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        // 快指针先走k步
        for (int i = 0; i < k; i++) {
            // 避免k不合法的情况
            if (fast == null) {
                return fast;
            }
            fast = fast.next;
        }

        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}