package com.dianchao.leetcode51_100;

import java.util.Random;

/**
 * 这个题目说的是，给你一个整数数组表示一副牌，你要写一个随机洗牌函数来返回这个数组的一个排列。并且要保证每次返回的排列都是等概率的。
 * 假设已经给你一个完美的随机数生成器。
 *
 */
public class Shuffle_58 {
    //定义随机数生成器
    private Random rnd = new Random();

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // Time: O(n), Space: O(1)
    public int[] shuffle(int[] nums) {
        //从数组最后的位置向前进行遍历
        for (int i = nums.length - 1; i > 0; --i) {
            //随机取（0-i）下的一个下标
            int j = rnd.nextInt(i+1);
            //交换下标i和下标j的数字
            swap(nums, i, j);
        }

        //遍历结束返回数组
        return nums;
    }
}
