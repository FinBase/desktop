/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.bind;

import org.jetbrains.annotations.NonNls;

import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.field.Field;
import at.mjst.finbase.desktop.model.entity.meta.FieldIdentifier;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Implements the {@link Callback}-interface to provide tableView data from entities supporting {@link Field}s.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-19
 */
public class GenericCellValueFactory<S extends Entity, T>
        implements Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>>
{
    @NonNls
    private static final String ERR_DATA_TYPE = "Field datatype mismatch for '%s'";
    private final Class<T> dataType;
    private FieldIdentifier identifier;

    /**
     * @param identifier field identifier assigned to column
     * @param dataType   expected field data type
     */
    public GenericCellValueFactory(FieldIdentifier identifier, Class<T> dataType)
    {
        this.identifier = identifier;
        this.dataType = dataType; // ToDo: maybe check for data type not necessary?
    }

    /**
     * The <code>call</code> method is called when required, and is given a
     * single argument of type P, with a requirement that an object of type R
     * is returned.
     *
     * @param cellData The single argument upon which the returned value should be determined.
     * @return An object of type R that may be determined based on the provided parameter value.
     */
    @Override
    @SuppressWarnings("unchecked")
    public ObservableValue<T> call(TableColumn.CellDataFeatures<S, T> cellData)
    {
        Field<?> field = cellData.getValue().getField(identifier.fieldName());
        // getId() of cellData.getTableColumn() will/might be the same, however we reference our own fieldName here!
        // alternative: Field<T> field = entity.getField(cellData.getTableColumn().getId(), type);
        if (field.dataType() == dataType) {
            return (ObservableValue<T>) field.observableValue();
        } else {
            throw new RuntimeException(String.format(ERR_DATA_TYPE, identifier.fieldName()));
        }
    }
}
