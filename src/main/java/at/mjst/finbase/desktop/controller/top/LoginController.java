/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.top;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import java.net.URL;
import java.util.ResourceBundle;

import at.mjst.finbase.desktop.dto.Credentials;
import at.mjst.finbase.desktop.eventsystem.UIBus;
import at.mjst.finbase.desktop.eventsystem.events.LoginEvent;
import at.mjst.finbase.desktop.model.service.LoginService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * fxml-UIBus for loginPane.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-10
 */
public class LoginController implements Initializable
{
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @Inject
    private Credentials credentials;
    @Inject
    private LoginService loginService;
    @Inject
    @UIBus
    private EventBus eventBus;

    public void execOnUsername()
    {
        passwordField.requestFocus();
    }

    public void execOnPassword()
    {
        loginButton.requestFocus();
    }

    @Subscribe
    private void onLogoff(LoginEvent.LogoffSuccess event)
    {
        System.out.println("Event in LoginController " + event);
        setLoggedOffState();
    }

    private void setLoggedOffState()
    {
        userNameField.setDisable(false);
        passwordField.setDisable(false);
        passwordField.clear();
        loginButton.setText("Login");
        validateLoginButtonDisableState();
        userNameField.requestFocus();
    }

    private void validateLoginButtonDisableState()
    {
        if (!loginFieldsValid()) {
            loginButton.setDisable(true);
        } else {
            loginButton.setDisable(false);
        }
    }

    private boolean loginFieldsValid()
    {
        return (!userNameField.getText().isEmpty()) && (!passwordField.getText().isEmpty());
    }

    @Subscribe
    private void onLoginFailed(LoginEvent.LoginFailedEvent event)
    {
        System.out.println("Event in LoginController " + event);
        setLoggedOffState();
        // @see: http://code.makery.ch/blog/javafx-dialogs-official/
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("FinBaseTitle");
        alert.setHeaderText("Login failed!");
        alert.setContentText(event.getErrorText());
        alert.showAndWait();
    }

    public void execLoginButton()
    {
        credentials.setUserName(userNameField.getText());
        credentials.setPassword(passwordField.getText());
        if (credentials.valid() && !loginService.loggedIn()) {
            setLoggedInState(); // immediately set loggedIn state on UI to disable any interaction!
            loginService.doLogin(credentials); // todo: exec this and others via thread (or WITHIN service-class?)
        } else {
            loginService.doLogout();
        }
    }

    private void setLoggedInState()
    {
        userNameField.setDisable(true); // todo: buttonGroup - disable at once!
        passwordField.setDisable(true);
        loginButton.setText("Logout");
    }

    public void execExitButton()
    {
        Platform.exit();
    }

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
        loginButton.defaultButtonProperty().bind(loginButton.focusedProperty());
        setLoggedOffState();
    }

    public void execOnKeyTyped()
    {
        validateLoginButtonDisableState();
    }
}
