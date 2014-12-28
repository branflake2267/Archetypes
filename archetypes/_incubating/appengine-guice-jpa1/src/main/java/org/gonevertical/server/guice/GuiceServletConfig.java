package org.gonevertical.server.guice;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * This starts in the web.xml. All requests are intercepted and processed
 * through Guice.
 * 
 * @see ServletPathModule
 */
public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServerModule());
  }

}