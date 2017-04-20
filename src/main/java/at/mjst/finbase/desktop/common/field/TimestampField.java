/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.common.field;

import java.sql.Timestamp;

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
     * @param registry  an optional map to add this field to
     */
    public TimestampField(String fieldName, FieldRegistry registry)
    {
        super(fieldName, registry, Timestamp.class);
    }
}
