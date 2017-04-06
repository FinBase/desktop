/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.view;

import at.mjst.finbase.desktop.dto.columnselection.ColumnDefinition;
import at.mjst.finbase.desktop.dto.columnselection.ColumnProperties;
import at.mjst.finbase.desktop.model.entity.field.FieldIdentifier;
import javafx.scene.control.TableColumn;

/**
 * Implementation of {@link TableColumn} to support assignment of custom properties
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public class CustomTableColumn<S, T> extends TableColumn<S, T>
{
    /**
     * Default constructor II
     *
     * @param columnDefinition is used to configure this column
     */
    public CustomTableColumn(ColumnDefinition columnDefinition)
    {
        this(columnDefinition.identifier());
        applyProperties(columnDefinition.getProperties());
    }

    /**
     * Default constructor
     *
     * @param fieldIdentifier is used to uniquely identify this column
     */
    public CustomTableColumn(FieldIdentifier fieldIdentifier)
    {
        super();
        setId(fieldIdentifier.toString());
    }

    /**
     * Configures the column's display properties
     *
     * @param properties the column's properties
     */
    public void applyProperties(ColumnProperties properties)
    {
        setMinWidth(properties.getWidth());
        setText(properties.getCaption());
    }
}
