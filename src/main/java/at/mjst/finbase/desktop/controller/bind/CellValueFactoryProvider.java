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
 * Enables guice to instantiate a {@link Callback} interface for cellValueFactories. Because the required class is
 * highly generic, we do NOT implement Guice's {@link com.google.inject.Provider} here, since it requires fully
 * qualified types!
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-07
 */
public interface CellValueFactoryProvider
{
    /**
     * Creates a new CellValueFactory
     *
     * @param identifier the {@link FieldIdentifier} to be used for creating the factory
     * @return new {@link Callback}
     */
    <S extends Entity, T> Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> get(
            FieldIdentifier identifier);
}
