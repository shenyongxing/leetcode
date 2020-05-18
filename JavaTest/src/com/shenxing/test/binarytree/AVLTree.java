package com.shenxing.test.binarytree;

/**
 * avl平衡二叉树
 * 说明文档见我的csdn博客
 */
public class AVLTree {
    /**
     * 查找
     * @param root 根节点
     * @param target 目标值
     * @return
     */
    public TreeNode find(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        if (root.val < target) {
            return find(root.right, target);
        } else if (root.val > target) {
            return find(root.left, target);
        } else {
            return root;
        }
    }

    /**
     * 插入
     * @param root 根节点
     * @param value 待插入的值
     * @return
     */
    public TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (root.val < value) {
            root.right = insert(root.right, value);
            if (height(root.right) - height(root.left) == 2) {
                if (root.right.val > value) {
                    rotateWithRightChild(root);
                } else {
                    // 双旋转
                    doubleRotateWithRightChild(root);
                }
            }
        } else if (root.val > value) {
            root.left = insert(root.left, value);
            // 插入完成后检测高度
            if (height(root.left) - height(root.right) == 2) {
                // 高度差等于2时需要旋转
                if (value < root.left.val) {
                    rotateWithLeftChild(root);
                } else {
                    doubleRotateWithLeftChild(root);
                }
            }
        } else {
            // 存在相同的值，不插入
        }

        return root;
    }

    /**
     * 树的高度 
     * 说明，在《数据结构与算法分析》书中高度是放在TreeNode中，在插入时不断的维护的，这样效率更高。 
     * 此处为了理解avl树的操作，单独计算树的高度。 
     */
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * 左边的节点进行旋转
     * @param root
     * @return
     */
    public TreeNode rotateWithLeftChild(TreeNode root) {
        TreeNode k = root.left;
        root.left = k.right;
        k.right = root;

        return k;
    }

    /**
     * 左边的节点进行双旋转
     * @param root
     * @return
     */
    public TreeNode doubleRotateWithLeftChild(TreeNode root) {
        root.left = rotateWithRightChild(root.left);
        return rotateWithLeftChild(root);
    }

    /**
     * 右边的节点进行旋转
     * @param root
     * @return
     */
    public TreeNode rotateWithRightChild(TreeNode root) {
        TreeNode k = root.right;
        root.right = k.left;
        k.left = root;

        return k;
    }

    /**
     * 右边的节点进行双旋转
     * @param root
     * @return
     */
    public TreeNode doubleRotateWithRightChild(TreeNode root) {
        root.right = rotateWithLeftChild(root.right);
        return rotateWithRightChild(root);
    }



    /**
     * 删除相关节点值
     * @param root
     * @param value
     */
    public TreeNode remove(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (root.val < value) {
            root.right = remove(root.right, value);
        } else if (root.val > value) {
            root.left = remove(root.left, value);
        } else {
            // 当前root即是要删除的节点
            if (root.left != null && root.right != null) {
                // 当左右节点都存在时
                TreeNode minNode = getMinNode(root.right);  // 在右节点中找最小的 或 在左节点中找最大的均可
                root.val = minNode.val;     // 把root中右节点的最小值作为新的根节点。（此处采用的是交换值的方式）
                
                // 然后递归调用删除 minNode这个节点
                remove(root.right, minNode.val);
            } else {
                root = (root.left == null ? root.right : root.left);
            }
        }

        return root;
    }

    /**
     * 获取最小节点
     * @param node
     * @return
     */
    public TreeNode getMinNode(TreeNode node) {
        if (node != null) {
            // 一直找最左边的节点，值到left为空，则node为目标节点
            while (node.left != null) {
                node = node.left;
            }

            return node;
        }   

        return node;
    }
}