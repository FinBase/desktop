/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem.events;

import java.util.UUID;

/**
 * Enums for {@link Event}s require this interface to be implemented
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-12
 */
public interface EventEnum
{
    /**
     * @return the {@link UUID} provided with the enumeration
     */
    UUID getUuid();
}