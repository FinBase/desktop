/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Creates and configures the child-injector, that creates the controller-instances
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-23
 */
public class ControllerChildInjectorProvider implements ChildInjectorProvider
{
    @Inject
    private Injector injector;

    @Override
    public Injector get()
    {
        return injector.createChildInjector(new _ControllerModule());
    }
}
