package com.algo.demo.controller;

import java.util.UUID;

@FunctionalInterface
public interface RecursiveFiveWithParentId <T1, T2, T3, T4, T5, R>{
    R recurse(RecursiveFiveWithParentId <T1, T2, T3, T4, T5, R> self, T1 param1, T2 param2, T3 param3, T4 param4, T5 param5, int level, UUID parentId, UUID currentId);

}
