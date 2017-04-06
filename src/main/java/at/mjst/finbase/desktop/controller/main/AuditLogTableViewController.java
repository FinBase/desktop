/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.main;

import com.google.common.eventbus.Subscribe;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import at.mjst.finbase.desktop.controller.events.EventBusListener;
import at.mjst.finbase.desktop.controller.events.TabActivationEvent;
import at.mjst.finbase.desktop.dto.columnselection.ColumnSelection;
import at.mjst.finbase.desktop.model.entity.AuditLog;
import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.field.FieldIdentifier;
import at.mjst.finbase.desktop.model.entity.field.ImmutableFieldIdentifier;
import at.mjst.finbase.desktop.model.service.columnselection.ArrayBasedGenerator;
import at.mjst.finbase.desktop.view.CustomTableView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import static at.mjst.finbase.desktop.model.entity.Account.TABLE_ACCOUNT;
import static at.mjst.finbase.desktop.model.entity.AuditLog.FIELD_APPLICATION;
import static at.mjst.finbase.desktop.model.entity.AuditLog.FIELD_TIMESTAMP_ON;
import static at.mjst.finbase.desktop.model.entity.AuditLog.TABLE_AUDITLOG;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-22
 */
public class AuditLogTableViewController implements Initializable, EventBusListener
{
    private final FieldIdentifier[] FIELDS = {
            new ImmutableFieldIdentifier(TABLE_AUDITLOG, Entity.FIELD_ID),
            new ImmutableFieldIdentifier(TABLE_AUDITLOG, FIELD_APPLICATION), new ImmutableFieldIdentifier(
            TABLE_AUDITLOG, FIELD_TIMESTAMP_ON), new ImmutableFieldIdentifier(TABLE_ACCOUNT, Entity.FIELD_ID)
            // will be ignored, hopefully
    };
    /**
     * This should not be used, it is only implemented,
     * to enable the reference the {@link CustomTableViewController} beyond.
     */
    @FXML
    public CustomTableView<AuditLog> customTableView;
    /**
     * Reference to the subordinate controller for the tableView
     */
    @FXML
    private CustomTableViewController<AuditLog> customTableViewController;
    // ToDo: Inject Service-Classes immediately (they represent the layer, that ensures DAO-creation after login)!
    @Inject
    private ColumnSelection columnSelection;
    @Inject
    private ArrayBasedGenerator columnSelectionLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println(String.format("initialize: %s", this.getClass().getName()));
        columnSelectionLoader.setArray(FIELDS);
        columnSelection.setLoader(columnSelectionLoader);
        customTableViewController.setColumnSelection(columnSelection);
        customTableViewController.setReadOnly();
    }

    @Subscribe
    public void handleLoginEvent(TabActivationEvent event)
    {
        System.out.println("BaseTableViewController received TabActivationEvent!");
        loadData();
    }

    protected void loadData()
    {        // ToDo: decouple db-access via *Service-Classes
        //        Injector injector = Guice.createInjector(...);
        //        CreditCardProcessor creditCardProcessor = new PayPalCreditCardProcessor();
        //        injector.injectMembers(creditCardProcessor);
        List<AuditLog> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new AuditLog((long) i, "Test" + i, new Timestamp(i), "App" + i));
        }
        //        //        ObservableList<AuditLog> data = FXCollections.observableArrayList(aulDAO.queryAll());
        //        //        tabView.setItems(data);
        customTableView.setItems(FXCollections.observableList(list)); // todo: execute via controller!!!
    }
}
