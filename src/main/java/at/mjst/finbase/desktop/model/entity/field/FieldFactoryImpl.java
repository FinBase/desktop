/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link FieldFactory}-interface.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-21
 */
public class FieldFactoryImpl implements FieldFactory
{
    private static final Map<Class<?>, FieldProvider> typeMap = new HashMap<>();

    static {
        synchronized (typeMap) {
            // any new data-type <-> fieldProvider mapping must be registered here!!
            register(Integer.class, IntegerField::new);
            register(String.class, StringField::new);
            register(Long.class, LongField::new);
            register(Timestamp.class, TimestampField::new);
        }
    }

    /**
     * Statically registers a new data-type <-> {@link Field}-mapping.
     *
     * @param dataType the data-type to be assigned a {@link Field} to.
     * @param provider anonymous class (method reference) to the fields constructor
     * @see FieldProvider
     */
    private static synchronized void register(Class<?> dataType, FieldProvider provider)
    {
        if (typeMap.containsKey(dataType)) {
            throw new RuntimeException(String.format("Map already contains Field of type %s!", dataType.getName()));
        }
        typeMap.put(dataType, provider);
    }

    @Override
    public Field<?> getNewFieldInstance(String fieldName, Class<?> dataType)
    {
        FieldProvider builder = getFromMap(dataType);
        return builder.createInstance(fieldName);
    }

    /**
     * Retrieves the mapped {@link FieldProvider} for later instantiating a {@link Field}.
     *
     * @param dataType desired data-type
     * @return a {@link FieldProvider} capable of creating a new Instance of {@link Field}
     */
    private FieldProvider getFromMap(Class<?> dataType)
    {
        synchronized (typeMap) {
            return typeMap.get(dataType);
            // ToDo: sync. really necessary? Might not be necessary, since the factory is already initialized, long before other threads come into play
        }
    }

    /**
     * Use to implement construction of specific {@link Field}-type.
     *
     * @author Ing. Michael J. Stallinger (projects@mjst.at)
     * @since 2017-01-24
     */
    public interface FieldProvider
    {
        /**
         * Creates a new instance of {@link Field} and assignes a name to it.
         *
         * @param fieldName desired name.
         * @return a new instance of {@link Field}
         */
        Field<?> createInstance(String fieldName);
    }
}
