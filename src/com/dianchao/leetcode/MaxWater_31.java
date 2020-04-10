package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个非负整数数组，数组中的数字表示高度值，在平面坐标画出来后，连同 X 轴一起，会形成许多的凹槽。
 * 你要找到两个高度值，使其形成的凹槽所能容纳的水最多。最后返回容纳的水量。
 *
 * 思路：
 * i<j
 * s = min(h(i),h(j)) * (j-i)
 *
 * 解法1：简单直接，两层for循环，把i和j依次取出来，然后计算s值，取最大值即可
 *
 * 解法2：i和j分别从数组两头开始，此时长度最大，当
 * h(i)<h(j): i = i+1;
 * else: j = j - 1; 计算s的值取最大值
 */
public class MaxWater_31 {
    // Time: O(n), Space: O(1)
    public int maxWater(int[] height) {
        //初始化值为0
        int max = 0;

        //定义游标i,j分别指向数组第一个元素和最后一个元素
        int i = 0, j = height.length - 1;

        //当i小于j时不断循环以下操作
        while (i < j) {
            //计算当前可容纳的水量
            int cur = Math.min(height[i], height[j]) * (j - i);
            //当前容纳的水量和最大水量进行对比，取较大值
            max = Math.max(max, cur);

            //如果i指向的高度值较小，则i向右移动一位；否则j向左移动一位
            if (height[i] < height[j]){
                i++;
            } else{
                j--;
            }
        }

        return max;
    }
}
