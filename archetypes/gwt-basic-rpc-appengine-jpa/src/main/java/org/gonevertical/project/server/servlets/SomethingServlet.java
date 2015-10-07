package org.gonevertical.project.server.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gonevertical.project.server.endpoints.TodoEndpoint;
import org.gonevertical.project.shared.domain.Todo;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SomethingServlet extends HttpServlet {

  public SomethingServlet() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();

    String thisURL = request.getRequestURI();

    response.setContentType("text/html");
    if (request.getUserPrincipal() != null) {
      response.getWriter().println(
          "<p>Hello, " + request.getUserPrincipal().getName() + "!  You can <a href=\""
              + userService.createLogoutURL(thisURL) + "\">sign out</a>.</p>");
    } else {
      response.getWriter().println("<p>Please <a href=\"" + userService.createLoginURL(thisURL) + "\">sign in</a>.</p>");
    }
    
    new TodoEndpoint().insertTodo(new Todo());
  }

}
