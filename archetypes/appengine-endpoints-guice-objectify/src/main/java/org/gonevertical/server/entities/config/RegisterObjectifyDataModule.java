package org.gonevertical.server.entities.config;

import org.gonevertical.server.entities.SystemUser;
import org.gonevertical.server.entities.Todo;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

/**
 * Objectify Module
 * https://github.com/objectify/objectify/wiki/Entities
 */
public class RegisterObjectifyDataModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ObjectifyFilter.class).in(Singleton.class);
    
    ObjectifyService.register(SystemUser.class);
    ObjectifyService.register(Todo.class);
  }

}
