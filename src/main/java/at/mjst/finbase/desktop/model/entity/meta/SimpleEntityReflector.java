/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.field.Field;

/**
 * Reflects entities ({@link Entity}) field- and other meta-data by accessing a simple instance of the class.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-23
 */
class SimpleEntityReflector implements EntityReflector
{
    /**
     * A thread-local map, containing already reflected Entities
     */
    private static ThreadLocal<Map<Class<? extends Entity>, EntityMetaData>> classMapContainer = new ThreadLocal<>();

    @Override
    @NotNull
    public EntityMetaData getEntityMetaData(
            Class<? extends Entity> entityClass) throws InstantiationException, IllegalAccessException
    {
        if (getClassMap().containsKey(entityClass)) {
            return getClassMap().get(entityClass);
        } else {
            EntityMetaData data = generateEntityMetaData(entityClass);
            getClassMap().put(entityClass, data);
            return data;
        }
    }

    /**
     * @return {@link Entity} to {@link EntityMetaData} map (thread local)
     */
    @NotNull
    private Map<Class<? extends Entity>, EntityMetaData> getClassMap()
    {
        if (classMapContainer.get() == null) {
            Map<Class<? extends Entity>, EntityMetaData> classMap = new HashMap<>();
            classMapContainer.set(classMap);
            return classMap;
        } else {
            return classMapContainer.get();
        }
    }

    /**
     * Generates new {@link EntityMetaData} for given {@link Entity} class
     *
     * @param entityClass the class to be analyzed
     * @return {@link EntityMetaData} a metadata-structure for the questioned entity
     * @throws IllegalAccessException if the no-args constructor of the entity is not accessible
     * @throws InstantiationException if there are other errors instantiating the entity
     */
    @NotNull
    private EntityMetaData generateEntityMetaData(
            Class<? extends Entity> entityClass) throws IllegalAccessException, InstantiationException
    {
        Entity entity = entityClass.newInstance();
        return new EntityMetaData(entity.tableName(), getFieldTypeMap(entity));
    }

    /**
     * @param entity the {@link Entity}-instance to analyze
     * @return an object, that maps the field's name to it's data type
     */
    @NotNull
    private Map<String, FieldMetaData<?>> getFieldTypeMap(Entity entity)
    {
        Map<String, FieldMetaData<?>> fieldTypeMap = new HashMap<>();
        for (Field<?> field : entity.getFields()) {
            String fieldName = field.identifier().fieldName();
            assert (!fieldTypeMap.containsKey(fieldName)) : String.format("Field '%s' already found!", fieldName);
            fieldTypeMap.put(fieldName, new FieldMetaData<>(fieldName, field.dataType()));
        }
        return fieldTypeMap;
    }
}
