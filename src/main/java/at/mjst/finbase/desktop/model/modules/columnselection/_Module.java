/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.columnselection;

import com.google.inject.AbstractModule;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-05-06
 */
public class _Module extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(ArrayBasedGenerator.class).to(ArrayBasedGeneratorImpl.class);
        bind(EntityBasedGenerator.class).to(EntityBasedGeneratorImpl.class);
    }
}
