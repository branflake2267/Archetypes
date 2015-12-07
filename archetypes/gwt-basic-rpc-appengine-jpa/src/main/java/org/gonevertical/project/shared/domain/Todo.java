package org.gonevertical.project.shared.domain;

import javax.persistence.Entity;

/**
 * Represents a simple Task.
 */
@Entity
public class Todo extends BaseEntity {

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
