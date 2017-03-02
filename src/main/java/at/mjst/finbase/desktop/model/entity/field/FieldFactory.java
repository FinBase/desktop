/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

/**
 * A Factory generating field-objects by their wrapping type.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-25
 */
public interface FieldFactory
{
    /**
     * Generates a new instance of {@link Field}.
     *
     * @param fieldName the field's future name
     * @param dataType  the data-type wrapped by the future field
     * @return a new instance of {@link Field} with the given name and data-type
     */
    Field<?> getNewFieldInstance(String fieldName, Class<?> dataType);
}
