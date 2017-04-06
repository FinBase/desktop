/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service.columnselection;

import at.mjst.finbase.desktop.dto.columnselection.ColumnSelection;

/**
 * Interface for saving a {@link ColumnSelection} to a specific destination
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public interface Persister
{
    /**
     * Implement this method to save a specific {@link ColumnSelection}
     *
     * @param columnSelection the selection to save
     * @return true on success, false otherwise
     */
    boolean executeSave(ColumnSelection columnSelection);
}
