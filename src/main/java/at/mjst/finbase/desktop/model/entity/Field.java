/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

/**
 * Wrapper around instances of {@link ObservableValue<>} to enable
 * enumeration of {@link at.mjst.finbase.desktop.model.entity.Entity}-columns.
 *
 * Note that instances of {@link Field} are NOT injected by the DI-framework for performance considerations!
 *
 * @param <T> any (serializable) value to be represented by field
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public final class Field<T>
{
    /**
     * The property to be wrapped
     */
    private final ObjectProperty<T> property;
    /**
     * The fields data type
     */
    private final Class<T> dataType;
    /**
     * This fields identifier
     */
    private final FieldIdentifier identifier;

    /**
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @param dataType  the fields data type
     */
    private Field(@NotNull String fieldName, @NotNull FieldRegistry registry, @NotNull Class<T> dataType)
    {
        this(new FieldIdentifier(registry.tableName(), fieldName), dataType, new SimpleObjectProperty<>());
        registry.registerField(this);
    }

    /**
     * @param identifier the fields identifier
     * @param dataType   the fields data type
     * @param property   the property to be wrapped
     */
    private Field(@NotNull FieldIdentifier identifier, @NotNull Class<T> dataType,
                  @NotNull SimpleObjectProperty<T> property)
    {
        this.identifier = identifier;
        this.dataType = dataType;
        this.property = property;
    }

    /**
     * Creates a new field of the given type
     *
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @return a new instance of {@link Field}
     */
    @NotNull
    public static Field<Integer> createInteger(String fieldName, FieldRegistry registry)
    {
        return new Field<>(fieldName, registry, Integer.class);
    }

    /**
     * Creates a new field of the given type
     *
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @return a new instance of {@link Field}
     */
    @NotNull
    public static Field<String> createString(String fieldName, FieldRegistry registry)
    {
        return new Field<>(fieldName, registry, String.class);
    }

    /**
     * Creates a new field of the given type
     *
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @return a new instance of {@link Field}
     */
    @NotNull
    public static Field<Long> createLong(String fieldName, FieldRegistry registry)
    {
        return new Field<>(fieldName, registry, Long.class);
    }

    /**
     * Creates a new field of the given type
     *
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @return a new instance of {@link Field}
     */
    @NotNull
    public static Field<BigDecimal> createBigDecimal(String fieldName, FieldRegistry registry)
    {
        return new Field<>(fieldName, registry, BigDecimal.class);
    }

    /**
     * Creates a new field of the given type
     *
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @return a new instance of {@link Field}
     */
    @NotNull
    public static Field<LocalDate> createLocalDate(String fieldName, FieldRegistry registry)
    {
        return new Field<>(fieldName, registry, LocalDate.class);
    }

    /**
     * Creates a new field of the given type
     *
     * @param fieldName the fields name
     * @param registry  an optional object able to register this field to
     * @return a new instance of {@link Field}
     */
    @NotNull
    public static Field<LocalDateTime> createLocalDateTime(String fieldName, FieldRegistry registry)
    {
        return new Field<>(fieldName, registry, LocalDateTime.class);
    }

    /**
     * @return the type represented by this field
     */
    @Contract(pure = true)
    public final Class<T> dataType()
    {
        return dataType;
    }

    /**
     * @return this fields identifier {@link FieldIdentifier}
     */
    @Contract(pure = true)
    public FieldIdentifier identifier()
    {
        return identifier;
    }

    /**
     * Returns the {@link ObservableValue<>} interface of {@link #property}.
     *
     * @return {@link ObservableValue<>}
     */
    @Contract(pure = true)
    public ObservableValue<T> observableValue()
    {
        return property;
    }

    /**
     * @return the value of type <T>
     */
    public T getValue()
    {
        return property.get();
    }

    /**
     * Sets value of type T
     *
     * @param value T
     */
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
