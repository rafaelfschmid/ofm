///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.net.multiway.background.data.dao;
//
//import com.net.multiway.background.data.DataReceiveValues;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
///**
// *
// * @author Phelipe
// */
//public class DataReceiveValuesDAO {
//
//    private EntityManagerFactory emf = null;
//
//    public DataReceiveValuesDAO() {
//        emf = Persistence.createEntityManagerFactory("BackgroundDB");
//    }
//
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public void create(DataReceiveValues data) throws Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//
//            em.persist(data);
//
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findDataReceiveValues(data.getID()) != null) {
//                throw new Exception("DataReceiveValues " + data.getID() + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(DataReceiveValues data) throws Exception {
//        EntityManager em = null;
//
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
////            DataReceiveValues d = em.find(DataReceiveValues.class, data.getID());
//            data = em.merge(data);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Long id = data.getID();
//                if (findDataReceiveValues(id) == null) {
//                    throw new Exception("The DataReceiveValues with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public DataReceiveValues findDataReceiveValues(Long id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(DataReceiveValues.class, id);
//        } finally {
//            em.close();
//        }
//    }
//    
//    public void deleteData(DataReceiveValues receive) throws Exception {
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//
//            DataReceiveValues d;
//            try {
//                d = em.getReference(DataReceiveValues.class, receive.getID());
//                d.getID();
//            } catch (Exception ex) {
//                throw new Exception("The parameters configuration with id " + receive.getID() + " no longer exists.", ex);
//            }
//            
////            List<int> events = receive.getData();
////            for (DataReceiveValues event : events) {
////                event.setDataReceive(null);
////                event = em.merge(event);
////            }
//            
//            em.remove(d);
//
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//}
