package com.algo.demo.controller;

import java.util.UUID;

@FunctionalInterface
public interface RecursiveTwoWithParentID <T1, T2, R> {
 R recurse(RecursiveTwoWithParentID<T1, T2, R> self, T1 param1, T2 param2, int level, UUID parentId, UUID currentId);
}
