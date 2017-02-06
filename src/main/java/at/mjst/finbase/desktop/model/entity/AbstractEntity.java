/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

import at.mjst.finbase.desktop.model.entity.field.Field;
import at.mjst.finbase.desktop.model.entity.field.FieldFactory;
import at.mjst.finbase.desktop.model.entity.field.IntegerField;
import at.mjst.finbase.desktop.model.entity.field.LongField;
import at.mjst.finbase.desktop.model.entity.field.StringField;
import at.mjst.finbase.desktop.model.entity.field.TimestampField;

import static at.mjst.finbase.desktop.model.entity.field.Module.getFieldFactoryInstance;

/**
 * This is the superclass of all data-entities.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-17
 */
public class AbstractEntity implements Entity
{
    private final Map<String, Field> fieldMap = new HashMap<>();
    private FieldFactory fieldFactory;

    /**
     * If called, builds the Field-objects for storing the value-properties.
     */
    AbstractEntity()
    {
        this(getFieldFactoryInstance());
    }

    /**
     * Constructor using external {@link FieldFactory}
     *
     * @param fieldFactory the factory to be used
     */
    public AbstractEntity(FieldFactory fieldFactory)
    {
        this.fieldFactory = fieldFactory;
        generateFields(); // ask derivation to generate it's fields ..
        autoGenerateFields(); // .. automatically generate otherwise
    }

    /**
     * Override this method to implement manual field-creation and -assignment in derived classes.
     */
    protected void generateFields()
    {
    }

    /**
     * Automatically generates Field-objects by reflection. This approach is a bit slower.
     */
    private void autoGenerateFields()
    {
        if (fieldMapInitialized()) return; // Already initialized with fields -> exit!
        if (fieldFactory == null) throw new RuntimeException("fieldFactory is null!");
        for (Method method : getClass().getMethods()) {
            // find all methods annotated with the column-annotation
            //  (so we'll find all entity-columns defined - note, that this does not mean 'every columns of the table'!)
            Column columnAnnotation = method.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String fieldName = columnAnnotation.name();
                Class resultType = method.getReturnType();
                Field field = fieldFactory.getNewFieldInstance(fieldName, resultType);
                addField(field);
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

    /**
     * Adds a new field to the fieldMap.
     *
     * @param field the newly instantiated field to add
     */
    void addField(Field field)
    {
        String fieldName = field.getName();
        assert (!fieldMap.containsKey(fieldName)) : String.format("Must not map column-key (%s) twice!", fieldName);
        fieldMap.put(fieldName, field);
    }

    @Override
    public int hashCode()
    {
        if (!fieldMapInitialized()) return super.hashCode();
        int result = 0; // ToDo: TESTME!
        for (Field field : fieldMap.values()) {
            // regarding to generated entities from IntelliJ JPA-Module
            Object o = field.getValue();
            result = 31 * result + (o != null ? o.hashCode() : 0);
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            // regarding to generated entities from IntelliJ JPA-Module
            Object thisPropertyValue = entry.getValue().getValue();
            Object thatPropertyValue = ((AbstractEntity) o).getField(entry.getKey(), Field.class).getValue();
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
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            if (result.length() > 0) result.append(", ");
            result.append(String.format("%s=%s", entry.getKey(), entry.getValue().getValue()));
        }
        return result.toString();
    }

    @Override
    public <T extends Field> T getField(String fieldName, Class<T> fieldType)
    {
        return fieldType.cast(fieldMap.get(fieldName));
    }

    @Override
    public Integer getValueAsInteger(String fieldName)
    {
        return getField(fieldName, IntegerField.class).getValue();
    }

    @Override
    public void setValueAsInteger(String fieldName, Integer value)
    {
        getField(fieldName, IntegerField.class).setValue(value);
    }

    @Override
    public String getValueAsString(String fieldName)
    {
        return getField(fieldName, StringField.class).getValue();
    }

    @Override
    public void setValueAsString(String fieldName, String value)
    {
        getField(fieldName, StringField.class).setValue(value);
    }

    @Override
    public Long getValueAsLong(String fieldName)
    {
        return getField(fieldName, LongField.class).getValue();
    }

    @Override
    public void setValueAsLong(String fieldName, Long value)
    {
        getField(fieldName, LongField.class).setValue(value);
    }

    @Override
    public Timestamp getValueAsTimestamp(String fieldName)
    {
        return getField(fieldName, TimestampField.class).getValue();
    }

    @Override
    public void setValueAsTimestamp(String fieldName, Timestamp value)
    {
        getField(fieldName, TimestampField.class).setValue(value);
    }
}
