/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataReceiveValues;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DataReceiveValuesDAO {

    private EntityManagerFactory emf = null;

    public DataReceiveValuesDAO() {
        emf = Persistence.createEntityManagerFactory("BackgroundDB");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataReceiveValues data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDataReceiveValues(data.getID()) != null) {
                throw new Exception("DataReceiveValues " + data.getID() + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataReceiveValues data) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataReceiveValues d = em.find(DataReceiveValues.class, data.getID());
            //d.copy(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (findDataReceiveValues(id) == null) {
                    throw new Exception("The DataReceiveValues with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DataReceiveValues findDataReceiveValues(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataReceiveValues.class, id);
        } finally {
            em.close();
        }
    }
}
