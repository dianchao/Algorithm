package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个字符串，你要把它转成一个 int 类型的数字。这个字符串里可能会包含空格，字母或是其它字符。
 *
 * 一个可以有效地转成数字的字符串包含以下特点：
 *
 * 1. 可以有前导空格或前导 0，但不能有其它前导字符
 * 2. 可能会有一个加号或减号表示正负，也可能没有，连续的多个加号或减号则视为不合法
 * 3. 紧接着是一段连续的数字，如果没有数字则示为不合法
 * 4. 数字后的其它字符都可以忽略
 * 5. 如果数字大于 int 的最大值或小于 int 的最小值，返回相应的极值即可
 * 6. 字符串如果不能合法地转为整数，则返回 0
 */
public class String2Integer_42 {
    public int string2Integer(String str) {
        //start表示遍历字符串的开始下标，初始化为0
        //p表示遍历字符串的游标
        //n表示字符串的长度
        int start = 0, p = 0, n = str.length();

        //表示这个数字是否为负数
        boolean negative = false;

        //去除字符串的前导空格
        while (p < n && str.charAt(p) == ' ') ++p;

        //循环结束后如果p等于n则表示这个字符串是个空串或者全部为空格
        if (p == n) return 0;


        if (str.charAt(p) == '+') {
            //如果字符是+，则p向后移动
            ++p;
        } else if (str.charAt(p) == '-') {
            //如果字符是-，则p向后移动，同时表示它是负数
            ++p;
            negative = true;
        }

        //数字部分，由于数字前面可能有多个前导0，所以将前导0忽略
        while (p < n && str.charAt(p) == '0') ++p;

        //将第一个非0数字的字符的下标赋值给start
        start = p;

        while (p < n && str.charAt(p) >= '0' && str.charAt(p) <= '9') ++p;

        //如果p仍然等于start说明数字部分一个数字都不包含，说明不能转为合法整数，直接返回0
        if (p == start) return 0;

        //超出整数范围
        if (p - start > 10) {
            if (negative) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }

        //将字符串的数字部分先转为long型整数
        long num = 0;
        for (int i = start; i < p; ++i)
            num = num * 10 + (str.charAt(i) - '0');

        //如果是负数，转换为负数，否则整数
        num = negative ? -num : num;

        //如果小于int的最小值或大于int最大值，则返回极值，否则返回实际值
        if (num < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        else if (num > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else return (int)num;
    }
}
