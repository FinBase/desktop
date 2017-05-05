/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.persistence.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    private Provider<EntityManager> entityManagerProvider;
    // Transactional only possible, if this one (or its provider?) is injected via Injector!

    @Transactional
    public AuditLog find(Long id)
    {
        return getEm().find(AuditLog.class, id);
    }

    /**
     * According to <a href="http://stackoverflow.com/a/21679881/700165">this stackOverflow article</a>, it is
     * strongly recommended, to only inject a provider and get the em-instance through the provider! Also, every CRUD
     * method should be marked with {@link Transactional}
     *
     * @return a new instance of {@link EntityManager} using a provider
     */
    public EntityManager getEm()
    {
        return entityManagerProvider.get();
    }

    /**
     * Hint: The {@link Transactional} annotation invokes a TxnInterceptor, which starts a {@link
     * com.google.inject.persist.UnitOfWork} (and the transaction). The {@link EntityManager}-{@link Provider} provides
     * always the same instance inside these blocks. Note, that the {@link EntityManager} will be closed outside the
     * last unit of work!
     *
     * @return a list of {@link AuditLog}s
     */
    @Transactional
    public List<AuditLog> queryAll()
    {
        try {
            EntityManager em = getEm();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<AuditLog> criteriaQuery = criteriaBuilder.createQuery(AuditLog.class);
            Root<AuditLog> root = criteriaQuery.from(AuditLog.class);
            criteriaQuery.select(root);
            TypedQuery<AuditLog> q2 = em.createQuery(criteriaQuery);
            List<AuditLog> auditLogs = q2.getResultList();
            System.out.println(auditLogs.size() + " Logs(s) found!");
            return auditLogs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void merge(AuditLog log, LocalDateTime now)
    {
        EntityManager em = getEm();
        System.out.println("Is transaction active? " + em.getTransaction().isActive());
        AuditLog log2 = em.merge(log);
        log2.setTimestampOff(LocalDateTime.now()); // todo: on and off are changed on update?!
        insertOrUpdate(log2);
        // todo: much better: http://stackoverflow.com/a/41203093/700165
        // todo: implement, so that the same instance of DAO also can use same instance of em?!
    }

    @Transactional
    public void insertOrUpdate(AuditLog logEntry)
    {
        EntityManager em = getEm();
        System.out.println("Is transaction active? " + em.getTransaction().isActive());
        em.persist(logEntry);
    }
}
