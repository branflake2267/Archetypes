package org.gonevertical.project.server.servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gonevertical.project.server.dao.SystemUserDao;
import org.gonevertical.project.server.domain.SystemUser;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
  private static Logger logger = Logger.getLogger(HomeServlet.class.getName());
  
  private SystemUserDao systemUserDao;

  @Inject
  public HomeServlet(SystemUserDao systemUserDao) {
    this.systemUserDao = systemUserDao;
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();

    String thisURL = request.getRequestURI();

    response.setContentType("text/html");
    if (request.getUserPrincipal() != null) {
      response.getWriter().println(
          "<p>Hello, " + request.getUserPrincipal().getName() + "!  You can <a href=\""
              + userService.createLogoutURL(thisURL) + "\">sign out</a>.</p>");
      trySomeDbTasks(userService);
    } else {
      response.getWriter()
          .println("<p>Please <a href=\"" + userService.createLoginURL(thisURL) + "\">sign in</a>.</p>");
    }
  }

  private void trySomeDbTasks(UserService userService) {
    createUser(userService.getCurrentUser());
    
    findSomeUsers();
  }

  private void createUser(User user) {
    SystemUser systemUser = new SystemUser();
    systemUser.setGoogleId(user.getUserId());
    systemUser.setName(user.getNickname());
    systemUserDao.put(systemUser);
    
    logger.info("User created: systemUser=" + systemUser);
    
    findByGoogleId(systemUser);
  }

  private void findByGoogleId(SystemUser systemUser) {
    SystemUser foundSystemUser = systemUserDao.findByGoogleId(systemUser.getGoogleId());
    System.out.println("found by GoogleId: foundSystemUser=" + foundSystemUser);
  }

  private void findSomeUsers() {
    List<SystemUser> list = systemUserDao.findAll();
    
    logger.info("findAllUsers list=" + list);
  }
  
}
