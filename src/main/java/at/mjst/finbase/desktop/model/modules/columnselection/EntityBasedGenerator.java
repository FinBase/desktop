/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.columnselection;

import at.mjst.finbase.desktop.controller.modules.columnselection.ColumnSelection;
import at.mjst.finbase.desktop.model.entity.Entity;

/**
 * Generates a {@link ColumnSelection} from {@link Entity}s passed to this instance
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public interface EntityBasedGenerator extends Loader
{
    void addEntityClass(Class<? extends Entity> entityClass);
}
