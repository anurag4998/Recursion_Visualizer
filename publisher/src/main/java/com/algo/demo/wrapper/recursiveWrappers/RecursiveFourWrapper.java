package com.algo.demo.wrapper.recursiveWrappers;

import java.util.UUID;

@FunctionalInterface
public interface RecursiveFourWrapper<T,U,V,W,R> {
    R call (T t, U u, V v, W w, UUID parentId, RecursiveFourWrapper <T,U,V,W,R> self);
}
