/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

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
            if (findDataParameters(data.getID()) != null) {
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
            DataParameters d = em.find(DataParameters.class, data.getID());
            em.getTransaction().begin();
            d.copy(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (findDataParameters(id) == null) {
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

    public DataParameters findDataParameters(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataParameters.class, id);
        } finally {
            em.close();
        }
    }

    public void deleteData(DataParameters parameters) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            em.remove(em.getReference(DataParameters.class, parameters.getID()));

            em.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println("Data " + parameters.toString() + " doesn't deleted.");
            throw ex;
        } finally {
            em.close();
        }
    }
}
