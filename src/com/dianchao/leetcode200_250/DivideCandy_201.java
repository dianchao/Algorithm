package com.dianchao.leetcode200_250;

/**
 * 这个题目说的是，n 个小孩站成一条直线，每个小孩都有一个对应的评分。你要根据小孩的评分给他们发糖果，要求每个小孩至少要有一个糖果，并且评分较高的小孩要比相邻的小孩分到更多糖果。你要计算出，至少需要准备多少颗糖果，才能按要求完成分配。
 *
 * 比如说，给你的小孩评分数组是：
 *
 * [3, 3, 1, 6, 4, 3]
 *
 * 你最少要给这 6 个小孩分配的糖果数量是：
 *
 * [1, 2, 1, 3, 2, 1]
 *
 * 按照这种分配方法，就可以达到题目要求。因此最少要准备的糖果数量就是这个数组的和，也就是 10。
 *
 * 思路分析：
 * 约束条件：
 * 1）每个小孩至少要分到一个糖果，假如只有这一个约束条件那我们只要给每个小孩1个糖果即可；
 * 2）在相邻的小孩中，评分高的小孩要分到更多的糖果，这意味着有的小孩分到不止1个糖果，那么在什么情况下小孩会得到不止1个糖果，而什么
 * 情况下，小孩只会得到1个糖果。
 *
 * 根据例子和糖果分配情况，不难发现，位于评分波谷的小孩，我们只要给他一个糖果即可。这个不难理解，对于波谷的评分，左右评分都会比它大，
 * 所以给波谷的评分1个糖果可以满足题目的要求。
 * 位于评分波谷的小孩分到一个糖果后，我们就可以向波谷两边去延伸，评分变大的话，糖果数量就加1，
 *
 * 3, 3, 1, 6, 4, 3
 * 对于第一个波谷1，向左评分是3，比它大，因此分的糖果数量为2，继续向左评分3和它右边的评分相等，停止操作；
 * 对于第一个波谷1，向右评分是6，比它大，因此分的糖果数量为2，继续向右评分4比6小，停止操作；
 * 1, 2, 1, 2, 1, 1
 *
 * 对于第二个波谷3，向左的评分是4，比它大，因此分的糖果数量为2，继续向左评分6比4大，分的糖果是3，再向左评分1不比6大，因此停止操作；
 *
 * 对于评分6，从左边的波谷走来，分的糖果是2，从右边的波谷走来分的糖果是3，为了满足题目要求，因此取二者最大值。
 *
 * 对于第一个评分，题目要求评分高的相邻小孩分的更多的糖果，而3并不大于3所以分配1个糖果即可。
 *
 * 通过上面的例子，我们是通过找到评分中的每一个波谷，然后给波谷位置的小孩一个糖果，接着从波谷出发依次计算两边小孩的糖果数量，
 * 为了避免每次找到波谷后要同时往两个方向处理，我们可以把从左往右递增的斜坡放在一趟中处理，而从右向左递增的斜坡放另一趟中处理。
 *
 * [3, 3, 1, 6, 4, 3]
 *
 * 1, 1, 1, 1, 1, 1  先给每个小孩分配一个糖果，这样位于评分波谷的小孩手里就有了一个糖果，其他位置的小孩给1个糖果可能不对，接下来调整
 * 1, 1, 1, 2, 1, 1  第一趟从左向右遍历，只处理从波谷开始向右递增的斜波
 * 1, 2, 1, 3, 2, 1  第二天从右向左遍历，处理从波谷开始向左递增的斜坡
 * 1, 2, 1, 3, 2, 1
 */
public class DivideCandy_201 {
    // Time: O(n), Space: O(n)
    public int candy(int[] ratings) {
        //边界处理，如果数组为null或数组长度为0，直接返回
        if (ratings == null || ratings.length == 0) return 0;

        //计算数组长度
        int n = ratings.length;
        //定义一个辅助数组来存储分给小孩的糖果数量
        int[] d = new int[n];

        //给0号小孩1个糖果
        d[0] = 1;

        //从1遍历到n-1
        for (int i = 1; i < n; ++i) {
            //如果第1个小孩的评分比i-1小孩的评分高，则分的糖果数量是i-1小孩数量加1，否则就给第i小孩1个糖果
            if (ratings[i] > ratings[i-1])
                d[i] = d[i-1] + 1;
            else
                d[i] = 1;
        }

        //计算波谷左半部分上升斜坡，在遍历过程中同时求sum
        //初始化sum为第n-1个小孩糖果数量，之所以能这么做是因为在第一轮遍历结束后，第n-1肯定就是最后的分配数量
        int sum = d[n-1];

        //从n-2遍历到0，如果第i小孩的评分大于右边小孩的评分，那么第i小孩糖果的候选值就是第i+1小孩的糖果数+1，
        // 然后和上一轮计算第i小孩糖果值进行比较，然后取最大值。
        for (int i = n-2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1])
                d[i] = Math.max(d[i], d[i+1] + 1);

            //确定第i小孩的糖果数后，累加到sum变量上。
            sum += d[i];
        }
        return sum;
    }
}