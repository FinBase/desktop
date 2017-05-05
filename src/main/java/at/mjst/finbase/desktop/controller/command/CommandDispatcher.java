/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.command;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import at.mjst.finbase.desktop.model.connection.ConnectionContext;
import at.mjst.finbase.desktop.model.connection.ConnectionEvent;
import at.mjst.finbase.desktop.model.connection.ConnectionManager;
import at.mjst.finbase.desktop.model.modules.Service;

/**
 * ToDo: Short class description
 * ToDo: interface, concrete implementations
 * ToDo: Put this class and commands into model-layer?
 *
 * Notes:
 * 1 dispatcher = 1 service!
 *
 * @param <T> type of the service being used for executing the command
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-25
 */
public class CommandDispatcher<T extends Service>
{
    private final ConnectionManager connectionManager;
    private final String connectionName; // hier connection name - context?
    private final Class<T> classType;
    private ExecutorService executor = null;

    @Inject
    public CommandDispatcher(ConnectionManager connectionManager, String connectionName, Class<T> classType)
    {
        this.connectionManager = connectionManager;
        this.connectionName = connectionName;
        this.classType = classType;
        // if the dispatcher is created later, maybe there's already a connection alive, so test it here
        if (connectionManager.isInitialized(connectionName)) {
            initExecutor();
        }
    }

    private void initExecutor()
    {
        executor = Executors.newSingleThreadExecutor();
    }

    @Subscribe
    void onConnectionChanged(ConnectionEvent event)
    {
        ConnectionContext context = event.getContext();
        if (Objects.equals(context.name(), connectionName)) {
            if (event instanceof ConnectionEvent.ContextRegistered) {
                context.registerDispatcher(this);
                // from now on, we can create (but not necessarily use, since there might not be any db-connection yet)
                //  a service-instance from the context!
                initExecutor();
            } else if (event instanceof ConnectionEvent.AnnounceShutdown) {
                shutdownExecutor();
                context.deregisterDispatcher(this);
                System.out.println("Dispatcher received remove-Event and deregistered");
            }
        }
    }

    private void shutdownExecutor()
    {
        ExecutorService internalExecutor = executor; // set current state to 'disabled'
        executor = null;
        internalExecutor.shutdownNow();
        // ToDo: shutdown executor and wait! (use own thread! => UIThread here! thread deregisters then...!
        // todo: something to do, if long running task gets disrupted by an early logoff...
        //        new Thread(shutdownCommand).start();
        //        internalExecutor.awaitTermination(); ...
    }

    public void startCommand(Command<T> command)
    {
        if (command != null) {
            validateExecutor();
            executor.execute(command.createWithService(getService()));
        }
    }

    private void validateExecutor()
    {
        if (executor == null) throw new RuntimeException("Dispatcher not enabled/Context not registered!");
    }

    private T getService()
    {
        // always create new service - or ensure service to be synchronized! (ToDo: let guice-module decide?)
        return connectionManager.getConnectedService(connectionName, classType);
    }
}
