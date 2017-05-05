/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.command;

import org.jetbrains.annotations.NotNull;

import at.mjst.finbase.desktop.model.modules.Service;

/**
 * Ensure all derivatives to be immutable!
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-25
 */
public abstract class Command<T extends Service> implements Runnable
{
    final protected T service;

    protected Command(T service)
    {
        this.service = service;
    }

    @Override
    public final void run()
    {
        try {
            if (service != null) {
                execute(service);
            } else {
                throw new RuntimeException("No service for command!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // ToDo: log? show dialog later?
        }
    }

    protected abstract void execute(@NotNull T service);

    @NotNull
    public abstract Command<T> createWithService(T service);
}
