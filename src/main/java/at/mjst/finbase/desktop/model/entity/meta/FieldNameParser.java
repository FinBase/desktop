/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-07
 */
public interface FieldNameParser
{
    /**
     * Creates a {@link FieldIdentifier} by parsing a string name in the format tableName.fieldName
     *
     * @param tableAndFieldName String in the format 'tableName.fieldName'
     * @return a new {@link FieldIdentifier}
     */
    FieldIdentifier generateIdentifier(String tableAndFieldName);
}
