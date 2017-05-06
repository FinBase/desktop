/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.auditlog;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

import at.mjst.finbase.desktop.common.util.NlsManager;
import at.mjst.finbase.desktop.eventsystem.ModelBus;
import at.mjst.finbase.desktop.model.modules.user.UserService;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-12
 */
class AuditLogDbService implements AuditLogService
{
    @Inject
    private AuditLogContainer auditLogContainer;
    @Inject
    private UserService userService;
    @Inject
    private AuditLogDAO auditLogDAO;
    @Inject
    @ModelBus
    private EventBus eventBus;

    public void recordLogin()
    {
        AuditLog log = createNewAuditLog();
        log.setApplication(NlsManager.getNls("finbase.title"));
        log.setTimestampOn(LocalDateTime.now());
        //         = sessionProvider.getSessionInstance(UserService.class);
        log.setUser(userService.getCurrentUser());
        auditLogDAO.insertOrUpdate(log);
    }

    private AuditLog createNewAuditLog()
    {
        AuditLog log = new AuditLog();
        auditLogContainer.setAuditLog(log);
        return log;
    }
    //    private AuditLogDAO getAuditLogDAO()
    //    {
    //        return sessionProvider.getSessionInstance(AuditLogDAO.class);
    //    }

    public void recordLogout()
    {
        //        AuditLogContainer container = sessionProvider.getSessionInstance(AuditLogContainer.class);
        AuditLog log = auditLogContainer.getAuditLog(); // todo: only works, if id is passed!
        if (log != null) {
            auditLogDAO.merge(log, LocalDateTime.now());
        } else {
            throw new RuntimeException("Session AuditLog not found!");
        }
    }

    public boolean executeLoad()
    {
        //        AuditLogDAO dao = getAuditLogDAO();
        List<AuditLog> list = auditLogDAO.queryAll();
        if (list != null) {
            System.out.println("done!");
            eventBus.post(new AuditLogDataEvent(this, list));
            return true;
        } else {
            return false;
        }
    }
}
