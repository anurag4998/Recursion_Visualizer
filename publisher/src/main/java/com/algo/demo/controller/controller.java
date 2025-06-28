package com.algo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.algo.demo.wrapper.RecursiveTwo;


@Controller
public class controller{

    @Autowired
    private Code code;
    
    /*
     * Would be later changed to push packets to the client
     * and then the client would render the tree
     */
    @GetMapping("/tree")
    public String tree() {
        //code.numDistinct("rabbbit", "rabbit");
        RecursiveTwo<Integer,int[], Integer> fibonacci = (n, dp, self) -> {
            if(n <= 1) {
                return n;
            }
            if(dp[n] != -1) {
                return dp[n];
            }
            return self.execute(n - 1, dp, self) + self.execute(n - 2, dp, self);
        };

        code.main(5, fibonacci);
        //code.minPathSum(0, 0, new int[][]{{1,3,1},{1,5,1},{4,2,1}});
        //code.binarySearch(new int[]{1,2,3,1,1}, 4);
        return "done";
    }
}
