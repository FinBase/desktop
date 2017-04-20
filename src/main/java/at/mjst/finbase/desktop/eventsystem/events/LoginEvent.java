/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem.events;

/**
 * EventBus Event fired, if the login-button is pressed
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-12
 */
public abstract class LoginEvent extends Event
{
    /**
     * Basic constructor for login-events
     *
     * @param sender the event's sender
     */
    private LoginEvent(Object sender)
    {
        super(sender);
    }

    /**
     * Sent, if the login service successfully logged us in
     */
    public static class LoginSuccess extends LoginEvent
    {
        /**
         * @param sender the event's sender
         */
        public LoginSuccess(Object sender)
        {
            super(sender);
        }
    }

    /**
     * Sent, if logout was successful
     */
    public static class LogoffSuccess extends LoginEvent
    {
        /**
         * @param sender the event's sender
         */
        public LogoffSuccess(Object sender)
        {
            super(sender);
        }
    }

    /**
     * Sends error information in case of login failure
     */
    public static class LoginFailedEvent extends LoginEvent
    {
        private String errorText;

        /**
         * @param sender    the event's sender
         * @param errorText error information in textual form
         */
        public LoginFailedEvent(Object sender, String errorText)
        {
            super(sender);
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
