package com.dianchao.leetcode;

import java.util.Arrays;

/**
 * 描述
 * 这个题目说的是，给你一个二维数组 matrix，和一个目标值 target。你要在数组里找到这个目标值，然后返回它的行/列下标。如果找不到，则返回 [-1,-1]。
 *
 * 这个数组的每一行都是从左向右递增，每一列都是从上到下递增。和「二维数组的二分搜索」不同，这道题目并不保证每一行的第一个数都比上一行的最后一个数要大。
 *
 * 比如说，给你的二维数组是：
 *
 * 1, 3, 5
 * 2, 4, 6
 *
 * 给你的目标值是 4。目标值 4 在这个数组中，找到后返回它的下标 [1, 1] 即可。
 *
 * 如果给你的目标值是 100，显然它不在这个二维数组中，你要返回 [-1，-1]。
 */
public class ArraySearch_7 {
    public static void main(String[] args){
        int[][] array = new int[][]{{1, 3, 5}, {2, 4, 6}};
        int[] location = searchIn2DArray(array, 4);
        System.out.println(Arrays.toString(location));
    }

    // Time: O(m+n), Space: O(1)

    /**
     * 思路：分析这个二维数组的特征，随便选择某个位置元素，在列上它下面的元素都比它大，在行上它左边的元素都比它小。
     * 这样我们可以二维数组的右上角元素开始与目标数字比较，如果目标值比它小，则目标值比这一列的所有元素都小，这一列可以排除，
     * 列坐标减1，如果目标比它大，则目标值比这一行的所有元素都大，所以这一行可以排除掉，行坐标加1。
     */
    private static int[] searchIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0)
            return new int[]{-1, -1};

        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (target < matrix[i][j]) --j;
            else if (target > matrix[i][j]) ++i;
            else return new int[]{i, j};
        }
        return new int[]{-1, -1};
    }
}
