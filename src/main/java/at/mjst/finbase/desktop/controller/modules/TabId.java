/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.modules;

import java.util.UUID;

/**
 * Identifies the tabs added to {@link TabController}. The UUIDs are not really needed here, but I want to save this
 * form of implementation for further use cases.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-12
 */
public enum TabId
{
    /**
     * Accounts-Tab
     */
    ACCOUNT(UUID.fromString("60657143-3df3-4b8f-8301-6e9bff647789")),
    /**
     * Transactions-Tab
     */
    TRANSACTION(UUID.fromString("018818ed-6237-4948-84b5-f78e6292f6e1")),
    /**
     * AuditLog-Tab
     */
    AUDIT_LOG(UUID.fromString("2ecead43-f5f1-4e96-9461-fa24ff9aa2b1"));
    // member for the UUID assigned to the enum
    private final UUID uuid;

    /**
     * Private enum constructor to pass an {@link UUID} to the define
     *
     * @param uuid unique identifier for the controller id
     */
    TabId(UUID uuid)
    {
        this.uuid = uuid;
    }

    /**
     * @return the {@link UUID} provided with the enumeration
     */
    public UUID getUuid()
    {
        return uuid;
    }
}
