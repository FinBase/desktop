/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto.columnselection;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.mjst.finbase.desktop.model.service.columnselection.Loader;
import at.mjst.finbase.desktop.model.service.columnselection.Persister;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public class ColumnSelectionImpl implements ColumnSelection
{
    private List<ColumnDefinition> selection = new ArrayList<>();
    private Loader loader;
    private Persister persister;

    @Override
    public void setLoader(Loader loader)
    {
        this.loader = loader;
    }

    @Override
    public void setPersister(Persister persister)
    {
        this.persister = persister;
    }

    @Override
    public void add(ColumnDefinition columnDefinition)
    {
        if (validateNewItem(columnDefinition)) {
            selection.add(columnDefinition);
        }
    }

    /**
     * Checks if the new item matches given conditions
     *
     * @param columnDefinition the item to add
     * @return true, if the item is allowed to be added
     */
    private boolean validateNewItem(ColumnDefinition columnDefinition)
    {
        return (columnDefinition != null) && (!selection.contains(columnDefinition));
    }

    @Override
    public void insert(ColumnDefinition columnDefinition, int index)
    {
        if (validateNewItem(columnDefinition)) {
            selection.add(index, columnDefinition);
        }
    }

    @Override
    public boolean remove(ColumnDefinition columnDefinition)
    {
        return ((columnDefinition != null) && selection.remove(columnDefinition));
    }

    @Override
    public int count()
    {
        return selection.size();
    }

    @Override
    public ColumnDefinition get(int index)
    {
        return selection.get(index);
    }

    @Override
    public void move(ColumnDefinition columnDefinition, int destinationIndex)
    {
        if (remove(columnDefinition)) {
            insert(columnDefinition, destinationIndex);
        }
    }

    @Override
    public boolean load()
    {
        if (loader != null) {
            clear();
            return loader.executeLoad(this);
        } else {
            return false;
        }
    }

    @Override
    public boolean save()
    {
        return ((persister != null) && persister.executeSave(this));
    }

    @Override
    public void clear()
    {
        selection.clear();
    }

    @NotNull
    @Override
    public Iterator<ColumnDefinition> iterator()
    {
        return selection.iterator();
    }
}
