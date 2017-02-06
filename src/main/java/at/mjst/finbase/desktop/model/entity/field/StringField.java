/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import javafx.beans.property.ObjectProperty;

/**
 * Concrete implementation of {@link AbstractField}, wrapping a {@link String}.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-18
 */
public class StringField extends AbstractField<String>
{
    /**
     * Implement to make this class instantiatable by the
     * {@link at.mjst.finbase.desktop.model.entity.field.FieldFactoryImpl.FieldProvider}.
     *
     * @param fieldName desired fieldName.
     */
    StringField(String fieldName)
    {
        super(fieldName);
    }

    /**
     * Used to manually create an instance. If property is not provided, it will be created and owned automatically!
     *
     * @param fieldName the fields fieldName
     * @param property  ObjectProperty to be wrapped
     */
    public StringField(String fieldName, ObjectProperty<String> property)
    {
        super(fieldName, property);
    }
}
