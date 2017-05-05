/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.connection;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.persist.jpa.JpaPersistModule;

import java.util.Properties;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-25
 */
class _JpaModule extends AbstractModule
{
    private final Properties connectionProperties = new Properties();
    private final String jpaUnit;

    public _JpaModule(String jpaUnit)
    {
        this.jpaUnit = jpaUnit;
    }

    // Todo: put the service- and the dao-packages here!
    @Override
    protected void configure()
    {
        install(new JpaPersistModule(jpaUnit).properties(connectionProperties));
        install(new at.mjst.finbase.desktop.model.modules._Module());
    }

    /**
     * @return connectionProperties provided by this module
     */
    @Provides
    @ConnectionProperties
    Properties getConnectionProperties()
    {
        return connectionProperties;
    }
}
