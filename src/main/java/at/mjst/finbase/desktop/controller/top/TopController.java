/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.top;

import com.google.common.eventbus.Subscribe;

import java.net.URL;
import java.util.ResourceBundle;

import at.mjst.finbase.desktop.controller.events.EventBusListener;
import at.mjst.finbase.desktop.controller.events.LoginEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-10
 */
public class TopController implements Initializable, EventBusListener
{
    //    @FXML
//    LoginController loginPaneController; // fx:id+Controller - we need @FXML-annotation here, to allow automatic injection!

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
//        if(loginPaneController!=null){}
//        System.out.println(tp != null);
    }

    @Subscribe
    public void handleLoginEvent(LoginEvent event)
    {
        System.out.println("LoginEvent in TopController");
    }

    public void execTest(ActionEvent actionEvent)
    {
//        System.out.println(loginPanex != null);
        // ToDo: assign listener to getValue notified to enable the tabs! Register onWhat?!
    }
//    @Override
//    public void onLoginSuccessful()
//    {
//    }
//
//    @Override
//    public void onLoginFailed()
//    {
//    }
}
