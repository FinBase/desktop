/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto;

import org.jetbrains.annotations.Contract;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-08-03
 */
public interface Credentials
{
    @Contract("null -> fail")
    static void validate(Credentials credentials)
    {
        if ((credentials == null) || !credentials.valid()) {
            throw new IllegalArgumentException("Login credentials incomplete!");
        }
    }

    boolean valid();

    String getUserName();

    boolean isUserNameEmpty();

    String getPassword();

    boolean isPasswordEmpty();
}
