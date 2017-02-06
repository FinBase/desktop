/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity.field;

import com.google.inject.AbstractModule;

/**
 * Guice configuration module.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-25
 */
public class Module extends AbstractModule
{
    private static ThreadLocal<FieldFactory> defaultFieldFactory = new ThreadLocal<>();

    /**
     * According to
     * <a href="http://stackoverflow.com/questions/11638311/can-you-use-dependency-injection-in-persistent-entities">
     * this stackOverflow article</a>, it is not advised to use DI for entities. So, we have to statically
     * set, which factory to use for our entity-fields here. However, this might be changed some day...
     *
     * @return a new instance of the current FieldFactory implementation.
     */
    public static FieldFactory getFieldFactoryInstance()
    {
        try {
            if (defaultFieldFactory.get() == null) {
                FieldFactory factory = getFieldFactoryClass().newInstance();
                defaultFieldFactory.set(factory);
                return factory;
            } else {
                return defaultFieldFactory.get();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return the class of the current {@link FieldFactory}-implementation.
     */
    private static Class<? extends FieldFactory> getFieldFactoryClass()
    {
        return FieldFactoryImpl.class;
    }

    @Override
    protected void configure()
    {
        bind(FieldFactory.class).to(getFieldFactoryClass());
    }
}
