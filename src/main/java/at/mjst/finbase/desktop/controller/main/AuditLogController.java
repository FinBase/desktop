/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.main;

import com.google.common.eventbus.Subscribe;

import at.mjst.finbase.desktop.controller.ControllerId;
import at.mjst.finbase.desktop.controller.ControllerIdProvider;
import at.mjst.finbase.desktop.eventsystem.events.TabSwitchEvent;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-12
 */
public class AuditLogController implements ControllerIdProvider
{
    @Override
    public ControllerId getControllerId()
    {
        return ControllerId.AUDIT_LOG;
    }

    @Subscribe
    public void onTabSwitch(TabSwitchEvent event)
    {
        if (event.getNewTabId() == TabId.AUDIT_LOG) {
            System.out.println("AuditLogActivation received!");
        }
        if (event.getOldTabId() == TabId.AUDIT_LOG) {
            System.out.println("AuditLogTab lost focus!");
        }
    }
}
