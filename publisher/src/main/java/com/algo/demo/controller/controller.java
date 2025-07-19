package com.algo.demo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.algo.demo.service.RecursionRunner;
import com.algo.demo.wrapper.funcItfs.RecursiveTwo;


@Controller
public class controller{

    private final RecursionRunner recursionRunner;
    
    @Autowired
    public controller(RecursionRunner recursionRunner) {
        // Constructor
        this.recursionRunner = recursionRunner;
    }

    /*
     * Would be later changed to push packets to the client
     * and then the client would render the tree
     */

    
    @GetMapping("/tree")
    public String tree() {
        RecursiveTwo<Integer,int[], Integer> fibonacci = (n, dp, self) -> {
            if(n <= 1) {
                return n;
            }
            if(dp[n] != -1) {
                return dp[n];
            }
            return self.execute(n - 1, dp, self) + self.execute(n - 2, dp, self);
        };

        int[] dp = new int[6];
        Arrays.fill(dp, -1);

        //write the debug statement you want to display on every recursive call
        // Do not pass more parameters than required, the input to the function is taken as values to be replaced in the template
        String template = "The value of n is {0}";

        recursionRunner.runRecursiveComputation(fibonacci, template,  5, dp);
        return "done";
    }
}

/**
 * 
 *  I want to allow any number of inputs to be passed, I have to define a particular 
 *  format though.
 * 
 */