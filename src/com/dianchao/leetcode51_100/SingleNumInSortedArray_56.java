package com.dianchao.leetcode51_100;

/**
 * 这个题目说的是，给你一个排好序的整数数组，里面的数字都出现两次，只有一个数字出现了一次，我们管它叫单身数字，你要写代码找到这个单身数字。
 *
 * 比如说给你的有序数组是：
 *
 * 1, 1, 2, 2, 4, 4, 6, 8, 8
 *
 * 这个数组里 6 只出现了一次，因此你要返回的数字就是 6。
 *
 * 思路分析：
 *
 * 非常简洁的方法是将数组中的所有数组进行异或操作，最后结果就是只出现一次的数字，这种解法利用的是相同的数字异或等于0的特点
 *
 * 注意这道题还有一个特点数组是有序的，数组有序相同的数字肯定是相邻的，因此对于数组中的任意一个数字要么等于左边数字，要么等于
 * 右边的数字，否则它就是只出现一次的数字，使用二分搜索的思想解这道题，首先初始化low和high游标，然后求他们中间索引mid，
 * mid落在哪里有三种情况：
 * 1. mid指向的数字等于左边的数字
 * 2. mid指向的数字等于右边的数字
 * 3. mid指向的数字既不等于左边数字，也不等于右边数字，因此mid是单身数字，返回它即可
 * 如果在这一轮中没有找到单身数字，那么mid一定是指向相等数字左边的那个数字，此时通过mid-low计算low到mid之间一共有多少个
 * 数字，
 * mid-low是奇数：high = mid - 1;
 * mid-low是偶数：low = mid + 2；
 */
public class SingleNumInSortedArray_56 {
    // Time: O(n), Space: O(1)
    public int singleNumInSortedArrayWithXOR(int[] nums) {
        int result = 0;
        for (int num: nums) result ^= num;
        return result;
    }

    // Time: O(log(n)), Space: O(1)
    public int singleNumInSortedArrayBinarySearch(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            //计算中间游标
            int mid = low + (high - low)/2;
            //如果mid左边有效，并且左边的数字等于它，则mid减1
            if (mid-1 >= 0 && nums[mid-1] == nums[mid]) {
                --mid;
            //如果mid右边的数字有效，并且右边的数字等于mid，则不做任何操作
            } else if (mid+1 < nums.length && nums[mid+1] == nums[mid]) {
            //否则是单身数字，直接返回
            } else {
                return nums[mid];
            }
            //如果没有返回，并且mid-low是奇数，则更新high为mid-1
            if ((mid-low) % 2 == 1) high = mid - 1;
            //否则，更新low为mid+2
            else low = mid + 2;
        }
        //返回-1，说明该数组不存在单身数字
        return -1;
    }

}
