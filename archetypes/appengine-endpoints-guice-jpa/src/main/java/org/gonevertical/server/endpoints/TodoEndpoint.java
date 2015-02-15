package org.gonevertical.server.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gonevertical.server.entities.Todo;

import com.google.api.server.spi.config.Api;
import com.google.inject.Provider;

/**
 * Entity to store simple Todos.
 * 
 * Testing <br/>
 * Goto: http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
 * curl --header "Content-Type: application/json" http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
 */
@Api(name = "todoendpoint")
public class TodoEndpoint {

  /**
   * Use a EntityManager Provider for the DAO singleton
   */
  @Inject
  protected Provider<EntityManager> entityManagerProvider;

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET method.
   *
   * Testing <br/>
   * Goto: http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
   * curl --header "Content-Type: application/json" http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
   *
   * @return List of all entities persisted.
   */
  @SuppressWarnings({"cast", "unchecked"})
  public List<Todo> listTodos() {
    EntityManager mgr = getEntityManager();
    List<Todo> list = new ArrayList<Todo>();
    try {
      Query query = mgr.createQuery("select from Todo as Todo");
      for (Object obj : (List<Object>) query.getResultList()) {
        list.add(((Todo) obj));
      }
    } finally {
      mgr.close();
    }
    return list;
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public Todo getTodo(@Named("id") Long id) {
    EntityManager mgr = getEntityManager();
    Todo todo = null;
    try {
      todo = mgr.find(Todo.class, id);
    } finally {
      mgr.close();
    }
    return todo;
  }

  /**
   * This inserts the entity into App Engine datastore. It uses HTTP POST method.
   *
   * Testing <br/>
   * curl --header "Content-Type: application/json" -X POST -d '{"task": "Testing 1 2 3?"}' http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
   *
   * @param todo the entity to be inserted.
   * @return The inserted entity.
   */
  public Todo insertTodo(Todo todo) {
    EntityManager mgr = getEntityManager();
    try {
      mgr.persist(todo);
    } finally {
      mgr.close();
    }
    return todo;
  }

  /**
   * This method is used for updating a entity. It uses HTTP PUT method.
   *
   * @param todo the entity to be updated.
   * @return The updated entity.
   */
  public Todo updateTodo(Todo todo) {
    EntityManager mgr = getEntityManager();
    try {
      mgr.persist(todo);
    } finally {
      mgr.close();
    }
    return todo;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE method.
   *
   * @param id the primary key of the entity to be deleted.
   * @return The deleted entity.
   */
  public Todo removeTodo(@Named("id") Long id) {
    EntityManager mgr = getEntityManager();
    Todo todo = null;
    try {
      todo = mgr.find(Todo.class, id);
      mgr.remove(todo);
    } finally {
      mgr.close();
    }
    return todo;
  }

  private EntityManager getEntityManager() {
    return entityManagerProvider.get();
  }

}
