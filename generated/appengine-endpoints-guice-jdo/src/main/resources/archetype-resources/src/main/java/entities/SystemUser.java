#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entities;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * Represents a Person/User, which may have a linked Google Account.
 */
@PersistenceCapable
public class SystemUser implements Serializable {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private String googleId;

  @Persistent
  private String name;

  public SystemUser() {
    googleId = "";
  }

  public Key getKey() {
    return key;
  }

  public void setKey(Key key) {
    this.key = key;
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
