package com.algo.demo.wrapper.funcItfs;

@FunctionalInterface
public interface RecursiveTwo <T, U,R> extends RecursiveFunc{
    R execute ( T t, U u, RecursiveTwo <T,U,R> self);
}
