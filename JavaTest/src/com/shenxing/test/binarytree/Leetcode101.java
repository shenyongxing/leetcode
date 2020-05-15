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
            // t1 与 t2可能为空值，故此处要加入判断
            if (t1 == null && t2 == null) {
                continue;
            }

            if (t1 == null || t2 == null) {
                return false;
            }

            if (t1.val != t2.val) {
                return false;
            }

            // 注意这里的加入队列的顺序,因为linklist可以存放空值，故此处没有加入检验。
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);

        }

        return true;
    }


    /**
     * 对称二叉树
     */
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }

        TreeNode t1 = root.left;
        TreeNode t2 = root.right;

        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        // 当前节点的左右子节点不相同
        if (t1.val != t2.val) {
            return false;
        }

        // 在这里就要比较t1.left与t2.right的关系了。
        // 但这里进行不下去了， 想不清楚了。 
        // 因为互为镜像要考虑两个节点的比较，而此方法只有一个参数，所以递归调用比较困难。
        // 在这种情况下，可以像方法一一样重写一个方法。 
        
        return true;

    }
}