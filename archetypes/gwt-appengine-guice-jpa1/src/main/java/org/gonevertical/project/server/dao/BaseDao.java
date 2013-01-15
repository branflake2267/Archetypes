package org.gonevertical.project.server.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public abstract class BaseDao<T> {

  private final Class<T> clazz;

  /**
   * Use a EntityManager Provider for the DAO singleton
   */
  @Inject
  protected Provider<EntityManager> entityManagerProvider;

  protected BaseDao(final Class<T> clazz) {
    this.clazz = clazz;
  }

  public List<T> findAll() {
    EntityManager em = entityManagerProvider.get();
    return em.createQuery("select o from " + clazz.getName() + " o").getResultList();
  }
  
  public T put(T object) {
    EntityManager em = entityManagerProvider.get();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.merge(object);
      tx.commit();
    } catch (Exception exception) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
    } 
    
    return object;
  }

  public T get(Key key) {
    EntityManager em = entityManagerProvider.get();
    return em.find(clazz, key);
  }

  public T get(Long id) {
    EntityManager em = entityManagerProvider.get();
    Key key = KeyFactory.createKey(clazz.getName(), id);
    return em.find(clazz, key);
  }

}