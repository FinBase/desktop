/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-08-03
 */
public interface Credentials
{
    static void validate(Credentials credentials)
    {
        if ((credentials == null) || !credentials.valid()) {
            throw new IllegalArgumentException("Login credentials incomplete!");
        }
    }

    boolean valid();

    String getUserName();

    void setUserName(String userName);

    boolean isUserNameEmpty();

    String getPassword();

    void setPassword(String password);

    boolean isPasswordEmpty();
}
