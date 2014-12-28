package org.gonevertical.server.guice;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JpaInitilization {

  @Inject
  public JpaInitilization(PersistService service) {
    service.start();
  }

}
