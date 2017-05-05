/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto.columnselection;

import at.mjst.finbase.desktop.dto.columnselection.columnselection.Loader;
import at.mjst.finbase.desktop.dto.columnselection.columnselection.Persister;

/**
 * Provides selected column properties and selection order
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public interface ColumnSelection extends Iterable<ColumnDefinition>
{
    /**
     * @param loader the loader to be used, if {@link ColumnSelection#load()} is called
     */
    void setLoader(Loader loader);

    /**
     * @param persister the persister to be used, if {@link ColumnSelection#save()} is called
     */
    void setPersister(Persister persister);

    /**
     * Adds a new {@link ColumnDefinition} to the selection
     *
     * @param columnDefinition extra {@link ColumnDefinition}
     */
    void add(ColumnDefinition columnDefinition);

    /**
     * Adds an extra {@link ColumnDefinition} to the selection
     *
     * @param columnDefinition extra {@link ColumnDefinition}
     * @param index            index
     */
    void insert(ColumnDefinition columnDefinition, int index);

    /**
     * Removes a {@link ColumnDefinition} from the selection
     *
     * @param columnDefinition extra {@link ColumnDefinition}
     * @return true, if the item has been successfully removed
     */
    boolean remove(ColumnDefinition columnDefinition);

    /**
     * @return count of selected columns
     */
    int count();

    /**
     * @param index required index
     * @return a {@link ColumnDefinition} at the given index
     */
    ColumnDefinition get(int index);

    /**
     * Moves the given {@link ColumnDefinition} to its destination
     *
     * @param columnDefinition {@link ColumnDefinition} to move
     * @param destinationIndex destination to move the definition to
     */
    void move(ColumnDefinition columnDefinition, int destinationIndex);

    /**
     * Loads a selection using a {@link Loader}, if provided. Note, that this will clear the selection
     * first.
     *
     * @return true on success, false if no loader assigned
     */
    boolean load();

    /**
     * Saves the current selection using a {@link Persister}, if provided
     *
     * @return true on success, false if no persister assigned
     */
    boolean save();

    /**
     * Removes all elements from the selection
     */
    void clear();
}
