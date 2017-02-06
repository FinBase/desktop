/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto.bind;

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
public class GenericCellValueFactory<I, T extends Field<I>, E extends Entity>
        implements Callback<TableColumn.CellDataFeatures<E, I>, ObservableValue<I>>
{
    private Class<T> type;

    public GenericCellValueFactory(Class<T> type)
    {
        this.type = type;
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
    public ObservableValue<I> call(TableColumn.CellDataFeatures<E, I> cellData)
    {
        Field<I> field = cellData.getValue().getField(cellData.getTableColumn().getId(), type);
        return field.getObservableValue();
    }
}
