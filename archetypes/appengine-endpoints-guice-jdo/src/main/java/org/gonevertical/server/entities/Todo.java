package org.gonevertical.server.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Represents a simple Task.
 */
@PersistenceCapable
public class Todo extends BaseEntity {

  @Persistent
  private String task;

  public Todo() {
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

}
