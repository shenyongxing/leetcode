package com.shenxing.test.linklist;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LeetcodeInterview35 {

    static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 复杂链表的复制
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 建立旧链表与新链表节点之前的映射
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        Node dummy = new Node(0);
        Node p = dummy;
        while (cur != null) {
            Node tmp = null;
            // 先从map中取
            if (map.containsKey(cur)) {
                tmp = map.get(cur);
            } else {
                tmp = new Node(cur.val);
                map.put(cur, tmp);
            }
            p.next = tmp;
            p = p.next;

            // 同理random指针也是
            Node tmpRandom = null;
            if (map.containsKey(cur.random)) {
                tmpRandom = map.get(cur.random);
            } else {
                if (cur.random != null) {
                    tmpRandom = new Node(cur.random.val);
                }
            }

            // 上面两个map.containsKey的逻辑可以抽取一个公用的方法。
            tmp.random = tmpRandom;

            map.put(cur.random, tmpRandom);
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 链表的深度复制
     * 看了别人的解法才知道有这么多的解法，但是有一个思路很赞，就是这样的链表本质上是一个图。 
     * 而对于图的遍历有深度优先遍历与广度优先遍历。
     * （还有一些其他的解法，也很巧妙。）
     */
    public Node copyRandomList2(Node head) {
        // return dfs(head);
        return bfs(head);
    }

    // 用来记录某个节点是否已经访问过
    Map<Node, Node> map = new HashMap<Node, Node>();
    /**
     * 深度优先遍历算法 查找当前节点的拷贝
     * @param head 
     * @return
     */
    public Node dfs(Node head) {
        if (head == null) {
            return null;
        }

        // 当前节点有存在对应的节点，则直接返回
        if (map.containsKey(head)) {
            return map.get(head);
        }

        Node copy = new Node(head.val);
        // 建立索引
        map.put(head, copy);
        // 查找head.next的拷贝   
        copy.next = dfs(head.next);
        // 同理，查找head.random的拷贝
        copy.random = dfs(head.random);

        // 这种深度优先的算法也相当于递归。
        return copy;
    }

    /**
     * 广度优先遍历算法
     * @param head
     */
    public Node bfs(Node head) {
        if (head == null) {
            return head;
        }

        // 广度优先算法一般用到队列
        Deque<Node> queue = new ArrayDeque<Node>();
        // 头节点入队
        queue.offer(head);

        // 构建头节点的拷贝
        Node clone = new Node(head.val);
        map.put(head, clone);

        while (!queue.isEmpty()) {
            // 从队列中取出一个节点
            Node tmp = queue.poll();
            if (tmp.next != null && !map.containsKey(tmp.next)) {
                // 建立旧链表与新链表节点之前的对应关系
                map.put(tmp.next, new Node(tmp.next.val));
                queue.offer(tmp.next);
            }

            if (tmp.random != null && !map.containsKey(tmp.random)) {
                map.put(tmp.random, new Node(tmp.random.val));
                queue.offer(tmp.random);
            }
            
            // 注意key的值
            map.get(tmp).next = map.get(tmp.next); 
            map.get(tmp).random = map.get(tmp.random);
        }

        return clone;
    }

    /**
     * 这种解法也很巧妙。在每个节点后复制本身并插入到其后，处理了random后，再断开链表。
     * @param head
     * @return
     */
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return head;
        }

        Node cur = head;
        while (cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;  // 将cur指向新节点
            cur = tmp.next;  // 指向原有的下一个节点
        }
        // 至此，每一个节点都复制了一个一样的节点在其后面。
        // 将cur重新指向头节点
        cur = head;
        
        // 链表random部分
        while (cur != null && cur.next != null) {
            // 此时cur.next是复制的节点，注意这个逻辑
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            } 
            cur = cur.next.next;
        }

        // 再将两个链表分开
        Node curOldList = head;
        Node curNewList = head.next;
        Node newHead = head.next;

        while (curOldList != null) {
            // 因为每个旧节点后确定有一个复制的节点，故可以保证curOldList.next不为空，但是新复制的节点就不可以了，故要加入判空处理。 
            curOldList.next = curOldList.next.next;
            if (curNewList.next != null) {
                curNewList.next = curNewList.next.next;
            }

            curOldList = curOldList.next;
            curNewList = curNewList.next;
        }

        // 这个地方不能直接返回head.next。因为经过上面的新旧链表断开过程，head.next已经不是新链表的头节点了。
        // 故上面用一个新的变量来保存新链表的头节点。 
        return newHead;
    }
}