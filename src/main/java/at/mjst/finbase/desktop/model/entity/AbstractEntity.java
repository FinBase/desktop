/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.jetbrains.annotations.NonNls;

import java.util.HashMap;
import java.util.Map;

import at.mjst.finbase.desktop.model.entity.field.Field;

/**
 * This is the superclass of all data-entities.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-17
 */
public class AbstractEntity implements Entity
{
    @NonNls
    private static final String FMT_TO_STRING = "%s=%s";
    /**
     * Map, containing all entities fields
     */
    final Map<String, Field<?>> fieldMap = new HashMap<>();

    /**
     * If called, builds the Field-objects for storing the value-properties.
     */
    AbstractEntity()
    {
    }

    @Override
    public int hashCode()
    {
        if (!fieldMapInitialized()) return super.hashCode();
        int result = 0; // ToDo: TESTME!
        for (Field<?> field : fieldMap.values()) {
            // regarding to generated entities from IntelliJ JPA-Module
            Object o = field.getValue();
            result = 31 * result + (o != null ? o.hashCode() : 0);
        }
        return result;
    }

    /**
     * @return true, if there are already elements in an existing fieldMap, false otherwise
     */
    private boolean fieldMapInitialized()
    {
        return (fieldMap.size() > 0);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        for (Map.Entry<String, Field<?>> entry : fieldMap.entrySet()) {
            // regarding to generated entities from IntelliJ JPA-Module
            Object thisPropertyValue = entry.getValue().getValue();
            Object thatPropertyValue = ((AbstractEntity) o).getField(entry.getKey()).getValue();
            if (thisPropertyValue != null ? !thisPropertyValue.equals(thatPropertyValue) : thatPropertyValue != null) {
                return false; // ToDo: TESTME!
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        if (!fieldMapInitialized()) return super.toString(); // todo: what happens, if no field?
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Field<?>> entry : fieldMap.entrySet()) {
            if (result.length() > 0) result.append(", ");
            result.append(String.format(FMT_TO_STRING, entry.getKey(), entry.getValue().getValue()));
        }
        return result.toString();
    }
    //    @Override
    //    public <T extends Field<?>> T getField(String fieldName, Class<T> fieldType)
    //    {
    //        return fieldType.cast(getField(fieldName));
    //    }

    @Override
    public Field<?> getField(String fieldName)
    {
        return fieldMap.get(fieldName);
    }
}
