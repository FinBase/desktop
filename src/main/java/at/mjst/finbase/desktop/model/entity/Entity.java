/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import java.sql.Timestamp;

import at.mjst.finbase.desktop.model.entity.field.Field;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-19
 */
public interface Entity
{
    /**
     * Returns a {@link Field} by name, casted to the corresponding class.
     *
     * @param fieldName unique name of the field to be found
     * @param fieldType desired class of field - not to be confused with 'dataType' here/
     * @param <T>       data-type of wrapped value
     * @return a {@link Field} instance
     */
    <T extends Field> T getField(String fieldName, Class<T> fieldType);

    /**
     * Returns the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to retrieve the value from
     * @return value or null
     */
    Integer getValueAsInteger(String fieldName);

    /**
     * Sets the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to set the value to
     * @param value     value to set
     */
    void setValueAsInteger(String fieldName, Integer value);

    /**
     * Returns the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to retrieve the value from
     * @return value or null
     */
    String getValueAsString(String fieldName);

    /**
     * Sets the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to set the value to
     * @param value     value to set
     */
    void setValueAsString(String fieldName, String value);

    /**
     * Returns the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to retrieve the value from
     * @return value or null
     */
    Long getValueAsLong(String fieldName);

    /**
     * Sets the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to set the value to
     * @param value     value to set
     */
    void setValueAsLong(String fieldName, Long value);

    /**
     * Returns the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to retrieve the value from
     * @return value or null
     */
    Timestamp getValueAsTimestamp(String fieldName);

    /**
     * Sets the value of a field identified by fieldName.
     *
     * @param fieldName the fields name to set the value to
     * @param value     value to set
     */
    void setValueAsTimestamp(String fieldName, Timestamp value);
}
