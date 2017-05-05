/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.command;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import at.mjst.finbase.desktop.eventsystem.UIBus;
import at.mjst.finbase.desktop.model.connection.ConnectionManager;
import at.mjst.finbase.desktop.model.modules.auditlog.AuditLogService;

import static at.mjst.finbase.desktop.model.connection.ConnectionManager.LOCAL;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-25
 */
public class _Module extends AbstractModule
{
    @Override
    protected void configure()
    {
        //        install(new at.mjst.finbase.desktop.controller.modules.columnselection._Module());
        //        bind(Key.get(new TypeLiteral<ServiceDispatcher<AuditLogService>>(){}).toProvider();
    }

    @Provides
    CommandDispatcher<AuditLogService> getAuditLogServiceDispatcher(ConnectionManager connectionManager,
                                                                    @UIBus EventBus eventBus)
    {
        CommandDispatcher<AuditLogService> dispatcher = new CommandDispatcher<>(connectionManager, LOCAL,
                AuditLogService.class);
        // We use the UI-bus here, because dispatcher runs on the UI-thread and we only can receive-model-events 'later'
        eventBus.register(dispatcher);
        return dispatcher;
    }
}
