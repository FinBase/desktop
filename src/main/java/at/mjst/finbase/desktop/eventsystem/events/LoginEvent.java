/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.eventsystem.events;

import at.mjst.finbase.desktop.model.service.ModelId;

/**
 * EventBus Event fired, if the login-button is pressed
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-12
 */
public abstract class LoginEvent extends Event<ModelId>
{
    private LoginEvent(ModelId senderId)
    {
        super(senderId);
    }

    public static class LoginSuccess extends LoginEvent
    {
        public LoginSuccess(ModelId senderId)
        {
            super(senderId);
        }
    }

    public static class LogoffSuccess extends LoginEvent
    {
        public LogoffSuccess(ModelId senderId)
        {
            super(senderId);
        }
    }

    public static class LoginFailedEvent extends LoginEvent
    {
        private String errorText;

        public LoginFailedEvent(ModelId senderId, String errorText)
        {
            super(senderId);
            this.errorText = errorText;
        }

        public String getErrorText()
        {
            return errorText;
        }
    }
}
