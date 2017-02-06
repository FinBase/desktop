/*
 * Copyright (c) 2016, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.persistence.dao;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import java.util.List;

import javax.persistence.EntityManager;

import at.mjst.finbase.desktop.model.entity.AuditLog;

/**
 * ToDo: Short class description
 * ToDo: See hibernate stragegy: http://salilstock.blogspot.co.at/2013/01/eliminating-hibernate-boiler-plate-code.html
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2016-07-26
 */
//
// Classical example code:
//
//  EntityManager em = sp.getNewEntityManager();
//  em.getTransaction().begin();
//  try {
//      em.persist(entity);
//      em.find(entity.class, id);
//  } finally {
//      em.getTransaction().commit();
//      em.close();
//  }
//
public class AuditLogDAO
{
    @Inject
    private EntityManager em; // Transactional only possible, if this one (or its provider?) is injected via Injector!

    // ToDo: http://stackoverflow.com/questions/18101488/does-guice-persist-provide-transaction-scoped-or-application-managed-entitymanag
    @Transactional
    public AuditLog find(Long id)
    {
        return getEm().find(AuditLog.class, id);
    }

    private EntityManager getEm()
    {
        return em;
    }

    @Transactional
    public void insertOrUpdate(AuditLog logEntry)
    {
        System.out.println("Is transaction active? " + getEm().getTransaction().isActive());
        getEm().persist(logEntry);
    }

    public List<AuditLog> queryAll()
    {
        return getEm().createQuery("select id from AuditLog", AuditLog.class).getResultList();
    }
}
