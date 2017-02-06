/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.events;

import javafx.scene.control.Tab;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-08-03
 */
public class TabActivationEvent
{
    private Tab activeTab;

    public TabActivationEvent(Tab activeTab)
    {
        this.activeTab = activeTab;
    }
}
