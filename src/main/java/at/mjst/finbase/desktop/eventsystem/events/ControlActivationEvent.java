/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem.events;

import at.mjst.finbase.desktop.controller.ControllerId;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-12
 */
public class ControlActivationEvent extends Event<ControllerId>
{
    private final boolean enabled;

    /**
     * @param senderId the id of the event's sender
     * @param enabled  specifies, if the control is enabled or disabled
     */
    public ControlActivationEvent(ControllerId senderId, boolean enabled)
    {
        super(senderId);
        this.enabled = enabled;
    }

    public boolean getEnabled()
    {
        return enabled;
    }
}
