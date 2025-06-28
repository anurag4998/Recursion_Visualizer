package com.algo.demo.wrapper;

@FunctionalInterface
public interface RecursiveTwo <T, U,R> {
    R execute ( T t, U u, RecursiveTwo <T,U,R> self);
}
