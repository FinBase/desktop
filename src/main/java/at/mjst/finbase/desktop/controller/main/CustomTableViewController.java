/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.main;

import com.google.inject.Inject;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

import at.mjst.finbase.desktop.controller.bind.GenericCellValueFactory;
import at.mjst.finbase.desktop.controller.events.EventBusListener;
import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.meta.EntityMetaData;
import at.mjst.finbase.desktop.model.entity.meta.EntityReflector;
import at.mjst.finbase.desktop.model.entity.meta.FieldIdentifier;
import at.mjst.finbase.desktop.view.ConfigurableButtonCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * ToDo: Short class description
 *
 * @param <S> The type of the objects contained within the TableView.
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-22
 */
public class CustomTableViewController<S extends Entity> implements Initializable, EventBusListener
{
    @NonNls
    private static final String ERR_UNSUPPORTED_FIELD = "Entity '%s' does not support field '%s'!";
    @NonNls
    private static final String COLUMN_GENERATED = "Column generated: '%s'";
    /**
     * References the tableView component from fxml. If  {@link at.mjst.finbase.desktop.view.CustomTableView} is used,
     * mousewheel-scrolling behavior will be a bit more familiar.
     */
    @FXML
    public TableView<S> customTableView;
    /**
     * This is the control-column, every instance of CustomTableView comes with it
     * ToDo: make ButtonCell configurable or enable a factory to be set!
     */
    @FXML
    public TableColumn<S, Object> controlColumn;
    private Class<S> mainEntityClass;
    //
    private EntityMetaData metaData;
    @Inject
    private EntityReflector entityReflector;
    private FieldIdentifier[] fieldSelection;

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
        System.out.println(String.format("initialize: %s", this.getClass().getName()));
        controlColumn.setCellFactory(param -> new ConfigurableButtonCell<>()); // todo: guice?!
    }

    /**
     * Sets the tableView readOnly
     */
    void setReadOnly()
    {
        customTableView.setEditable(false);
    }

    /**
     * Generates all columns for the tableView
     */
    private void generateColumns()
    {
        //        generateControlColumn();
        for (FieldIdentifier fieldIdentifier : getFieldSelection()) {
            if (metaData.matchesIdentifier(fieldIdentifier)) {
                TableColumn<S, ?> col = generateTableColumn(fieldIdentifier);
                if (col != null) {
                    customTableView.getColumns().add(col);
                }
                System.out.println(String.format(COLUMN_GENERATED, fieldIdentifier));
            } else {
                System.out.println(
                        String.format(ERR_UNSUPPORTED_FIELD, getMainEntityClass().getName(), fieldIdentifier));
            }
        }
    }

    /**
     * @return class of the {@link Entity} to be displayed
     */
    private Class<S> getMainEntityClass()
    {
        return mainEntityClass;
    }

    /**
     * After successful set, the entities metadata is fetched.
     *
     * @param mainEntityClass the {@link Entity}, that will be displayed within this tableView.
     */
    void setMainEntityClass(Class<S> mainEntityClass)
    {
        this.mainEntityClass = mainEntityClass;
        try {
            metaData = entityReflector.getEntityMetaData(getMainEntityClass()); // fetch metaData for Entity!
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Possibility to override the construction of a {@link TableColumn}
     *
     * @param <I>             the {@link TableColumn}'s value-type
     * @param fieldIdentifier the fields/columns identifier
     * @return a new instance of {@link TableColumn}
     */
    @NotNull
    private <I> TableColumn<S, I> getNewTableColumn(FieldIdentifier fieldIdentifier)
    {
        TableColumn<S, I> col = new TableColumn<>(); // ToDo: optional factory, if other colType is needed...
        col.setId(fieldIdentifier.toString());
        return col;
    }

    /**
     * Configures the column's display properties
     * ToDo: think of a column configuration!
     *
     * @param fieldIdentifier the fields/columns identifier
     * @param col             the {@link TableColumn} to configure
     * @param <I>             the fields/columns data type
     */
    private <I> void setColumnDisplayProperties(FieldIdentifier fieldIdentifier, TableColumn<S, I> col)
    {
        col.setMinWidth(50);
        col.setText("D:" + fieldIdentifier.fieldName());
    }

    /**
     * Generates and configures one column, identified by {@link FieldIdentifier}
     *
     * @param fieldIdentifier the fields/columns identifier
     * @param <I>             the column's data type
     * @return a new instance of {@link TableColumn}
     */
    private <I> TableColumn<S, I> generateTableColumn(FieldIdentifier fieldIdentifier)
    {
        TableColumn<S, I> col = getNewTableColumn(fieldIdentifier);
        if (col.getCellValueFactory() == null) {
            col.setCellValueFactory(new GenericCellValueFactory<>(fieldIdentifier));// todo: guice?!
        }
        setColumnDisplayProperties(fieldIdentifier, col);
        // ToDo: col.setCellFactory(), if needed
        return col;
    }

    /**
     * Sets the desired field-selection.
     *
     * @param fieldSelection array of {@link FieldIdentifier}s
     */
    void setFieldSelection(FieldIdentifier[] fieldSelection)
    {
        this.fieldSelection = fieldSelection;
        generateColumns();
    }

    /**
     * @return the loaded field-selection
     */
    private FieldIdentifier[] getFieldSelection()
    {
        return fieldSelection;
    }
}
