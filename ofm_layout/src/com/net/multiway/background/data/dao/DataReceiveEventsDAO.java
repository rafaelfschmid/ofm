/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataReceive;
import com.net.multiway.background.data.DataReceiveEvents;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DataReceiveEventsDAO implements Serializable {

    private EntityManagerFactory emf = null;

    public DataReceiveEventsDAO() {
        emf = Persistence.createEntityManagerFactory("BackgroundDB");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataReceiveEvents data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            DataReceive d = data.getDataReceive();
            if (d != null) {
                d = em.getReference(d.getClass(), d.getID());
                data.setDataReceive(d);
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

    public void edit(DataReceiveEvents data) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            DataReceiveEvents d = em.find(DataReceiveEvents.class, data.getID());
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

    public DataReceiveEvents find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataReceiveEvents.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(DataReceiveEvents event )throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            
            DataReceiveEvents d;
            try {
                d = em.getReference(DataReceiveEvents.class, event.getID());
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
