/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Concrete implementation of {@link BaseField}, wrapping a {@link Timestamp}.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public class TimestampField extends BaseField<Timestamp>
{
    /**
     * Creates an instance of this class.
     *
     * @param fieldName the fields fieldName
     * @param fieldMap  an optional map to add this field to
     */
    public TimestampField(String fieldName, Map<String, Field<?>> fieldMap)
    {
        super(fieldName, Timestamp.class, fieldMap);
    }
}
