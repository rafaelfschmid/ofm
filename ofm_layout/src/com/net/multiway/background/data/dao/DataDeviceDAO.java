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

    public void create(DataDevice data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (find(data.getID()) != null) {
                throw new Exception("Device " + data.getIp() + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataDevice data) throws Exception {
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

    public DataDevice find(Long id) {
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

    public void delete(DataDevice device) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataDevice d;
            try {
                d = em.getReference(DataDevice.class, device.getID());
                d.getID();
            } catch (Exception ex) {
                throw new Exception("The device with id " + device.getID() + " no longer exists.", ex);
            }
            
            DataReferenceDAO dao = new DataReferenceDAO();
            dao.delete(d.getID());
            
            em.remove(d);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
