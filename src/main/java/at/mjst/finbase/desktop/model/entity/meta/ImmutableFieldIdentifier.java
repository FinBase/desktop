/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import org.jetbrains.annotations.NonNls;

/**
 * Key-Object for identifying a field within a table.
 * Immutable.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-23
 */
public class ImmutableFieldIdentifier implements FieldIdentifier
{
    @NonNls
    private static final String IDENTIFIER_FORMAT = "%s.%s";
    // table- and field-name as string
    private String tableName;
    private String fieldName;

    /**
     * Creates the identifier by name. The full identifier will be tableName.fieldName
     *
     * @param tableName tableName
     * @param fieldName fieldName
     */
    public ImmutableFieldIdentifier(String tableName, String fieldName)
    {
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    @Override
    public String toString()
    {
        return String.format(IDENTIFIER_FORMAT, tableName(), fieldName());
    }

    @Override
    public String tableName()
    {
        return tableName;
    }

    @Override
    public String fieldName()
    {
        return fieldName;
    }

    @Override
    public boolean equals(String tableName, String fieldName)
    {
        return (equalsTableName(tableName) && fieldName().equals(fieldName));
    }

    @Override
    public boolean equalsTableName(String tableName)
    {
        return tableName().equals(tableName);
    }
}
