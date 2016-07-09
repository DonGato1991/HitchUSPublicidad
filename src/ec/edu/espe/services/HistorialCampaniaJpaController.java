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
import ec.edu.espe.models.Campania;
import ec.edu.espe.models.HistorialCampania;
import ec.edu.espe.services.exceptions.NonexistentEntityException;
import ec.edu.espe.services.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author homer
 */
public class HistorialCampaniaJpaController implements Serializable {

    public HistorialCampaniaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialCampania historialCampania) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campania campania = historialCampania.getCampania();
            if (campania != null) {
                campania = em.getReference(campania.getClass(), campania.getCampaniaPK());
                historialCampania.setCampania(campania);
            }
            em.persist(historialCampania);
            if (campania != null) {
                campania.getHistorialCampaniaCollection().add(historialCampania);
                campania = em.merge(campania);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialCampania(historialCampania.getIdHistorialCampania()) != null) {
                throw new PreexistingEntityException("HistorialCampania " + historialCampania + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialCampania historialCampania) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistorialCampania persistentHistorialCampania = em.find(HistorialCampania.class, historialCampania.getIdHistorialCampania());
            Campania campaniaOld = persistentHistorialCampania.getCampania();
            Campania campaniaNew = historialCampania.getCampania();
            if (campaniaNew != null) {
                campaniaNew = em.getReference(campaniaNew.getClass(), campaniaNew.getCampaniaPK());
                historialCampania.setCampania(campaniaNew);
            }
            historialCampania = em.merge(historialCampania);
            if (campaniaOld != null && !campaniaOld.equals(campaniaNew)) {
                campaniaOld.getHistorialCampaniaCollection().remove(historialCampania);
                campaniaOld = em.merge(campaniaOld);
            }
            if (campaniaNew != null && !campaniaNew.equals(campaniaOld)) {
                campaniaNew.getHistorialCampaniaCollection().add(historialCampania);
                campaniaNew = em.merge(campaniaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialCampania.getIdHistorialCampania();
                if (findHistorialCampania(id) == null) {
                    throw new NonexistentEntityException("The historialCampania with id " + id + " no longer exists.");
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
            HistorialCampania historialCampania;
            try {
                historialCampania = em.getReference(HistorialCampania.class, id);
                historialCampania.getIdHistorialCampania();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialCampania with id " + id + " no longer exists.", enfe);
            }
            Campania campania = historialCampania.getCampania();
            if (campania != null) {
                campania.getHistorialCampaniaCollection().remove(historialCampania);
                campania = em.merge(campania);
            }
            em.remove(historialCampania);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialCampania> findHistorialCampaniaEntities() {
        return findHistorialCampaniaEntities(true, -1, -1);
    }

    public List<HistorialCampania> findHistorialCampaniaEntities(int maxResults, int firstResult) {
        return findHistorialCampaniaEntities(false, maxResults, firstResult);
    }

    private List<HistorialCampania> findHistorialCampaniaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialCampania.class));
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

    public HistorialCampania findHistorialCampania(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialCampania.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialCampaniaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialCampania> rt = cq.from(HistorialCampania.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
