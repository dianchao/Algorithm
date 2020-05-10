package com.dianchao.leetcode51_100;

import java.util.*;

/**
 * 这个题目说的是，给你一个整数数组，你要找到数组中三个数相加等于 0 的所有可能组合。返回的答案里，每个组合都是唯一的，不能重复。
 *
 * 比如说，给你的数组是：
 *
 * -1, 0, -1, 0, 1, 0, -4
 *
 * 这个数组中有两个组合，使得三个数相加等于 0。第一个组合是：
 *
 * -1, 0, 1
 *
 * 虽然数组里有 3 个 0，两个 -1，但不管用哪个 0 或 -1，组合 -1,0,1 都只算一个。另一个有效的组合是：
 *
 * 0, 0, 0
 *
 * 思路：
 * 最简单方式暴力法：3层for循环取出任意3个数字组合，求和判断结果是否为0，如果为0则判断这个集合是否已经加入到结果集合中，
 * 如果没有，则加入结果集合中
 *
 * 优化方法：首先需要对数组进行排序，需要花费O(nlogn)的时间，例子中数组排序后为：-4，-1，-1，0，0，0，1
 * 固定三个数字中最大的那个数字，假设游标为k，开始k指向最后一个数字，然后使用游标i和j在k左侧的子数组中从两头向中间移动，
 * 即i指向-4，j指向1，i和j指向的两个数之和为-4，小于目标值-1，由于数组元素是递增的，所以游标i向右移动增加两数之和，
 * 于是找到满足条件的三元组（-1 0 1），由于返回的答案中不能有重复的三元组，于是i和j要跳过相等的数字...当i>=j时，这一轮
 * 查找结束。
 *
 */
public class ThreeNumSumToZero_53 {
    /**
     *
     * @param nums：输入是一个数组
     * @return：输出是满足条件的三元组
     */
    // Time: O(n^3), Space: O(n)
    public List<List<Integer>> threeNumSumToZeroOn3(int[] nums) {
        //定义返回结果result
        List<List<Integer>> result = new ArrayList<>();

        //定义集合set来去重三元组
        Set<List<Integer>> set = new HashSet<>();

        //数组从小到大进行排序
        Arrays.sort(nums);

        //三层for循环
        for (int i = 0; i < nums.length; ++i)
            for (int j = i+1; j < nums.length; ++j)
                for (int k = j+1; k < nums.length; ++k)
                    //如果相加等于0，则将它们组成一个三元组
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> elem = Arrays.asList(nums[i], nums[j], nums[k]);
                        //检查Set集合中是否包含这个三元组，如果包含则进行下次循环，否则将三元组加入到集合中
                        if (set.contains(elem)) continue;
                        set.add(elem);
                        result.add(elem);
                    }

        return result;
    }

    // Time: O(n^2), Space: O(1)
    public List<List<Integer>> threeNumSumToZeroOn2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        //k从数组尾部开始遍历
        for (int k = nums.length-1; k >= 2; --k) {
            //如果k指向的数字已经小于0，则跳出循环
            if (nums[k] < 0) break;

            //定义要找的数字之和target
            int target = -nums[k];
            //游标i从0开始
            int i = 0;
            //游标j从k-1开始
            int j = k-1;

            //当i小于j时，进行如下操作
            while (i < j) {
                //如果i和j指向的数字之和等于目标值，则将i、j、k指向的数字加入到结果集中
                if (nums[i] + nums[j] == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    //如果i+1和i指向的数字相等，则向右移动游标i
                    while (i < j && nums[i+1] == nums[i]) ++i;
                    //如果j-1和j指向的数字相等，则向左移动游标j
                    while (i < j && nums[j-1] == nums[j]) --j;
                    //重要：最后i和j分别指向最后相等的数字，需要分别再向右和向左移动一位
                    ++i; --j;
                //如果i和j指向两个数字之和小于目标值，则移动游标i
                } else if (nums[i] + nums[j] < target) {
                    ++i;
                //如果i和j指向的两个数字之和大于目标值，则移动游标j
                } else {
                    --j;
                }
            }

            //i、j移动结束后，同样对k也要跳过相同的数字。
            while (k >= 2 && nums[k-1] == nums[k]) --k;
        }

        return result;
    }

}
