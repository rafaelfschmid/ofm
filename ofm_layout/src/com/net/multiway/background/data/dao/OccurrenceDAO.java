/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.Device;
import com.net.multiway.background.data.Limits;
import com.net.multiway.background.data.Occurrence;
import com.net.multiway.background.database.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phelipe
 */
public class OccurrenceDAO {
     public OccurrenceDAO() {
        emf = Database.getInstance().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Occurrence data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
//            if (find(data.getID()) != null) {
//                throw new Exception("Limits already exists.", ex);
//            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Occurrence data) throws Exception {
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

    public Occurrence find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Occurrence.class, id);
        } finally {
            em.close();
        }
    }
    
     public List<Occurrence> getOccurrences() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT e FROM DataOccurrence e").getResultList();
        } finally {
            em.close();
        }
    }

    public void delete(Occurrence device) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Occurrence d;
            try {
                d = em.getReference(Occurrence.class, device.getID());
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
