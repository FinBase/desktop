/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-08-01
 */
public final class ObfuscatedCredentials implements Credentials
{
    /**
     * Username (typically provided by user)
     */
    private final String userName;
    /**
     * Password (never store!)
     */
    private final char[] password;

    /**
     * @param userName the user name
     * @param password password stored as char array
     */
    private ObfuscatedCredentials(@NotNull String userName, @NotNull String password)
    {
        this(userName, password.toCharArray());
    }

    /**
     * @param userName the user name
     * @param password password stored as char array
     */
    private ObfuscatedCredentials(@NotNull String userName, @NotNull char[] password)
    {
        this.userName = userName;
        this.password = password;
    }

    /**
     * @param userName the user name
     * @param password password stored as char array
     * @return a new instance of ObfuscatedCredentials
     */
    @NotNull
    public static Credentials create(@NotNull String userName, @NotNull String password)
    {
        return new ObfuscatedCredentials(userName, password);
    }

    @Override
    public boolean valid()
    {
        return (!(isUserNameEmpty() || isPasswordEmpty()));
    }

    @Contract(pure = true)
    @Override
    public final String getUserName()
    {
        return userName;
    }

    @Contract(pure = true)
    @Override
    public boolean isUserNameEmpty()
    {
        return ((userName == null) || userName.isEmpty());
    }

    @NotNull
    @Override
    public String getPassword()
    {
        return String.valueOf(password);
    }

    @Override
    public boolean isPasswordEmpty()
    {
        return (getPassword().isEmpty());
    }
}
