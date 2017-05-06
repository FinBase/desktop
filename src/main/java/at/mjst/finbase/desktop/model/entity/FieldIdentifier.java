/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.jetbrains.annotations.Contract;

import at.mjst.finbase.desktop.common.util.HashCodeBuilder;

/**
 * Key-Object for identifying a field within a table.
 * Immutable, threadsafe.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-23
 */
public final class FieldIdentifier
{
    private static final String DEFAULT_SEPARATOR = ".";
    private static final String IDENTIFIER_FORMAT = ("%s" + DEFAULT_SEPARATOR + "%s");
    private static final String ERR_IDENTIFIER = "%s is not a valid field identifier!";
    // table- and field-name as string
    private final String tableName;
    private final String fieldName;
    private final int hashCode;

    /**
     * Creates the identifier by name. The full identifier will be tableName.fieldName
     *
     * @param tableName tableName
     * @param fieldName fieldName
     */
    public FieldIdentifier(String tableName, String fieldName)
    {
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.hashCode = generateHashCode();
    }

    /**
     * @return the hashCode for the current identifier, to make the class truly immutable and therefore threadsafe!
     */
    private int generateHashCode()
    {
        return new HashCodeBuilder().append(tableName, fieldName).hashCode();
    }

    /**
     * Parses tableName and fieldName from a given string
     *
     * @param tableAndFieldName String in the format 'tableName[separator]fieldName'
     */
    public FieldIdentifier(String tableAndFieldName)
    {
        String[] parts = tableAndFieldName.split(DEFAULT_SEPARATOR);
        if (parts.length == 2) {
            this.tableName = parts[0];
            this.fieldName = parts[1];
            this.hashCode = generateHashCode();
        } else {
            throw new RuntimeException(String.format(ERR_IDENTIFIER, tableAndFieldName));
        }
    }

    @Contract(pure = true)
    @Override
    public int hashCode()
    {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (super.equals(obj) || ((obj instanceof FieldIdentifier) && ((FieldIdentifier) obj).equals(tableName,
                fieldName)));
    }

    @Override
    public String toString()
    {
        return String.format(IDENTIFIER_FORMAT, tableName(), fieldName());
    }

    /**
     * @param tableName table name
     * @param fieldName field name
     * @return true, if field- and table-name match the given parameters
     */
    public boolean equals(String tableName, String fieldName)
    {
        return (equalsTableName(tableName) && fieldName().equals(fieldName));
    }

    /**
     * @param tableName table name
     * @return true, if field- and table-name match the given parameters
     */
    public boolean equalsTableName(String tableName)
    {
        return tableName().equals(tableName);
    }

    /**
     * @return the field name
     */
    @Contract(pure = true)
    public String fieldName()
    {
        return fieldName;
    }

    /**
     * @return the table name
     */
    @Contract(pure = true)
    public String tableName()
    {
        return tableName;
    }
}
