package org.gonevertical.project.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {
  
  public static final Resources INSTANCE = GWT.create(Resources.class);

  public interface Styles extends CssResource {

  }

  public Styles styles();

}