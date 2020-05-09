package com.shenxing.test.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode98 {
    /**
     * 验证是否是合法二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 当前root值的上下界要在指定的范围内
     * @param root
     * @param lower 下界
     * @param upper 上界 
     * @return
     */
    public boolean isValidBST(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }

        // 不符合搜索树的定义
        if (root.val <= lower || root.val >= upper) {
            return false;
        }

        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }

    // 在写代码时一定要注意，二叉搜索树的所有左子节点均小于当前节点，所有右子节点均小于当前节点。
    // 注意如下二叉树
    //       3
    //     1   4
    //    0 2 2  5
    // 在这棵树中，2小于4 但是2小于3了，仍然不是合法的二叉树


    long pre = Long.MIN_VALUE;
    /**
     * @param root
     * @return 本质上还是利用了中序的排序性质，只是用了递归的方式。
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 先递归判断左子树是否合法
        if (!isValidBST2(root.left)) {
            return false;
        }

        // 在判断当前节点与左子节点的大小关系
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        // 在判断右子树是否合法
        return isValidBST2(root.right);
    }


    /**
     * 在遍历的过程中比较， 在leetcode上这种方式的时间还没有上面的递归调用快。 
     */
    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode tmp = stack.pop();
            if (tmp.val <= pre) {
                return false;
            } else {
                pre = tmp.val;
            }

            cur = tmp.right;
            // 这里不能在push了，因为cur在while循环开头就会push，如果这里push就会push两次了。 
            // if (cur != null) {
                // stack.push(cur);
            // }
        }

        return true;
    }
}