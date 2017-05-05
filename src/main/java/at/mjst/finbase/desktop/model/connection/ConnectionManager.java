/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.connection;

import at.mjst.finbase.desktop.common.credentials.Credentials;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-25
 */
public interface ConnectionManager
{
    String LOCAL = "local";
    String REMOTE = "remote";

    void initConnection(String connectionName, Credentials credentials);

    /**
     * Announces that the connection will be closed soon
     *
     * @param connectionName the connection to be closed
     */
    void closeConnectionAsync(String connectionName);

    boolean isInitialized(String connectionName);

    <T> T getConnectedService(String connectionName, Class<T> supportedClass);

    void closeAllConnections();
}
