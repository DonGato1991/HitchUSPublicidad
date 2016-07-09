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
import ec.edu.espe.models.FacturaEmpresa;
import ec.edu.espe.services.exceptions.NonexistentEntityException;
import ec.edu.espe.services.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author homer
 */
public class FacturaEmpresaJpaController implements Serializable {

    public FacturaEmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacturaEmpresa facturaEmpresa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa ruc = facturaEmpresa.getRuc();
            if (ruc != null) {
                ruc = em.getReference(ruc.getClass(), ruc.getRuc());
                facturaEmpresa.setRuc(ruc);
            }
            em.persist(facturaEmpresa);
            if (ruc != null) {
                ruc.getFacturaEmpresaCollection().add(facturaEmpresa);
                ruc = em.merge(ruc);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacturaEmpresa(facturaEmpresa.getIdFactura()) != null) {
                throw new PreexistingEntityException("FacturaEmpresa " + facturaEmpresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacturaEmpresa facturaEmpresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacturaEmpresa persistentFacturaEmpresa = em.find(FacturaEmpresa.class, facturaEmpresa.getIdFactura());
            Empresa rucOld = persistentFacturaEmpresa.getRuc();
            Empresa rucNew = facturaEmpresa.getRuc();
            if (rucNew != null) {
                rucNew = em.getReference(rucNew.getClass(), rucNew.getRuc());
                facturaEmpresa.setRuc(rucNew);
            }
            facturaEmpresa = em.merge(facturaEmpresa);
            if (rucOld != null && !rucOld.equals(rucNew)) {
                rucOld.getFacturaEmpresaCollection().remove(facturaEmpresa);
                rucOld = em.merge(rucOld);
            }
            if (rucNew != null && !rucNew.equals(rucOld)) {
                rucNew.getFacturaEmpresaCollection().add(facturaEmpresa);
                rucNew = em.merge(rucNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturaEmpresa.getIdFactura();
                if (findFacturaEmpresa(id) == null) {
                    throw new NonexistentEntityException("The facturaEmpresa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacturaEmpresa facturaEmpresa;
            try {
                facturaEmpresa = em.getReference(FacturaEmpresa.class, id);
                facturaEmpresa.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturaEmpresa with id " + id + " no longer exists.", enfe);
            }
            Empresa ruc = facturaEmpresa.getRuc();
            if (ruc != null) {
                ruc.getFacturaEmpresaCollection().remove(facturaEmpresa);
                ruc = em.merge(ruc);
            }
            em.remove(facturaEmpresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacturaEmpresa> findFacturaEmpresaEntities() {
        return findFacturaEmpresaEntities(true, -1, -1);
    }

    public List<FacturaEmpresa> findFacturaEmpresaEntities(int maxResults, int firstResult) {
        return findFacturaEmpresaEntities(false, maxResults, firstResult);
    }

    private List<FacturaEmpresa> findFacturaEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturaEmpresa.class));
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

    public FacturaEmpresa findFacturaEmpresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturaEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacturaEmpresa> rt = cq.from(FacturaEmpresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
