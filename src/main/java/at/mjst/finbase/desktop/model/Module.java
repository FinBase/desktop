/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;

/**
 * Guice dependency injection module configuration.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-15
 */
public class Module extends AbstractModule
{
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure()
    {
        install(new at.mjst.finbase.desktop.model.entity.Module());
        install(new at.mjst.finbase.desktop.model.service.Module());
        // create the SessionProvider as singleton and as early as possible to detect errors as soon as possible
        bind(SessionProvider.class).to(JpaSessionProvider.class).asEagerSingleton();
    }
}
