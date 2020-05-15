package com.shenxing.test.binarytree;

public class Leetcode108 {
    /**
     * 将排序的数组转化为一棵平衡的二叉搜索树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null;
        for (int i = 0; i < nums.length; i++) {
            root = insert(root, nums[i]);
        }

        return root;
    }

    /**
     * 在二叉搜索树中插入节点
     */
    public TreeNode insert(TreeNode root , int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (value < root.val) {
            root.left = insert(root.left, value);
            if (height(root.left) - height(root.right) == 2) {
                // 高度超过2，需要旋转
                if (root.left.val < value) {
                    // 插在左边
                    root = rotateLeft(root);
                } else {
                    // 插在右边
                    root = rotateLeftRight(root);
                }
            }
        } else if (value > root.val) {
            root.right = insert(root.right, value);
            if (height(root.right) - height(root.left) == 2) {
                // 高度超过2， 需要旋转
                if (root.right.val > value) {
                    // 插在右边
                    root = rotateRight(root);
                } else {
                    // 插在左边
                    root = rotateRight(root);
                }
            }
        } else {
            // do nothing
        }

        return root;
    }

    /**
     * 树的高度
     * @param node
     * @return
     */
    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = height(node.left);
        int right = height(node.right);

        return 1 + Math.max(left, right);
    }

    /**
     * 向右边旋转, 对应左左的情况
     * @param node
     * @return
     */
    public TreeNode rotateLeft(TreeNode node) {
        TreeNode k = node.left;
        node.left = k.right;
        k.right = node;
        return k;
    }

    /**
     * 对应左右的情况，先向左旋转，形成左左的情况，再向右边旋转
     * @param node
     * @return
     */
    public TreeNode rotateLeftRight(TreeNode node) {
        node.left = rotateRight(node.left);
        return rotateLeft(node);
    }

      /**
     * 向左边旋转
     * @param node
     * @return
     */
    public TreeNode rotateRight(TreeNode node) {
        TreeNode k = node.right;
        node.right = k.left;
        k.left = node;
        return k;
    }

    /**
     * 对应右左的情况，先向右形成右右的情形，然后再向左边旋转
     * @param node
     * @return
     */
    public TreeNode rotateRightLeft(TreeNode node) {
        node.right = rotateLeft(node.right);
        return rotateRight(node);
    }

}