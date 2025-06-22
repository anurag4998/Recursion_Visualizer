package com.algo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
        //code.fibonacci(6);
        code.minPathSum(0, 0, new int[][]{{1,3,1},{1,5,1},{4,2,1}});
        return "done";
    }
}
