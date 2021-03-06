/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.auditlog;

import at.mjst.finbase.desktop.model.modules.Service;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-13
 */
public interface AuditLogService extends Service
{
    void recordLogin();

    void recordLogout();

    boolean executeLoad();
}
