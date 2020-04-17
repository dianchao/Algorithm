package com.dianchao.leetcode200_250;

import java.util.Arrays;

/**
 * 这个题目说的是，给你一个数组，数组中的元素都是非负整数。你要重新排列这些整数，使得它们拼接后的数字最大，并返回这个数字。由于拼接后的数字可能非常大，因此结果以字符串的形式返回。
 *
 * 比如说，给你的非负整数数组是：
 *
 * 12, 8
 *
 * 数组中有两个元素，可以拼接成 "128" 或是 "812"。因此，拼接成的最大数字就是 "812"。注意，结果是一个字符串。
 *
 * 再比如说，给你的数组是：
 *
 * 3, 31, 36, 4, 8
 *
 * 这个数组可以拼接成的最大数字是 "8436331"。
 *
 * 思路：
 * cmp(s1, s2)
 * s12 = s1 + s2
 * s21 = s2 + s1
 *
 * s12 > s21 => s1,s2
 * else      => s2,s1
 *
 * 12   8
 * 128   812
 */
public class LargestNumber_203 {

    // Time: O(n*log(n)), Space: O(n)
    public String largestNumber(int[] nums) {
        //处理边界：如果数组长度为空，或者长度为0则返回空字符串
        if (nums == null || nums.length == 0) return "";

        //将整数转换为字符串
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            strs[i] = String.valueOf(nums[i]);
        }

        //对字符串数组进行排序
        //如果字符串o12大于o21，那么我们希望o1排在o2前面。而在compare函数中，对比o1和o2并且希望o1排在o2前面，则需要返回
        //一个负数。这个时候，我们知道o12大于o21，为了返回一个负数，我们让较小的o21去比较较大的o12即可
        Arrays.sort(strs, (o1, o2) -> {
            String o12 = o1 + o2;
            String o21 = o2 + o1;
            return o21.compareTo(o12);
        });

        //检查排序后第0个字符串是否是0，如果是0直接返回字符串0
        if (strs[0].equals("0")) return "0";

        //定义一个StringBuilder
        StringBuilder sb = new StringBuilder();
        for (String str: strs) sb.append(str);
        return sb.toString();
    }

    //上面实现每次对比o1和o2时，都需要额外去生成2个字符串o12和o21然后再进行对比，事实上o12和o21的对比，就是先拿o1中的字符和o2中
    //的字符进行对比，长度不足时，再拿后半部分的o2或者o1的字符进行对比，因此实际上我们并不需要生成o12和o21字符串
    // Time: O(n*log(n)), Space: O(n)
    public String largestNumberFast(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (o1, o2) -> {
            int n1 = o1.length(), n2 = o2.length();

            for (int i = 0; i < n1+n2; ++i) {
                //如果下标i小于n1，那么o12的当前字符在o1上第i个位置，否则在o2上的第i-n1的位置
                char c1 = i < n1 ? o1.charAt(i) : o2.charAt(i-n1);
                char c2 = i < n2 ? o2.charAt(i) : o1.charAt(i-n2);
                if (c1 == c2) continue;
                return c2 - c1;
            }
            return 0;
        });
        if (strs[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for (String str: strs) sb.append(str);
        return sb.toString();
    }

}
