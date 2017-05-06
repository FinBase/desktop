/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.common.util;

import org.jetbrains.annotations.NonNls;

import java.util.ResourceBundle;

/**
 * ToDo: Short class description
 * ToDo: non-static
 * ToDo: Guicify
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-06
 */
public class NlsManager
{
    @NonNls
    private static final String R_NLS = "at.mjst.finbase.desktop.i18n.captions";
    private static ResourceBundle bundle = ResourceBundle.getBundle(R_NLS);

    public static String getNls(String key)
    {
        return bundle.getString(key);
    }

    public static ResourceBundle getBundle()
    {
        return bundle;
    }
}
