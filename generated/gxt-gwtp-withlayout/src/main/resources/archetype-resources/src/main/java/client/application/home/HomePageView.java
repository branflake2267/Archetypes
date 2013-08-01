#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * See more on GXT <a href="http://docs.sencha.com/gxt-guides/3/ui/layout/LayoutContainers.html">Layout Containers</a>
 */
public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
  public interface Binder extends UiBinder<Widget, HomePageView> {
  }

  @Inject
  public HomePageView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
