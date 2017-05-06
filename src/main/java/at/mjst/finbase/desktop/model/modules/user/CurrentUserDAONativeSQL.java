/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.modules.user;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import javax.persistence.EntityManager;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-01-13
 */
class CurrentUserDAONativeSQL implements CurrentUserDAO
{
    @Inject
    private Provider<EntityManager> emp;

    @Override
    @Transactional
    public String getCurrentUser()
    {
        // WARNING! Native SQL - also executed on other db-systems!!
        return (String) emp.get().createNativeQuery("select USER()").getSingleResult();
    }
}
