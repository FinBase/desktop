/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import org.jetbrains.annotations.NonNls;

/**
 * Guice provider for instances of {@link EventBus}. Bound as singleton.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-09
 */
public abstract class EventBusProvider implements Provider<EventBus>
{
    @Override
    abstract public EventBus get();

    /**
     * Generates the provider for a controller-side bus
     */
    static class UI extends EventBusProvider
    {
        @NonNls
        private static final String NAME_CONTROLLER_BUS = "UIBus";

        @Override
        public EventBus get()
        {
            return new EventBus(NAME_CONTROLLER_BUS);
        }
    }

    /**
     * Generates the provider for a model-side bus
     */
    static class Model extends EventBusProvider
    {
        @NonNls
        private static final String NAME_MODEL_BUS = "Model";
        @Inject
        private UIEventDispatcher dispatcher;
        @Inject
        @UIBus
        private EventBus controllerBus;

        @Override
        public EventBus get()
        {
            EventBus eventBus = new EventBus(NAME_MODEL_BUS);
            connectBusses(eventBus);
            return eventBus;
        }

        /**
         * Connects the passed eventBus to the UIBus-Bus
         *
         * @param eventBus modelBus
         */
        private void connectBusses(EventBus eventBus)
        {
            dispatcher.subscribeTo(eventBus);
            dispatcher.setPublishTo(controllerBus);
        }
    }
}
