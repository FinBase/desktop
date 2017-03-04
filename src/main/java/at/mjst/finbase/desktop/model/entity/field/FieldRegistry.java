/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

/**
 * Implement this interface to enable registration of {@link Field} objects.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-03
 */
public interface FieldRegistry
{
    /**
     * @return the current table name
     */
    String tableName();

    /**
     * Adds a field to the registry
     *
     * @param field {@link Field} to be added
     */
    void registerField(Field<?> field);
}
