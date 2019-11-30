package com.dianchao.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoNumSumToGivenValue {
    public static void main(String [] args){
        TwoNumSumToGivenValue twoNumSumToGivenValue = new TwoNumSumToGivenValue();
        int [] nums = {1, 2, 3, 6, 8, 11};
        int target = 10;
        int [] result = twoNumSumToGivenValue.getTwoNumSumToGivenValueBruteForce(nums, target);
        System.out.println("暴力破解法：" + Arrays.toString(result));
        result = twoNumSumToGivenValue.getTwoNumSumToGivenValueHashMap(nums, target);
        System.out.println("HashMap方法求解：" + Arrays.toString(result));
    }

    // Time: O(n^2), Space: O(1)
    private int[] getTwoNumSumToGivenValueBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    // Time: O(n), Space: O(n)
    private int[] getTwoNumSumToGivenValueHashMap(int[] nums, int target) {
        //key为数组中的元素  val为数组下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            //每遍历一个元素检查Map中是否存在目标值与当前值的差值，如果存在则返回下标，不存在则将当前遍历的值和下标存放到Map中
            int numNeeded = target - nums[i];
            if (map.containsKey(numNeeded)) {
                return new int[]{map.get(numNeeded), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
