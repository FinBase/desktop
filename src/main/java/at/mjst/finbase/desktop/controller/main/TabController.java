/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.main;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

import java.net.URL;
import java.util.ResourceBundle;

import at.mjst.finbase.desktop.controller.events.EventBusListener;
import at.mjst.finbase.desktop.controller.events.LoginEvent;
import at.mjst.finbase.desktop.controller.events.TabActivationEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-12
 */
public class TabController implements Initializable, EventBusListener
{
    public TabPane tabPane;
    public Tab auditTab;
    @Inject
    Injector injector;

    @Subscribe
    public void handleLoginEvent(LoginEvent event)
    {
        System.out.println("LoginEvent in TabController");
        tabPane.setDisable(false);
    }

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
        tabPane.disabledProperty().addListener((observable, oldValue, newValue) -> {
            // alternative zu siehe oben (EventBus)
            System.out.println("TabPane state changed!");
        });
        tabPane.getSelectionModel().selectedItemProperty().addListener(
                injector.getInstance(ActiveTabChangeListener.class));
    }

    public void osc(Event event)
    {
        System.out.println("OSC");
    }
}

class ActiveTabChangeListener implements ChangeListener<Tab>
{
    @Inject
    @Named("ControllerBus")
    private EventBus eventBus;

    /**
     * This method needs to be provided by an implementation of
     * {@code ChangeListener}. It is called if the value of an
     * {@link ObservableValue} changes.
     * <p>
     * In general is is considered bad practice to modify the observed value in
     * this method.
     *
     * @param observable The {@code ObservableValue} which value changed
     * @param oldValue   The old value
     */
    @Override
    public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue)
    {
        eventBus.post(new TabActivationEvent(newValue));
    }
}
