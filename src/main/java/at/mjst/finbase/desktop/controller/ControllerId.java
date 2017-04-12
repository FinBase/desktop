/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller;

import java.util.UUID;

import at.mjst.finbase.desktop.eventsystem.events.EventEnum;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-12
 */
public enum ControllerId implements EventEnum
{
    /**
     * Id for the {@link at.mjst.finbase.desktop.controller.main.TabController}
     */
    TAB_CONTROLLER(UUID.fromString("60657143-3df3-4b8f-8301-6e9bff647789")),
    /**
     * Id for the {@link at.mjst.finbase.desktop.controller.main.AuditLogController}
     */
    AUDIT_LOG(UUID.fromString("2ecead43-f5f1-4e96-9461-fa24ff9aa2b1"));
    // member for the UUID assigned to the enum
    private final UUID uuid;

    /**
     * Private enum constructor to pass an {@link UUID} to the define
     *
     * @param uuid unique identifier for the controller id
     */
    ControllerId(UUID uuid)
    {
        this.uuid = uuid;
    }

    @Override
    public UUID getUuid()
    {
        return uuid;
    }
}
