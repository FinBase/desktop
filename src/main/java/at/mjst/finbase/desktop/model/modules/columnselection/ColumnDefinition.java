/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.columnselection;

import org.jetbrains.annotations.Contract;

import at.mjst.finbase.desktop.model.entity.FieldIdentifier;

/**
 * Represents a container for column properties, identified by {@link FieldIdentifier}. For
 * comparison, {@link #equals(Object)} and {@link #hashCode()} also delegate to this identifier.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public final class ColumnDefinition
{
    private final FieldIdentifier identifier;
    private final ColumnProperties properties = new ColumnProperties();

    /**
     * Required as constructor
     *
     * @param fieldIdentifier identifies this definition entry
     */
    public ColumnDefinition(FieldIdentifier fieldIdentifier)
    {
        this.identifier = fieldIdentifier;
    }

    /**
     * @return the properties for this definition
     */
    @Contract(pure = true)
    public ColumnProperties getProperties()
    {
        return properties;
    }

    @Contract(pure = true)
    @Override
    public int hashCode()
    {
        return identifier.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj) || ((obj instanceof ColumnDefinition) && identifier.equals(
                ((ColumnDefinition) obj).identifier()));
    }

    @Contract(" -> !null")
    @Override
    public String toString()
    {
        return (super.toString() + identifier.toString());
    }

    /**
     * @return this definitions identifier
     */
    @Contract(pure = true)
    public FieldIdentifier identifier()
    {
        return identifier;
    }
}
