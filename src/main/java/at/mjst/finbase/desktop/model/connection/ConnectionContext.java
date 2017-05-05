/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.connection;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-31
 */
public interface ConnectionContext
{
    String name();

    boolean isInitialized();

    <T> T getSessionInstance(Class<T> type);

    void registerDispatcher(Object obj);

    void deregisterDispatcher(Object obj);
}
