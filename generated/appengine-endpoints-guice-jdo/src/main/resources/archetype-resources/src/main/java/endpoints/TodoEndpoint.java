#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import ${package}.entities.Todo;

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

  /**
   * Provided from the ServerModule
   */
  final protected PersistenceManagerFactory pmf;

  @Inject
  public TodoEndpoint(PersistenceManagerFactory pmf) {
    this.pmf = pmf;
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
    List<Todo> list = new ArrayList<Todo>();
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      Query query = pm.newQuery(Todo.class);
      List<Todo> results = (List<Todo>) query.execute();
      for (Todo todo : results) {
        list.add(todo);
      }
    } finally {
      pm.close();
    }
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
    Todo todo = null;
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      todo = pm.getObjectById(Todo.class, id);
    } finally {
      pm.close();
    }
    return todo;
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
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      pm.makePersistent(todo);
    } finally {
      pm.close();
    }
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
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      pm.makePersistent(todo);
    } finally {
      pm.close();
    }
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
  public Todo removeTodo(@Named("id") Long id) {
    PersistenceManager pm = pmf.getPersistenceManager();
    Todo todo = null;
    try {
      todo = pm.getObjectById(Todo.class, id);
      pm.deletePersistent(todo);
    } finally {
      pm.close();
    }
    return todo;
  }

}
