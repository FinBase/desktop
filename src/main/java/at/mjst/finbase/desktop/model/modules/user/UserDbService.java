/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.user;

import com.google.inject.Inject;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-26
 */
class UserDbService implements UserService
{
    @Inject
    private CurrentUserDAO currentUserDAO;

    public String getCurrentUser()
    {
        return currentUserDAO.getCurrentUser();
    }
}
