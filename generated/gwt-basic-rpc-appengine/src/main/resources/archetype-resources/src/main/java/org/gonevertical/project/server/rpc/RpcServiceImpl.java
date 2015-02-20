#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package org.gonevertical.project.server.rpc;

import javax.inject.Inject;

import org.gonevertical.project.client.rpc.RpcService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

  @Inject
  public RpcServiceImpl() {
  }
  
  public String testCallServer(String input) throws IllegalArgumentException {
    return input + " server call works";
  }

}
