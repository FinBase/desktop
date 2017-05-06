/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.user;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-13
 */
class CurrentUserDAOView implements CurrentUserDAO
{
    @Override
    public String getCurrentUser()
    {
        // ToDo: Generate a VIEW for this one (use another class -> UserDbService)!
        //        CREATE or REPLACE VIEW USER_INFO AS
        //        (SELECT USER(), User, Host FROM mysql.user usr WHERE USER() like CONCAT(User, '@', Host));
        return null;
    }
}
