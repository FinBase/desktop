/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem.events;

import at.mjst.finbase.desktop.controller.ControllerId;
import at.mjst.finbase.desktop.controller.main.TabId;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-08-03
 */
public class TabSwitchEvent extends Event<ControllerId>
{
    private final TabId oldTabId;
    private final TabId newTabId;

    /**
     * @param senderId the id of the event's sender
     * @param oldTabId tab switched from
     * @param newTabId tab switched to
     */
    public TabSwitchEvent(ControllerId senderId, TabId oldTabId, TabId newTabId)
    {
        super(senderId);
        this.oldTabId = oldTabId;
        this.newTabId = newTabId;
    }

    public TabId getOldTabId()
    {
        return oldTabId;
    }

    public TabId getNewTabId()
    {
        return newTabId;
    }
}
