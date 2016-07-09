/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.services;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.edu.espe.models.Empresa;
import ec.edu.espe.models.TargetEdad;
import ec.edu.espe.models.TargetEdadPK;
import ec.edu.espe.services.exceptions.NonexistentEntityException;
import ec.edu.espe.services.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author homer
 */
public class TargetEdadJpaController implements Serializable {

    public TargetEdadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TargetEdad targetEdad) throws PreexistingEntityException, Exception {
        if (targetEdad.getTargetEdadPK() == null) {
            targetEdad.setTargetEdadPK(new TargetEdadPK());
        }
        targetEdad.getTargetEdadPK().setRuc(targetEdad.getEmpresa().getRuc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa empresa = targetEdad.getEmpresa();
            if (empresa != null) {
                empresa = em.getReference(empresa.getClass(), empresa.getRuc());
                targetEdad.setEmpresa(empresa);
            }
            em.persist(targetEdad);
            if (empresa != null) {
                empresa.getTargetEdadCollection().add(targetEdad);
                empresa = em.merge(empresa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTargetEdad(targetEdad.getTargetEdadPK()) != null) {
                throw new PreexistingEntityException("TargetEdad " + targetEdad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TargetEdad targetEdad) throws NonexistentEntityException, Exception {
        targetEdad.getTargetEdadPK().setRuc(targetEdad.getEmpresa().getRuc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TargetEdad persistentTargetEdad = em.find(TargetEdad.class, targetEdad.getTargetEdadPK());
            Empresa empresaOld = persistentTargetEdad.getEmpresa();
            Empresa empresaNew = targetEdad.getEmpresa();
            if (empresaNew != null) {
                empresaNew = em.getReference(empresaNew.getClass(), empresaNew.getRuc());
                targetEdad.setEmpresa(empresaNew);
            }
            targetEdad = em.merge(targetEdad);
            if (empresaOld != null && !empresaOld.equals(empresaNew)) {
                empresaOld.getTargetEdadCollection().remove(targetEdad);
                empresaOld = em.merge(empresaOld);
            }
            if (empresaNew != null && !empresaNew.equals(empresaOld)) {
                empresaNew.getTargetEdadCollection().add(targetEdad);
                empresaNew = em.merge(empresaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TargetEdadPK id = targetEdad.getTargetEdadPK();
                if (findTargetEdad(id) == null) {
                    throw new NonexistentEntityException("The targetEdad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TargetEdadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TargetEdad targetEdad;
            try {
                targetEdad = em.getReference(TargetEdad.class, id);
                targetEdad.getTargetEdadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The targetEdad with id " + id + " no longer exists.", enfe);
            }
            Empresa empresa = targetEdad.getEmpresa();
            if (empresa != null) {
                empresa.getTargetEdadCollection().remove(targetEdad);
                empresa = em.merge(empresa);
            }
            em.remove(targetEdad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TargetEdad> findTargetEdadEntities() {
        return findTargetEdadEntities(true, -1, -1);
    }

    public List<TargetEdad> findTargetEdadEntities(int maxResults, int firstResult) {
        return findTargetEdadEntities(false, maxResults, firstResult);
    }

    private List<TargetEdad> findTargetEdadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TargetEdad.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TargetEdad findTargetEdad(TargetEdadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TargetEdad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTargetEdadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TargetEdad> rt = cq.from(TargetEdad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
