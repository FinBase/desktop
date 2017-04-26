/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.bind;

import at.mjst.finbase.desktop.model.entity.Entity;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Default interface for {@link Entity}-based {@link CellValueFactory}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-23
 */
public interface CellValueFactory<S extends Entity, T>
        extends Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>>
{
}
