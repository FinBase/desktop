/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model;

import com.google.common.base.Throwables;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.persist.PersistService;

import org.hibernate.cfg.AvailableSettings;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import at.mjst.finbase.desktop.dto.Credentials;
import at.mjst.finbase.desktop.eventsystem.ModelBus;
import at.mjst.finbase.desktop.eventsystem.events.ConnectionEvent;
import at.mjst.finbase.desktop.model.service.AuditLogService;

/**
 * ToDo: Short class description
 * This class is threadsafe.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-31
 */
class JpaConnectionContext implements ManagedConnectionContext
{
    private final Injector injector; // the persistence child-injector
    private final PersistService service;
    private final EventBus eventBus;
    private final Properties connectionProperties;
    private final String name;
    private final AuditLogService auditLogService;
    private final Set<Object> registeredDispatchers = new HashSet<>();
    private boolean initialized = false;

    @Inject
    public JpaConnectionContext(Injector injector, @ModelBus EventBus eventBus, String name)
    {
        this.injector = injector;
        this.service = getSessionInstance(PersistService.class);
        this.eventBus = eventBus;
        this.connectionProperties = getSessionInstance(Key.get(Properties.class, ConnectionProperties.class));
        this.name = name;
        auditLogService = getSessionInstance(AuditLogService.class);
        System.out.println(String.format("%s created with name '%s'", getClass().getName(), name()));
    }

    // todo: ?   @Override
    public <T> T getSessionInstance(Key<T> type)
    {
        return injector.getInstance(type);
    }

    public String name()
    {
        return name;
    }

    @Override
    public synchronized boolean isInitialized()
    {
        return initialized; // todo: where to test for connection still alive?
    }

    @Override
    public <T> T getSessionInstance(Class<T> type)
    {
        return injector.getInstance(type);
    }

    @Override
    public synchronized void registerDispatcher(Object obj)
    {
        registeredDispatchers.add(obj);
    }

    @Override
    public synchronized void deregisterDispatcher(Object obj)
    {
        registeredDispatchers.remove(obj);
        if (registeredDispatchers.size() == 0) {
            closeConnectionForced();
        }
    }

    @Override
    public synchronized void initConnection(Credentials credentials)
    {
        if (isInitialized()) {
            throw new RuntimeException("Connection already initialized");
            // .. this will also be thrown, if service.start() is called a second time!
        }
        try {
            assignCredentialsToJpaProperties(credentials);
            service.start();
            initialized = true;
            auditLogService.recordLogin();
            eventBus.post(new ConnectionEvent.Established(this));
        } catch (Exception e) {
            e.printStackTrace(); // ToDo: log me!
            Throwable cause = Throwables.getRootCause(e);
            eventBus.post(new ConnectionEvent.Failure(this, cause.getMessage()));
        }
    }

    private void assignCredentialsToJpaProperties(Credentials credentials)
    {
        connectionProperties.put(AvailableSettings.JPA_JDBC_USER, credentials.getUserName());
        connectionProperties.put(AvailableSettings.JPA_JDBC_PASSWORD, credentials.getPassword());
        // connectionProperties.put(AvailableSettings.INTERCEPTOR, interceptor);
        // connectionProperties.put(AvailableSettings.SESSION_SCOPED_INTERCEPTOR, interceptor);
    }

    @Override
    public synchronized void closeConnectionForced()
    {
        System.out.println("Forceful shutdown initiated...");
        try {
            if (isInitialized()) {
                auditLogService.recordLogout();
                service.stop(); // we can never start this one again!
            } else {
                throw new RuntimeException("Connection has not been initialized!");
            }
        } finally {
            initialized = false;
        }
        eventBus.post(new ConnectionEvent.Closed(this));
    }

    @Override
    public void closeConnectionAsync()
    {
        if (isInitialized()) {
            eventBus.post(new ConnectionEvent.AnnounceShutdown(this));
        } else {
            throw new RuntimeException("Connection has not been initialized!");
        }
        // todo: post a thread, that waits and forces shutdown, if someone does not care to deregister!
    }
}
