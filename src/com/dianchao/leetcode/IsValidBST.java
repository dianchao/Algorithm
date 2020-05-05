package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一棵二叉树，你要判断它是不是一棵二叉搜索树。
 *
 * 二叉搜索树的定义是：
 *
 *   1. 左子树所有节点上的值都小于根节点上的值
 *   2. 右子树所有节点上的值都大于根节点上的值
 *   3. 左右子树同时也为二叉搜索树
 *
 * 比如说，给你的二叉树是：
 *
 *     4
 *    / \
 *   2   6
 *
 * 左子树只有一个节点 2，小于 4；右子树也只有一个节点 6，大于 4。因此这是一棵二叉搜索树。
 *
 * 再比如说，给你的二叉树是：
 *
 *     4
 *    / \
 *   2   6
 *      / \
 *     3   8
 *
 * 右子树存在一个节点 3，它不大于根节点 4。因此这不是一棵二叉搜索树。
 */
public class IsValidBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //求二叉搜索数的最小值(最小值位于最左侧的叶子节点)
    TreeNode min(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    //求二叉搜索树的最大值（最大值位于最右侧的叶子节点）
    TreeNode max(TreeNode root) {
        while (root.right != null) root = root.right;
        return root;
    }

    // Time: O(n*log(n)), Space: O(n)
    public boolean isValidBST(TreeNode root) {
        //如果树为空，则返回true
        if (root == null) return true;
        //如果左子树为null或者根节点大于当前左子树的最大值，则说明当前根节点的左子树是有效的
        boolean leftValid = root.left == null || root.val > max(root.left).val;
        //同理，如果右子树为null或者根节点小于右子树的最小值，则说明当前根节点的右子树是有效的
        boolean rightValid = root.right == null || root.val < min(root.right).val;

        //如果左右两侧都是有效的，并且左子树和右子树都是二叉搜索树，则该树就是一个二叉搜索树
        return leftValid && rightValid && isValidBST(root.left) && isValidBST(root.right);
    }

    // Time: O(n), Space: O(n)
    public boolean isValidBSTBound(TreeNode root) {
        //初始调用时，将上下界初始化为null
        return isValidBSTBound(root, null, null);
    }

    /**
     * 自顶向下使用上下界进行约束的方法
     *
     * @param root
     * @param lower 下界节点
     * @param upper 上界节点
     * @return
     */
    boolean isValidBSTBound(TreeNode root, TreeNode lower, TreeNode upper) {
        //如果树为null，则返回true
        if (root == null) return true;

        //如果下界不为null，并且它的值大于等于根节点，则不满足二叉搜索树的条件，返回false
        if (lower != null && lower.val >= root.val) return false;

        //如果上界不为null，并且它的值小于等于根节点，则不满足二叉搜索树的条件，返回false
        if (upper != null && upper.val <= root.val) return false;

        //其他情况，说明当前根节点满足上下界的约束，递归判断左右子树
        //判断左子树时，上界更新为当前的根节点
        //判断右子树时，下界更新为当前的根节点
        return isValidBSTBound(root.left, lower, root) && isValidBSTBound(root.right, root, upper);
    }

}
