package org.gonevertical.project.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>RpcService</code>.
 */
public interface RpcServiceAsync {
  void testCallServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
