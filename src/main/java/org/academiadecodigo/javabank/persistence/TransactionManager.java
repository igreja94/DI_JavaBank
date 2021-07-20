package org.academiadecodigo.javabank.persistence;

import javax.persistence.EntityManager;

public interface TransactionManager {

    public void beginRead();

    public void beginWrite();

    public void commit();

    public void rollback();

    public EntityManager getEm();

    public void stopSession();

}
