package com.dianchao.leetcode;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    private static void main(String[] args) {
        int [] nums = {5, 7, 5, 6, 6};
        System.out.println(singleNumberWithXOR(nums));

        System.out.println(singleNumberWithSet(nums));
    }

    // Time: O(n), Space: O(1)
    private static int singleNumberWithXOR(int[] nums) {
        int result = 0;
        for (int num : nums) result ^= num;
        return result;
    }

    // Time: O(n), Space: O(n)
    private static int singleNumberWithSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0, uniqueSum = 0;
        for (int num : nums) {
            if (!set.contains(num)) {
                uniqueSum += num;
                set.add(num);
            }
            sum += num;
        }
        return 2 * uniqueSum - sum;
    }
}
