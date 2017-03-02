/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import java.util.Map;

/**
 * Concrete implementation of {@link BaseField}, wrapping a {@link Long}.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public class LongField extends BaseField<Long>
{
    /**
     * Creates an instance of this class.
     *
     * @param fieldName the fields fieldName
     * @param fieldMap  an optional map to add this field to
     */
    public LongField(String fieldName, Map<String, Field<?>> fieldMap)
    {
        super(fieldName, Long.class, fieldMap);
    }
}
