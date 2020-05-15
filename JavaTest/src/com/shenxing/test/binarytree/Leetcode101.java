package com.shenxing.test.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode101 {

    /**
     * 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    /**
     * 两棵树是否为镜像
     * @param t1
     * @param t2
     * @return
     */
    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }


    /**
     * 对称二叉树
     */
    public boolean isSymmetric2(TreeNode root) {
        // 这里用linklist是因为它可以存放null值
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            // 连续取出两个元素比较
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) {
                continue;
            }

            if (t1 == null || t2 == null) {
                return false;
            }

            if (t1.val != t2.val) {
                return false;
            }

            // 注意这里的加入队列的顺序
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);

        }

        return true;
    }
}