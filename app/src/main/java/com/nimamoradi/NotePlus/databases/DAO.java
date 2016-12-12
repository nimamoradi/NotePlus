package com.nimamoradi.NotePlus.databases;

/**
 * Created by UserPc on 11/7/2016.
 */
public interface DAO<T> {
    long add(T object);

    T get(String name);

    boolean delete(T object);

    void update(T object);


}
