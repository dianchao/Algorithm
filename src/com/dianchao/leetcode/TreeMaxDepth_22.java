package com.dianchao.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这个题目说的是，给你一棵二叉树，你要找到从根节点到最远叶子节点的深度。
 *
 * 比如说，给你的二叉树是
 *
 *     1
 *   /   \
 *  2     4
 *       / \
 *      8  16
 *
 * 这棵树有 3 个叶子节点，分别是 2，8，16。最远的叶子节点是 8 和 16，根节点到 8 或 16 都有 3 个节点，因此最大深度是 3。
 *
 * 思路：
 * 递归解法：如果当前节点为null，则返回深度为0即可，如果当前节点非空，则递归求左右子树的最大深度，返回左右子树中深度较大的值+1即可。
 * null: 0
 * 非空：max(f(left), f(right))+1
 *
 * 迭代解法：层次遍历这棵树，遍历完每一层之后，深度+1，遍历完成后得到的深度就是这棵树的最大深度。需要一个辅助队列，先初始化最大深度
 * 为0，根节点入队。然后当队列非空时，将队列中的元素出队，在做这一步之前我们需要先求出当前队列中的元素size，以此限定每次只取当前层
 * 级中的节点，不取后面才入队的子树节点
 */
public class TreeMaxDepth_22 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Time: O(n), Space: (n)
    public int maxDepth(TreeNode root) {
        //递归的终止条件：如果当前根节点为null，则返回深度为0
        if (root == null) return 0;
        //否则递归求左右子树的最大深度，然后求两个深度值较大的那个值，加1即可。
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // Time: O(n), Space: O(n)
    public int maxDepthIterative(TreeNode root) {
        //如果根节点为空，直接返回深度为0
        if (root == null) return 0;

        //定义一个辅助队列
        Queue<TreeNode> q = new LinkedList<>();

        //首先将根节点入队
        q.add(root);
        //初始化树深度为0
        int depth = 0;


        //队列不为空时，循环以下操作
        while (!q.isEmpty()) {
            //取出队列的大小，这表示当前层级中节点的数量
            int size = q.size();

            //依次取出当前层级的节点
            for (int i = 0; i < size; ++i) {
                //取出队首元素
                TreeNode s = q.poll();
                //如果左子树不为空，则将左子树入队
                if (s.left != null) q.add(s.left);
                //如果右子树不为空，则将右子树入队
                if (s.right != null) q.add(s.right);
            }

            //for循环结束后，说明这一层级的节点已经访问完，深度+1
            ++depth;
        }

        return depth;
    }

}
