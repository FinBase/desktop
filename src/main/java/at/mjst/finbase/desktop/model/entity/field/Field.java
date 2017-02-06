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
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public interface Field<T>
{
    /**
     * @return this fields name
     */
    String getName();

    /**
     * Returns the {@link ObservableValue<>} interface of {@link AbstractField#property}.
     *
     * @return {@link ObservableValue<>}
     */
    ObservableValue<T> getObservableValue();

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
