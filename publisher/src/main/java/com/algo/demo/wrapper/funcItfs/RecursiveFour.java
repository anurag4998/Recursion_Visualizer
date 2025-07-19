package com.algo.demo.wrapper.funcItfs;

@FunctionalInterface
public interface RecursiveFour<T,U,V,W,R> extends RecursiveFunc {
    R execute(T t, U u, V v, W w, RecursiveFour<T,U,V,W,R> self);
}
