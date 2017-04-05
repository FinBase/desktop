/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

/**
 * Key-Object for identifying a field within a table.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-04
 */
public interface FieldIdentifier
{
    /**
     * @return the table name
     */
    String tableName();

    /**
     * @return the field name
     */
    String fieldName();

    /**
     * @param tableName table name
     * @param fieldName field name
     * @return true, if field- and table-name match the given parameters
     */
    boolean equals(String tableName, String fieldName);

    /**
     * @param tableName table name
     * @return true, if field- and table-name match the given parameters
     */
    boolean equalsTableName(String tableName);
}
