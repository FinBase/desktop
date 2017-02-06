/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.main;

import com.google.common.eventbus.Subscribe;

import java.net.URL;
import java.util.ResourceBundle;

import at.mjst.finbase.desktop.controller.events.EventBusListener;
import at.mjst.finbase.desktop.controller.events.TabActivationEvent;
import at.mjst.finbase.desktop.model.entity.AuditLog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-22
 */
public class AuditLogController implements Initializable, EventBusListener
{
    @FXML
    public TableView<AuditLog> tabView;
    // DAOs erst kreieren, wenn login sichergestellt werden konnte!
    //    @Inject
    //    private AuditLogDAO aulDAO;

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // ToDo: fill columns from entity!
        tabView.setEditable(false); // set the table non-editable
        //        ObservableList<AuditLog> data = FXCollections.observableArrayList(aulDAO.queryAll());
        //        tabView.setItems(data);
    }

    @Subscribe
    public void handleLoginEvent(TabActivationEvent event)
    {
        // ToDo: decouple db-access via *Service-Classes
        //        Injector injector = Guice.createInjector(...);
        //        CreditCardProcessor creditCardProcessor = new PayPalCreditCardProcessor();
        //        injector.injectMembers(creditCardProcessor);
        System.out.println("AuditLog received TabActivationEvent!");
    }
}
