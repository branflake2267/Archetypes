/**
 * Copyright 2012 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.project.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.ContentPanel;

/**
 * See more on GXT <a href="http://docs.sencha.com/gxt-guides/3/ui/layout/LayoutContainers.html">Layout Containers</a>
 */
public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
  public interface Binder extends UiBinder<Widget, ApplicationView> {
  }

  @UiField
  ContentPanel north;
  @UiField
  ContentPanel east;
  @UiField
  ContentPanel west;
  @UiField
  ContentPanel center;

  @Inject
  public ApplicationView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
    
    north.setHeadingText("North");
    east.setHeadingText("East");
    west.setHeadingText("West");
    center.setHeadingText("Center");
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == ApplicationPresenter.SLOT_SetNorth) {
      north.setWidget(content);
    } else if (slot == ApplicationPresenter.SLOT_SetEast) {
      east.setWidget(content);
    } else if (slot == ApplicationPresenter.SLOT_SetWest) {
      west.setWidget(content);
    } else if (slot == ApplicationPresenter.SLOT_SetCenter) {
      center.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
}
