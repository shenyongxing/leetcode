package com.shenxing.test.linklist;

public class LeetcodeInterview0204 {

    /**
     * 链表分割。大意是将小于x值的链表节点放在前面。 （与快排不一样）
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = head;
        ListNode cur = head.next;   // 注意，cur此时指向第二个元素。保留一个开始的元素，以便可以和此元素比较后插入。  
    
        while (cur != null) {
            if (cur.val < x) {
                // 比x元素小的节点采用头插法，将其插入在头节点后面。 
                prev.next = cur.next;
                cur.next = dummy.next;    // 如果cur开始的时候指向head，而不是head.next。那么在此步骤的时候cur会指向自已。 
                dummy.next = cur;
                cur = prev.next;    // 注意要移动cur
            } else {
                prev = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}