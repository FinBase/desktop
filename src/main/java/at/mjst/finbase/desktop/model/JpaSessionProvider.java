/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import org.hibernate.cfg.AvailableSettings;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import at.mjst.finbase.desktop.Resource;
import at.mjst.finbase.desktop.dto.Credentials;

/**
 * ToDo: Short class description
 * ToDo: threadsafe, as it is used as singleton!
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-31
 */
public class JpaSessionProvider implements SessionProvider
{
    @Inject
    private Properties connectionProperties;
    @Inject
    private Injector injector; // Main injector!
    private Injector persistenceInjector;
    // further object instances...
    private PersistService service;

    public JpaSessionProvider()
    {
        System.out.println(getClass().getName() + " created");
    }

    @Override
    public void initConnection(Credentials credentials)
    {
        if (initialized()) {
            throw new RuntimeException("Connection already initialized");
            // .. this will also be thrown, if service.start() is called a second time!
        }
        try {
            assignCredentialsToJpaProperties(credentials);
            buildChildInjector();
            buildAndStartPersistServices();
        } catch (Exception e) {
            clearInstances();
            throw e;
        }
    }

    private void assignCredentialsToJpaProperties(Credentials credentials)
    {
        connectionProperties.put(AvailableSettings.JPA_JDBC_USER, credentials.getUserName());
        connectionProperties.put(AvailableSettings.JPA_JDBC_PASSWORD, credentials.getPassword());
        // connectionProperties.put(AvailableSettings.INTERCEPTOR, interceptor);
        // connectionProperties.put(AvailableSettings.SESSION_SCOPED_INTERCEPTOR, interceptor);
    }

    private void buildChildInjector()
    {
        List<Module> modules = new LinkedList<>();
        // add the connectionProperties to the JPAModule - we'll 'inject' userName & password here!
        modules.add(new JpaPersistModule(Resource.ID_PERSISTENCE).properties(connectionProperties));
        modules.add(new at.mjst.finbase.desktop.model.persistence.Module());
        // create childInjector!
        persistenceInjector = injector.createChildInjector(modules);
    }

    private void buildAndStartPersistServices()
    {
        service = getSessionInstance(PersistService.class);
        service.start();
    }

    private void clearInstances()
    {
        service = null;
        persistenceInjector = null; // throw away childInjector and (maybe) its object graph
    }

    @Override
    public void closeConnection()
    {
        try {
            if (initialized()) {
                stopPersistService();
            } else {
                throw new RuntimeException("Connection has not been initialized!");
            }
        } finally {
            clearInstances();
        }
    }

    private void stopPersistService()
    {
        service.stop(); // we can never start this one again!
    }

    @Override
    public boolean initialized()
    {
        return (service != null); // todo: where to test for connection still alive?
    }

    @Override
    public <T> T getSessionInstance(Class<T> type)
    {
        return persistenceInjector.getInstance(type);
    }
}
