/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto.bind;

import org.jetbrains.annotations.NonNls;

import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.field.Field;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * ToDo: Short class description
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
    private String fieldName;

    /**
     * @param fieldName field by name assigned to column
     * @param dataType  expected field data type
     */
    public GenericCellValueFactory(String fieldName, Class<T> dataType)
    {
        this.fieldName = fieldName;
        this.dataType = dataType;
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
        Field<?> field = cellData.getValue().getField(fieldName);
        // getId() of cellData.getTableColumn() will/might be the same, however we reference our own fieldName here!
        // alternative: Field<T> field = entity.getField(cellData.getTableColumn().getId(), type);
        if (field.getType() == dataType) {
            return (ObservableValue<T>) field.getObservableValue();
        } else {
            throw new RuntimeException(String.format(ERR_DATA_TYPE, fieldName));
        }
    }
}
