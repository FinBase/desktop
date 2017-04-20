/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import at.mjst.finbase.desktop.common.field.Field;
import at.mjst.finbase.desktop.common.field.LongField;
import at.mjst.finbase.desktop.common.field.StringField;
import at.mjst.finbase.desktop.common.field.TimestampField;

import static at.mjst.finbase.desktop.model.entity.Entity.SCHEMA_FINBASE;

/**
 * AuditLog entity. Represents an entry storing user login/logout audit data.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-21
 */
@Entity
@DynamicUpdate() // used to update only changed fields of an attached entry
//@SelectBeforeUpdate(true) // not necessary as this entry is only maintained by the very same session!
@Table(name = AuditLog.TABLE_AUDITLOG, schema = SCHEMA_FINBASE)
public class AuditLog extends AbstractEntity
{
    // Constants should be held optionally - values must exactly match real column-names!
    public static final String TABLE_AUDITLOG = "aul_auditlog";
    public static final String FIELD_APPLICATION = "application";
    public static final String FIELD_USER = "user";
    public static final String FIELD_TIMESTAMP_ON = "timestamp_on";
    public static final String FIELD_TIMESTAMP_OFF = "timestamp_off";
    // These 'Field'-Objects wrap ObjectProperties<>, to access them via column-names!
    private final LongField id = new LongField(FIELD_ID, this);
    private final StringField user = new StringField(FIELD_USER, this);
    private final TimestampField timestampOn = new TimestampField(FIELD_TIMESTAMP_ON, this);
    private final TimestampField timestampOff = new TimestampField(FIELD_TIMESTAMP_OFF, this);
    private final StringField application = new StringField(FIELD_APPLICATION, this);

    /**
     * For testing purposes only
     */
    public AuditLog(Long id, String test1, Timestamp timestamp, String appl1)
    {
        this();
        setId(id);
        setUser(test1);
        setTimestampOn(timestamp);
        setApplication(appl1);
    }

    /**
     * A no-args constructor for entities is always needed!
     */
    public AuditLog()
    {
        super();
    }

    @Override
    public String tableName()
    {
        return TABLE_AUDITLOG;
    }

    @Override
    void buildBusinessKey(Collection<Field<?>> businessKey)
    {
        // there is no obvious business-key here...
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FIELD_ID, nullable = false, updatable = false)
    public Long getId()
    {
        return id.getValue();
    }

    private void setId(Long id)
    {
        this.id.setValue(id);
    }

    @Basic
    @Column(name = FIELD_USER, nullable = false, length = 81)
    public String getUser()
    {
        return user.getValue();
    }

    public void setUser(String user)
    {
        this.user.setValue(user);
    }

    @Basic
    @Column(name = FIELD_TIMESTAMP_ON, nullable = false)
    public Timestamp getTimestampOn()
    {
        return timestampOn.getValue();
    }

    public void setTimestampOn(Timestamp timestamp)
    {
        this.timestampOn.setValue(timestamp);
    }

    @Basic
    @Column(name = FIELD_TIMESTAMP_OFF)
    public Timestamp getTimestampOff()
    {
        return timestampOff.getValue();
    }

    public void setTimestampOff(Timestamp timestamp)
    {
        this.timestampOff.setValue(timestamp);
    }

    @Basic
    @Column(name = FIELD_APPLICATION, nullable = false, length = 45)
    public String getApplication()
    {
        return application.getValue();
    }

    public void setApplication(String application)
    {
        this.application.setValue(application);
    }
}
