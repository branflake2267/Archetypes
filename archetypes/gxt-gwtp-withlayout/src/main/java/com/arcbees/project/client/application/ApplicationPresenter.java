package com.arcbees.project.client.application;

import com.arcbees.project.client.application.east.EastPresenter;
import com.arcbees.project.client.application.north.NorthPresenter;
import com.arcbees.project.client.application.west.WestPresenter;
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

  private NorthPresenter northPresenter;
  private EastPresenter eastPresenter;
  private WestPresenter westPresenter;

  @ProxyStandard
  public interface MyProxy extends Proxy<ApplicationPresenter> {
  }

  @Inject
  public ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy, NorthPresenter northPresenter,
      EastPresenter eastPresenter, WestPresenter westPresenter) {
    super(eventBus, view, proxy, RevealType.Root);
    
    this.northPresenter = northPresenter;
    this.eastPresenter = eastPresenter;
    this.westPresenter = westPresenter;
  }

  @Override
  protected void onReveal() {
    super.onReveal();

    northPresenter.forceReveal();
    eastPresenter.forceReveal();
    westPresenter.forceReveal();
  }
}
