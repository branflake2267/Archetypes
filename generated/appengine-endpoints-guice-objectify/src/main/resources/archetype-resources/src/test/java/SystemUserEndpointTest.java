#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import ${package}.entities.SystemUser;
import ${package}.entities.Todo;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * First start the server then run debug on the test. 
 */
public class SystemUserEndpointTest {

  private String endpointUrl = "http://localhost:8080/_ah/api/systemuserendpoint/v1/systemuser";

  @Test
  public void testInsert() {
    SystemUser item = new SystemUser();
    item.setName("Brandon");

    SystemUser newItem = RestAssured.given().contentType(ContentType.JSON).and().content(item).expect().statusCode(200).when()
        .post(endpointUrl).as(SystemUser.class);

    assertNotNull(newItem.getId() != null && newItem.getId() > 0);
    assertEquals(item.getName(), "Brandon");
  }

  @Test
  public void testList() {
    createMultipleItems(20);

    List<SystemUser> items = RestAssured.given().param("offset", "10").and().param("limit", "10").expect().when()
        .get(endpointUrl).jsonPath().getList("items");

    assertTrue(items.size() > 0);
  }

  private void createMultipleItems(int howMany) {
    for (int i = 0; i < howMany; i++) {
      SystemUser item = new SystemUser();
      item.setName("" + i);

      RestAssured.given().contentType(ContentType.JSON).and().content(item).expect().statusCode(200).when()
          .post(endpointUrl).as(SystemUser.class);
    }
  }

}
