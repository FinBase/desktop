/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;

/**
 * Guice dependency injection module configuration.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-14
 */
public class _Module extends AbstractModule
{
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure()
    {
        bind(EventBus.class).annotatedWith(UIBus.class).toProvider(_EventBusProvider.UI.class).asEagerSingleton();
        bind(EventBus.class).annotatedWith(ModelBus.class).toProvider(_EventBusProvider.Model.class).asEagerSingleton();
    }
}
