/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import org.jetbrains.annotations.NonNls;

import java.util.LinkedList;
import java.util.List;

import at.mjst.finbase.desktop.controller.ControllerFactory;
import at.mjst.finbase.desktop.model.service.LoginService;
import at.mjst.finbase.desktop.util.NlsManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.google.inject.Stage.DEVELOPMENT;
import static com.google.inject.Stage.PRODUCTION;

/**
 * FinBase main application class.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-05-28
 */
public class FinBase extends Application
{
    @NonNls
    private static final String PARAM_DEVELOP = "development";
    @NonNls
    private static final String TRUE = "true";
    private Injector injector;

    /**
     * Main entry point.
     *
     * @param args Development-switch can be passed
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * The application initialization method. This method is called immediately after the Application class is loaded
     * and constructed. An application may override this method to perform initialization prior to the actual starting
     * of the application.
     *
     * <p> The implementation of this method provided by the Application class does nothing. </p>
     *
     * <p> NOTE: This method is not called on the JavaFX Application Thread. An application must not construct a Scene
     * or a Stage in this method. An application may construct other JavaFX objects in this method. </p>
     */
    @Override
    public void init() throws Exception
    {
        super.init();
        initInjector();
    }

    /**
     * The main entry point for all JavaFX applications. The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running. <p> <p> NOTE: This method is called on the
     * JavaFX Application Thread. </p>
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set. The
     *                     primary stage will be embedded in the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be primary stages and will
     *                     not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // here goes the fxml-Loader
        FXMLLoader loader = buildLoader();
        // load the fxml node-graph, it is defined as 'root'
        Parent root = loader.load();
        // set title and icon
        primaryStage.setTitle(NlsManager.getNls("finbase.title"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(Resource.R_APP_ICON)));
        // set and show the scene
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private FXMLLoader buildLoader()
    {
        FXMLLoader loader = getInjector().getInstance(FXMLLoader.class);
        loader.setLocation(getClass().getResource(Resource.R_APP_APP));
        loader.setResources(NlsManager.getBundle());
        // We use a custom factory to initialize the first-level controllers to know each other, so they can interact
        loader.setControllerFactory(getInjector().getInstance(ControllerFactory.class));
        return loader;
    }

    private Injector getInjector()
    {
        if (injector != null) {
            return injector;
        } else {
            throw new RuntimeException("Injector not properly initialized!");
        }
    }

    /**
     * This method is called when the application should stop, and provides a convenient place to prepare for
     * application exit and destroy resources.
     *
     * <p> The implementation of this method provided by the Application class does nothing. </p>
     *
     * <p> NOTE: This method is called on the JavaFX Application Thread. </p>
     */
    @Override
    public void stop() throws Exception
    {
        // Force the sessionProvider to stop it's service
        LoginService loginService = getInjector().getInstance(LoginService.class);
        loginService.doLogout();
    }

    private void initInjector()
    {
        com.google.inject.Stage stage;
        if (developmentMode()) {
            stage = DEVELOPMENT;
        } else {
            stage = PRODUCTION;
        }
        List<Module> modules = new LinkedList<>();
        initGuiceModules(modules);
        injector = Guice.createInjector(stage, modules);
    }

    private boolean developmentMode()
    {
        // ToDo: extract to config-class and store the args...
        // ToDo: FixMe! Does not work without args!
        return this.getParameters().getNamed().get(PARAM_DEVELOP).equals(TRUE);
    }

    private void initGuiceModules(List<Module> modules)
    {
        modules.add(new at.mjst.finbase.desktop.controller.Module());
        modules.add(new at.mjst.finbase.desktop.dto.Module());
        modules.add(new at.mjst.finbase.desktop.model.Module());
    }
}
