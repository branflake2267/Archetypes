package org.gonevertical.project.server.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gonevertical.project.server.domain.SystemUser;

import com.google.inject.Singleton;

@Singleton
public class SystemUserDao extends BaseDao<SystemUser> {

  public SystemUserDao() {
    super(SystemUser.class);
  }

  public SystemUser findByGoogleId(String googleId) {
    EntityManager em = entityManagerProvider.get();
    Query query = em.createQuery("select o from " + SystemUser.class.getName() + " o where o.googleId = :googleId");
    query.setParameter("googleId", googleId);
    query.setMaxResults(1);
    SystemUser systemUser = (SystemUser) query.getSingleResult();
    return systemUser;
  }

}
