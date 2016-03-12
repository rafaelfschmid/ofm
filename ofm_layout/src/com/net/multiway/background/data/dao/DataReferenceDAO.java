/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.DataReceive;
import com.net.multiway.background.data.DataReceiveEvents;
import com.net.multiway.background.data.DataReference;
import com.net.multiway.background.database.Database;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Phelipe
 */
public class DataReferenceDAO {

    private EntityManagerFactory emf = null;

    public DataReferenceDAO() {
        emf = Database.getInstance().getEntityManagerFactory();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private void create(DataReceive dr, DataDevice dd, DataParameters dp) throws Exception {
        DataReceiveDAO daor = new DataReceiveDAO();
        daor.create(dr);

        DataReceiveEventsDAO edao = new DataReceiveEventsDAO();
        for (DataReceiveEvents receiveEvents : dr.getEvents()) {
            edao.create(receiveEvents);
        }

        DataDeviceDAO daod = new DataDeviceDAO();
        daod.create(dd);

        DataParametersDAO daop = new DataParametersDAO();
        daop.create(dp);
    }

    public void create(DataReference data) throws Exception {
        create(data.getDataReceive(), data.getDevice(), data.getParameters());

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataReceive r = data.getDataReceive();
            if (r != null) {
                r = em.getReference(r.getClass(), r.getID());
                data.setDataReceive(r);
            }
            DataParameters dp = data.getParameters();

            if (dp != null) {
                dp = em.getReference(dp.getClass(), dp.getID());
                data.setParameters(dp);
            }
            DataDevice d = data.getDevice();

            if (d != null) {
                d = em.getReference(d.getClass(), d.getID());
                data.setDevice(d);
            }

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (find(data.getDevice().getID()) != null) {
                throw new Exception("Reference " + data.getDevice().getID() + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void delete(DataReceive dr, DataParameters dp) throws Exception {
        DataReceiveDAO daor = new DataReceiveDAO();
        daor.delete(dr);

        DataReceiveEventsDAO edao = new DataReceiveEventsDAO();
        for (DataReceiveEvents receiveEvents : dr.getEvents()) {
            edao.delete(receiveEvents);
        }

        DataParametersDAO daop = new DataParametersDAO();
        daop.delete(dp);
    }

    private void create(DataReceive dr, DataParameters dp) throws Exception {
        DataReceiveDAO daor = new DataReceiveDAO();
        daor.create(dr);

        DataReceiveEventsDAO edao = new DataReceiveEventsDAO();
        for (DataReceiveEvents receiveEvents : dr.getEvents()) {
            edao.create(receiveEvents);
        }

        DataParametersDAO daop = new DataParametersDAO();
        daop.create(dp);
    }

    public void edit(DataReference reference) throws Exception {

        EntityManager em = getEntityManager();
        DataReference d;
        try {
            em.getTransaction().begin();
            try {
                d = em.getReference(DataReference.class, reference.getDevice().getID());
                d.getDevice().getID();
            } catch (Exception ex) {
                throw new Exception("The reference with id " + reference.getDevice().getID() + " no longer exists.", ex);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        delete(d.getDataReceive(), d.getParameters());
        create(reference.getDataReceive(), reference.getParameters());
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            DataReceive r = reference.getDataReceive();
            if (r != null) {
                r = em.getReference(r.getClass(), r.getID());
                reference.setDataReceive(r);
            }
            DataParameters dp = reference.getParameters();

            if (dp != null) {
                dp = em.getReference(dp.getClass(), dp.getID());
                reference.setParameters(dp);
            }

            reference = em.merge(reference);

            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DataReference find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataReference.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) throws Exception {

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            DataReference d;
            try {
                d = em.getReference(DataReference.class, id);
                d.getDevice().getID();
            } catch (Exception ex) {
                throw new Exception("The reference with id " + id + " no longer exists.", ex);
            }
            delete(d.getDataReceive(), d.getParameters());

            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
