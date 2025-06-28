package com.algo.demo.controller;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.demo.pubSub.Publisher;
import com.algo.demo.templates.RecursiveFive;
import com.algo.demo.templates.RecursiveFour;
import com.algo.demo.templates.RecursiveUtils;

@Component
public class Code {
    
    @Autowired
    private Publisher publisher;

    @Autowired
    private RecursionRunner recursionRunner;

    RecursiveFiveWithParentId<String, String, Integer, Integer, int[][], Integer> recursiveWithParentId = (self, s, t, idx1, idx2, dp, level, parentId, currentId) -> {
        UUID childId = UUID.randomUUID();
        Message msg = new Message(level, "idx1:" + idx1 + ", idx2: " + idx2 +"", parentId, childId);
        publisher.notifyObservers(msg);

        if(idx2 == t.length()) {
            return 1;
        }
        if(idx1 >= s.length()) {
            return 0;   
        }
        if(dp[idx1][idx2] != -1) {
            return dp[idx1][idx2];
        }
        int take = 0;
        int noTake = 0;
        if(s.charAt(idx1) == t.charAt(idx2)) {
            take = self.recurse(self, s, t, idx1 + 1, idx2 + 1, dp, level + 1, childId, null);
        }

        noTake = self.recurse(self, s, t, idx1 + 1, idx2, dp, level + 1, childId, null);

        return dp[idx1][idx2] = take + noTake;
    };
    
    //
    // First step find a appropriate interface for your recursive function based on number of parameters.
    // convert the function to a lambda expression and then use the recursionRunner to run the function.
    
    RecursiveFive<Integer, Integer, String, String, int[][], Integer> recursiveLambda = (self, idx1, idx2, s, t, dp) -> {
        if(idx2 == t.length()) {
            return 1;
        }
        if(idx1 >= s.length()) {
            return 0;   
        }
        if(dp[idx1][idx2] != -1) {
            return dp[idx1][idx2];
        }
        int take = 0;
        int noTake = 0;
        if(s.charAt(idx1) == t.charAt(idx2)) {
            take = self.recurse(self, idx1 + 1, idx2 + 1, s, t, dp);
        }

        noTake = self.recurse(self, idx1 + 1, idx2, s, t, dp);
        return dp[idx1][idx2] = take + noTake;
      
    };
    
    int demoFunction(int idx1, int idx2, String s, String t, int[][] dp) {
        if(idx2 == t.length()) {
            return 1;
        }
        if(idx1 >= s.length()) {
            return 0;   
        }
        if(dp[idx1][idx2] != -1) {
            return dp[idx1][idx2];
        }
        int take = 0;
        int noTake = 0;
        if(s.charAt(idx1) == t.charAt(idx2)) {
            take = demoFunction(idx1 + 1, idx2 + 1, s, t, dp);
        }

        noTake = demoFunction(idx1 + 1, idx2, s, t, dp);

        return dp[idx1][idx2] = take + noTake;
    }


    RecursiveTwoWithParentID<Integer, int[], Integer> recursiveFibonacciFunction = (self, n, dp , level, parentId, currentId) -> {
        UUID childId = UUID.randomUUID();
        Message msg = new Message(level, "n'th fibonacci " + n, parentId, childId);
        publisher.notifyObservers(msg);

        if (n <= 1) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] = self.recurse(self, n - 1, dp, level + 1, childId, currentId) + self.recurse(self, n - 2, dp, level + 1, childId, currentId);

        return dp[n];
    };

    // RecursiveFourWithParentID<Integer, Integer, int[][], int[][], Integer> recursiveFourWithParentId = (self, i, j, grid, dp, level, parentId, currentId) -> {
    //     UUID childId = UUID.randomUUID();
    //     Message msg = new Message(level, "i: " + i + ", j: " + j, parentId, childId);
    //     publisher.notifyObservers(msg);

    //     if (i == grid.length - 1 && j == grid[0].length - 1) {
    //         return grid[i][j];
    //     }

    //     if (i >= grid.length || j >= grid[0].length) {
    //         return Integer.MAX_VALUE;
    //     }

    //     if (dp[i][j] != -1) {
    //         return dp[i][j];
    //     }

    //     int down = self.recurse(self, i + 1, j, grid, dp, level + 1, childId, null);
    //     int right = self.recurse(self, i, j + 1, grid, dp, level + 1, childId, null);

    //     return dp[i][j] = grid[i][j] + Math.min(down, right);
    // };
    int ans = Integer.MAX_VALUE;
    RecursiveFourWithParentID<Integer, Integer, int[], Integer, Integer> recursiveBinarySearchFunction = (self, low, high, weights, days, level, parentId, currentId) -> {
        UUID childId = UUID.randomUUID();
        Message msg = new Message(level, "low: " + low + ", high: " + high, parentId, childId);
        publisher.notifyObservers(msg);

        if (low > high) {
            return ans;
        }

        int mid = (low) + (high - low) / 2;
        if(weightToCarryInDay(weights, mid) > days) {
            low = mid + 1;
        }
        else {
            ans = Math.min(ans, mid);
            System.out.println(ans);
            high = mid - 1;
        }   
        return self.recurse(self, low, high, weights, days, level + 1, childId, null);
    };



    int weightToCarryInDay(int[] weights, int weight) {
        int days = 1;
        int right = 0;
        int sum = 0;
        while(right < weights.length) {
            sum += weights[right];
            if(sum > weight) {
                days++;
                sum = weights[right];
            }
            right++;
        }
        
        return days;
    }

    public int numDistinct(String s, String t) {
        int[][]dp = new int[s.length()][t.length()];
        for(int[] row :dp) {
            Arrays.fill(row, -1);
        }
        return recursionRunner.runRecursiveFunctionWithParentId(recursiveWithParentId, s, t, 0, 0, dp);
    }

    public int fibonacci(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return recursionRunner.runRecursiveTwoWithParentId(recursiveFibonacciFunction, n, dp);
    }

    // public int minPathSum(int i, int j, int[][] grid) {
    //     int[][]dp = new int[grid.length][grid[0].length];
    //     for(int[] row :dp) {
    //         Arrays.fill(row, -1);
    //     }
    //     return recursionRunner.runRecursiveFourWithParentId(recursiveFourWithParentId, i, j, grid, dp);
    // }

    RecursiveFour<Integer, Integer, int[], Integer, Integer> recursiveLambda2 = (self, low, high, weights, days) -> {
         if (low > high) {
            return ans;
        }

        int mid = (low) + (high - low) / 2;
        if(weightToCarryInDay(weights, mid) > days) {
            low = mid + 1;
        }
        else {
            ans = Math.min(ans, mid);
            System.out.println(ans);
            high = mid - 1;
        }  
        
        return self.recurse(self, low, high, weights, days);

      
    };

    public int binarySearch(int[] weights, int days) {
        int low = 1;
        int high = 0;
        for(int i : weights) {
            //low = Math.max(low, i);
            high += i;
        }
        
        RecursiveFourWithParentID<Integer, Integer, int[], Integer, Integer> wrapped = RecursiveUtils.wrapWithParentTracking(recursiveLambda2);
        

        
        //return recursionRunner.runRecursiveFourWithParentId(recursiveBinarySearchFunction ,low, high, weights, days);
    }
}
