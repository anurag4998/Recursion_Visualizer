package com.algo.demo.wrapper.funcItfs;

@FunctionalInterface
public interface RecursiveThree <T,U,V, R> extends RecursiveFunc{
    R execute(T t, U u, V v, RecursiveThree<T,U,V,R> self);
}
