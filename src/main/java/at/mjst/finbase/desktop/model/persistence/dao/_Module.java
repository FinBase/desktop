/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.persistence.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-12
 */
public class _Module extends AbstractModule
{
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure()
    {
        bind(AuditLogDAO.class);
        bind(CurrentUserDAO.class).to(CurrentUserDAONativeSQL.class);
    }
}
