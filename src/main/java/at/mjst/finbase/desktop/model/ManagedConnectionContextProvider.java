/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model;

import com.google.common.base.Strings;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Injector;

import at.mjst.finbase.desktop.Resource;
import at.mjst.finbase.desktop.eventsystem.ModelBus;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-28
 */
class ManagedConnectionContextProvider implements ConnectionContextProvider
{
    @Inject
    @ModelBus
    private EventBus eventBus;
    @Inject
    private Injector injector;

    @Override
    public ManagedConnectionContext get(String connectionName)
    {
        if (Strings.isNullOrEmpty(connectionName)) {
            throw new RuntimeException("ConnectionName not set!");
        }
        return new JpaConnectionContext(getNewChildInjector(), eventBus, connectionName);
    }

    private Injector getNewChildInjector()
    {
        return injector.createChildInjector(new _JpaModule(Resource.ID_PERSISTENCE));
    }
}
