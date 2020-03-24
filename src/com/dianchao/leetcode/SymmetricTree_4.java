package com.dianchao.leetcode;

import java.util.Stack;

/**
 * 这个题目说的是，给你一个二叉树，你要判断它是否沿中轴线对称。
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     2
 *  / \   / \
 * 4   8 8   4
 *
 * 这棵二叉树是沿中轴线对称的，因此要返回 true。如果我去掉最后这个 4：
 *
 *      1
 *    /   \
 *   2     2
 *  / \   /
 * 4   8 8
 *
 * 就不对称了，这时就要返回 false。
 */
public class SymmetricTree_4 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean isSymmetric(TreeNode s, TreeNode t) {
        if (null != s && null != t) {
            return s.val == t.val && isSymmetric(t.left, s.right) && isSymmetric(s.right, t.left);
        } else {
            return null == s && null == t;
        }
    }

    // Time: O(n), Space: O(n)
    public boolean isSymmetricTreeRecursive(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    // Time: O(n), Space: O(n)
    public boolean isSymmetricTreeIterative(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.empty()) {
            TreeNode s = stack.pop(), t = stack.pop();
            if (s == null && t == null) continue;
            if (s == null || t == null) return false;
            if (s.val != t.val) return false;

            stack.push(s.left);
            stack.push(t.right);
            stack.push(s.right);
            stack.push(t.left);
        }

        return true;
    }
}
