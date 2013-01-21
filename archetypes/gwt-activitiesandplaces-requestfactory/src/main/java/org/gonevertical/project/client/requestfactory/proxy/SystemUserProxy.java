package org.gonevertical.project.client.requestfactory.proxy;

import org.gonevertical.project.server.domain.SystemUser;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(SystemUser.class)
public interface SystemUserProxy extends EntityProxy {

  Long getVersion();
  
  String getLoginUrl();
  
  String getLogoutUrl();
  
  Long getId();
  
  void setId(Long id);

  String getGoogleNickname();
  
}
