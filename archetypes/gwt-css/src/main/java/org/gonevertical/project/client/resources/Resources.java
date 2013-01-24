package org.gonevertical.project.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface Resources extends ClientBundle {

  public static final Resources INSTANCE = GWT.create(Resources.class);

  public interface LayoutStyles extends CssResource {

    /* Example Constant is 100% */
    String wallTowall();

    String boxy();
    
    String logoBox();
    
  }
  
  public LayoutStyles layoutStyles();
  
  @Source("logo.jpg")
  @ImageResource.ImageOptions(repeatStyle = RepeatStyle.None)
  ImageResource logo();

}
