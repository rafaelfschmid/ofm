/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.Device;
import com.net.multiway.background.data.Parameters;
import com.net.multiway.background.data.Data;
import com.net.multiway.background.data.DataEvents;
import com.net.multiway.background.data.Device;
import com.net.multiway.background.database.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Phelipe
 */
public class DeviceDAO {

    private EntityManagerFactory emf = null;

    public DeviceDAO() {
        emf = Database.getInstance().getEntityManagerFactory();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private void create(Data dr, Parameters dp) throws Exception {
        DataDAO daoData = new DataDAO();
        daoData.create(dr);

        DataEventsDAO edao = new DataEventsDAO();
        for (DataEvents receiveEvents : dr.getEvents()) {
            edao.create(receiveEvents);
        }

        ParametersDAO daop = new ParametersDAO();
        daop.create(dp);
    }

    public void create(Device data) throws Exception {

        create(data.getData(), data.getParameters());

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Data r = data.getData();
            if (r != null) {
                r = em.find(r.getClass(), r.getID());
                data.setData(r);
            }
            Parameters dp = data.getParameters();

            if (dp != null) {
                dp = em.find(dp.getClass(), dp.getID());
                data.setParameters(dp);
            }
            Data d = data.getData();

            if (d != null) {
                d = em.find(d.getClass(), d.getID());
                data.setData(d);
            }

            em.persist(data);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (find(data.getData().getID()) != null) {
                throw new Exception("Reference " + data.getData().getID() + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void createPartial(Device reference) throws Exception {

        DataDAO daor = new DataDAO();
        daor.create(reference.getData());

        DataEventsDAO edao = new DataEventsDAO();
        for (DataEvents receiveEvents : reference.getData().getEvents()) {
            edao.create(receiveEvents);
        }
    }

    public void deletePartial(Long id) throws Exception {
        EntityManager em = getEntityManager();

        Device dReference;
        Data receive;
        Parameters parameters;

        Long idReceive;
        Long idParameters;
        try {
            em.getTransaction().begin();
            try {
                dReference = em.find(Device.class, id);
                idReceive = dReference.getData().getID();
                idParameters = dReference.getParameters().getID();
                dReference.setData(null);
                dReference.setParameters(null);
            } catch (Exception ex) {
                throw new Exception("The reference with id " + id + " no longer exists.", ex);
            }

            receive = em.find(Data.class, idReceive);

            List<DataEvents> events = receive.getEvents();
            for (DataEvents e : events) {
                em.remove(e);
            }
            em.remove(receive);

//            parameters = em.find(DataParameters.class, idParameters);
//            em.remove(parameters);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Device reference) throws Exception {
        deletePartial(reference.getData().getID());
        createPartial(reference);

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            Data r = reference.getData();
            if (r != null) {
                r = em.getReference(r.getClass(), r.getID());
                reference.setData(r);
            }

            Parameters dp = reference.getParameters();

            if (dp != null) {
                dp = em.merge(dp);
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

    public Device find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Device.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) throws Exception {

        EntityManager em = getEntityManager();

        Device dReference;
        Data receive;
        Parameters parameters;

        Long idReceive;
        Long idParameters;
        try {
            em.getTransaction().begin();
            try {
                dReference = em.find(Device.class, id);
                idReceive = dReference.getData().getID();
                idParameters = dReference.getParameters().getID();
                dReference.setData(null);
                dReference.setParameters(null);
            } catch (Exception ex) {
                throw new Exception("The reference with id " + id + " no longer exists.", ex);
            }

            receive = em.find(Data.class, idReceive);

            List<DataEvents> events = receive.getEvents();
            for (DataEvents e : events) {
                em.remove(e);
            }
            em.remove(receive);

            parameters = em.find(Parameters.class, idParameters);
            em.remove(parameters);

            em.remove(dReference);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
     public List<Device> getDevices() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Device e").getResultList();
        } finally {
            em.close();
        }
    }

}
