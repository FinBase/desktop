/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import org.jetbrains.annotations.NonNls;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-07
 */
public class SimpleFieldNameParser implements FieldNameParser
{
    @NonNls
    private static final String ERR_IDENTIFIER = "%s is not a valid field identifier!";
    private static final String DEFAULT_SEPARATOR = ".";

    @Override
    public FieldIdentifier generateIdentifier(String tableAndFieldName)
    {
        return parse(tableAndFieldName);
    }

    /**
     * Parses tableName and fieldName from a given string
     *
     * @param tableAndFieldName String in the format 'tableName.fieldName'
     * @return a new {@link FieldIdentifier}
     */
    private FieldIdentifier parse(String tableAndFieldName)
    {
        return parse(tableAndFieldName, DEFAULT_SEPARATOR);
    }

    /**
     * Parses tableName and fieldName from a given string
     *
     * @param tableAndFieldName String in the format 'tableName[separator]fieldName'
     * @param separator         Separator used to split the string
     * @return a new {@link FieldIdentifier}
     */
    private FieldIdentifier parse(String tableAndFieldName, String separator)
    {
        String[] parts = tableAndFieldName.split(separator);
        if (parts.length == 2) {
            return new ImmutableFieldIdentifier(parts[0], parts[1]);
        } else {
            throw new RuntimeException(String.format(ERR_IDENTIFIER, tableAndFieldName));
        }
    }
}
