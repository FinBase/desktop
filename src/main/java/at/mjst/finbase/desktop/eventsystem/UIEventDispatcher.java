/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;

/**
 * Dispatches every {@link Event}, posted by the 'subscribeTo' eventBus, to the registered 'publishTo' eventBus.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-10
 */
class UIEventDispatcher
{
    private EventBus publisher;

    /**
     * Registers the dispatcher to handle the passed events of the passed {@link EventBus}
     *
     * @param eventBus eventBus, listening to
     */
    void subscribeTo(EventBus eventBus)
    {
        eventBus.register(this);
    }

    /**
     * Sets an eventBus to publish the events to.
     *
     * @param eventBus to publish to
     */
    void setPublishTo(EventBus eventBus)
    {
        publisher = eventBus;
        // todo: maybe a list some day...
    }

    /**
     * Receives event of the registered eventBus and dispatches it to an Event bus at the gui-level
     *
     * @param event event received
     */
    @Subscribe
    void handleAsync(Event event)
    {
        if (publisher != null) {
            System.out.println(String.format("Performing event '%s' later...", event));
            Platform.runLater(() -> publisher.post(event));
        }
    }
}
