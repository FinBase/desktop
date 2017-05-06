/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.common.util;

/**
 * Helper for generating HashCodes. ToDo: maybe move to lib-java some day
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-05
 */
public class HashCodeBuilder
{
    private final static int SHL = 5;
    private int hashCode = 0;

    /**
     * Creates HashCodeBuilder by passing varargs
     *
     * @param objects multiple objects, whose hashCodes to be added
     * @return an instance of the {@link HashCodeBuilder}, to allow chained calls
     */
    public HashCodeBuilder append(Object... objects)
    {
        for (Object o : objects) {
            append(o);
        }
        return this;
    }

    /**
     * Adds the objects hashCode() to this instances hashCode
     *
     * @param o object, whose hashCode to be added
     * @return an instance of the {@link HashCodeBuilder}, to allow chained calls
     */
    public HashCodeBuilder append(Object o)
    {
        hashCode = (((hashCode << SHL) - hashCode) + (o == null ? 0 : o.hashCode()));
        return this;
    }

    @Override
    public int hashCode()
    {
        return hashCode;
    }
}
