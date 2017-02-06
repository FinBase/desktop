/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import at.mjst.finbase.desktop.ResourceLocations;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-06-19
 */
@Entity
@Table(name = Account.TABLE_ACCOUNT, schema = ResourceLocations.SCHEMA_FINBASE)
public class Account extends AbstractEntity
{
    static final String TABLE_ACCOUNT = "acc_account";
    // Constants should be held optionally - values must exactly match real column-names!
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_FLAGS = "flags";

    public Account(String name, String description)
    {
        this();
        setName(name);
        setDescription(description);
    }

    /**
     * A no-args constructor for entities is always needed!
     */
    public Account()
    {
        super();
    }

    /**
     * @return the unique id of this entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FIELD_ID, nullable = false, updatable = false)
    public Integer getId()
    {
        return getValueAsInteger(FIELD_ID);
    }

    private void setId(Integer id)
    {
        setValueAsInteger(FIELD_ID, id);
    }

    @Basic
    @Column(name = FIELD_NAME, nullable = false, length = 50)
    public String getName()
    {
        return getValueAsString(FIELD_NAME);
    }

    public void setName(String name)
    {
        setValueAsString(FIELD_NAME, name);
    }

    @Basic
    @Column(name = FIELD_DESCRIPTION, length = 200)
    public String getDescription()
    {
        return getValueAsString(FIELD_DESCRIPTION);
    }

    public void setDescription(String description)
    {
        setValueAsString(FIELD_DESCRIPTION, description);
    }

    @Basic
    @Column(name = FIELD_FLAGS, nullable = false)
    public Integer getFlags()
    {
        return getValueAsInteger(FIELD_FLAGS);
    }

    public void setFlags(Integer flags)
    {
        setValueAsInteger(FIELD_FLAGS, flags);
    }
}
