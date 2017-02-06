/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

import at.mjst.finbase.desktop.controller.events.EventBusListener;
import javafx.util.Callback;

/**
 * Factory used to create and configure the fxml-controllers and enable
 * their {@link com.google.common.eventbus.EventBus}-functionality if they implement the appropriate interface.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-12
 */
public class ControllerFactory implements Callback<Class<?>, Object>
{
    @Inject
    @Named("ControllerBus")
    private EventBus eventBus;
    @Inject
    private Injector injector;

    /**
     * The <code>call</code> method is called when required, and is given a
     * single argument of type P, with a requirement that an object of type R
     * is returned.
     *
     * @param param The single argument upon which the returned value should be determined.
     * @return An object of type R that may be determined based on the provided parameter value.
     */
    @Override
    public Object call(Class<?> param)
    {
        try {
            // use the injector to create the controller-instance, so it has control over the complete object-graph!
            Object obj = injector.getInstance(param);
            // set the eventBus, if it is supported
            if (obj instanceof EventBusListener) {
                eventBus.register(obj);
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e); // fatal, just bail...
        }
    }
}

