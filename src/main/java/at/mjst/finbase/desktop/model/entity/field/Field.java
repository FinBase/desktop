/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import javafx.beans.value.ObservableValue;

/**
 * Used as wrapper around instances of {@link ObservableValue} to enable
 * enumeration of {@link at.mjst.finbase.desktop.model.entity.Entity}-columns.
 *
 * Note that instances, implementing {@link Field} are NOT injected by the DI-framework for performance considerations!
 *
 * @param <T> any (serializable) value to be represented by field
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public interface Field<T>
{
    /**
     * @return the type represented by this field
     */
    Class<T> dataType();

    /**
     * @return this fields identifier {@link FieldIdentifier}
     */
    FieldIdentifier identifier();

    /**
     * @return the standalone field name
     */
    String fieldName();

    /**
     * Returns the {@link ObservableValue<>} interface of {@link BaseField#property}.
     *
     * @return {@link ObservableValue<>}
     */
    ObservableValue<T> observableValue();

    /**
     * @return the value of type <T>
     */
    T getValue();

    /**
     * Sets value of type T
     *
     * @param value T
     */
    void setValue(T value);

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    String toString();
}
