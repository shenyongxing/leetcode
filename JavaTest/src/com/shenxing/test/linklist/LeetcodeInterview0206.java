package com.shenxing.test.linklist;

public class LeetcodeInterview0206 {

    /**
     * 判断回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到链表的中间节点
        ListNode second = split(head);
        // 将后半部分反转
        second = reverse(second);
        while (second != null && head != null) {
            if (second.val != head.val) {
                return false;
            }
            second = second.next;
            head = head.next;
        }

        return true;
    }

    /**
     * 将链表从中间断开
     * @param head
     * @return 后半部分的头节点
     */
    public ListNode split(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        return slow;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(5);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(3);
        ListNode f = new ListNode(4);
        ListNode g = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;

        LeetcodeInterview0206 test = new LeetcodeInterview0206();
        System.out.println(test.isPalindrome(a));
    }
}