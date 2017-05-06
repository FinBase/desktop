/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.bind;

import com.google.inject.Injector;

import javax.inject.Inject;

import at.mjst.finbase.desktop.model.entity.Entity;
import at.mjst.finbase.desktop.model.entity.FieldIdentifier;
import javafx.util.Callback;

/**
 * Enables guice to instantiate a {@link Callback} interface for cellValueFactories. Because the required class is
 * highly generic, we do NOT implement Guice's {@link com.google.inject.Provider} here, since it requires fully
 * qualified types! But we can fortunately circumvent injection-issues by calling guice's injectMembers() method!
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-07
 */
public abstract class CellValueFactoryProvider
{
    @Inject
    private Injector injector;

    /**
     * This method is based on guice {@link com.google.inject.Provider} but with the convenience of generics and the
     * possibility to add the {@link FieldIdentifier} here.
     *
     * @param identifier the fields identifier, which value is displayed
     * @param <S>        the entity the factory is built for
     * @param <T>        field's data type
     * @return a new {@link CellValueFactory}
     */
    public <S extends Entity, T> CellValueFactory<S, T> get(FieldIdentifier identifier)
    {
        if (identifier == null) {
            throw new RuntimeException("Missing FieldIdentifier!");
        }
        CellValueFactory<S, T> cellValueFactory = generateInstance(identifier);
        injector.injectMembers(cellValueFactory);
        return cellValueFactory;
    }

    /**
     * Creates a new CellValueFactory
     *
     * @param identifier the {@link FieldIdentifier} to be used for creating the factory
     * @return new {@link Callback}
     */
    abstract <S extends Entity, T> CellValueFactory<S, T> generateInstance(FieldIdentifier identifier);
}
