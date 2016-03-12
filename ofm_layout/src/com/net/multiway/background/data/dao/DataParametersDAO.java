/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.data.DataParameters;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DataParametersDAO implements Serializable {

    private EntityManagerFactory emf = null;

    public DataParametersDAO() {
        emf = Persistence.createEntityManagerFactory("BackgroundDB");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataParameters data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (find(data.getID()) != null) {
                throw new Exception("Parameter configuration " + data.getID()+ " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataParameters data) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            //DataParameters d = em.find(DataParameters.class, data.getID());
            em.getTransaction().begin();
            data = em.merge(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (find(id) == null) {
                    throw new Exception("The parameters configuration with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DataParameters find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataParameters.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(DataParameters parameters) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            
            DataParameters d;
            try {
                d = em.getReference(DataParameters.class, parameters.getID());
                d.getID();
            } catch (Exception ex) {
                throw new Exception("The parameters configuration with id " + parameters.getID() + " no longer exists.", ex);
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
