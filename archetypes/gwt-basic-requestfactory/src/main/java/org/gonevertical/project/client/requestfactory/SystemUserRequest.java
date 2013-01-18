package org.gonevertical.project.client.requestfactory;

import org.gonevertical.project.client.requestfactory.proxy.SystemUserProxy;
import org.gonevertical.project.server.dao.DaoServiceLocator;
import org.gonevertical.project.server.domain.SystemUser;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = SystemUser.class, locator = DaoServiceLocator.class)
public interface SystemUserRequest extends RequestContext {

  Request<SystemUserProxy> findSystemUser(String id);

  InstanceRequest<SystemUserProxy, SystemUserProxy> persist();

  InstanceRequest<SystemUserProxy, Void> remove();

}
