package com.algo.demo.controller;

import java.util.UUID;

import com.algo.demo.templates.RecursiveFour;

@FunctionalInterface
public interface RecursiveFourWithParentID <T1, T2, T3, T4 , R> {
    R recurse(RecursiveFour<T1, T2, T3, T4, R> self, T1 param1, T2 param2, T3 param3, T4 param4, int level, UUID parentId, UUID currentId);
}