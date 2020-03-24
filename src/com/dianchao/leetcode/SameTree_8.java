package com.dianchao.leetcode;

import java.util.Stack;

/**
 * 这个题目说的是，给你两个二叉树，你要判断它们是否相同。这里所谓相同，指的是两棵树结构相同，并且相应节点上的数值相等。
 *
 * 比如说，给你的两棵二叉树都是：
 *
 *    1          1
 *   / \        / \
 *  2   4      2   4
 *
 * 它们的结构相同，相应节点上的值也相等，因此返回 true。 如果另一棵树是：
 *
 *    1
 *   / \
 *  2   5
 *
 * 或
 *
 *     1
 *    /
 *   2
 *  /
 * 4
 *
 * 两棵树则不相同，返回 false。
 */
public class SameTree_8 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Time: O(n), Space: O(n)
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        //当两棵树都为空时，返回true
        if (p == null && q == null) return true;
        //如果一个节点为空，一个节点非空，则返回false
        if (p == null || q == null) return false;
        return p.val == q.val &&
                isSameTreeRecursive(p.left, q.left) &&
                isSameTreeRecursive(p.right, q.right);
    }

    // Time: O(n), Space: O(n)
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        //定义一个辅助栈将两棵树的根节点入栈
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);

        //当栈不为空时不断执行以下操作
        while (!stack.empty()) {
            //将栈顶的两个元素出栈
            TreeNode s = stack.pop(), t = stack.pop();
            //当它们都为空时表示当前两棵子树相同，继续比较栈内的其他元素
            if (s == null && t == null) continue;
            //如果一棵子树为空，一棵非空，直接返回false
            if (s == null || t == null) return false;
            //如果没有在以上条件返回说明两个节点都非空，则比较两个节点的值，如果不相等则返回false
            if (s.val != t.val) return false;

            //如果值相同，则将它们的左子树和右子树分别入栈，开始新一轮比较。
            stack.push(s.left);
            stack.push(t.left);
            stack.push(s.right);
            stack.push(t.right);
        }

        //如果循环结束还没有提前返回，说明这两棵树是相同的返回true。
        return true;
    }
}
