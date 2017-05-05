/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.main;

import com.google.common.eventbus.Subscribe;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import at.mjst.finbase.desktop.common.field.FieldIdentifier;
import at.mjst.finbase.desktop.controller.modules.CommandDispatcher;
import at.mjst.finbase.desktop.dto.columnselection.ColumnSelection;
import at.mjst.finbase.desktop.eventsystem.events.AuditLogDataEvent;
import at.mjst.finbase.desktop.eventsystem.events.TabSwitchEvent;
import at.mjst.finbase.desktop.model.entity.AuditLog;
import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.service.AuditLogService;
import at.mjst.finbase.desktop.model.service.columnselection.ArrayBasedGenerator;
import at.mjst.finbase.desktop.view.CustomTableView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import static at.mjst.finbase.desktop.model.entity.AuditLog.FIELD_APPLICATION;
import static at.mjst.finbase.desktop.model.entity.AuditLog.FIELD_TIMESTAMP_ON;
import static at.mjst.finbase.desktop.model.entity.AuditLog.TABLE_AUDITLOG;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-22
 */
public class AuditLogTableViewController implements Initializable
{
    private final FieldIdentifier[] FIELDS = {
            new FieldIdentifier(TABLE_AUDITLOG, Entity.FIELD_ID), new FieldIdentifier(TABLE_AUDITLOG,
            FIELD_APPLICATION), new FieldIdentifier(TABLE_AUDITLOG, FIELD_TIMESTAMP_ON)//,
            //            new FieldIdentifier(TABLE_ACCOUNT, Entity.FIELD_ID)
            // will be ignored, hopefully
    };
    @Inject
    private CommandDispatcher<AuditLogService> dispatcher;
    /**
     * This should not be used, it is only implemented,
     * to enable the reference the {@link CustomTableViewController} beyond.
     */
    @FXML
    private CustomTableView<AuditLog> customTableView;
    // Reference to the subordinate controller for the tableView
    @FXML
    private CustomTableViewController<AuditLog> customTableViewController;
    // ToDo: Inject Service-Classes immediately (they represent the layer, that ensures DAO-creation after login)!
    @Inject
    private ColumnSelection columnSelection;
    @Inject
    private ArrayBasedGenerator columnSelectionGenerator;
    // todo: via dispatcher too! maybe special layer that uses a dispatcher...

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println(String.format("initialize: %s", this.getClass().getName()));
        columnSelectionGenerator.setArray(FIELDS);
        columnSelection.setLoader(columnSelectionGenerator);
        customTableViewController.setColumnSelection(columnSelection);
        customTableViewController.setReadOnly();
    }

    @Subscribe
    public void onTabSwitch(TabSwitchEvent event)
    {
        if (event.getNewTabId() == TabId.AUDIT_LOG) {
            System.out.println("AuditLogTabViewActivation received!");
            loadData();
        }
    }

    protected void loadData()
    {
        // ToDo: do not always load on tab-getFocus ;)
        AuditLogLoadCommand loadCommand = AuditLogLoadCommand.createCommand(true);
        dispatcher.startCommand(loadCommand);
    }

    @Subscribe
    public void onDataLoaded(AuditLogDataEvent event)
    {
        System.out.println("EVENT RECEIVED!");
        //        if (event.getSender() == dispatcher.getService()) { todo: how to check? need check?? use a command-id?
        //            System.out.println("it's for me :)");
        customTableView.setItems(FXCollections.observableList(event.getList()));
        //        }
    }
}
