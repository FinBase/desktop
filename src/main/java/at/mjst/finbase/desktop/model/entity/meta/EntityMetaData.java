/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Immutable descriptor for entity metadata
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-26
 */
public class EntityMetaData
{
    private Map<FieldIdentifier, FieldMetaData<?>> fields;
    private String mainTableName;
    private Map<String, EntityMetaData> relatedMetaDataMap = new HashMap<>();

    /**
     * @param mainTableName main table, that is represented by this metadata
     * @param fields        field-metadata contained within this table
     */
    EntityMetaData(String mainTableName, Map<FieldIdentifier, FieldMetaData<?>> fields)
    {
        this.mainTableName = mainTableName;
        this.fields = fields;
    }

    /**
     * @param includeRelated adds fields of all related entities
     * @return a map of fieldNames and their metadata
     */
    public Collection<FieldMetaData<?>> getFields(boolean includeRelated)
    {
        Collection<FieldMetaData<?>> list = new LinkedList<>();
        list.addAll(fields.values());
        if (includeRelated) {
            for (EntityMetaData metaData : relatedMetaDataMap.values()) {
                list.addAll(metaData.getFields(true));
            }
        }
        return list;
    }

    /**
     * Checks, whether a field-identifier is contained within this entity.
     *
     * @param fieldIdentifier the fields identifier asked for
     * @return true, if the entity supports the given field
     */
    public boolean matchesIdentifier(FieldIdentifier fieldIdentifier)
    {
        if (fields.containsKey(fieldIdentifier)) {
            return true;
        } else {
            EntityMetaData relatedMetaData = relatedMetaDataMap.get(fieldIdentifier.tableName());
            return (relatedMetaData != null) && relatedMetaData.matchesIdentifier(fieldIdentifier);
        }
    }

    public void putRelatedMetaData(EntityMetaData relatedMetaData)
    {
        relatedMetaDataMap.put(relatedMetaData.mainTableName(), relatedMetaData);
    }

    /**
     * @return the tableName
     */
    public String mainTableName()
    {
        return mainTableName;
    }
}
