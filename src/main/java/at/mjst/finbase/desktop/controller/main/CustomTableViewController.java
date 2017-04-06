/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.main;

import org.jetbrains.annotations.NonNls;

import java.net.URL;
import java.util.ResourceBundle;

import at.mjst.finbase.desktop.controller.bind.GenericCellValueFactory;
import at.mjst.finbase.desktop.controller.events.EventBusListener;
import at.mjst.finbase.desktop.dto.columnselection.ColumnDefinition;
import at.mjst.finbase.desktop.dto.columnselection.ColumnSelection;
import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.field.FieldIdentifier;
import at.mjst.finbase.desktop.view.ConfigurableButtonCell;
import at.mjst.finbase.desktop.view.CustomTableColumn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * This is the default implementation for a {@link ColumnSelection} based {@link TableView}-controller
 *
 * @param <S> The type of the objects contained within the TableView.
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-22
 */
public class CustomTableViewController<S extends Entity> implements Initializable, EventBusListener
{
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
    /**
     * the {@link ColumnSelection} to be used
     */
    private ColumnSelection columnSelection;

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
     * @return the loaded field-selection
     */
    private ColumnSelection getColumnSelection()
    {
        return columnSelection;
    }

    /**
     * Sets the desired column-selection provider.
     *
     * @param columnSelection see {@link ColumnSelection}
     */
    void setColumnSelection(ColumnSelection columnSelection)
    {
        this.columnSelection = columnSelection;
        generateColumns();
    }

    /**
     * Generates all columns for the tableView
     */
    private void generateColumns()
    {
        // ToDo: load delayed to login-state, if fieldSelection is loaded from db!
        getColumnSelection().load();
        for (ColumnDefinition columnDefinition : getColumnSelection()) {
            TableColumn<S, ?> col = generateTableColumn(columnDefinition);
            if (col != null) {
                customTableView.getColumns().add(col);
            }
            System.out.println(String.format(COLUMN_GENERATED, columnDefinition));
        }
        // ToDo: save configuration on logout or on custom save-event
    }

    /**
     * Generates and configures one column, identified by {@link FieldIdentifier}
     *
     * @param <I>              the column's data type
     * @param columnDefinition the fields/columns identifier
     * @return a new instance of {@link TableColumn}
     */
    private <I> TableColumn<S, I> generateTableColumn(ColumnDefinition columnDefinition)
    {
        TableColumn<S, I> col = new CustomTableColumn<>(columnDefinition);
        if (col.getCellValueFactory() == null) {
            col.setCellValueFactory(new GenericCellValueFactory<>(columnDefinition.identifier()));// todo: guice?!
        }
        // ToDo: col.setCellFactory() for formatting, if needed
        return col;
    }
}
