package com.shenxing.test.linklist;

public class Leetcode2 {

    /**
     * 两数之和
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int delta = 0;  // 进位
        // 只要有一个链表不为空，就要继续遍历
        while (l1 != null || l2 != null) {
            int tmp = 0;
            // while条件是或， 故此处还要判断空指针
            if (l1 != null) {
                tmp += l1.val;
                l1 = l1.next;
            }
            
            if (l2 != null) {
                tmp += l2.val;
                l2 = l2.next;
            }
            // 加上进位
            tmp += delta;
            // 当前位的个位数
            int bit = tmp % 10; 
            ListNode newNode = new ListNode(bit);
            // 链接到结果上
            cur.next = newNode;
            cur = cur.next;
            // 新的进位， 下一次遍历时参与计算
            delta = tmp / 10;
        }

        // 最后产生的进位还要加入到链表
        if (delta != 0) {
            ListNode newNode = new ListNode(delta);
            cur.next = newNode;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);

        a.next = b;
        b.next = c;

        ListNode aa = new ListNode(5);
        ListNode bb = new ListNode(6);
        ListNode cc = new ListNode(4);

        aa.next = bb;
        bb.next = cc;

        Leetcode2 test = new Leetcode2();
        ListNode res = test.addTwoNumbers(a, aa);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}