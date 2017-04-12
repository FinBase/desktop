/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.view;

import at.mjst.finbase.desktop.controller.main.TabId;
import javafx.scene.control.Tab;

/**
 * Extension of {@link Tab} to enable simple identification via enum {@link TabId}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-12
 */
public class IdentifiedTab extends Tab
{
    private TabId tabId;

    public TabId getTabId()
    {
        return tabId;
    }

    public void setTabId(TabId tabId)
    {
        this.tabId = tabId;
    }
}
