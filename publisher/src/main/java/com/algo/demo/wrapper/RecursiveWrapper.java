package com.algo.demo.wrapper;

import java.util.UUID;

@FunctionalInterface
public interface RecursiveWrapper <T,U,R>{
    R call (T input, U input2, UUID parentId, RecursiveWrapper <T,U,R> self );
     
}
