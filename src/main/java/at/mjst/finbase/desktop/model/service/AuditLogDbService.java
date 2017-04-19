/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import at.mjst.finbase.desktop.eventsystem.ModelBus;
import at.mjst.finbase.desktop.eventsystem.events.AuditLogDataEvent;
import at.mjst.finbase.desktop.model.SessionProvider;
import at.mjst.finbase.desktop.model.entity.AuditLog;
import at.mjst.finbase.desktop.model.persistence.AuditLogContainer;
import at.mjst.finbase.desktop.model.persistence.dao.AuditLogDAO;
import at.mjst.finbase.desktop.util.NlsManager;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-12
 */
class AuditLogDbService implements AuditLogService
{
    @Inject
    private SessionProvider sessionProvider;
    @Inject
    @ModelBus
    private EventBus eventBus;

    public void recordLogin()
    {
        AuditLog log = createNewAuditLog();
        log.setApplication(NlsManager.getNls("finbase.title"));
        log.setTimestampOn(new Timestamp(new Date().getTime()));
        UserService userService = sessionProvider.getSessionInstance(UserService.class);
        log.setUser(userService.getCurrentUser());
        getAuditLogDAO().insertOrUpdate(log);
    }

    private AuditLog createNewAuditLog()
    {
        AuditLog log = sessionProvider.getSessionInstance(AuditLog.class); // normal creation via main-injector?
        AuditLogContainer container = sessionProvider.getSessionInstance(AuditLogContainer.class);
        container.setAuditLog(log);
        return log;
    }

    private AuditLogDAO getAuditLogDAO()
    {
        return sessionProvider.getSessionInstance(AuditLogDAO.class);
    }

    public void recordLogout()
    {
        AuditLogContainer container = sessionProvider.getSessionInstance(AuditLogContainer.class);
        AuditLog log = container.getAuditLog();
        if (log != null) {
            log.setTimestampOff(new Timestamp(new Date().getTime()));
            getAuditLogDAO().insertOrUpdate(log);
        } else {
            throw new RuntimeException("Session AuditLog not found!");
        }
    }

    public boolean executeLoad()
    {
        AuditLogDAO dao = getAuditLogDAO();
        List<AuditLog> list = dao.queryAll();
        if (list != null) {
            System.out.println("done!");
            eventBus.post(new AuditLogDataEvent(this, list));
            return true;
        } else {
            return false;
        }
    }
}
