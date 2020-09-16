package com.dianchao.leetcode200_250;

/**
 * 描述
 * 这个题目说的是，给你 3 个字符串 s1, s2 和 s3，你要判断 s1 和 s2 是否可以交错合并形成 s3。
 *
 * 比如说，给你的 s1, s2, s3 分别是：
 *
 * s1 = abd
 * s2 = bcca
 * s3 = abcbcad
 *
 * s3 整个字符串，是由 s1 和 s2 上的所有字符，按从左到右的顺序交错合并而成。因此，对于这个例子我们要返回 true。
 *
 * 如果把 s3 首尾两个字符对调一下：
 *
 * s3 = dbcbcaa
 *
 * 那么 s3 就无法由 s1 和 s2 交错合并形成，返回 false
 *
 * 思路：
 * 这个题目判断字符串s3是否是由s1和s2交错合并而成，首先我们可以做一个最简单的初步判断，看字符串s1的长度n1和字符串
 * s2的长度n2，是否等于s3的长度n3, 如果不满足则表示s3肯定不是s1和s2交错合并的结果，直接返回false即可。否则需要做
 * 进一步分析，交错合并字符串的特点：交错合并字符串中的第一个字符，要么等于s1的第一个字符，要么等于s2的第一个字符。
 * 交错合并字符串中的最后一个字符，要么等于s1的最后一个字符，要么等于s2的最后一个字符。于是我们可以从s3的第一个字符
 * 切入，也可以从s3的最后一个字符进行切入来分析这个问题。
 * s1 = abd
 * s2 = bcca
 * s3 = abcbcad
 * 假设我们用s3的最后一个字符d，来对比s1和s2最后一个字符，发现和s1最后的字符相等，于是在这个例子中判断s3是否由
 * s1和s2交错合并完成，就只需要判断s3的前缀子串abcbca是否是由s1的前缀子串ab和s2交错合并完成，这个问题本质上和
 * 原来问题是一样的，只不过问题规模变小了，是原问题的一个子问题。因此我们可以定义一个通用状态来表示这类问题的解，
 * 由于这个问题涉及s1、s2、s3三个字符串，3个变量，很自然地我们会想到使用一个三维数组来表示状态。
 * 状态定义：
 * d(i,j,k)表示s1长度为i的前缀子串，与s2长度为i的前缀子串，是否可以交错合并形成s3长度为k的前缀子串。
 * 因为我们目标是求d(n1, n2, n3)的值，其中n1、n2、n3是字符串s1、s2、s3的长度。
 *
 * 递推式：
 * d(i,j,k)
 * 情况1：s3(k-1)==s1(i-1) && d(i-1,j,k-1)
 * 情况2：s3(k-1)==s2(j-1) && d(i,j-1,k-1)
 * 这2种情况只要有一种成立，则d(i,j,k)成立。即
 * d(i,j,k) = (s3(k-1)==s1(i-1) && d(i-1,j,k-1)) || (s3(k-1)==s2(j-1) && d(i,j-1,k-1))
 *
 * 在这个递推式中有一个隐藏的约束条件，只有当s1前缀的长度i加上s2的前缀子串长度j等于s3前缀子串的长度k，s3
 * 的前缀子串才可能是另外两个子串的交错合并结果，即d(i,j,k)才有可能为true。
 *
 * k = i + j
 * 初始状态：
 * d(0,0,0) = true
 * d(i,0,k) = d(i,0,i)=s3(i-1) == s1(i-1)&&d(i-1,0,i-1), 1<=i<=n1
 * d(0,j,j) = s3(j-1)==s2(j-1)&&d(0,j-1,j-1), 1<=j<=n2
 *
 * 有了这些初始条件我们只需要让i从1遍历到n1 让j从1遍历到n2, 不断计算d(i,j,k)的值，最后就能得到问题的答案。
 */
public class IsTwoStrMergeAsOne_206 {
    // Time: O(n1*n2), Space: O(n1*n2*n3)
    public boolean isInterleave3DArray(String s1, String s2, String s3) {
        //处理边界
        if (s1 == null || s2 == null || s3 == null) return false;
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        //如果n1+n2不等n3直接返回false
        if (n1+n2 != n3) return false;
        //定义三维数组
        boolean[][][] d = new boolean[n1+1][n2+1][n3+1];

        //初始状态计算
        d[0][0][0] = true;

        for (int i = 1; i <= n1; ++i)
            d[i][0][i] = d[i-1][0][i-1] && s1.charAt(i-1) == s3.charAt(i-1);
        for (int j = 1; j <= n2; ++j)
            d[0][j][j] = d[0][j-1][j-1] && s2.charAt(j-1) == s3.charAt(j-1);

        //计算一般情况下的d(i,j,k)，两层for循环
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                //计算第三个纬度值
                int k = i + j;
                d[i][j][k] = (d[i-1][j][k-1] && s1.charAt(i-1) == s3.charAt(k-1))
                        || (d[i][j-1][k-1] && s2.charAt(j-1) == s3.charAt(k-1));
            }
        }
        return d[n1][n2][n3];
    }

    // Time: O(n1*n2), Space: O(n1*n2)
    public boolean isInterleave2DArray(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (n1+n2 != n3) return false;
        boolean[][] d = new boolean[n1+1][n2+1];
        d[0][0] = true;

        for (int i = 1; i <= n1; ++i)
            d[i][0] = d[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        for (int j = 1; j <= n2; ++j)
            d[0][j] = d[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);

        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                int k = i + j;
                d[i][j] = (d[i-1][j] && s1.charAt(i-1) == s3.charAt(k-1))
                        || (d[i][j-1] && s2.charAt(j-1) == s3.charAt(k-1));
            }
        }
        return d[n1][n2];
    }
}
