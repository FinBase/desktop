/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.jetbrains.annotations.NonNls;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import at.mjst.finbase.desktop.common.field.Field;
import at.mjst.finbase.desktop.common.field.FieldIdentifier;
import at.mjst.finbase.desktop.common.field.FieldRegistry;
import at.mjst.finbase.desktop.util.HashCodeBuilder;

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
    // Map, containing a reference to related entities, if registered
    private final Map<String, RelatedEntityGetter> relationMap = new HashMap<>();
    // BusinessKey
    private Collection<Field<?>> businessKey;
    // hashCode
    private int hashCode = 0;

    /**
     * Registers related entities. Only *ToOne-relations are supported!
     *
     * @param tableName    table name of related entity
     * @param entityGetter getter, implemented to fetch the related entity
     */
    void registerToOneRelation(String tableName, RelatedEntityGetter entityGetter)
    {
        relationMap.put(tableName, entityGetter);
    }

    /**
     * @param tableName table name, that identifies the related entity
     * @return a related {@link Entity} by executing the registered getter
     */
    private Entity getRelatedEntity(String tableName)
    {
        if (relationMap.containsKey(tableName)) {
            RelatedEntityGetter getter = relationMap.get(tableName);
            return getter.getRelatedEntity();
        } else {
            return null;
        }
    }

    @Override
    public int hashCode()
    {
        if (hashCode == 0) {
            if (getBusinessKey().size() == 0) {
                hashCode = super.hashCode();
            } else {
                HashCodeBuilder builder = new HashCodeBuilder();
                for (Field<?> field : getBusinessKey()) {
                    builder.append(field.getValue());
                }
                hashCode = builder.hashCode();
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
        } else {
            Entity relatedEntity = getRelatedEntity(identifier.tableName());
            if (relatedEntity != null) { // test, if it's loaded...
                return relatedEntity.getField(identifier);
            } else {
                return null;
            }
        }
    }

    @Override
    public Collection<Field<?>> getFields(boolean withRelatedFields)
    {
        Collection<Field<?>> fields = new LinkedList<>();
        fields.addAll(fieldMap.values());
        if (withRelatedFields) {
            for (RelatedEntityGetter getter : relationMap.values()) {
                Entity relatedEntity = getter.getRelatedEntity();
                if (relatedEntity != null) { // test, if it's loaded...
                    fields.addAll(relatedEntity.getFields(true));
                }
            }
        }
        return fields;
    }

    @Override
    public Collection<Field<?>> getFields()
    {
        return getFields(true);
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

    /**
     * Interface to register and execute *ToOne-Relation getters
     *
     * @author Ing. Michael J. Stallinger (projects@mjst.at)
     * @since 2017-04-04
     */
    interface RelatedEntityGetter
    {
        /**
         * This method executes the implemented getter-function to ...
         *
         * @return ... the related {@link Entity}
         */
        Entity getRelatedEntity();
    }
}
