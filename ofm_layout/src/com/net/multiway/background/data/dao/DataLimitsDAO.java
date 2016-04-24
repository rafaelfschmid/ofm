/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.data.DataLimits;
import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.database.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phelipe
 */
public class DataLimitsDAO {
      public DataLimitsDAO() {
        emf = Database.getInstance().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataLimits data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (find(data.getID()) != null) {
                throw new Exception("Limits already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataLimits data) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            //DataDevice d = em.find(DataDevice.class, data.getID());
            em.getTransaction().begin();
            data = em.merge(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (find(id) == null) {
                    throw new Exception("The device with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DataLimits find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataLimits.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(DataLimits device) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataLimits d;
            try {
                d = em.getReference(DataLimits.class, device.getID());
                d.getID();
            } catch (Exception ex) {
                throw new Exception("The device with id " + device.getID() + " no longer exists.", ex);
            }
            em.remove(d);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
