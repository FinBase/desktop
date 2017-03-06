/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import org.jetbrains.annotations.NotNull;

import at.mjst.finbase.desktop.model.entity.Entity;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-27
 */
public interface EntityReflector
{
    /**
     * Returns field-definitions for an {@link Entity}
     *
     * @param entityClass the class to be analyzed
     * @return {@link EntityMetaData} a metadata-structure for the questioned entity
     * @throws IllegalAccessException if the no-args constructor of the entity is not accessible
     * @throws InstantiationException if there are other errors instantiating the entity
     */
    @NotNull EntityMetaData getEntityMetaData(
            Class<? extends Entity> entityClass) throws InstantiationException, IllegalAccessException;
}
