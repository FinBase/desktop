/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.connection;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import at.mjst.finbase.desktop.common.credentials.Credentials;
import at.mjst.finbase.desktop.eventsystem.ModelBus;

/**
 * ToDo: Short class description
 * ToDo: what to do, if connection gets fucked up?
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-25
 */
class ConnectionManagerImpl implements ConnectionManager
{
    @NonNls
    protected static final String ERR_EXISTING_CONNECTION = "Please shutdown existing connection '%s' first";
    private final Map<String, ManagedConnectionContext> connections = new HashMap<>();
    private final ManagedConnectionContextProvider managedConnectionContextProvider;
    private final EventBus eventBus;

    @Inject
    public ConnectionManagerImpl(ManagedConnectionContextProvider managedConnectionContextProvider,
                                 @ModelBus EventBus eventBus)
    {
        this.managedConnectionContextProvider = managedConnectionContextProvider;
        this.eventBus = eventBus;
    }

    @Override
    public void initConnection(String connectionName, Credentials credentials)
    {
        Credentials.validate(credentials);
        ManagedConnectionContext c = getOrCreateContext(connectionName);
        if (!c.isInitialized()) {
            c.initConnection(credentials);
        } else {
            throw new RuntimeException(String.format(ERR_EXISTING_CONNECTION, connectionName));
        }
    }

    @Override
    public void closeConnectionAsync(String connectionName)
    {
        getOrCreateContext(connectionName).closeConnectionAsync();
        removeContext(connectionName); // todo: good place here?
    }

    private synchronized void removeContext(String connectionName)
    {
        connections.remove(connectionName);
    }

    @Override
    public boolean isInitialized(String connectionName)
    {
        return getOrCreateContext(connectionName).isInitialized();
    }

    @Override
    public <T> T getConnectedService(String connectionName, Class<T> supportedClass)
    {
        return getOrCreateContext(connectionName).getSessionInstance(supportedClass);
    }

    @Override
    public synchronized void closeAllConnections()
    {
        for (ManagedConnectionContext c : connections.values()) {
            if (c.isInitialized()) {
                c.closeConnectionAsync();
            }
            // todo: close async and execute wait (do not close app immediately) !
        }
        connections.clear();
    }

    @NotNull
    private synchronized ManagedConnectionContext getOrCreateContext(String connectionName)
    {
        ManagedConnectionContext mc = connections.get(connectionName);
        if (mc == null) {
            return createContext(connectionName);
        } else {
            return mc;
        }
    }

    @NotNull
    private ManagedConnectionContext createContext(String connectionName)
    {
        ManagedConnectionContext context = managedConnectionContextProvider.get(connectionName);
        putContext(connectionName, context);
        // Post to listeners, that there's a new connection-object / injector
        eventBus.post(ConnectionEvent.contextRegistered(context));
        return context;
    }

    private synchronized void putContext(String connectionName, ManagedConnectionContext context)
    {
        connections.put(connectionName, context);
    }
}
