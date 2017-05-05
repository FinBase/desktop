/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.auditlog;

import org.jetbrains.annotations.Contract;

import java.util.List;

import at.mjst.finbase.desktop.eventsystem.Event;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-19
 */
public final class AuditLogDataEvent extends Event
{
    private final List<AuditLog> list;

    public AuditLogDataEvent(Object sender, List<AuditLog> list)
    {
        super(sender);
        this.list = list;
    }

    @Contract(pure = true)
    public List<AuditLog> getList()
    {
        return list;
    }
}
