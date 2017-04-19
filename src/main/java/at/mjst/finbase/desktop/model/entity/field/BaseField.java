/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

/**
 * Wrapper around instances of {@link ObservableValue<>} to enable
 * enumeration of {@link at.mjst.finbase.desktop.model.entity.Entity}-columns.
 *
 * Note that instances of {@link BaseField} are NOT injected by the DI-framework for performance considerations!
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public class BaseField<T> implements Field<T>
{
    /**
     * The property to be wrapped
     */
    private ObjectProperty<T> property;
    /**
     * The fields data type
     */
    private Class<T> dataType;
    /**
     * This fields identifier
     */
    private FieldIdentifier identifier;

    /**
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @param dataType  the fields data type
     */
    BaseField(String fieldName, FieldRegistry registry, Class<T> dataType)
    {
        this(new FieldIdentifier(registry.tableName(), fieldName), dataType, new SimpleObjectProperty<>());
        registry.registerField(this);
    }

    /**
     * @param identifier the fields identifier
     * @param dataType   the fields data type
     * @param property   the property to be wrapped
     */
    private BaseField(FieldIdentifier identifier, Class<T> dataType, SimpleObjectProperty<T> property)
    {
        this.identifier = identifier;
        this.dataType = dataType;
        this.property = property;
    }

    @Override
    public final Class<T> dataType()
    {
        return dataType;
    }

    @Override
    public FieldIdentifier identifier()
    {
        return identifier;
    }

    @Override
    public String fieldName()
    {
        return identifier().fieldName();
    }

    /**
     * @return {@link ObservableValue<>} interface of {@link #property}
     */
    @Override
    public ObservableValue<T> observableValue()
    {
        return property;
    }

    /**
     * @return the value of type <T>
     */
    @Override
    public T getValue()
    {
        return property.get();
    }

    /**
     * Sets value of type T
     *
     * @param value T
     */
    @Override
    public void setValue(T value)
    {
        property.set(value);
    }

    /**
     * @return a string representation of the object
     */
    @Override
    public String toString()
    {
        return String.valueOf(property.get());
    }
}
