package org.gonevertical.server.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.gonevertical.server.entities.SystemUser;

import com.google.api.server.spi.config.Api;

/**
 * Represent a user in the applications system.
 * 
 * Testing <br/>
 * Goto: http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
 * curl --header "Content-Type: application/json" http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
 */
@Api(name = "systemuserendpoint")
public class SystemUserEndpoint {

  /**
  * Provided from the ServerModule
  */
 final protected PersistenceManagerFactory pmf;

 @Inject
 public SystemUserEndpoint(PersistenceManagerFactory pmf) {
   this.pmf = pmf;
 }

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET method.
   *
   * Testing <br/>
   * Goto: http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
   * curl --header "Content-Type: application/json" http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
   * 
   * @return List of all entities persisted.
   */
  @SuppressWarnings({"cast", "unchecked"})
  public List<SystemUser> listSystemUser() {
    PersistenceManager pm = pmf.getPersistenceManager();
    List<SystemUser> list = new ArrayList<SystemUser>();
    try {
      Query query = pm.newQuery(SystemUser.class);
      List<SystemUser> results = (List<SystemUser>) query.execute();
      for (SystemUser systemUser : results) {
        list.add(systemUser);
      }
    } finally {
      pm.close();
    }
    return list;
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public SystemUser getSystemUser(@Named("id") Long id) {
    PersistenceManager pm = pmf.getPersistenceManager();
    SystemUser systemuser = null;
    try {
      systemuser = pm.getObjectById(SystemUser.class, id);
    } finally {
      pm.close();
    } 
    return systemuser;
  }

  /**
   * This inserts the entity into App Engine datastore. It uses HTTP POST method.
   *
   * Testing <br/>
   * curl --header "Content-Type: application/json" -X POST -d '{"name": "Brandon Donnelson"}' http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
   *
   * @param systemuser the entity to be inserted.
   * @return The inserted entity.
   */
  public SystemUser insertSystemUser(SystemUser systemuser) {
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      pm.makePersistent(systemuser);
    } finally {
      pm.close();
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
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      pm.makePersistent(systemuser);
    } finally {
      pm.close();
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
    PersistenceManager pm = pmf.getPersistenceManager();
    SystemUser systemuser = null;
    try {
      systemuser = pm.getObjectById(SystemUser.class, id);
      pm.deletePersistent(systemuser);
    } finally {
      pm.close();
    }
    return systemuser;
  }

}
