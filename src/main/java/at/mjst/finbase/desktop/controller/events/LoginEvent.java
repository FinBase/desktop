/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.events;

/**
 * EventBus Event fired, if the login-button is pressed
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-12
 */
public class LoginEvent
{
    private final String userName;

    public LoginEvent(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
}
