package org.gonevertical.server.endpoints;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.gonevertical.server.entities.SystemUser;
import org.gonevertical.server.entities.Todo;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.Nullable;

/**
 * Represent a user in the applications system.
 * 
 * Testing <br/>
 * Goto: http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
 * curl --header "Content-Type: application/json"
 * http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
 */
@Api(name = "systemuserendpoint")
public class SystemUserEndpoint {

  @Inject
  public SystemUserEndpoint() {
  }

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET
   * method.
   *
   * Testing <br/>
   * Goto: http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
   * curl --header "Content-Type: application/json"
   * http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
   * 
   * @return List of all entities persisted.
   */
  public List<SystemUser> listSystemUser(@Named("offset") @Nullable Integer offset, @Nullable @Named("limit") Integer limit) {
    if (offset == null) {
      offset = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    
    List<SystemUser> list = ofy().load().type(SystemUser.class).list();
    
    return list;
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id
   *          the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public SystemUser getSystemUser(@Named("id") Long id) {
    SystemUser item = ofy().load().type(SystemUser.class).id(id).now();
    
    return item;
  }

  /**
   * This inserts the entity into App Engine datastore. It uses HTTP POST
   * method.
   *
   * Testing <br/>
   * curl --header "Content-Type: application/json" -X POST -d '{"name":
   * "Brandon Donnelson"}'
   * http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser <br/>
   *
   * @param systemUser
   *          the entity to be inserted.
   * @return The inserted entity.
   */
  public SystemUser insertSystemUser(SystemUser systemUser) {
    ofy().save().entity(systemUser).now();
    return systemUser;
  }

  /**
   * This method is used for updating a entity. It uses HTTP PUT method.
   *
   * @param systemUser
   *          the entity to be updated.
   * @return The updated entity.
   */
  public SystemUser updateSystemUser(SystemUser systemUser) {
    ofy().save().entity(systemUser).now();
    return systemUser;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE
   * method.
   *
   * @param id
   *          the primary key of the entity to be deleted.
   * @return The deleted entity.
   */
  public void removeSystemUser(@Named("id") Long id) {
    ofy().delete().type(Todo.class).id(id).now();
  }

}
