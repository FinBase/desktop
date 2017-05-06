/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.connection;

import org.jetbrains.annotations.NotNull;

import at.mjst.finbase.desktop.eventsystem.Event;

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

    @NotNull
    public static AnnounceShutdown announceShutdown(ConnectionContext context)
    {
        return new AnnounceShutdown(context);
    }

    @NotNull
    public static Established established(ConnectionContext context)
    {
        return new Established(context);
    }

    @NotNull
    public static Closed closed(ConnectionContext context)
    {
        return new Closed(context);
    }

    @NotNull
    public static Failure failure(ConnectionContext context, String errorText)
    {
        return new Failure(context, errorText);
    }

    @NotNull
    public static ContextRegistered contextRegistered(ConnectionContext context)
    {
        return new ContextRegistered(context);
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
        private AnnounceShutdown(ConnectionContext context)
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
        private ContextRegistered(ConnectionContext context)
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
        private Established(ConnectionContext context)
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
        private Closed(ConnectionContext context)
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
        private Failure(ConnectionContext context, String errorText)
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
