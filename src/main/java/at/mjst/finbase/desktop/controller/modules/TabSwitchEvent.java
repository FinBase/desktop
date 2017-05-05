/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.modules;

import org.jetbrains.annotations.Contract;

import at.mjst.finbase.desktop.eventsystem.Event;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-08-03
 */
public final class TabSwitchEvent extends Event
{
    private final TabId oldTabId;
    private final TabId newTabId;

    /**
     * @param sender   the event's sender
     * @param oldTabId tab switched from
     * @param newTabId tab switched to
     */
    public TabSwitchEvent(Object sender, TabId oldTabId, TabId newTabId)
    {
        super(sender);
        this.oldTabId = oldTabId;
        this.newTabId = newTabId;
    }

    @Contract(pure = true)
    public TabId getOldTabId()
    {
        return oldTabId;
    }

    @Contract(pure = true)
    public TabId getNewTabId()
    {
        return newTabId;
    }
}
