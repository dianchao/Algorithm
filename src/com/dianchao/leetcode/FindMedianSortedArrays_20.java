package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你两个排好序的整数数组 nums1 和 nums2，假设数组是以递增排序的，数组的大小分别是 m 和 n。你要找到这两个数组的中位数。要求算法的时间复杂度是 O(log(m+n))。
 *
 * 这里两个数组中位数的意思是，两个数组合到一起排序后，位于中间的那个数，如果一共有偶数个，则是位于中间的两个数的平均数。
 *
 * 比如说，给你的两个数组是：
 *
 * 1, 3
 * 2
 *
 * 它们放在一起排序后是：
 *
 * 1, 2, 3
 *
 * 所以中位数就是 2。
 *
 * 再比如说，给你的两个数组是：
 *
 * 1, 3
 * 2, 4
 *
 * 它们放在一起排序后是：
 *
 * 1, 2, 3, 4
 *
 * 所以中位数就是 (2 + 3) / 2 = 2.5。
 *
 * 思路：
 * 如果没有时间复杂度要求，我们只需要将两个有序数组合并成一个大的有序数组，然后根据数组大小是奇数还是偶数，直接计算出中位数即可。
 * 将两个有序数组合并成一个有序数组，只需要定义两个游标分别指向数组num1和num2，然后取两个游标较小的值放到结果数组即可。
 *
 * 注：如果一个算法的时间复杂度要求是log，那么就要求我们每一次操作都要将数据规模减少到原来的一半，比如二分搜索。
 *
 * 满足要求解法：定义一个函数f(k)：表示在2个有序数组中找到第k小的数，k从1开始，这样当两个数组长度之和为奇数时，则返回f(m+n/2+1)即可
 * 如果两个数组长度之和为偶数时，则返回1/2*( f((m+n)/2 + f((m+n)/2+1) )即可。
 *
 * 1    2   6   7   8
 * 3    4   5   7   9
 *
 * k=4 可以从2个数组各取一半，然后比较一半里各自最大的数组， 第一个数组的2小于4，而这两个部分整合有k个数，所以第k小的数要么就是4，要么就在
 * 剩下的6、7、8中，不可能在1、2这部分，因此可以将1、2排除掉，即排除k/2个数
 *
 * 考虑k为奇数的情况，实际是从一个数组取k/2个数，另一个数组取k-k/2个数
 *
 * 每一步排除k/2个数，直到满足以下终止条件：
 * k减至1，那么第1小的数就是两个数组头部元素中较小的那个值
 * 把其中一个数组排除完，那么第k小的数就直接从剩余的那个数组中取出即可。
 */
public class FindMedianSortedArrays_20 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //计算两个数组长度之和
        int total = nums1.length + nums2.length;

        //如果总长度是奇数
        if ((total & 1) == 1) {
            return findKthSmallestInSortedArrays(nums1, nums2, total / 2 + 1);
        } else {
            //如果总长度是偶数
            double a = findKthSmallestInSortedArrays(nums1, nums2, total / 2);
            double b = findKthSmallestInSortedArrays(nums1, nums2, total / 2 + 1);
            return (a + b) / 2;
        }
    }

    // Time: O(log(k)) <= O(log(m+n)), Space: O(1)
    public double findKthSmallestInSortedArrays(int[] nums1, int[] nums2, int k) {
        //定义数组1和数组2长度变量
        int len1 = nums1.length;
        int len2 = nums2.length;

        //定义访问数组1和数组2的偏移量
        int base1 = 0;
        int base2 = 0;

        //
        while (true) {
            //如果len1=0则说明len1中数据都被排除完了，直接返回nums2中剩余元素的第k-1个元素
            if (len1 == 0) return nums2[base2 + k - 1];
            //如果len2=0则说明len2中数据都被排除完了，直接返回num1中剩余元素的第k-1个元素
            if (len2 == 0) return nums1[base1 + k - 1];
            //如果k==1，直接返回num1和num2头部元素较小的即可。
            if (k == 1) return Math.min(nums1[base1], nums2[base2]);

            //由于k/2的值可能大于当前数组长度值，所以k/2和len1要取较小的值
            int i = Math.min(k / 2, len1);
            //同理，在num2中取k-i个数时需要和当前数组长度len2比较，取较小的那个值
            int j = Math.min(k - i, len2);

            //在两个数组中把这两个数取出来
            int a = nums1[base1 + i - 1];
            int b = nums2[base2 + j - 1];

            //如果这两部分数据加起来正好是k并且a=b，即返回a或b即可
            if (i + j == k && a == b) return a;

            //否则如果a小于等于b，则将num1前i个数都排除
            if (a <= b) {
                base1 += i;
                len1 -= i;
                k -= i;
            }
            //否则当a大于等于b，则将num2前j个数都排除
            if (a >= b) {
                base2 += j;
                len2 -= j;
                k -= j;
            }
        }
    }
}
