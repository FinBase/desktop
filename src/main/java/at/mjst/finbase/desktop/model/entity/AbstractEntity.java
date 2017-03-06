/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.jetbrains.annotations.NonNls;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import at.mjst.finbase.desktop.model.entity.field.Field;
import at.mjst.finbase.desktop.model.entity.field.FieldRegistry;
import at.mjst.finbase.desktop.model.entity.meta.FieldIdentifier;

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
    // Map, containing all the entities fields
    private final Map<String, Field<?>> fieldMap = new HashMap<>();
    // BusinessKey
    private Collection<Field<?>> businessKey;
    // hashCode
    private int hashCode = 0;

    @Override
    public int hashCode()
    {
        if (hashCode == 0) {
            if (getBusinessKey().size() == 0) {
                hashCode = super.hashCode();
            } else {
                final int PRIME = 31;
                for (Field<?> field : getBusinessKey()) {
                    Object o = field.getValue();
                    hashCode = PRIME * hashCode + (o != null ? o.hashCode() : 0);
                }
            }
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o)
    {
        if (super.equals(o)) return true;
        if ((o == null)
                || (o.getClass() != this.getClass())
                || (o.hashCode() != this.hashCode())
                || (getBusinessKey().size() == 0)) {
            // if the hashCode does not match, the objects are definitely different
            //  - that does NOT mean, that if the hashCodes were equal, the objects might be equal too!
            return false;
        }
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
        StringBuilder result = new StringBuilder(super.toString());
        for (Map.Entry<String, Field<?>> entry : fieldMap.entrySet()) {
            if (result.length() > 0) result.append(";");
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
        if (identifier.equalsTableName(tableName())) {
            return fieldMap.get(identifier.fieldName());
            // return fieldType.cast(getField(..)); -- typed approach
        } else {
            return null; // ToDo: return registered dependencies ...
        }
    }

    @Override
    public Collection<Field<?>> getFields()
    {
        return Collections.unmodifiableCollection(fieldMap.values());
    }

    /**
     * @return the defined 'business key'. These are the key fields for the equals() and hashCode()-Methods.
     */
    private Collection<Field<?>> getBusinessKey()
    {
        if (businessKey == null) {
            businessKey = new LinkedList<>();
            buildBusinessKey(businessKey);
            validateFieldsRegistered(businessKey);
        }
        return businessKey;
    }

    /**
     * Builds a new business key field collection. Refer to {@link AbstractEntity#getBusinessKey} for further
     * information.
     *
     * @param businessKey collection, to add the fields to
     */
    abstract void buildBusinessKey(Collection<Field<?>> businessKey);

    /**
     * Checks, if all fields from the array are this entities fields
     *
     * @param fields the {@link Field}s to check
     */
    private void validateFieldsRegistered(Collection<Field<?>> fields)
    {
        if (fields == null) return;
        for (Field<?> field : fields) {
            if (!fieldMap.containsValue(field)) {
                throw new RuntimeException(String.format(ERR_KEY_FIELD_MISSING, field.identifier()));
            }
        }
    }

    @Override
    public void registerField(Field<?> field)
    {
        fieldMap.put(field.identifier().fieldName(), field);
        field.observableValue().addListener(observable -> resetHashCode());
    }

    /**
     * Internally resets an already calculated hashCode for this Entity
     */
    private void resetHashCode()
    {
        hashCode = 0;
    }
}
