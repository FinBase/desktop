/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem.events;

import org.jetbrains.annotations.NonNls;

/**
 * Base class for events, that are sent via the {@link at.mjst.finbase.desktop.eventsystem.UIBus} or
 * {@link at.mjst.finbase.desktop.eventsystem.ModelBus} event-buses.
 * For specialities in communication model -> UI see: {@link at.mjst.finbase.desktop.eventsystem.UIEventDispatcher}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-10
 */
public class Event<EnumType extends EventEnum>
{
    @NonNls
    private static final String STR_SENDER_ID = "%s (senderId=%s, UUID=%s)";
    private final EnumType senderId;

    /**
     * @param senderId the id of the event's sender
     */
    public Event(EnumType senderId)
    {
        this.senderId = senderId;
    }

    /**
     * @return the event's senderId, if set
     */
    public EnumType getSenderId()
    {
        return senderId;
    }

    @Override
    public String toString()
    {
        if (senderId != null) {
            return String.format(STR_SENDER_ID, super.toString(), senderId, senderId.getUuid());
        } else {
            return super.toString();
        }
    }
}
