/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop;

import com.google.inject.AbstractModule;

/**
 * Basic application module
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-25
 */
public class _Module extends AbstractModule
{
    @Override
    protected void configure()
    {
        install(new at.mjst.finbase.desktop.controller._Module());
        install(new at.mjst.finbase.desktop.dto._Module()); // todo: to be removed!
        install(new at.mjst.finbase.desktop.eventsystem._Module());
        install(new at.mjst.finbase.desktop.model._Module());
    }
}
