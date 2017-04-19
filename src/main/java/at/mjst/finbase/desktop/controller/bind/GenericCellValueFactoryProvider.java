/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.bind;

import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.field.FieldIdentifier;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Generates a new instance of {@link GenericCellValueFactory}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public class GenericCellValueFactoryProvider implements CellValueFactoryProvider
{
    @Override
    public <S extends Entity, T> Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> get(
            FieldIdentifier identifier)
    {
        if (identifier == null) {
            throw new RuntimeException("Missing FieldIdentifier!");
        }
        return new GenericCellValueFactory<>(identifier);
    }
}
