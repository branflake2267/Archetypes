package org.gonevertical.server.endpoints;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.gonevertical.server.entities.Todo;

import com.google.api.server.spi.config.Api;

/**
 * Entity to store simple Todos.
 * 
 * Testing <br/>
 * Goto: http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
 * curl --header "Content-Type: application/json"
 * http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
 */
@Api(name = "todoendpoint")
public class TodoEndpoint {

  @Inject
  public TodoEndpoint() {
  }

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET
   * method.
   *
   * Testing <br/>
   * Goto: http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
   * curl --header "Content-Type: application/json"
   * http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
   *
   * @return List of all entities persisted.
   */
  @SuppressWarnings({ "cast", "unchecked" })
  public List<Todo> listTodos() {
    List<Todo> list = ofy().load().type(Todo.class).list();

    return list;
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id
   *          the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public Todo getTodo(@Named("id") Long id) {
    Todo item = ofy().load().type(Todo.class).id(id).now();

    return item;
  }

  /**
   * This inserts the entity into App Engine datastore. It uses HTTP POST
   * method.
   *
   * Testing <br/>
   * curl --header "Content-Type: application/json" -X POST -d '{"task":
   * "Testing 1 2 3?"}' http://localhost:8080/_ah/api/todoendpoint/v1/todo <br/>
   *
   * @param todo
   *          the entity to be inserted.
   * @return The inserted entity.
   */
  public Todo insertTodo(Todo todo) {
    ofy().save().entity(todo).now();
    return todo;
  }

  /**
   * This method is used for updating a entity. It uses HTTP PUT method.
   *
   * @param todo
   *          the entity to be updated.
   * @return The updated entity.
   */
  public Todo updateTodo(Todo todo) {
    ofy().save().entity(todo).now();
    return todo;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE
   * method.
   *
   * @param id
   *          the primary key of the entity to be deleted.
   * @return The deleted entity.
   */
  public void removeTodo(@Named("id") Long id) {
    ofy().delete().type(Todo.class).id(id).now();
  }

}
