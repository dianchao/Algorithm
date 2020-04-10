package com.dianchao.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 这个题目说的是，给你一棵二叉树，要求你从叶子节点到根节点一层一层地对其进行访问，对于每一层的节点，则是从左向右进行访问。将访问的结果以二维数组返回。
 *
 * 这道题目和二叉树层序遍历的唯一区别是，它是从下向上一层一层去访问的。
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *        / \
 *       8  16
 *
 * 它的逆层序遍历结果是：
 *
 * [
 *  [8, 16]，
 *  [2, 4],
 *  [1],
 * ]
 *
 * 思路：
 * 先层序遍历二叉树，然后将结果对调一下即可。
 */
public class TreeLevelOrderTraversalFromBottom_34 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Time: O(n), Space: O(n)
    public List<List<Integer>> levelOrderTraversalFromBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> elem = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode s = q.poll();
                elem.add(s.val);
                if (s.left != null) q.add(s.left);
                if (s.right != null) q.add(s.right);
            }
            result.add(elem);
        }

        //对调层序遍历结果
        for (int i = 0; i < result.size()/2; i++) {
            int j = result.size() - 1 - i;
            List<Integer> tmp = result.get(j);
            result.set(j, result.get(i));
            result.set(i, tmp);
        }
        return result;
    }
}
