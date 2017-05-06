/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.bind;

import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.FieldIdentifier;

/**
 * Generates a new instance of {@link GenericCellValueFactory}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
class GenericCellValueFactoryProvider extends CellValueFactoryProvider
{
    @Override
    <S extends Entity, T> CellValueFactory<S, T> generateInstance(FieldIdentifier identifier)
    {
        return new GenericCellValueFactory<>(identifier);
    }
}
