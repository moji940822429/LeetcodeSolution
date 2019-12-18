/*
 * Baijiahulian.com Inc.Copyright(c) 2019 All Rights Reserved.
 */


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


}