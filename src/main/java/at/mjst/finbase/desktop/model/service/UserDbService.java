/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.service;

import com.google.inject.Inject;

import at.mjst.finbase.desktop.model.SessionProvider;
import at.mjst.finbase.desktop.model.persistence.dao.CurrentUserDAO;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-26
 */
class UserDbService implements UserService
{
    @Inject
    private SessionProvider sessionProvider;

    public String getCurrentUser()
    {
        CurrentUserDAO dao = sessionProvider.getSessionInstance(CurrentUserDAO.class);
        return dao.getCurrentUser();
    }
}
