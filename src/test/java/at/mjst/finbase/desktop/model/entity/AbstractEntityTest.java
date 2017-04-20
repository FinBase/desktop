/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.jetbrains.annotations.NonNls;
import org.junit.Test;

import java.util.Collection;

import at.mjst.finbase.desktop.common.field.Field;
import at.mjst.finbase.desktop.common.field.IntegerField;
import at.mjst.finbase.desktop.common.field.StringField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for some methods of {@link AbstractEntity}
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-06
 */
public class AbstractEntityTest
{
    private static final Integer TEST_ID_1 = 17;
    private static final Integer TEST_ID_2 = 24;
    @NonNls
    private static final String TEST_NAME_1 = "NAME1";
    @NonNls
    private static final String TEST_NAME_2 = "NAME2";

    /**
     * Does an Entity without fields work?
     *
     * @throws Exception if this does not work
     */
    @Test
    public void createInstanceWithoutFields() throws Exception
    {
        Entity e1 = new EntityWithoutFields();
        assertNotEquals(0, e1.hashCode());
    }

    /**
     * Instances have different hashCodes
     *
     * @throws Exception if hashCodes are the same
     */
    @Test
    public void hashCodesNotEqualWoBusinessKey() throws Exception
    {
        Entity e1;
        Entity e2;
        e1 = new EntityWOBusinessKey(TEST_ID_1, TEST_NAME_1);
        e2 = new EntityWOBusinessKey(TEST_ID_1, TEST_NAME_1);
        assertNotEquals(e1.hashCode(), e2.hashCode());
        e1 = new EntityWOBusinessKey(TEST_ID_2, TEST_NAME_2);
        e2 = new EntityWOBusinessKey(TEST_ID_2, TEST_NAME_2);
        assertNotEquals(e1.hashCode(), e2.hashCode());
    }

    /**
     * Instances with BK have equal hashCodes
     *
     * @throws Exception if hashCodes are not the same
     */
    @Test
    public void hashCodesEqualWithBusinessKey() throws Exception
    {
        Entity e1;
        Entity e2;
        e1 = new EntityWithBusinessKey(TEST_ID_1, TEST_NAME_1);
        e2 = new EntityWithBusinessKey(TEST_ID_1, TEST_NAME_1);
        assertEquals(e1.hashCode(), e2.hashCode());
        e1 = new EntityWithBusinessKey(TEST_ID_2, TEST_NAME_2);
        e2 = new EntityWithBusinessKey(TEST_ID_2, TEST_NAME_2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    /**
     * Instances without BK are not the same until it is the same instance
     *
     * @throws Exception if different instances are the same
     */
    @Test
    public void notEqualsWoBusinessKey() throws Exception
    {
        Entity e1;
        Entity e2;
        Entity e3;
        e1 = new EntityWOBusinessKey(TEST_ID_1, TEST_NAME_1);
        e2 = new EntityWOBusinessKey(TEST_ID_1, TEST_NAME_1);
        e3 = new EntityWOBusinessKey(TEST_ID_2, TEST_NAME_2);
        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e3));
        Entity e4 = e1;
        assertTrue(e1.equals(e4));
    }

    /**
     * Instances without BK are not the same until it is the same instance
     *
     * @throws Exception if different instances are the same
     */
    @Test
    public void notEqualsWithBusinessKey() throws Exception
    {
        Entity e1;
        Entity e2;
        EntityWithBusinessKey e3;
        e1 = new EntityWithBusinessKey(TEST_ID_1, TEST_NAME_1);
        e2 = new EntityWithBusinessKey(TEST_ID_1, TEST_NAME_1);
        e3 = new EntityWithBusinessKey(TEST_ID_2, TEST_NAME_2);
        assertTrue(e1.equals(e2));
        assertFalse(e2.equals(e3));
        Entity e4 = e1;
        assertTrue(e1.equals(e4));
        e3.setId(TEST_ID_1);
        e3.setName(TEST_NAME_1);
        assertTrue(e2.equals(e3));
    }

    /**
     * Analyzes, if the descriptor made from toString() is correct
     *
     * @throws Exception if it does not meet the toString requirements of FinBase's Entities
     */
    @Test
    public void toStringTest() throws Exception
    {
        Entity e1 = new EntityWithBusinessKey(TEST_ID_1, TEST_NAME_1);
        assertFalse(e1.toString().isEmpty()); // ToDo: analyze string?
    }

    /**
     * Values are stored and retrieved via fields created in Entity
     *
     * @throws Exception if the value cannot be stored/retrieved
     */
    @Test
    public void getFieldSetField() throws Exception
    {
        EntityWOBusinessKey e1 = new EntityWOBusinessKey(TEST_ID_1, TEST_NAME_1);
        assertEquals(e1.getId(), TEST_ID_1);
        assertEquals(e1.getName(), TEST_NAME_1);
        e1.setId(TEST_ID_2);
        e1.setName(TEST_NAME_2);
        assertNotEquals(e1.getId(), TEST_ID_1);
        assertNotEquals(e1.getName(), TEST_NAME_1);
        assertEquals(e1.getId(), TEST_ID_2);
        assertEquals(e1.getName(), TEST_NAME_2);
    }
}

/**
 * Concrete implementation of AbstractEntity that does not have any fields
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-06
 */
class EntityWithoutFields extends AbstractEntity
{
    @NonNls
    private static final String TEST_TABLE_NAME = "TEST_TABLE_NO_FIELDS";

    @Override
    public String tableName()
    {
        return TEST_TABLE_NAME;
    }

    @Override
    void buildBusinessKey(Collection<Field<?>> businessKey)
    {
    }
}

/**
 * Concrete implementation of AbstractEntity without business key fields
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-06
 */
class EntityWOBusinessKey extends AbstractEntity
{
    @NonNls
    private static final String FIELD_ID = "FIELD_ID";
    @NonNls
    private static final String FIELD_NAME = "FIELD_NAME";
    @NonNls
    private static final String TEST_TABLE_NAME = "TEST_TABLE_NO_BK";
    /**
     * Fields
     */
    Field<String> name = new StringField(FIELD_NAME, this);
    private Field<Integer> id = new IntegerField(FIELD_ID, this);

    /**
     * @param id   initial value for id
     * @param name initial value for name
     */
    EntityWOBusinessKey(int id, String name)
    {
        this.id.setValue(id);
        this.name.setValue(name);
    }

    /**
     * @return the id
     */
    Integer getId()
    {
        return this.id.getValue();
    }

    /**
     * @param id to set
     */
    void setId(Integer id)
    {
        this.id.setValue(id);
    }

    /**
     * @return the name
     */
    String getName()
    {
        return this.name.getValue();
    }

    /**
     * @param name to set
     */
    void setName(String name)
    {
        this.name.setValue(name);
    }

    @Override
    public String tableName()
    {
        return TEST_TABLE_NAME;
    }

    @Override
    void buildBusinessKey(Collection<Field<?>> businessKey)
    {
    }
}

/**
 * Concrete implementation of AbstractEntity with name as business key fields
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-06
 */
class EntityWithBusinessKey extends EntityWOBusinessKey
{
    @NonNls
    private static final String TEST_TABLE_NAME = "TEST_TABLE_WITH_BK";

    /**
     * @param id   initial value for id
     * @param name initial value for name
     */
    EntityWithBusinessKey(int id, String name)
    {
        super(id, name);
    }

    @Override
    public String tableName()
    {
        return TEST_TABLE_NAME;
    }

    @Override
    void buildBusinessKey(Collection<Field<?>> businessKey)
    {
        businessKey.add(name);
    }
}
