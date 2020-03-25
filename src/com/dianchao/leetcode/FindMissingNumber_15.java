package com.dianchao.leetcode;

/**
 * 这个题目说的是，从 0 到 n 这 n+1 个整数中去掉一个，然后把剩下的 n 个整数放进一个长度为 n 的数组，给你这个数组，你要找到那个去掉的整数。
 *
 * 比如说，给你的数组是：
 *
 * 4, 1, 0, 2
 *
 * 这里的数组长度是 4，说明这是从 0 到 4 中去掉一个数字后形成的数组。数组中缺失的数字是 3，因此我们要返回 3。
 *
 * 思路：
 * 两个相同的整数异或结果是0，x^x=0， 0与任何数字异或的结果是那个数字本身，0^y=y.因此我们需要将0到n这n+1个整数进行异或
 * 操作，然后再与数组中的元素进行异或操作，结果就是缺失的那个数字。（其他数字都出现了两次异或操作后结果为0）
 */
public class FindMissingNumber_15 {
    // Time: O(n), Space: O(1)
    public int missingNumber(int[] nums) {
        int n = nums.length, result = n;
        for (int i = 0; i < n; ++i) result = result ^ i ^ nums[i];
        return result;
    }
}
