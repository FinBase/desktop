/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service.columnselection;

import at.mjst.finbase.desktop.common.field.FieldIdentifier;

/**
 * Generates a {@link at.mjst.finbase.desktop.dto.columnselection.ColumnSelection} from
 * an array of {@link FieldIdentifier}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public interface ArrayBasedGenerator extends Loader
{
    void setArray(FieldIdentifier[] fieldIdentifiers);
}
