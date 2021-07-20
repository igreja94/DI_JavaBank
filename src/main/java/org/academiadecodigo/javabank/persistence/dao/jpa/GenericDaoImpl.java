package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.persistence.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.JpaTransactionManager;
import org.academiadecodigo.javabank.persistence.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericDaoImpl<T> implements Dao<T> {

    JpaSessionManager sm;

    public void setSm(JpaSessionManager sm) {
        this.sm = sm;
    }


    @Override
    public List<T> findAll(Class<T> type) {


        CriteriaQuery<T> criteriaQuery = sm.getCurrentSession().getCriteriaBuilder().createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();


    }

    @Override
    public T findById(Class<T> type,Integer id) {

         return sm.getCurrentSession().find(type,id);

    }

    @Override
    public T saveOrUpdate(T t) {

        return sm.getCurrentSession().merge(t);

    }

    @Override
    public void delete(Class<T> type, Integer id) {

        sm.getCurrentSession().remove(sm.getCurrentSession().find(type,id));

    }
}
