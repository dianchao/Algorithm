package com.dianchao.leetcode51_100;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 这个题目说的是，给你一个整数数组和一个整数 K，你要找到数组中第 K 大的元素。
 *
 * 比如说，给你的整数数组是：
 *
 * 4, 2, 8, 1, 8
 *
 * 整数 K 是 2。这个数组中第 2 大的元素是 8，因此你要返回 8。
 *
 * 思路：
 * 这个题目最简单直接的办法是把数组从大到下进行排序，然后取下标为k-1的数即可，时间复杂度为n*logN
 *
 * 第二种方式维护一个topK的最小堆，遍历一次数组，把数字入到最小堆中，最后堆顶元素就是第k大的元素，对于例子k=2，
 * 所以我们维护一个Top2的最小堆，当堆中元素小于2时，不断将数组元素放入堆中，当堆达到容量时，对于新来的元素
 * 我们需要判断它的值是否比堆顶元素大，如果是，则将堆顶元素移除，同时加入新数字。
 *
 */
public class findKthLargestInArray_59 {
    // Time: O(n*log(k)), Space: O(k)
    public int findKthLargestMinHeap(int[] nums, int k) {
        //定义最小堆
        Queue<Integer> minHeap = new PriorityQueue<>();

        //遍历数组
        for (int num: nums) {
            //如果最小堆的数量不足k个，则往堆中添加数字即可
            if (minHeap.size() < k) {
                minHeap.add(num);
            //否则说明堆中元素已经有k个，当新来的元素大于堆顶元素时，把堆顶元素移除，然后加入新来的元素
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
        //数组遍历完成后，堆中保存的就是topK元素，堆顶元素就是topK大的元素
        return minHeap.peek();
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    int partition(int[] nums, int low, int high) {
        //定义pivot和左右游标i和j
        int pivot = nums[low], i = low, j = high;

        //当i小于j时
        while (i < j) {
            //nums[j]小于pivot, 则游标j一直向左移动
            while (i < j && nums[j] < pivot) --j;
            //循环结束后，如果i仍然小于j，说明j指向的数字不小于pivot，交换i和j的元素
            if (i < j) swap(nums, i, j);
            //nums[i]大于等于pivot，则游标i一直向右移动
            while (i < j && nums[i] >= pivot) ++i;
            //循环结束后，如果i仍然小于j，交换i和j指向的数字
            if (i < j) swap(nums, i, j);
        }
        //循环结束后，i和j同时指向中间的pivot，返回i即可
        return i;
    }

    // Time(avg): O(n), Time(worst): O(n^2), Space: O(1)
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int low = 0, high = nums.length - 1;

        //当low小于high时
        while (low <= high) {
            //调用partition函数将数组分为大小两个部分
            int p = partition(nums, low, high);
            //如果p正好等于k-1，说明下标p指向的元素正好是第k大的元素，返回它即可。
            if (p == k-1) return nums[p];
            //否则如果p大于k-1，说明第k大的元素在左边，于是更新high游标为p-1
            else if (p > k-1) high = p - 1;
            //否则说明第k大的元素在p右边，于是更新low游标为p+1
            else low = p + 1;
        }
        return -1;
    }
}
