#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.north;

import ${package}.client.application.ApplicationPresenter;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class NorthPresenter extends Presenter<NorthPresenter.MyView, NorthPresenter.MyProxy> {
  interface MyView extends View {
  }

  @ContentSlot
  public static final Type<RevealContentHandler<?>> SLOT_North = new Type<RevealContentHandler<?>>();

  @ProxyStandard
  public interface MyProxy extends Proxy<NorthPresenter> {
  }

  @Inject
  public NorthPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetNorth);
  }

}
