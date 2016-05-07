/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.Data;
import com.net.multiway.background.data.DataEvents;
import com.net.multiway.background.database.Database;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DataEventsDAO implements Serializable {

    private EntityManagerFactory emf = null;

    public DataEventsDAO() {
        emf = Database.getInstance().getEntityManagerFactory();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataEvents data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Data d = data.getData();
            if (d != null) {
                d = em.getReference(d.getClass(), d.getID());
                data.setData(d);
            }

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (find(data.getID()) != null) {
                throw new Exception("Evento " + data.getID() + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataEvents data) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            DataEvents d = em.find(DataEvents.class, data.getID());
            em.getTransaction().begin();
            data = em.merge(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (find(id) == null) {
                    throw new Exception("The event with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DataEvents find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataEvents.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(DataEvents event )throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            
            DataEvents d;
            try {
                d = em.getReference(DataEvents.class, event.getID());
                d.getID();
            } catch (Exception ex) {
                throw new Exception("The parameters configuration with id " + event.getID() + " no longer exists.", ex);
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
