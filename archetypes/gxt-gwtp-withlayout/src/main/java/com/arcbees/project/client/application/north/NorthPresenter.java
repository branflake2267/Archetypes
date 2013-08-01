package com.arcbees.project.client.application.north;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.arcbees.project.client.application.ApplicationPresenter;

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
