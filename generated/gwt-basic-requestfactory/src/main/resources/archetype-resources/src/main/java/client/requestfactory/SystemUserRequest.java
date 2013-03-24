#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.requestfactory;

import ${package}.client.requestfactory.proxy.SystemUserProxy;
import ${package}.server.domain.SystemUser;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(SystemUser.class)
public interface SystemUserRequest extends RequestContext {

  Request<SystemUserProxy> findSystemUser(Long id);

}
