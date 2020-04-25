package com.shenxing.test.linklist;

public class LeetcodeInterview0207 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
             val = x;
             next = null;
        }
    }

    /**
     * 返回两个相交链表的相交节点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 参数合法性校验， leetcode中一定要注意。 
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        ListNode curB = headB;

        // 不用担心在无相交节点时这个while的条件无法退出。 因为如果两个链表没有相交节点时，curA与curB一定
        // 会在都等于null时退出循环。 
        while (curA != curB) {
            // 当链表A走到尾部后， 从B链表开始从新遍历
            if (curA == null) {
                curA = headB;
            } else {
                curA = curA.next;
            }
            // 同理链表B走到尾部后，从A链表开始遍历

            // 注意这种思想，当一个链表遍历到结尾时，要从另一个链表开始遍历。 
            // 此题看了题解的。 因为有了之前题目求带环链表的相交节点基础，我的想法最开始是这样的：
            // 先遍历完其中一个链表，将最后一个节点与另一个链表链接起来，形成带环的链表，然后用之前的解法来解这道题。 
            if (curB == null) {
                curB = headA;
            } else {
                curB = curB.next;
            }
        }

        return curA;
    }
}