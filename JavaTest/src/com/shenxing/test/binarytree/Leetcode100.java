package com.shenxing.test.binarytree;

public class Leetcode100 {

    /**
     * 相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            // 本来这里也是一个if else的，写下来结果可以直接简化成这样的。
            return q == null;
        }

        if (q == null) {
            return p == null;
        }

        // 比较当前节点是否相同
        if (q.val != p.val) {
            return false;
        }

        // 分别对左右子树递归调用
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 从这题看，基本上也是前序遍历的思想。先比较当前节点，然后在依次左右节点，最后加入对异常状况的处理。 
}