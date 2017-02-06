/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import at.mjst.finbase.desktop.ResourceLocations;
import at.mjst.finbase.desktop.model.entity.field.LongField;
import at.mjst.finbase.desktop.model.entity.field.StringField;
import at.mjst.finbase.desktop.model.entity.field.TimestampField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * AuditLog entity. Represents an entry storing user login/logout audit data.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-21
 */
@Entity
@DynamicUpdate() // used to update only changed fields of an attached entry
//@SelectBeforeUpdate(true) // not necessary as this entry is only maintained by the very same session!
@Table(name = AuditLog.TABLE_AUDITLOG, schema = ResourceLocations.SCHEMA_FINBASE)
public class AuditLog extends AbstractEntity
{
    // TableName
    static final String TABLE_AUDITLOG = "aul_auditlog";
    // Constants should be held optionally - values must exactly match real column-names!
    private static final String FIELD_ID = "id";
    private static final String FIELD_USER = "user";
    private static final String FIELD_TIMESTAMP_ON = "timestamp_on";
    private static final String FIELD_TIMESTAMP_OFF = "timestamp_off";
    private static final String FIELD_APPLICATION = "application";
    // These properties are wrapped into 'Field'-Objects at construction time to access them via column-names!
    private ObjectProperty<Long> id;
    private ObjectProperty<String> user;
    private ObjectProperty<Timestamp> timestampOn;
    private ObjectProperty<Timestamp> timestampOff;
    private ObjectProperty<String> application;

    /**
     * Manually creates the value(object)-properties and their wrapping fields.
     */
    @Override
    public void generateFields()
    {
        super.generateFields();
        id = new SimpleObjectProperty<>();
        addField(new LongField(FIELD_ID, id));
        user = new SimpleObjectProperty<>();
        addField(new StringField(FIELD_USER, user));
        timestampOn = new SimpleObjectProperty<>();
        addField(new TimestampField(FIELD_TIMESTAMP_ON, timestampOn));
        timestampOff = new SimpleObjectProperty<>();
        addField(new TimestampField(FIELD_TIMESTAMP_OFF, timestampOff));
        application = new SimpleObjectProperty<>();
        addField(new StringField(FIELD_APPLICATION, application));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FIELD_ID, nullable = false, updatable = false)
    public Long getId()
    {
        return id.get();
    }

    private void setId(Long id)
    {
        this.id.set(id);
    }

    @Basic
    @Column(name = FIELD_USER, nullable = false, length = 81)
    public String getUser()
    {
        return user.get();
    }

    public void setUser(String user)
    {
        this.user.set(user);
    }

    @Basic
    @Column(name = FIELD_TIMESTAMP_ON, nullable = false)
    public Timestamp getTimestampOn()
    {
        return timestampOn.get();
    }

    public void setTimestampOn(Timestamp timestamp)
    {
        this.timestampOn.set(timestamp);
    }

    @Basic
    @Column(name = FIELD_TIMESTAMP_OFF)
    public Timestamp getTimestampOff()
    {
        return timestampOff.get();
    }

    public void setTimestampOff(Timestamp timestamp)
    {
        this.timestampOff.set(timestamp);
    }

    @Basic
    @Column(name = FIELD_APPLICATION, nullable = false, length = 45)
    public String getApplication()
    {
        return application.get();
    }

    public void setApplication(String application)
    {
        this.application.set(application);
    }
}
