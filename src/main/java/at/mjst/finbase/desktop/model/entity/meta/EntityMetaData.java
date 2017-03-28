/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Immutable descriptor for entity metadata
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-26
 */
public class EntityMetaData
{
    private Map<String, FieldMetaData<?>> fields;
    private String tableName;

    /**
     * @param tableName table, that is represented by this metadata
     * @param fields    field-metadata contained within this table
     */
    EntityMetaData(String tableName, Map<String, FieldMetaData<?>> fields)
    {
        this.tableName = tableName;
        this.fields = Collections.unmodifiableMap(fields);
    }

    /**
     * @return a map of fieldNames and their metadata
     */
    public Collection<FieldMetaData<?>> getFields()
    {
        return fields.values();
    }

    public boolean matchesIdentifier(FieldIdentifier fieldIdentifier)
    {
        return (fieldIdentifier.tableName().equals(this.tableName()) && fields.containsKey(
                fieldIdentifier.fieldName())); // todo: format-settings: && force newline!
    }

    /**
     * @return the tableName
     */
    public String tableName()
    {
        return tableName;
    }
}
