package com.algo.demo.controller;

import java.util.UUID;

public interface RecursiveThreeWithParentID <T1, T2, T3, R> {

    R recurse(RecursiveThreeWithParentID<T1, T2, T3, R> self, T1 param1, T2 param2, T3 param3, int level, UUID parentId, UUID currentId);
}
