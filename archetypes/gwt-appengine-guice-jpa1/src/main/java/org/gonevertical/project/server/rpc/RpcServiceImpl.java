package org.gonevertical.project.server.rpc;

import javax.inject.Inject;

import org.gonevertical.project.client.rpc.RpcService;
import org.gonevertical.project.server.dao.SystemUserDao;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

  private SystemUserDao systemUserDao;

  @Inject
  public RpcServiceImpl(SystemUserDao systemUserDao) {
    this.systemUserDao = systemUserDao;
  }
  
  public String testCallServer(String input) throws IllegalArgumentException {
    return input + " server call works";
  }

}
