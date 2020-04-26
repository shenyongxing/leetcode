package com.shenxing.test.linklist;

public class Leetcode23 {

    /**
     * 合并k个排序链表。 主要思想是两两合并
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeRange(lists, 0, lists.length - 1);
    }

    /**
     * 将给定的范围的数组排序。 通过遍历不断的将排序范围缩小。
     * @param lists
     * @param left 左边界
     * @param right 右边界
     * @return
     */
    public ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left >= right) {
            return lists[left]; // 临界条件，可以在纸上画一画
        }

        int mid = left + (right - left) / 2;    // 一般在求中间点的时候用这个算法可以避免数据溢出。 
        ListNode leftHead = mergeRange(lists, left, mid);
        ListNode rightHead = mergeRange(lists, mid + 1, right);
        return merge(leftHead, rightHead);
    }

    /**
     * 合并两个有序链表， 这个是将k个排序的基础
     * @param headA
     * @param headB
     * @return
     */
    public ListNode merge(ListNode headA, ListNode headB) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (headA != null && headB != null) {
            if (headA.val < headB.val) {
                cur.next = headA;
                headA = headA.next;
            } else {
                cur.next = headB;
                headB = headB.next;
            }
            cur = cur.next;
        }

        // 将剩余的链表链接到结果上，下同。 注意这里不是while。
        if (headA != null) {
            cur.next = headA;
        }

        if (headB != null) {
            cur.next = headB;
        }

        return dummy.next;
    }
    // 此算法的时间复杂度为O(nlogK). 
}