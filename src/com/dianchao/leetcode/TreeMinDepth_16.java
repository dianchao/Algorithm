package com.dianchao.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这个题目说的是，给你一棵二叉树，你要找到从根节点到最近的叶子节点的深度。
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *        / \
 *       8  16
 *
 * 这棵树有 3 个叶子节点，分别是 2，8，16。最近的叶子节点是 2，根节点到 2 共有两个节点，因此最小深度是 2。
 *
 * 再比如说，给你的二叉树是：
 *
 *   1
 *    \
 *     2
 *      \
 *       4
 *
 * 这棵树唯一的叶子节点是 4，根节点到它共有 3 个节点，因此最小深度是 3。
 *
 * 思路：
 * 递归求解：
 * 如果当前节点为null，返回深度为0即可
 * 如果当前节点非null，判断当前节点的左右子树情况，共有四种组合：
 *   1）left=null right=null：说明当前节点是叶子节点，返回深度为1（否则说明左右子树至少有一个非空）
 *   2）left=null right！=null：说明右子树非空，递归调用右子树最小深度然后加1：1+f(right)
 *   3）left！=null right=null：说明左子树非空，递归调用左子树最小深度然后加1：1+f(left)
 *   4）left!=null right!=null：说明左右子树都非空，递归调用左右子树最小深度，min(f(left), f(right)) + 1
 *
 * 非递归求解：只要找到距离根节点最近的叶子节点，就知道了树的最小深度。层级遍历
 * 如果使用层级遍历这棵树则访问的第一个叶子节点就是距离根节点最近的叶子节点，层级遍历一棵树需要辅助队列。
 * 首先初始化最小深度为1，然后将根节点入队，作为树的第一层
 * 当队列中的元素不为空时，将队列中的元素出队，在做这一步之前我们需要先取出当前队列元素个数size，以此限定每次只取当前
 * 层级的节点，不取后面才入队的节点。第一层的节点(1)出队之后深度+1；第一层节点出队后将左右非空的子节点(2、4)入队，
 * 第二层级的节点只有两个所以这次只从队列中出队两个节点
 */
public class TreeMinDepth_16 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Time: O(n), Space: (n)
    public int minDepth(TreeNode root) {
        //递归的终止条件：
        //如果当前根节点为null，则返回深度为0
        if (root == null) return 0;
        //如果当前根节点的左右子树为空，则说明它是叶子节点，返回深度为1
        if (root.left == null && root.right == null) return 1;
        //如果左子树为null，则递归求右子树的深度，然后加1
        if (root.left == null) return minDepth(root.right) + 1;
        //如果右子树为null，则递归求左子树的深度，然后加1
        if (root.right == null) return minDepth(root.left) + 1;
        //否则，左右子树都非空，则分别求左右子树的最小深度，取较小的值然后加1
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    // Time: O(n), Space: O(n)
    public int minDepthIterative(TreeNode root) {
        //如果根节点为null直接返回深度为0
        if (root == null) return 0;

        //定义一个辅助队列，并将根节点入队
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        //初始化深度为1
        int depth = 1;

        //当队列不为空时，循环一下操作
        while (!q.isEmpty()) {
            //获取队列大小，表示当前层级中节点的数量
            int size = q.size();
            //循环取出元素
            for (int i = 0; i < size; ++i) {
                //将队头元素出队
                TreeNode s = q.poll();
                //如果它的左右节点都为null，则表示它是一个叶子节点，直接返回当前累计的深度
                if (s.left == null && s.right == null) return depth;

                //如果左子树不为null，则将左子树入队
                if (s.left != null) q.add(s.left);
                //同样，如果右子树不为null，则将右子树入队
                if (s.right != null) q.add(s.right);
            }
            //for循环结束后说明这一层级的所有节点都已经访问完，深度加1.
            ++depth;
        }

        return -1;
    }
}
