package com.dianchao.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个题目说的是，给你 n 对括号，你要返回这 n 对括号的所有合法排列方式。
 *
 * 比如说，n 等于 3 时，合法的排列有 5 个：
 *
 *  ((()))
 *  (()())
 *  (())()
 *  ()(())
 *  ()()()
 *
 *  思路：
 *  关键点：
 *  1）任何时候写下左括号都是合法的
 *  2）只有已经写下的左括号比右括号多时，写下右括号才是合法的。
 *
 *  递归：
 *  使用left和right表示手里的左右括号数量,str表示添加括号的字符串
 *  left>0(只要手里有左括号就可以一直添加左括号)：f(str + '(', left-1, right)
 *  right > left：f(str + ')', left, right-1)
 *  left==0, right==0：产生一个合法的括号排列str
 *
 *  动态规划：
 *  d(i)表示i对括号可以组成的所有合法排列，接下来我们看如何根据已经求解出来的子状态来得到d(i),我们知道d(i)比d(i-1)多使用了
 *  一对括号，那么是不是我们在所有d(i-1)的排列上插入这一对括号就ok了呢？
 *  这样做有两个问题：1）合法的插入位置不好找 2）是会产生好多重复的排列
 *  换种思路把i-1对括号分成两部分，加入第1部分有j对括号，那么第2部分就有i-j-1对括号，d(j)表示j对括号的所有合法排列，我们从
 *  d(j)中拿出一种合法排列left，从d(i-j-1)中拿出一种合法排列right，则left+right一定是一个合法排列，并且使用的括号对数是
 *  i-1，这时候在left排列左右加上一对括号即"("+left+")"+right，此时仍然是合法括号排列，并且使用了i对括号，我们只需要将j
 *  从0遍历到i-1（j->0-i-1），这个组合就会穷举完i对括号可以组成的所有合法排列。
 *  0<=i-j-1<=i-1 ==> 0<=j<=i-1
 *
 *  d(0) : ""
 *  d(1): ("") ""  => ()
 *  d(2): ""()  ()"" => ("")() (())"" => ()()  (())
 *
 */
public class IsRightParentheses_29 {
    /**
     * 递归辅助函数
     *
     * @param result：保存合法排列的结果
     * @param str：保存当前排列的str
     * @param left：剩余可用的左括号的数量
     * @param right：剩余可用的右括号的数量
     */
    private void generate(List<String> result, String str, int left, int right) {
        //当剩余的左右括号都为0时，表示产生了一个合法排列，将它加入到结果列表中
        if (left == 0 && right == 0) {
            result.add(str);
        } else {
            //如果还有剩余的左括号，则添加左括号，同时left减1（任何时候写下左括号都是合法的）
            if (left > 0) generate(result, str + '(', left - 1, right);
            //如果剩余的右括号比左括号多，则可以添加右括号，同时right-1 （只有已经写下的左括号比右括号多时，写下右括号才是合法的。）
            if (right > left) generate(result, str + ')', left, right - 1);
        }
    }

    // Time: O(4^n / sqrt(n)), Space: O(n)
    public List<String> generateParentheses(int n) {
        //当n小于等于0时返回一个空的list
        if (n <= 0) return new ArrayList<>();

        //定义一个list集合保存结果
        List<String> result = new ArrayList<>();
        generate(result, "", n, n);
        return result;
    }

    // Time: O(4^n / n*sqrt(n)), Space: O(4^n / n*sqrt(n))
    public List<String> generateParenthesesDP(int n) {
        //当n小于等于0时，返回一个空的list
        if (n <= 0) return new ArrayList<>();

        List<List<String>> d = new ArrayList<>(n+1);

        //初始list
        for (int i = 0; i < n+1; ++i) {
            d.add(new ArrayList<>());
        }
        //d(0)初始化为一个空串
        d.get(0).add("");

        //两层for循环遍历i和j
        //i的取值范围为[1, n]
        for (int i = 1; i < n+1; ++i){
            for (int j = 0; j < i; ++j){
                //从d(j)和d(i-j-1)中分别取出一个合法排列left和right
                for (String left: d.get(j)){
                    for (String right: d.get(i-j-1)){
                        //在left排列左右拼上左右括号，再拼上right，得到新的排列然后加入到d(i)中
                        d.get(i).add('(' + left + ')' + right);
                    }
                }
            }
        }

        return d.get(n);
    }
}
