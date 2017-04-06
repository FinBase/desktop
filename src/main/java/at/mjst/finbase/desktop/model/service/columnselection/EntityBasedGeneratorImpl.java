/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service.columnselection;

import java.util.Collection;
import java.util.LinkedList;

import at.mjst.finbase.desktop.dto.columnselection.ColumnDefinition;
import at.mjst.finbase.desktop.dto.columnselection.ColumnSelection;
import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.field.Field;
import at.mjst.finbase.desktop.model.entity.field.FieldIdentifier;

/**
 * Implementation of {@link EntityBasedGenerator}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public class EntityBasedGeneratorImpl implements EntityBasedGenerator
{
    private Collection<FieldIdentifier> selection = new LinkedList<>();

    @Override
    public void addEntityClass(Class<? extends Entity> entityClass)
    {
        try {
            Entity entity = entityClass.newInstance();
            for (Field<?> field : entity.getFields(false)) {
                selection.add(field.identifier());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean executeLoad(ColumnSelection columnSelection)
    {
        for (FieldIdentifier identifier : selection) {
            columnSelection.add(new ColumnDefinition(identifier));
        }
        System.out.println("Loaded: " + toString());
        return true;
    }
}
