package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个非空整数数组，你要找到和最大的连续子序列，然后返回它的和。
 *
 * 比如说，给你的数组 a 是：
 *
 * 2, -8, 3, -2, 4, -10
 *
 * 和最大的连续子序列是 3, -2, 4,  他们的和是 5。
 *
 * 思路：
 * 解法1：最简单方法，暴力破解，两层for循环，穷举所有子序列，求和进行对比，最后取最大的值，Time: O(n^3)
 *
 * 预先取出第i个数之和S(i), 这样第i个数到第j个数之和P(i,j) = S(j)-S(i-1),子序列求和需要时间复杂度为O(1)
 *
 * 解法2：从数组第一个数开始累加子序列和，将当前连续子序列的和记为cur，如果cur小于等于0，那么就从当前元素开始，重新开始一个子序列，当前和
 * 就更新为当前元素值，否则子序列就继续往下相加，每次子序列的和更新后都要和最大值对比，以此更新最大值
 *
 * cur <= 0: cur = a(i)
 * else: cur = cur + a(i)
 * maxSum = Math.max(maxSum, cur)
 *
 */
public class MaxSumOfSubArray_21 {
    // Time: O(n), Space: O(1)
    public int maxSumOfSubArray(int[] nums) {
        //初始化子序列当前最大值
        int max = Integer.MIN_VALUE;
        //初始化子序列当前累加和为0
        int cur = 0;

        for (int i = 0; i < nums.length; ++i) {
            //如果子序列当前累加和小于等于0，则用当前元素值num[i]更新它，表示开始一个新的子序列，否则继续累加当前元素的值
            cur = cur <= 0 ? nums[i] : (cur + nums[i]);

            //更新当前子序列的最大值
            max = Math.max(max, cur);
        }

        return max;
    }
}
