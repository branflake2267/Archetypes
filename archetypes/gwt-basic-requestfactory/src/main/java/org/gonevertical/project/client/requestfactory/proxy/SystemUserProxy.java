package org.gonevertical.project.client.requestfactory.proxy;

import org.gonevertical.project.server.domain.SystemUser;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(SystemUser.class)
public interface SystemUserProxy extends EntityProxy {

  String getLoginUrl();
  
  String getLogoutUrl();
  
  String getId();

  String getGoogleNickname();
  
}
