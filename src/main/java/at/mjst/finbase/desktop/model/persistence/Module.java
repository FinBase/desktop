/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Singleton;

/**
 * Module for configuration of persistenceInjector (this is a guice child-injector creating classes that need
 * initialized {@link com.google.inject.persist.PersistService} and open db-connection to properly inject
 * {@link javax.persistence.EntityManager} and {@link com.google.inject.persist.jpa.JpaLocalTxnInterceptor})
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-12
 */
public class Module extends AbstractModule
{
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure()
    {
        install(new at.mjst.finbase.desktop.model.persistence.dao.Module());
        // Bind the AuditLogContainer as singleton within this child-injectors lifetime
        bind(AuditLogContainer.class).in(Singleton.class);
    }
}
