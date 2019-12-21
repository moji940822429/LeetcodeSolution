/*
 * Baijiahulian.com Inc.Copyright(c) 2019 All Rights Reserved.
 */


import java.util.Arrays;
import java.util.Map;

/**
 * @author zhaogege
 * @date 2019-12-17 19:05
 */
public class Solution {
    //给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    /**
     * 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
     * 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
     */
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num:nums){
            if(sum<=0){
                sum = num;
            }else{
                sum += num;
            }
            ans = Math.max(sum,ans);
        }
        return ans;
    }

    //爬楼梯，每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到n阶台阶楼顶

    /**
     * 当n为2时有两种，一个是每次上一个（1+1），一个是一次迈两个（2），这样就有两种方法
     * 这样f（2）=2；当有三级台阶时，n=3时，f（3）=f（2）+f（1）=2+1=3
     * 当n=4时，f（4）=f（3）+f（2）=3+2=5
     * 这样的结果就是当前项的值为前两项之和
     * 也就是斐波那契数列，
     */
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        //初始化数列前两项
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    //优化内存
    public int climbStairs2(int n){
        int pre = 1;
        int preOfPre = 1;
        int curr=1;
        for(int i=2; i<=n; i++){
            curr = pre + preOfPre;
            preOfPre = pre;
            pre = curr;
        }
        return curr;
    }

    //打家劫舍：求数组非连续数的最大和

    public int rob(int[] nums) {
        int len = nums.length;
        if(len==0){
            return 0;
        }
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i=2;i<len+1;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i-1]);
        }
        return dp[len];
    }

    //使用最小花费爬楼梯??没理解
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[len - 1], dp[len - 2]);
    }

    //最长回文子串【解决最优子结构问题】,建立Dp数组记录状态
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len<=1){
            return s;
        }
        //回文串长度
        int longestPalindrome = 1;
        //如果没有回文串取第一个字母作为输出结果
        String longestPalindromeStr = s.substring(0,1);
        //创建dp数组
        boolean[][] dp = new boolean[len][len];
        //从前往后一点一点的找回文串
        for(int r=1;r<len;r++){
            for(int l=0;l<r;l++){
                if(s.charAt(l)==s.charAt(r) && (r-l<=2 || dp[l+1][r-1])){
                    dp[l][r] = true;
                    if(r-l+1>longestPalindrome){
                        longestPalindrome = r-l+1;
                        longestPalindromeStr = s.substring(l,r+1);
                    }
                }

            }
        }
        return longestPalindromeStr;
    }

    //一个机器人位于一个 m x n 网格的左上角,机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角,问总共有多少条不同的路径？

    /**
     * 二维数组的动态规划
     *  到达位置(i,j) ，只有两条路，从(i-1,j)到达(i,j)，和从(i,j-1)到达(i,j)。
     * 因此有,d[i][j] = d[i-1][j] + d[i][j-1]。
     * 参考跳楼梯，从a[0][0]开始，有a[0][1]/a[1][0]两种
     * 动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 需要一个dp数组记录状态
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //初始化dp数组
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j<n; j++){
            dp[0][j] = 1;
        }
        //实现动态方程
        for (int i = 1;i < m; i++){
            for (int j = 1; j < n;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    //空间优化，从二维数组变为两个一维数组
    public int uniquePaths2(int m, int n){
        int[] pre = new int[n];
        int[] curr = new int[n];
        //初始化
        Arrays.fill(pre,1);
        Arrays.fill(curr,1);
        for (int i=1; i<m;i++) {
            for (int j = 1;j<n;j++){
                curr[j] = curr[j-1]+pre[j];
            }
            pre = curr.clone();
        }
        return curr[n-1];
    }
    //空间继续优化，只用一个一维数组记录状态
    public int uniquePaths3(int m, int n){
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 1; i < m; i++){
            for (int j = 1; j<n; j++){
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }

    //不同路径2：有障碍物
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //初始化dp数组
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0]=1;
        }

        for (int j = 0; j<n; j++){
            if (obstacleGrid[0][j] == 1){
                break;
            }
            dp[0][j]=1;
        }
        for (int i = 1;i < m; i++){
            for (int j = 1; j < n;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


}