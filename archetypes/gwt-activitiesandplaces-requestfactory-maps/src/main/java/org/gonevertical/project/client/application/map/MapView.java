 package org.gonevertical.project.client.application.map;

import java.util.ArrayList;

import org.gonevertical.project.client.application.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MapView extends Composite {

  private static SignInViewImplUiBinder uiBinder = GWT.create(SignInViewImplUiBinder.class);

  interface SignInViewImplUiBinder extends UiBinder<Widget, MapView> {}
  @UiField
  HTMLPanel map;
  
  private Presenter presenter;

  private MapWidget mapWidget;

  public MapView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }
  
  public void start() {
    loadMapApi();
  }
  
  private void loadMapApi() {
    boolean sensor = true;

    // load all the libs for use in the maps
    ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
    loadLibraries.add(LoadLibrary.ADSENSE);
    loadLibraries.add(LoadLibrary.DRAWING);
    loadLibraries.add(LoadLibrary.GEOMETRY);
    loadLibraries.add(LoadLibrary.PANORAMIO);
    loadLibraries.add(LoadLibrary.PLACES);
    loadLibraries.add(LoadLibrary.WEATHER);
    loadLibraries.add(LoadLibrary.VISUALIZATION);

    Runnable onLoad = new Runnable() {
      @Override
      public void run() {
        draw();
      }
    };

    LoadApi.go(onLoad, loadLibraries, sensor);
  }

  private void draw() {
    LatLng center = LatLng.newInstance(49.496675, -102.65625);
    MapOptions opts = MapOptions.newInstance();
    opts.setZoom(4);
    opts.setCenter(center);
    opts.setMapTypeId(MapTypeId.HYBRID);

    mapWidget = new MapWidget(opts);
    map.add(mapWidget);
    mapWidget.setSize("100%", "100%");
  }

}
