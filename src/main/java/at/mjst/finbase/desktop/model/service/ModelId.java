/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service;

import java.util.UUID;

import at.mjst.finbase.desktop.eventsystem.events.EventEnum;

/**
 * Identifiers for models/services, that take part at the event-system
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-12
 */
public enum ModelId implements EventEnum
{
    /**
     * Id for the {@link at.mjst.finbase.desktop.model.service.LoginService}
     */
    LOGIN(UUID.fromString("fbe9bc41-bcb0-42dc-a803-3fbe424a518e"));
    // member for the UUID assigned to the enum
    private final UUID uuid;

    /**
     * Private enum constructor to pass an {@link UUID} to the define
     *
     * @param uuid unique identifier for the controller id
     */
    ModelId(UUID uuid)
    {
        this.uuid = uuid;
    }

    @Override
    public UUID getUuid()
    {
        return uuid;
    }
}
