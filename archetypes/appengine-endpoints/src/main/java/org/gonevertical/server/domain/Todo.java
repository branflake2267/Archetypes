package org.gonevertical.server.domain;

import javax.persistence.Entity;

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
