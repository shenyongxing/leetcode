package com.shenxing.test.binarytree;

import java.util.Deque;
import java.util.LinkedList;

// 填充每个节点的下一个右侧节点指针
public class Leetcode116 {
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        // linklist可以插入null值
        Deque<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                // 换行节点，跳过
                if (tmp == null) {
                    continue;
                }
                tmp.next = queue.peek();

                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }

            if (!queue.isEmpty()) {
                queue.offer(null);  // 用于标识换行
            }
        }

        return root;
    }
}