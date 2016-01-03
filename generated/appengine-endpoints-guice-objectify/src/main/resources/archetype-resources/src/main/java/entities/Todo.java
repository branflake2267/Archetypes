#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entities;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Represents a simple Task.
 */
@Entity
public class Todo implements Serializable {

  @Id
  private Long id;

  private String name;

  public Todo() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
