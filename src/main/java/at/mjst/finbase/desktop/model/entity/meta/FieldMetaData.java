/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import org.jetbrains.annotations.NonNls;

/**
 * Immutable descriptor for field metadata
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-27
 */
public class FieldMetaData<T>
{
    @NonNls
    protected static final String FMT_TO_STRING = "%s (for %s)";
    private FieldIdentifier fieldIdentifier;
    private Class<T> dataType;
    // ...

    /**
     * @param fieldIdentifier given identifier for field
     * @param dataType        dataType for the current field (eg. String, Integer, ...)
     */
    public FieldMetaData(FieldIdentifier fieldIdentifier, Class<T> dataType)
    {
        this.fieldIdentifier = fieldIdentifier;
        this.dataType = dataType;
    }

    /**
     * @return the name for the field described
     */
    public String fieldName()
    {
        return fieldIdentifier.fieldName();
    }

    /**
     * @return the dataType of the described {@link at.mjst.finbase.desktop.model.entity.field.Field}
     */
    public Class<T> dataType()
    {
        return dataType;
    }

    @Override
    public String toString()
    {
        return String.format(FMT_TO_STRING, super.toString(), fieldIdentifier);
    }
}
