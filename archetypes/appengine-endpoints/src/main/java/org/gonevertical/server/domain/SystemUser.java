package org.gonevertical.server.domain;

import javax.persistence.Entity;

@Entity
public class SystemUser extends BaseEntity {
  
  private String googleId;
  
  private String name;

  public SystemUser() {
    googleId = "";
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    String s = "SystemUser(";
    s += "googleId=" + googleId + ", ";
    s += "name=" + name + ", ";
    s += ")";
    return s;
  }
  
}