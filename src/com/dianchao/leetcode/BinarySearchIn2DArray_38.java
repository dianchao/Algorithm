package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个二维数组 matrix，和一个目标值 target。你要在数组里找到这个目标值，然后返回它的行/列下标。如果找不到，则返回 [-1,-1]。
 *
 * 这个数组的每一行都是递增的，并且每一行的第一个数都比上一行的最后一个数要大。也就是，这个数组可以看成，从左到右、从上到下，呈 Z 字形递增。
 *
 * 比如说，给你的二维数组是：
 *
 * 1, 3, 5
 * 7, 9, 11
 *
 * 给你的目标值是 9。9 在这个数组中，找到后返回它的下标 [1, 1] 即可。
 *
 * 如果给你的目标值是 100。显然它不在这个二维数组中，你要返回 [-1，-1]。
 *
 * 思路：把这个二维数组当做一维数组，从左到右从上到下读即可，假设我们搜索的数字是9，在1,3,5,7,9,11中查找目标值9.
 * 假设游标low和high分别指向头尾两个数，初始化为0和5，mid表示它们中间的游标，是它们和的一半，初始化为2，这样我们需要将数组2
 * 转化为行，列坐标，行坐标用r = min/n, 列坐标用 c = min%n
 *
 */
public class BinarySearchIn2DArray_38 {
    // Time: O(log(m*n)), Space: O(1)
    public int[] binarySearchIn2DArray(int[][] matrix, int target) {

        //边界处理
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0)
            return new int[]{-1, -1};

        //计算二维数组行和列数
        int m = matrix.length, n = matrix[0].length;

        //初始化游标
        int low = 0, high = m * n - 1;

        //如果low小于等于high
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int r = mid / n, c = mid % n;
            if (target < matrix[r][c]) high = mid - 1;
            else if (target > matrix[r][c]) low = mid + 1;
            else return new int[]{r, c};
        }
        return new int[]{-1, -1};
    }

}
