/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.factory;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Injector;

import at.mjst.finbase.desktop.eventsystem.UIBus;
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
    /**
     * Injector, that creates the controller instances (usually, a child-injector here)
     */
    private final Injector childInjector;
    /**
     * UI-{@link EventBus}
     */
    private final EventBus eventBus;

    /**
     * Creates the controller-factory with required dependencies
     *
     * @param childInjectorProvider provider, that returns an {@link Injector} that creates the controller instances
     * @param eventBus              the {@link EventBus} for the UI
     */
    @Inject
    public ControllerFactory(ChildInjectorProvider childInjectorProvider, @UIBus EventBus eventBus)
    {
        this.childInjector = childInjectorProvider.get();
        this.eventBus = eventBus;
    }

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
            System.out.println("Injecting..." + param.toString());
            // use the injector to create the controller-instance, so it has control over the complete object-graph!
            Object obj = childInjector.getInstance(param);
            // set the eventBus to every controller class
            eventBus.register(obj);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e); // fatal, just bail...
        }
    }
}

