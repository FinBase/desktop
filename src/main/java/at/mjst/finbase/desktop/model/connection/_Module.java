/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.connection;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;

/**
 * Guice dependency injection module configuration.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-15
 */
public class _Module extends AbstractModule
{
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure()
    {
        bind(ConnectionContextProvider.class).to(ManagedConnectionContextProvider.class);
        // create the ConnectionManager as eager singleton
        bind(ConnectionManager.class).to(ConnectionManagerImpl.class).asEagerSingleton();
        // .. rest is being bound in modules-package!!
    }
}
