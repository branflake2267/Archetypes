package org.gonevertical.server.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gonevertical.server.entities.SystemUser;

import com.google.api.server.spi.config.Api;
import com.google.inject.Provider;

/**
 * Represent a user in the applications system.
 * 
 * Testing <br/>
 * Goto: http://localhost:8888/_ah/api/systemuserendpoint/v1/systemuser <br/>
 * curl --header "Content-Type: application/json" http://localhost:8888/_ah/api/systemuserendpoint/v1/systemuser <br/>
 */
@Api(name = "systemuserendpoint")
public class SystemUserEndpoint {

  /**
   * Use a EntityManager Provider for the DAO singleton.
   */
  @Inject
  protected Provider<EntityManager> entityManagerProvider;

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET method.
   *
   * Testing <br/>
   * Goto: http://localhost:8888/_ah/api/systemuserendpoint/v1/systemuser <br/>
   * curl --header "Content-Type: application/json" http://localhost:8888/_ah/api/systemuserendpoint/v1/systemuser <br/>
   * 
   * @return List of all entities persisted.
   */
  @SuppressWarnings({"cast", "unchecked"})
  public List<SystemUser> listSystemUser() {
    EntityManager mgr = getEntityManager();
    List<SystemUser> result = new ArrayList<SystemUser>();
    try {
      Query query = mgr.createQuery("select from SystemUser as SystemUser");
      for (Object obj : (List<Object>) query.getResultList()) {
        result.add(((SystemUser) obj));
      }
    } finally {
      mgr.close();
    }
    return result;
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public SystemUser getSystemUser(@Named("id") Long id) {
    EntityManager mgr = getEntityManager();
    SystemUser systemuser = null;
    try {
      systemuser = mgr.find(SystemUser.class, id);
    } finally {
      mgr.close();
    }
    return systemuser;
  }

  /**
   * This inserts the entity into App Engine datastore. It uses HTTP POST method.
   *
   * Testing <br/>
   * curl --header "Content-Type: application/json" -X POST -d '{"name": "Brandon Donnelson"}' http://localhost:8888/_ah/api/systemuserendpoint/v1/systemuser <br/>
   *
   * @param systemuser the entity to be inserted.
   * @return The inserted entity.
   */
  public SystemUser insertSystemUser(SystemUser systemuser) {
    EntityManager mgr = getEntityManager();
    try {
      mgr.persist(systemuser);
    } finally {
      mgr.close();
    }
    return systemuser;
  }

  /**
   * This method is used for updating a entity. It uses HTTP PUT method.
   *
   * @param systemuser the entity to be updated.
   * @return The updated entity.
   */
  public SystemUser updateSystemUser(SystemUser systemuser) {
    EntityManager mgr = getEntityManager();
    try {
      mgr.persist(systemuser);
    } finally {
      mgr.close();
    }
    return systemuser;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE method.
   *
   * @param id the primary key of the entity to be deleted.
   * @return The deleted entity.
   */
  public SystemUser removeSystemUser(@Named("id") Long id) {
    EntityManager mgr = getEntityManager();
    SystemUser systemuser = null;
    try {
      systemuser = mgr.find(SystemUser.class, id);
      mgr.remove(systemuser);
    } finally {
      mgr.close();
    }
    return systemuser;
  }

  private EntityManager getEntityManager() {
    return entityManagerProvider.get();
  }

}
