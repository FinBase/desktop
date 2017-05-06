/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.modules;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.ResourceBundle;

import at.mjst.finbase.desktop.eventsystem.UIBus;
import at.mjst.finbase.desktop.model.connection.ConnectionEvent;
import at.mjst.finbase.desktop.view.IdentifiedTab;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-12
 */
public class TabController implements Initializable
{
    @FXML
    private TabPane tabPane;
    @Inject
    @UIBus
    private EventBus eventBus;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tabPane.setDisable(true);
        // whenever the pane gets enabled/disabled, this event will be sent...
        tabPane.disabledProperty().addListener(new DisabledChangeListener());
        // Listener for switching from one tab to another...
        tabPane.getSelectionModel().selectedItemProperty().addListener(new TabChangeListener());
    }

    @Subscribe
    public void onLogin(ConnectionEvent event)
    {
        System.out.println("Event in TabController " + event);
        tabPane.setDisable(!(event instanceof ConnectionEvent.Established));
    }

    @Nullable
    private TabId getTabId(Tab tab)
    {
        if (tab instanceof IdentifiedTab) {
            return ((IdentifiedTab) tab).getTabId();
        } else {
            return null;
        }
    }

    /**
     * Notifies all listeners about enabled/disabled-state change by sending the {@link ControlActivationEvent} and
     * the {@link TabSwitchEvent} for the first tab selected via the {@link UIBus}
     */
    class DisabledChangeListener implements ChangeListener<Boolean>
    {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
        {
            System.out.println("TabPane disabled state changed! " + oldValue + "->" + newValue);
            eventBus.post(ControlActivationEvent.create(this, !newValue)); // note: NOT disabled == enabled
            // here...
            if (!newValue) {
                TabId tabId = getTabId(tabPane.getSelectionModel().getSelectedItem());
                eventBus.post(TabSwitchEvent.create(this, null, tabId));
            }
        }
    }

    /**
     * Notifies all listeners about tab switching by sending a {@link TabSwitchEvent} via {@link UIBus}
     */
    class TabChangeListener implements ChangeListener<Tab>
    {
        @Override
        public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue)
        {
            TabId oldTabId = getTabId(oldValue);
            TabId newTabId = getTabId(newValue);
            eventBus.post(TabSwitchEvent.create(this, oldTabId, newTabId));
        }
    }
}

