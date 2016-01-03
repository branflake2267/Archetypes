package org.gonevertical.server;

import static org.junit.Assert.assertNotNull;

import org.gonevertical.server.entities.Todo;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * Run todo end point test. First start a server at the localhost.
 *
 */
public class TodoEndpointTest {

	private String endpointUrl = "http://localhost:8888/_ah/api/todoendpoint/v1/todo";

	private String devAppserverLoginCookie;

	@Test
	public void testInsert() {
		Todo todo = new Todo();
		todo.setName("Brandon");

		Todo newTodo = RestAssured.given().contentType(ContentType.JSON)
				.cookie("dev_appserver_login", devAppserverLoginCookie).and()
				.content(todo).expect().statusCode(200).when()
				.post(endpointUrl).as(Todo.class);

		assertNotNull(newTodo.getId() != null && newTodo.getId() > 0);
	}

}
