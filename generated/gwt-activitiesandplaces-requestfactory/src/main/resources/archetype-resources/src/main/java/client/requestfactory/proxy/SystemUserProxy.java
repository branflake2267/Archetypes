#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.requestfactory.proxy;

import ${package}.server.domain.SystemUser;

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
