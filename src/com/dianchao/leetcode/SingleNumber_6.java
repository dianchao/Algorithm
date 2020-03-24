package com.dianchao.leetcode;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber_6 {
    private static void main(String[] args) {
        int [] nums = {5, 7, 5, 6, 6};
        System.out.println(singleNumberWithXOR(nums));

        System.out.println(singleNumberWithSet(nums));
    }

    // Time: O(n), Space: O(1)

    /**
     * 思路：
     * 位运算：如果两个二进制位上的值相同异或结果为0，如果二进制位上的值不同异或结果为1
     * 同时位运算操作满足交换律，将集合中所有的元素进行异或操作，最终结果是0与单身数字进行异或操作，0与任何数字异或结果都等于那个数字本身。
     * @param nums
     * @return
     */
    private static int singleNumberWithXOR(int[] nums) {
        int result = 0;
        for (int num : nums) result ^= num;
        return result;
    }

    // Time: O(n), Space: O(n)

    /**
     *
     * 思路：维护一个Set集合，单身数字等于set集合中的元素2倍减去所有元素的和
     */
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
