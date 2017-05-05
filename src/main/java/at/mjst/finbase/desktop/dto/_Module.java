/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;

/**
 * ToDo: This package will be removed!
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
        install(new at.mjst.finbase.desktop.dto.columnselection._Module());
        install(new at.mjst.finbase.desktop.dto.columnselection.columnselection._Module()); // TODO: !!! FIXME!!!
    }
}
