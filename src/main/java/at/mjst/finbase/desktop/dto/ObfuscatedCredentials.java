/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-08-01
 */
public class ObfuscatedCredentials implements Credentials
{
    /**
     * Username (typically provided by user)
     */
    private String userName;
    /**
     * Password (never store!)
     */
    private char[] password;

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public boolean isUserNameEmpty()
    {
        return ((userName == null) || userName.isEmpty());
    }

    @Override
    public String getPassword()
    {
        return String.valueOf(password);
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password.toCharArray();
    }

    @Override
    public boolean isPasswordEmpty()
    {
        return (getPassword().isEmpty());
    }

    @Override
    public boolean valid()
    {
        return (!(isUserNameEmpty() || isPasswordEmpty()));
    }
}
