/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

/**
 * Immutable descriptor for field metadata
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-27
 */
public class FieldMetaData<T>
{
    private String fieldName;
    private Class<T> dataType;

    /**
     * @param fieldName given fieldName
     * @param dataType  dataType for the current field (eg. String, Integer, ...)
     */
    public FieldMetaData(String fieldName, Class<T> dataType)
    {
        this.fieldName = fieldName;
        this.dataType = dataType;
    }

    /**
     * @return the name for the field described
     */
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * @return the dataType of the described {@link at.mjst.finbase.desktop.model.entity.field.Field}
     */
    public Class<T> getDataType()
    {
        return dataType;
    }
}
