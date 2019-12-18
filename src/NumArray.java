/*
 * Baijiahulian.com Inc.Copyright(c) 2019 All Rights Reserved.
 */

/**
 * @author zhaogege
 * @date 2019-12-18 15:10
 */


/**
 *给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 */
public class NumArray {
    /**
     //这个可以优化，不用另开辟一个数组，直接存储在原数组中
     //后来发现不能只用一个传入参数的数组。
     int[] arr;
    public NumArray(int[] nums) {
        int len = nums.length;
        arr = new int[len+1];
        arr[0] = 0;
        for(int i=1;i<=len;i++){
            arr[i] = nums[i-1] + arr[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return arr[j+1]-arr[i];
    }
*/


}