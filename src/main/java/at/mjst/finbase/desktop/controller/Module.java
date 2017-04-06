/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import org.jetbrains.annotations.NonNls;

/**
 * Guice dependency injection module configuration.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-14
 */
public class Module extends AbstractModule
{
    @NonNls
    private static final String NAME_CONTROLLER_BUS = "ControllerBus";
    private EventBus controllerBus;

    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure()
    {
        install(new at.mjst.finbase.desktop.controller.bind.Module());
    }

    /**
     * @return EventBus
     */
    @Provides
    @Named("ControllerBus")
    EventBus provideNamedEventBus()
    {
        // ToDo: maybe create a toProvider-Binding
        if (controllerBus == null) {
            controllerBus = new EventBus(NAME_CONTROLLER_BUS);
        }
        return controllerBus;
    }
}

