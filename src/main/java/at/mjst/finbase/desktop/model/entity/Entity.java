/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import java.util.Collection;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-19
 */
public interface Entity
{
    /**
     * Almost every PK in every table is called 'id'
     */
    String FIELD_ID = "id";
    /**
     * 'flags' is a standardized fieldname here, too
     */
    String FIELD_FLAGS = "flags";
    /**
     * The database schema, finbase is located in
     */
    String SCHEMA_FINBASE = "at_mjst_finbase";

    /**
     * @return the current table name
     */
    String tableName();
    //    /**
    //     * Returns a {@link Field} by name, casted to the corresponding class.
    //     *
    //     * @param fieldName unique name of the field to be found
    //     * @param fieldType desired class of field - not to be confused with 'dataType' here/
    //     * @param <T>       data-type of wrapped value
    //     * @return a {@link Field} instance
    //     */
    //    <T extends Field<?>> T getField(String fieldName, Class<T> fieldType);

    /**
     * Returns a {@link Field} by name, the superclass will be returned.
     *
     * @param fieldName unique name of the field to be found
     * @return a {@link Field} instance
     */
    Field<?> getField(String fieldName);

    /**
     * Returns a {@link Field} by name, the superclass will be returned. If the field does not match with the entities
     * table name, this method tries to find a field of eventually existing relations.
     *
     * @param identifier unique identifier of the field to be found
     * @return a {@link Field} instance
     */
    Field<?> getField(FieldIdentifier identifier);

    /**
     * @param withRelatedFields sets whether to add fields of related entities, or not
     * @return an unmodifiable Collection of the entities {@link Field}s
     */
    Collection<Field<?>> getFields(boolean withRelatedFields);

    /**
     * @return an unmodifiable Collection of ALL entities {@link Field}s (own and related)
     */
    Collection<Field<?>> getFields();
}
