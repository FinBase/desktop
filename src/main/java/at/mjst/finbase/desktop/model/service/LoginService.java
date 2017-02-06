/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service;

import com.google.common.base.Throwables;
import com.google.inject.Inject;

import java.sql.SQLException;

import at.mjst.finbase.desktop.dto.Credentials;
import at.mjst.finbase.desktop.model.SessionProvider;
import javafx.scene.control.Alert;

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

    public boolean doLogin(Credentials credentials)
    {
        Credentials.validate(credentials);
        try {
            System.out.println(String.format("Login: Attempting to connect as '%s'", credentials.getUserName()));
            sessionProvider.initConnection(credentials);
            auditLogService.recordLogin();
            System.out.println("Login: success");
            return true;
        } catch (Throwable e) {
            @SuppressWarnings("ThrowableResultOfMethodCallIgnored") Throwable cause = Throwables.getRootCause(e);
            if (cause instanceof SQLException) {
                // @see: http://code.makery.ch/blog/javafx-dialogs-official/
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("FinBaseTitle");
                alert.setHeaderText("Login failed!");
                alert.setContentText(cause.getMessage());
                alert.showAndWait();
                return false;
            } else {
                throw e;
            }
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
            System.out.println("Logout: success");
        } else {
            System.out.println("Logout: not logged in");
        }
    }
}
