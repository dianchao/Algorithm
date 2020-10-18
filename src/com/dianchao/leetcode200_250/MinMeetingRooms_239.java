package com.dianchao.leetcode200_250;

/**
 * 描述
 * 这个题目说的是，给你一系列的会议时间区间，每个时间区间由开始时间和结束时间构成。你要计算出开完这些会所需要的最少会议室数量。
 *
 * 比如说，给你两个时间区间：
 *
 * [2, 4]
 * [4, 6]
 *
 * 这两个时间区间没有重叠，只需要使用一个会议室，因此返回 1。
 *
 * 如果再增加一个会议，得到三个时间区间：
 *
 * [2, 4]
 * [4, 6]
 * [1, 3]
 *
 * 对于这 3 个会议，至少需要两个会议室。一种会议安排方式为：
 *
 * 会议室 1：[1, 3], [4, 6]
 * 会议室 2：[2, 4]
 *
 * 因此，需要的最少会议室数量是 2。
 *
 * 解题思路：
 * 与能否参加所有会议题目类似，都是和区间重叠处理有关，在解决能否参加所有会议问题时，为了避免穷举所有的区间对来检查区间重叠情况，
 * 我们可以先对区间序列进行排序，这样我们只需要比较相邻的区间即可，这里我们也是对区间进行排序而且是以区间的开始时间从小到大进行
 * 排序，得到的结果是：[1, 3] [2, 4] [4, 6], 接下来我们就将这些会议安排到不同的会议室，由于我们已经按照会议的开始时间从小到大
 * 进行排序，因此我们只需要从左往右一个个处理会议即可。我们先将[1,3]放到1号会议室，接着处理[2,4]，由于[1,3]的结束时间3大于[2,4]
 * 的开始时间2，因此[2,4]不能放到1号会议室，我们把它放到2号会议室，最后处理[4,6], 这个会议4点开始，这个时候1号会议室已经空出来
 * 因此把[4,6]放到1号会议室即可。这样就得到了最少的会议室数量为2.假设我们要处理n个时间区间，那么这种方法需要先花费O(nlog(n))的时
 * 间去排序序列，然后再从排序后序列中一个个取出每个区间去检查是否可以放到当前已经分配好的会议室中，如果不可以我们还需要为它去分
 * 配一个新的会议室。这个过程最快需要花费O(n^2)的时间，因此这种方法总的时间复杂度为O(n^2)，空间复杂度O(n)
 *
 * 优化：在上面的方法中，其实并不需要在每个会议室中存储所有在这里开的会议，我们只需要存储最后一个会议的时间区间区间即可，比如上面
 * 的例子，我们可以用[4,6]覆盖[1,3]并不需要两个区间都要存储。更进一步其实我们关心的只是每个会议室最后一个会议区间的结束时间，比如
 * 再增加一个会议[5,8]我们只需要拿这个会议的开始时间5和每个会议室中最后一个会议的结束时间进行比较即可，因此每个会议室只需要保存最后
 * 一个会议的结束时间即可。
 *
 * 暴力解法之所以要花费O(n^2)的时间，原因在于每次处理一个时间区间，我们都要去检查所有的会议室，直到找到一个可以开会的地方，但是
 * 实际上如果我们知道哪一个会议室中的会议最早结束，那么就只需要检查那个会议室即可。可以看到在这种方法中我们每次只需要对比一个会
 * 议室中的结束时间即可，而其中的关键就是在这个过程中我们需要始终维护一个结束时间的最小值，而这个很好解决只要用最小堆minHeap来保
 * 存这些结束时间即可，处理结束后，堆的大小就是我们所需的最少会议室数量。
 *
 * 使用扫描线算法求解：第一步要做的是对总共6个时间点按从小到大进行排序，这一次我们把开始时间和结束时间放在一起排序，对于画出来的
 * 区间如果我们只看水平方向就是6个时间点从小到大排序的结果，使用一条垂直的扫描线，从左边往右扫过这些线段。扫描线同时穿过的最多线
 * 段数量就是需要的最少会议室数量。
 *
 * 扫描线同时穿过的线段，表示了同时在进行的会议，这些同时在进行的会议只可能使用不同的会议室。因此当扫描线同时穿过的线段数量最多
 * 是k条时，我们就至少需要k个会议室。
 *
 */
public class MinMeetingRooms_239 {
}
