package com.shenxing.test.binarytree;

public class Leetcode114 {
    
    /**
     * 原地将二叉树转化为链表
     * @param root
     */
    public void flatten(TreeNode root) {
        flatternToLinkList(root);
    }

    public TreeNode flatternToLinkList(TreeNode root) {
        if (root == null) {
            return root;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        // 左右根的处理
        TreeNode left = flatternToLinkList(root.left);
        TreeNode right = flatternToLinkList(root.right);
        // 将left链断开
        root.left = null;

        if (left != null) {
            // 将左边节点链接到右边
            root.right = left;
            // 这里要将右子树链接到左子树的最末端
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;
        } else {
            // left为空时不处理
        }
        return root;
    }

    // 真不容易啊，通过一步步的调试通过了。

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(5);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(6);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;

        Leetcode114 test = new Leetcode114();
        test.flatten(a);
    }
}