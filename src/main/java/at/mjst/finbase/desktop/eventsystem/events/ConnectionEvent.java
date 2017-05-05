/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem.events;

import at.mjst.finbase.desktop.model.ConnectionContext;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-26
 */
public abstract class ConnectionEvent extends Event
{
    /**
     * @param context the event's sender/connection context
     */
    private ConnectionEvent(ConnectionContext context)
    {
        super(context);
    }

    public ConnectionContext getContext()
    {
        return (ConnectionContext) getSender();
    }

    /**
     * Notifies listeners, that a shutdown is immanent and connection will be closed soon
     */
    public static class AnnounceShutdown extends ConnectionEvent
    {
        /**
         * @param context the event's sender/connection context
         */
        public AnnounceShutdown(ConnectionContext context)
        {
            super(context);
        }
    }

    /**
     * Sent, if connection context created
     */
    public static class ContextRegistered extends ConnectionEvent
    {
        /**
         * @param context the event's sender/connection context
         */
        public ContextRegistered(ConnectionContext context)
        {
            super(context);
        }
    }

    /**
     * Sent, if the connection was successfully established
     */
    public static class Established extends ConnectionEvent
    {
        /**
         * @param context the event's sender/connection context
         */
        public Established(ConnectionContext context)
        {
            super(context);
        }
    }

    /**
     * Sent, if logout was successful and connection closed
     */
    public static class Closed extends ConnectionEvent
    {
        /**
         * @param context the event's sender/connection context
         */
        public Closed(ConnectionContext context)
        {
            super(context);
        }
    }

    /**
     * Sends error information in case of login failure
     */
    public static class Failure extends ConnectionEvent
    {
        private String errorText;

        /**
         * @param context   the event's sender/connection context
         * @param errorText error information in textual form
         */
        public Failure(ConnectionContext context, String errorText)
        {
            super(context);
            this.errorText = errorText;
        }

        /**
         * @return error information in textual form
         */
        public String getErrorText()
        {
            return errorText;
        }
    }
}
