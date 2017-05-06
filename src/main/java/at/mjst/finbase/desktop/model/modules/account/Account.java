/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.account;

import org.hibernate.annotations.DynamicUpdate;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import at.mjst.finbase.desktop.model.entity.AbstractEntity;
import at.mjst.finbase.desktop.model.entity.Field;
import at.mjst.finbase.desktop.model.modules.transaction.TraAggregated;

import static at.mjst.finbase.desktop.model.entity.Entity.SCHEMA_FINBASE;
import static at.mjst.finbase.desktop.model.modules.account.Account.FIELD_NAME;

/**
 * ToDo: Short class description
 * ToDo: add
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-06-19
 */
@Entity
@DynamicUpdate() // used to update only changed fields of an attached entry
@Table(name = Account.TABLE_ACCOUNT, schema = SCHEMA_FINBASE, uniqueConstraints = {@UniqueConstraint(columnNames = FIELD_NAME)})
public class Account extends AbstractEntity
{
    public static final String TABLE_ACCOUNT = "acc_account";
    // Constants should be held optionally - values must exactly match real column-names!
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    // These 'Field'-Objects wrap ObjectProperties<>, to access them via column-names!
    private final Field<Integer> id = Field.createInteger(FIELD_ID, this);
    private final Field<String> name = Field.createString(FIELD_NAME, this);
    private final Field<String> description = Field.createString(FIELD_DESCRIPTION, this);
    private final Field<Integer> flags = Field.createInteger(FIELD_FLAGS, this);
    // todo: register...?!
    private TraAggregated traAggregated;

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
        registerToOneRelation(TraAggregated.VIEW_TRA_AGGREGATED, this::getTraAggregated);
    }

    @Override
    public String tableName()
    {
        return TABLE_ACCOUNT;
    }

    @Override
    protected void buildBusinessKey(Collection<Field<?>> businessKey)
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

    /**
     * Relationship with aggregated view over account-transactions. See {@link TraAggregated} for details.
     * Notes:
     * - 'FetchType.LAZY' means, that the relation is loaded 'on demand', some JPA/Hibernate-magic happens here...
     * - The 'optional' switch tells the implementation, that there definitely WILL be a corresponding dataset there.
     * So, it can replace the (lazily loaded) Entity with a proxy-object for now, and load it later, if accessed.
     *
     * Therefor, the view MUST represent distinct data for EVERY account-id!!
     *
     * @return an instance of {@link TraAggregated}-row
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = FIELD_ID, referencedColumnName = FIELD_ID)
    public TraAggregated getTraAggregated()
    {
        return traAggregated;
    }

    private void setTraAggregated(TraAggregated traAggregated)
    {
        this.traAggregated = traAggregated;
    }
}
