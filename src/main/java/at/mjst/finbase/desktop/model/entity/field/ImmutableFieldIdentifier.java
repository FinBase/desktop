/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

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
    private static final String ERR_IDENTIFIER = "%s is not a valid field identifier!";
    @NonNls
    private static final String IDENTIFIER_FORMAT = "%s.%s";
    private static final String DEFAULT_SEPARATOR = ".";
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

    /**
     * Creates the identifier by parsing a string name in the format tableName.fieldName
     *
     * @param tableAndFieldName String in the format 'tableName.fieldName'
     */
    public ImmutableFieldIdentifier(String tableAndFieldName)
    {
        parse(tableAndFieldName);
    }

    /**
     * Parses tableName and fieldName from a given string
     *
     * @param tableAndFieldName String in the format 'tableName.fieldName'
     */
    private void parse(String tableAndFieldName)
    {
        parse(tableAndFieldName, DEFAULT_SEPARATOR);
    }

    /**
     * Parses tableName and fieldName from a given string
     *
     * @param tableAndFieldName String in the format 'tableName[separator]fieldName'
     * @param separator         Separator used to split the string
     */
    private void parse(String tableAndFieldName, String separator)
    {
        String[] parts = tableAndFieldName.split(separator);
        if (parts.length == 2) {
            tableName = parts[0];
            fieldName = parts[1];
        } else {
            throw new RuntimeException(String.format(ERR_IDENTIFIER, tableAndFieldName));
        }
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
        return (equals(tableName) && fieldName().equals(fieldName));
    }

    @Override
    public boolean equals(String tableName)
    {
        return tableName().equals(tableName);
    }
}
