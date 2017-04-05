/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import at.mjst.finbase.desktop.util.HashCodeBuilder;

/**
 * Key-Object for identifying a field within a table.
 * Immutable.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-23
 */
public class ImmutableFieldIdentifier implements FieldIdentifier
{
    private static final String DEFAULT_SEPARATOR = ".";
    private static final String IDENTIFIER_FORMAT = ("%s" + DEFAULT_SEPARATOR + "%s");
    private static final String ERR_IDENTIFIER = "%s is not a valid field identifier!";
    // table- and field-name as string
    private String tableName;
    private String fieldName;
    private int hashCode = 0;

    /**
     * Creates the identifier by name. The full identifier will be tableName.fieldName
     *
     * @param tableName tableName
     * @param fieldName fieldName
     */
    public ImmutableFieldIdentifier(String tableName, String fieldName)
    {
        setData(tableName, fieldName);
    }

    /**
     * Privately (and hopefully only ONCE!) sets date to this immutable instance!
     *
     * @param tableName tableName
     * @param fieldName fieldName
     */
    private void setData(String tableName, String fieldName)
    {
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    /**
     * Parses tableName and fieldName from a given string
     *
     * @param tableAndFieldName String in the format 'tableName[separator]fieldName'
     */
    public ImmutableFieldIdentifier(String tableAndFieldName)
    {
        String[] parts = tableAndFieldName.split(DEFAULT_SEPARATOR);
        if (parts.length == 2) {
            setData(parts[0], parts[1]);
        } else {
            throw new RuntimeException(String.format(ERR_IDENTIFIER, tableAndFieldName));
        }
    }

    @Override
    public int hashCode()
    {
        if (hashCode == 0) {
            hashCode = new HashCodeBuilder().append(tableName, fieldName).hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (super.equals(obj) || ((obj instanceof FieldIdentifier) && ((ImmutableFieldIdentifier) obj).equals(
                tableName, fieldName)));
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
