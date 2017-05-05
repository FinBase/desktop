/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem;

import org.jetbrains.annotations.NonNls;

/**
 * Base class for events, that are sent via the {@link at.mjst.finbase.desktop.eventsystem.UIBus} or
 * {@link at.mjst.finbase.desktop.eventsystem.ModelBus} event-buses.
 * For specialities in communication model -> UI see: {@link at.mjst.finbase.desktop.eventsystem.UIEventDispatcher}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-10
 */
public class Event
{
    @NonNls
    private static final String STR_SENDER = "%s (sender=%s)";
    private final Object sender;

    /**
     * @param sender the event's sender
     */
    public Event(Object sender)
    {
        this.sender = sender;
    }

    /**
     * @return the event's sender, if set
     */
    public Object getSender()
    {
        return sender;
    }

    @Override
    public String toString()
    {
        if (sender != null) {
            return String.format(STR_SENDER, super.toString(), sender.toString());
        } else {
            return super.toString();
        }
    }
}
