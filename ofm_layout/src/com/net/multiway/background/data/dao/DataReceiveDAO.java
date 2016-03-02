/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataReceive;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DataReceiveDAO {

    private EntityManagerFactory emf = null;

    public DataReceiveDAO() {
        emf = Persistence.createEntityManagerFactory("BackgroundDB");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataReceive data) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findData0x9000(data.getID()) != null) {
                System.out.println("Data " + data.toString() + " already exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataReceive data) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            DataReceive d = em.find(DataReceive.class, data.getID());
            em.getTransaction().begin();
            //d.copy(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (findData0x9000(id) == null) {
                    System.out.println("The data with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DataReceive findData0x9000(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataReceive.class, id);
        } finally {
            em.close();
        }
    }

}
