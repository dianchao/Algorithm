package com.dianchao.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这个题目说的是，给你一个整数数组，并且数组中没有重复元素，你要返回这个数组所有可能的排列。
 */
public class AllOrder {
    /**
     * 递归函数
     *
     * @param nums
     * @param start：用于划分开始排列的元素和进行全排列的子数组
     * @param result: 保存排列结果
     */
    void permuteRec(List<Integer> nums, int start, List<List<Integer>> result) {
        //说明已经没有需要进行全排列的子数组
        if (start == nums.size()) {
            //将当前的排列加入到排列结果集中
            result.add(new ArrayList<>(nums));
        } else {
            //从start开始的子数组
            for (int i = start; i < nums.size(); ++i) {
                //把子数组的元素依次和start位置的元素进行交换，相当于固定不同元素到子数组的第一个位置
                Collections.swap(nums, i, start);
                //递归求从start+1开始的子数组的全排列
                permuteRec(nums, start + 1, result);
                //每一次递归结束后都需要将之前交换的元素交换回来，这样才可以进行进行for循环中新一轮元素交换
                Collections.swap(nums, i, start);
            }
        }
    }

    // Time: O(n*n!), Space: O(n)
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        //初始化要返回的结果
        List<List<Integer>> result = new ArrayList<>();

        //为了方便操作将数组转换为list
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        permuteRec(list, 0, result);
        return result;
    }

}
