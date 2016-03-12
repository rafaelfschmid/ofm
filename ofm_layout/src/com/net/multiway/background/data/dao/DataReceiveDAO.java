/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.DataReceive;
import com.net.multiway.background.data.DataReceiveEvents;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DataReceiveDAO implements Serializable {

    private EntityManagerFactory emf = null;

    public DataReceiveDAO() {
        emf = Persistence.createEntityManagerFactory("BackgroundDB");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataReceive data) throws Exception {
        if (data.getEvents() == null) {
            data.setEvents(new ArrayList<DataReceiveEvents>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (find(data.getID()) != null) {
                throw new Exception("DataReceive " + data.getID() + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataReceive data) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
//            DataReceive dPersistence = em.find(DataReceive.class, data.getID());
            em.getTransaction().begin();
            data = em.merge(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (find(id) == null) {
                    throw new Exception("The DataReceive with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DataReceive find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataReceive.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(DataReceive receive) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            DataReceive d;
            try {
                d = em.getReference(DataReceive.class, receive.getID());
                d.getID();
            } catch (Exception ex) {
                throw new Exception("The parameters configuration with id " + receive.getID() + " no longer exists.", ex);
            }
            
            List<DataReceiveEvents> events = receive.getEvents();
            for (DataReceiveEvents event : events) {
                event.setDataReceive(null);
                event = em.merge(event);
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
