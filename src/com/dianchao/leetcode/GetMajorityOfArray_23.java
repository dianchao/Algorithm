package com.dianchao.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个题目说的是，给你一个数组，里面有一个数字出现的次数超过了一半，你要找到这个数字并返回。
 *
 * 比如说，给你的数组是
 *
 * 1, 3, 3, 1, 3, 1, 1
 *
 * 这个数组的长度是 7，这里我们只考虑整数除法，长度 7 除以 2 是 3。数组里面 1 出现了 4 次，超过了一半的数量 3，因此你要返回的就是 1。
 *
 * 思路：
 * 最简单直观的方法：使用HashMap，遍历一次数组，统计每个数字出现的次数，出现次数最多的那个数字就要找的超过一半的数字
 * 1：4
 * 3：3
 * T:O(n) S:O(n)
 * 这中解法在空间复杂度上还可以进一步优化，由于数组中有一个数字超过了一半，那么即使所有数字与他配对一一消除，最后剩下的也肯定是过半的数字。
 * 因此我们可以遍历一次这个数组，记下当前的数字和出现的次数，当遇到相同次数时计数器+1，当遇到不同数字时，计数器-1，表示和这个数字进行消除，
 * 当计数器为0时，说明截止目前，当前记录的数字已经被消除完。于是重新开始记录数字，并且初始化计数器为1。
 * 这种算法也称为摩尔投票算法。
 * T:O(n) S:O(1)
 *
 */
public class GetMajorityOfArray_23 {
    // Time: O(n), Space: O(n)
    public int getMajorityWithHashMap(int[] nums) {
        //定义一个Map,存储数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        //定义出现次数最多的数字以及出现的次数
        int maxNum = 0, maxCount = 0;

        //遍历数字
        for (int num: nums) {
            //以num为key取出出现的次数，如果key不存在，则返回0，+1作为新的次数
            int newCnt = map.getOrDefault(num, 0) + 1;

            //更新这个数字的次数
            map.put(num, newCnt);

            //如果这个数字出现的次数大于最大的次数，则更新最大次数和最大次数对应的数字
            if (newCnt > maxCount) {
                maxCount = newCnt;
                maxNum = num;
            }
        }
        //返回出现次数最多的数字即可
        return maxNum;
    }

    // Time: O(n), Space: O(1)
    public int getMajority(int[] nums) {
        //将数组第一个数字记录起来，并且初始化计数器为1
        int num = nums[0], count = 1;

        //从第2个数字开始遍历数组
        for (int i = 1; i < nums.length; ++i) {
            //如果计数器已经清0，则重新记录当前数字，并且初始化计数器为1
            if (count == 0) {
                num = nums[i];
                count = 1;

            //否则如果当前数字和记录的数字相同，则计数器+1
            } else if (nums[i] == num) {
                ++count;
            //否则当前数字与记录的数字不同，则计数器-1
            } else --count;
        }

        //最后返回记录数字即可
        return num;
    }
}
