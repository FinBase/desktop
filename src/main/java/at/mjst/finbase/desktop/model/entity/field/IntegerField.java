/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import java.util.Map;

/**
 * Concrete implementation of {@link AbstractField}, wrapping an {@link Integer}.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public class IntegerField extends AbstractField<Integer>
{
    /**
     * Implement to make this class instantiable by the
     * {@link at.mjst.finbase.desktop.model.entity.field.FieldFactoryImpl.FieldProvider}.
     *
     * @param fieldName the fields fieldName
     */
    IntegerField(String fieldName)
    {
        super(fieldName);
    }

    /**
     * Creates an instance of this class.
     *
     * @param fieldName the fields fieldName
     * @param fieldMap  an optional map to add this field to
     */
    public IntegerField(String fieldName, Map<String, Field<?>> fieldMap)
    {
        super(fieldName, fieldMap);
    }

    @Override
    public Class<Integer> getType()
    {
        return Integer.class;
    }
}
