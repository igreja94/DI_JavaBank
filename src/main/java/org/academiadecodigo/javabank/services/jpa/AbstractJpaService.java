package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.JpaTransactionManager;
import org.academiadecodigo.javabank.services.CRUDService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * A generic jpa service to be used as a base for concrete jpa service implementations
 *
 * @param <T> the model type
 * @see CRUDService
 */
public abstract class AbstractJpaService<T extends AbstractModel> implements CRUDService<T> {

    protected JpaTransactionManager tm;
    private Class<T> modelType;


    public AbstractJpaService(JpaTransactionManager tm, Class<T> modelType) {
        this.tm = tm;
        this.modelType = modelType;
    }

    /**
     * @see CRUDService#list()
     */
    @Override
    public List<T> list() {

        tm.beginRead();

        try {

            CriteriaQuery<T> criteriaQuery = tm.getEm().getCriteriaBuilder().createQuery(modelType);
            Root<T> root = criteriaQuery.from(modelType);
            return tm.getEm().createQuery(criteriaQuery).getResultList();

        } finally {
            if (tm.getEm() != null) {
                tm.stopSession();
            }
        }
    }

    /**
     * @see CRUDService#get(Integer)
     */
    @Override
    public T get(Integer id) {

        tm.beginRead();

        try {

            return tm.getEm().find(modelType, id);

        } finally {

            tm.stopSession();

        }
    }

    /**
     * @see CRUDService#save(AbstractModel)
     */
    @Override
    public T save(T modelObject) {

        tm.beginRead();

        try {

            tm.beginWrite();
            T savedObject = tm.getEm().merge(modelObject);
            tm.commit();

            return savedObject;

        } catch (RollbackException ex) {

            tm.rollback();
            return null;

        } finally {
            if (tm.getEm() != null) {
                tm.getEm().close();
            }
        }
    }

    /**
     * @see CRUDService#delete(Integer)
     */
    @Override
    public void delete(Integer id) {

        tm.beginRead();

        try {

            tm.beginWrite();
            tm.getEm().remove(tm.getEm().find(modelType, id));
            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();

        } finally {
            if (tm.getEm() != null) {
                tm.stopSession();
            }
        }
    }
}
