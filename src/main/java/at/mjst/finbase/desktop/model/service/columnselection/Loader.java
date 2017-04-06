/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service.columnselection;

import at.mjst.finbase.desktop.dto.columnselection.ColumnSelection;

/**
 * Interface for loading {@link ColumnSelection} from a specific source
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public interface Loader
{
    /**
     * Implement this method to load a specific {@link ColumnSelection}
     *
     * @param columnSelection the selection to load
     * @return if load succeeded
     */
    boolean executeLoad(ColumnSelection columnSelection);
}
