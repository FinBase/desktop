/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model;

import at.mjst.finbase.desktop.dto.Credentials;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-31
 */
public interface SessionProvider
{
    void initConnection(Credentials credentials);

    void closeConnection();

    boolean initialized();

    <T> T getSessionInstance(Class<T> type);
}
