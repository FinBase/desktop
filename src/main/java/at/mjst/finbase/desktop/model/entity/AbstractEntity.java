/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.jetbrains.annotations.NonNls;

import java.util.HashMap;
import java.util.Map;

import at.mjst.finbase.desktop.model.entity.field.Field;
import at.mjst.finbase.desktop.model.entity.field.FieldIdentifier;
import at.mjst.finbase.desktop.model.entity.field.FieldRegistry;

/**
 * This is the superclass of all data-entities.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-17
 */
public abstract class AbstractEntity implements Entity, FieldRegistry
{
    @NonNls
    private static final String FMT_TO_STRING = "%s=%s";
    @NonNls
    private static final String ERR_KEY_FIELD_MISSING = "Field '%s' is not registered!";
    /**
     * Map, containing all the entities fields
     */
    private final Map<String, Field<?>> fieldMap = new HashMap<>();
    private Field<?>[] businessKey;
    private int hashCode; // ToDo: the hash-code

    @Override
    public int hashCode()
    {
        if (getBusinessKey() == null) {
            return super.hashCode();
        }
        validateFieldMapInitialized();
        int result = 0; // ToDo: reimplement!
        for (Field<?> field : fieldMap.values()) {
            // regarding to generated entities from IntelliJ JPA-Module
            Object o = field.getValue();
            result = 31 * result + (o != null ? o.hashCode() : 0);
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (super.equals(o)) return true; // ToDo: test getClass...!
        if ((o == null) || (o.getClass() != this.getClass()) || (getBusinessKey() == null)) return false;
        validateFieldMapInitialized();
        for (Field<?> field : getBusinessKey()) {
            Object thisPropertyValue = field.getValue();
            Object thatPropertyValue = ((AbstractEntity) o).getField(field.identifier()).getValue();
            if ((thisPropertyValue != null)
                    ? (!thisPropertyValue.equals(thatPropertyValue))
                    : (thatPropertyValue != null)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        if (!fieldMapInitialized()) {
            return super.toString();
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Field<?>> entry : fieldMap.entrySet()) {
            if (result.length() > 0) result.append(", ");
            result.append(String.format(FMT_TO_STRING, entry.getKey(), entry.getValue().getValue()));
        }
        return result.toString();
    }

    @Override
    public abstract String tableName();

    @Override
    public Field<?> getField(String fieldName)
    {
        return fieldMap.get(fieldName);
    }

    @Override
    public Field<?> getField(FieldIdentifier identifier)
    {
        if (identifier.equals(tableName())) {
            return fieldMap.get(identifier.fieldName());
        } else {
            return null; // ToDo: return registered dependencies ...
        }
    }
    //    @Override
    //    public <T extends Field<?>> T getField(String fieldName, Class<T> fieldType)
    //    {
    //        return fieldType.cast(getField(fieldName));
    //    }

    /**
     * @return the defined 'business key'. These are the key fields for the equals() and hashCode()-Methods.
     */
    private Field<?>[] getBusinessKey()
    {
        if (businessKey == null) {
            businessKey = buildBusinessKey();
            validateFieldsRegistered(businessKey);
        }
        return businessKey;
    }

    /**
     * Throws a {@link RuntimeException}, if there are no elements existing in the fieldMap.
     */
    private void validateFieldMapInitialized()
    {
        if (!fieldMapInitialized()) throw new RuntimeException("FieldMap is not initialized/no fields!");
    }

    /**
     * @return a new businessKey. Refer to {@link AbstractEntity#getBusinessKey} for further information.
     */
    Field<?>[] buildBusinessKey()
    {
        return null;
    }

    /**
     * Checks, if all fields from the array are this entities fields
     *
     * @param fields the {@link Field}s to check
     */
    private void validateFieldsRegistered(Field<?>[] fields)
    {
        if (fields == null) return;
        for (Field<?> field : fields) {
            if (!fieldMap.containsValue(field)) {
                throw new RuntimeException(String.format(ERR_KEY_FIELD_MISSING, field.identifier()));
            }
        }
    }

    /**
     * @return true, if there are already elements in an existing fieldMap, false otherwise
     */
    private boolean fieldMapInitialized()
    {
        return (fieldMap.size() > 0);
    }

    @Override
    public void registerField(Field<?> field)
    {
        fieldMap.put(field.identifier().fieldName(), field);
    }
}
