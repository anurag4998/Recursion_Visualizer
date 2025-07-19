package com.algo.demo.wrapper.recursiveWrappers;

import java.util.UUID;

@FunctionalInterface
public interface RecursiveThreeWrapper <T,U,V,R>{
    R call (T t, U u, V v, UUID parentId, RecursiveThreeWrapper <T,U,V,R> self);
}
