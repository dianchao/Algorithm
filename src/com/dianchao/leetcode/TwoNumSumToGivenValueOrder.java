package com.dianchao.leetcode;

import java.util.Arrays;

public class TwoNumSumToGivenValueOrder {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 8, 11};
        int target = 10;
        int[] result = getTwoNumSumToGivenValue(arr, target);
        System.out.println(Arrays.toString(result));
    }

    // Time: O(n), Space: O(1)
    private static int[] getTwoNumSumToGivenValue(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) --j;
            else if (numbers[i] + numbers[j] < target) ++i;
            else return new int[]{i + 1, j + 1};
        }
        return new int[]{-1, -1};
    }
}
