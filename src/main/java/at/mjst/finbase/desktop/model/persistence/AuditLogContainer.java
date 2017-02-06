/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.persistence;

import at.mjst.finbase.desktop.model.entity.AuditLog;

/**
 * ToDo: Short class description
 * ToDo: threadsafe, as it is used as singleton!
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-12
 */
public class AuditLogContainer
{
    private AuditLog auditLog;

    public AuditLogContainer()
    {
    }

    public AuditLog getAuditLog() // ToDo: synchronized!
    {
        return auditLog;
    }

    public void setAuditLog(AuditLog auditLog) // ToDo: synchronized!
    {
        this.auditLog = auditLog;
    }
}
