/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service;

import com.google.common.base.Throwables;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

import at.mjst.finbase.desktop.dto.Credentials;
import at.mjst.finbase.desktop.eventsystem.ModelBus;
import at.mjst.finbase.desktop.eventsystem.events.LoginEvent;
import at.mjst.finbase.desktop.model.SessionProvider;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-21
 */
public class LoginService
{
    @Inject
    private SessionProvider sessionProvider;
    @Inject
    private AuditLogService auditLogService;
    @Inject
    @ModelBus
    private EventBus eventBus;

    /**
     * Initializes the {@link SessionProvider} and tries to establish a db-connection using the given connection
     * information and {@link Credentials}.
     *
     * @param credentials login credentials
     */
    public void doLogin(Credentials credentials)
    {
        Credentials.validate(credentials);
        try {
            System.out.println(String.format("Login: Attempting to connect as '%s'", credentials.getUserName()));
            sessionProvider.initConnection(credentials);
            auditLogService.recordLogin();
            System.out.println("Login: success");
            eventBus.post(new LoginEvent.LoginSuccess(this));
        } catch (Throwable e) {
            // ToDo: log me!
            Throwable cause = Throwables.getRootCause(e);
            eventBus.post(new LoginEvent.LoginFailedEvent(this, cause.getMessage()));
        }
    }

    public boolean loggedIn()
    {
        return sessionProvider.initialized();
    }

    public void doLogout()
    {
        if (sessionProvider.initialized()) {
            auditLogService.recordLogout();
            sessionProvider.closeConnection();
            eventBus.post(new LoginEvent.LogoffSuccess(this));
            System.out.println("Logout: success");
        } else {
            System.out.println("Logout: not logged in");
        }
    }
}
