#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package org.gonevertical.project.server.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SomethingServlet extends HttpServlet {

  @Inject
  public SomethingServlet() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();

    String thisURL = request.getRequestURI();

    response.setContentType("text/html");
    if (request.getUserPrincipal() != null) {
      response.getWriter().println(
          "<p>Hello, " + request.getUserPrincipal().getName() + "!  You can <a href=${symbol_escape}""
              + userService.createLogoutURL(thisURL) + "${symbol_escape}">sign out</a>.</p>");
    } else {
      response.getWriter().println("<p>Please <a href=${symbol_escape}"" + userService.createLoginURL(thisURL) + "${symbol_escape}">sign in</a>.</p>");
    }
  }

}
