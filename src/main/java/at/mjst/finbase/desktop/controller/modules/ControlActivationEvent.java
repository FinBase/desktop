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
 * @since 2017-04-12
 */
final class ControlActivationEvent extends Event
{
    private final boolean enabled;

    /**
     * @param sender  the event's sender
     * @param enabled specifies, if the control is enabled or disabled
     */
    public ControlActivationEvent(Object sender, boolean enabled)
    {
        super(sender);
        this.enabled = enabled;
    }

    @Contract(pure = true)
    public boolean getEnabled()
    {
        return enabled;
    }
}
