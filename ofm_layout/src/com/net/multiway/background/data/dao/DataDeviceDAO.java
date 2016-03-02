/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataDevice;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DataDeviceDAO {

    private EntityManagerFactory emf = null;

    public DataDeviceDAO() {
        emf = Persistence.createEntityManagerFactory("BackgroundDB");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataDevice data) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findData(data.getID()) != null) {
                System.out.println("Data " + data.toString() + " already exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataDevice data) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            DataDevice d = em.find(DataDevice.class, data.getID());
            em.getTransaction().begin();
            d.copy(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = data.getID();
                if (findData(id) == null) {
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

    public DataDevice findData(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataDevice.class, id);
        } finally {
            em.close();
        }
    }

    public List<DataDevice> getDevices() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT e FROM DataDevice e").getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteData(DataDevice device) {

        if (device.getID() != null) {
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();

                em.remove(em.getReference(DataDevice.class, device.getID()));

                em.getTransaction().commit();

            } catch (Exception ex) {
                System.out.println("Data " + device.toString() + " doesn't deleted.");
                throw ex;
            } finally {
                em.close();
            }
        }
        else
        {
            System.out.println("Data " + device.toString() + " doesn't found.");
        }
    }

}
