#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entities;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Represents a Person/User, which may have a linked Google Account.
 */
@Entity
public class SystemUser implements Serializable {

  @Id
  private Long id;

  private String googleId;

  private String name;

  public SystemUser() {
    googleId = "";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;;
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
