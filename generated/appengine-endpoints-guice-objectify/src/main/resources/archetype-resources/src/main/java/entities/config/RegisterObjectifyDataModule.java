#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entities.config;

import ${package}.entities.SystemUser;
import ${package}.entities.Todo;

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
