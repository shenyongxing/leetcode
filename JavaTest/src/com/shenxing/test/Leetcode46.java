package com.shenxing.test;

import java.util.*;

public class Leetcode46 {

    private List<List<Integer>> res = new ArrayList<>();
    private int[] result;
    private int[] book;
    public List<List<Integer>> permute(int[] nums) {
        if (result == null) {
            result = new int[nums.length];
            book = new int[nums.length];
        }

        dfs(nums, 0);
        return res;
    }

    /**
     * 深度优先遍历
     * @param nums 数组
     * @param step 当前步数
     */
    public void dfs(int[] nums, int step) {
        if (step == result.length) {  
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < result.length; i++) {
                list.add(result[i]);
            }
            res.add(list);
            return;
        }

        // 将每个值放到结果上
        for (int i = 0; i < nums.length; i++) {
            if (book[i] == 0) {
                book[i] = 1;
                result[step] = nums[i]; // 当前step步取i的值
                dfs(nums, step + 1);
                book[i] = 0;   // 标记完成后需要取消
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3};
        Leetcode46 test = new Leetcode46();
        test.permute(a);
    }
}