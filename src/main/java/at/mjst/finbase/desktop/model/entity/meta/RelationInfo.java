/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.meta;

import at.mjst.finbase.desktop.model.entity.Entity;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-04
 */
public class RelationInfo
{
    private Class<? extends Entity> entityClass;
    private RelatedEntityGetter getter;

    public RelationInfo(Class<? extends Entity> entityClass, RelatedEntityGetter getter)
    {
        this.entityClass = entityClass;
        this.getter = getter;
    }

    public Class<? extends Entity> getEntityClass()
    {
        return entityClass;
    }

    public RelatedEntityGetter getGetter()
    {
        return getter;
    }

    public Entity executeGetter()
    {
        return getter.getRelatedEntity();
    }
}
