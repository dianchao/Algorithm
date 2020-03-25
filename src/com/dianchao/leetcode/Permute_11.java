package com.dianchao.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这个题目说的是，给你一个整数数组，并且数组中没有重复元素，你要返回这个数组所有可能的排列。
 *
 * 比如说给你的数组是：
 *
 * 0, 1, 2
 *
 * 你要返回的所有排列是：
 *
 * 0, 1, 2
 * 0, 2, 1
 * 1, 0, 2
 * 1, 2, 0
 * 2, 0, 1
 * 2, 1, 0
 *
 * 思路：观察所有的排列发现，012的排列等价于第一个位置固定为0，1和2子数组的全排列；第一个位置固定为1，0和2子数组的全排列；
 * 第一个位置固定为2，1和0子数组的全排列。这样只需要将数组的所有元素都和第一个元素进行交换，然后求从第二元素开始的子数组
 * 全排列即可，求子数组的全排列可以递归调用自己。n个元素一共有n！种排列方式，如果使用递归的方式则需要递归n次得到一种排列，
 * 因此时间复杂度为Time: O(n*n!), 空间复杂度为Space: O(n)
 *
 */
public class Permute_11 {
    public List<List<Integer>> permute(int[] nums) {
        //如果数组为空，则返回空的list
        if (nums == null || nums.length == 0) return new ArrayList<>();

        //初始化返回结果
        List<List<Integer>> result = new ArrayList<>();

        //方便操作将数组转换为list
        List<Integer> list = new ArrayList<>();
        for (int num: nums) list.add(num);

        permuteRec(list, 0, result);
        return result;
    }

    //定义一个递归函数，输入为当前的排列nums，开始下标start 和 保存排列结果的result
    private void permuteRec(List<Integer> nums, int start, List<List<Integer>> result){
        //如果start等于nums数组长度时说明start后面已经没有进行全排列的子数组，将当前的结果加入到result结果中
        if (start == nums.size()) {
            result.add(new ArrayList<>(nums));
        } else {
            //否则遍历从start开始的子数组，把子数组的元素依次和start位置的元素进行交换，相当于固定不同的元素到子数组的第一个位置
            for (int i = start; i < nums.size(); ++i) {
                Collections.swap(nums, i, start);
                //递归求从start+1开始的子数组全排列
                permuteRec(nums, start + 1, result);
                //每一次递归结束后需要将前面交换的元素交换回来，这样才可以进行for循环中的新一轮交换
                Collections.swap(nums, i, start);
            }
        }
    }
}
