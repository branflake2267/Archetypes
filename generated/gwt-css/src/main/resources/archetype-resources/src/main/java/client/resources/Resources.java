#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface Resources extends ClientBundle {

  public static final Resources INSTANCE = GWT.create(Resources.class);

  public interface LayoutStyles extends CssResource {

    /* example Constant which is 100% */
    String wallTowall();

    /* basic style */
    String boxy();
    
    /* sprite */
    String logoBox();
    
  }
  
  public LayoutStyles layoutStyles();
  
  @Source("logo.jpg")
  @ImageResource.ImageOptions(repeatStyle = RepeatStyle.None)
  ImageResource logo();

}
