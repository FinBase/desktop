/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import at.mjst.finbase.desktop.model.entity.field.Field;
import at.mjst.finbase.desktop.model.entity.field.IntegerField;
import at.mjst.finbase.desktop.model.entity.field.StringField;

import static at.mjst.finbase.desktop.model.entity.Entity.SCHEMA_FINBASE;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-06-19
 */
@Entity
@Table(name = Account.TABLE_ACCOUNT, schema = SCHEMA_FINBASE)
public class Account extends AbstractEntity
{
    public static final String TABLE_ACCOUNT = "acc_account";
    // Constants should be held optionally - values must exactly match real column-names!
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    // These 'Field'-Objects wrap ObjectProperties<>, to access them via column-names!
    private IntegerField id = new IntegerField(FIELD_ID, this);
    private StringField name = new StringField(FIELD_NAME, this);
    private StringField description = new StringField(FIELD_DESCRIPTION, this);
    private IntegerField flags = new IntegerField(FIELD_FLAGS, this);

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

    @Override
    public String tableName()
    {
        return TABLE_ACCOUNT;
    }

    @Override
    void buildBusinessKey(Collection<Field<?>> businessKey)
    {
        businessKey.add(name); // name is unique for account!
        // ToDo: future: ClientId (dataOwner)
    }

    /**
     * @return the unique id of this entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FIELD_ID, nullable = false, updatable = false)
    public Integer getId()
    {
        return id.getValue();
    }

    private void setId(Integer id)
    {
        this.id.setValue(id);
    }

    @Basic
    @Column(name = FIELD_NAME, nullable = false, length = 50)
    public String getName()
    {
        return name.getValue();
    }

    public void setName(String name)
    {
        this.name.setValue(name);
    }

    @Basic
    @Column(name = FIELD_DESCRIPTION, length = 200)
    public String getDescription()
    {
        return description.getValue();
    }

    public void setDescription(String description)
    {
        this.description.setValue(description);
    }

    @Basic
    @Column(name = FIELD_FLAGS, nullable = false)
    public Integer getFlags()
    {
        return flags.getValue();
    }

    public void setFlags(Integer flags)
    {
        this.flags.setValue(flags);
    }
}
