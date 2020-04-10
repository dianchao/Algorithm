package com.dianchao.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 这个题目说的是，给你一棵二叉树，要求你从根节点到叶子节点一层一层地对其进行访问，对于每一层的节点，则是从左向右进行访问。将访问的结果以二维数组返回。
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *        / \
 *       8  16
 *
 * 它的层序遍历结果是：
 *
 * [
 *  [1],
 *  [2, 4],
 *  [8, 16]
 * ]
 *
 * 思路：
 * 借助辅助队列达到层序遍历的目的，对于上面的例子，我们先将根节点1入队，当队列不为空是，先取出当前队列的元素个数，以此作为限定在
 * 这轮访问中只访问当前层级的节点，不访问在此过程中入队的子树层级节点。对于上面例子，当前队列中元素个数为1，于是只访问队列中的1
 * 个节点，同时将它的左右非空子树入队，即将2和4入队，当前队列不为空，队列中元素个数为2，因此这轮访问两个元素，依次出队，每次出队
 * 的元素判断左右子树是否为空，非空则入队，即2出队，左右子树为空不入队，4出队，左右子树都非空，将8,16入队，......继续同样操作，
 * 直到队列为空
 *
 */
public class TreeLevelOrderTraversal_33 {

    //定义二叉数节点
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Time: O(n), Space: O(n)
    public List<List<Integer>> levelOrderTraversal(TreeNode root) {
        //边界：如果树为空则返回空的list
        if (root == null) return new ArrayList<>();

        //定义层序遍历返回的结果
        List<List<Integer>> result = new ArrayList<>();

        //定义一个辅助队列，同时将根节点入队
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        //当队列不为空时，重复以下操作：
        while (!q.isEmpty()) {
            //定义一个list，保存当前层级访问到的元素
            List<Integer> elem = new ArrayList<>();

            //取出队列当前大小，表示当前层级中节点的个数
            int size = q.size();

            //for循环访问节点
            for (int i = 0; i < size; ++i) {
                //节点出队
                TreeNode s = q.poll();
                elem.add(s.val);
                //如果左子树不为空，将左子树入队
                if (s.left != null) q.add(s.left);
                //如果右子树不为空，则将右子树入队
                if (s.right != null) q.add(s.right);
            }
            //for循环结束后，表示这一层级节点访问完，将这一层级访问的节点list，加入到结果list中
            result.add(elem);
        }

        return result;
    }
}
