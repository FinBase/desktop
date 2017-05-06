/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto.columnselection.columnselection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import at.mjst.finbase.desktop.dto.columnselection.ColumnDefinition;
import at.mjst.finbase.desktop.dto.columnselection.ColumnSelection;
import at.mjst.finbase.desktop.model.entity.FieldIdentifier;

/**
 * Implementation of {@link ArrayBasedGenerator}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public class ArrayBasedGeneratorImpl implements ArrayBasedGenerator
{
    private Collection<FieldIdentifier> selection;

    @Override
    public void setArray(FieldIdentifier[] fieldIdentifiers)
    {
        selection = new ArrayList<>(fieldIdentifiers.length);
        Collections.addAll(selection, fieldIdentifiers);
    }

    @Override
    public boolean executeLoad(ColumnSelection columnSelection)
    {
        if (selection != null) {
            for (FieldIdentifier identifier : selection) {
                columnSelection.add(new ColumnDefinition(identifier));
            }
            System.out.println("Loaded: " + toString());
            return true;
        } else {
            return false;
        }
    }
}
