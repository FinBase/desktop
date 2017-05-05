/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.modules.auditlog;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import at.mjst.finbase.desktop.controller.command.Command;
import at.mjst.finbase.desktop.model.modules.auditlog.AuditLogService;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-05-03
 */
public final class AuditLogLoadCommand extends Command<AuditLogService>
{
    private final boolean param1;

    private AuditLogLoadCommand(AuditLogService service, boolean param1)
    {
        super(service);
        this.param1 = param1;
    }

    @NotNull
    public static AuditLogLoadCommand createCommand(boolean param1)
    {
        return new AuditLogLoadCommand(null, param1);
    }

    @Override
    protected void execute(@NotNull AuditLogService service)
    {
        service.executeLoad(); // if executeLoad fails -> Exception
    }

    @NotNull
    @Override
    public Command<AuditLogService> createWithService(AuditLogService service)
    {
        return new AuditLogLoadCommand(service, getParam1());
    }

    @Contract(pure = true)
    public boolean getParam1()
    {
        return param1;
    }
}
