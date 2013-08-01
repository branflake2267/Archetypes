package com.arcbees.project.client.application;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
  public interface MyView extends View {
  }

  @ContentSlot
  public static final Type<RevealContentHandler<?>> SLOT_SetNorth = new Type<RevealContentHandler<?>>();
  @ContentSlot
  public static final Type<RevealContentHandler<?>> SLOT_SetEast = new Type<RevealContentHandler<?>>();
  @ContentSlot
  public static final Type<RevealContentHandler<?>> SLOT_SetWest = new Type<RevealContentHandler<?>>();
  @ContentSlot
  public static final Type<RevealContentHandler<?>> SLOT_SetCenter = new Type<RevealContentHandler<?>>();
  
  @ProxyStandard
  public interface MyProxy extends Proxy<ApplicationPresenter> {
  }

  @Inject
  public ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, RevealType.Root);
  }
}
