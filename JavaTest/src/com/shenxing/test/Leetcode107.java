package com.shenxing.test;

import java.util.*;


public class Leetcode107 {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        Deque<List<Integer>> stack = new ArrayDeque<>();

        queue.offer(root);
        while (!queue.isEmpty()) {


            int size = queue.size();
            List<Integer> levels = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                // 出队列
                TreeNode tmp = queue.poll();
                levels.add(tmp.val);

                // 将子节点放入队列
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }

                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }

            result.add(0, levels);
        }

        return result;
    }
}
