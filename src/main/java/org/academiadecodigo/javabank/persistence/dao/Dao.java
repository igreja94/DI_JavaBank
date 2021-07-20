package org.academiadecodigo.javabank.persistence.dao;

import java.util.List;

public interface Dao <T>{

    List<T> findAll(Class<T> type);
    T findById(Class<T> type, Integer id);
    T saveOrUpdate(T t);
    void delete(Class<T> type,Integer id);

}
