/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data.dao;

import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.DataDevice;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phelipe
 */
public class DeviceDAO {
	private EntityManagerFactory emf = null;

	public DeviceDAO() {
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
			if (findData0x1002(data.getID()) != null) {
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
				if (findData0x1002(id) == null) {
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

	public DataDevice findData0x1002(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(DataDevice.class, id);
		} finally {
			em.close();
		}
	}
	
}
