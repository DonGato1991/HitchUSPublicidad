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
import ec.edu.espe.models.DetalleCampania;
import ec.edu.espe.models.DetalleCampaniaPK;
import ec.edu.espe.models.Elemento;
import ec.edu.espe.services.exceptions.NonexistentEntityException;
import ec.edu.espe.services.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author homer
 */
public class DetalleCampaniaJpaController implements Serializable {

    public DetalleCampaniaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleCampania detalleCampania) throws PreexistingEntityException, Exception {
        if (detalleCampania.getDetalleCampaniaPK() == null) {
            detalleCampania.setDetalleCampaniaPK(new DetalleCampaniaPK());
        }
        detalleCampania.getDetalleCampaniaPK().setSecCampania(detalleCampania.getCampania().getCampaniaPK().getSecCampania());
        detalleCampania.getDetalleCampaniaPK().setIdElemento(detalleCampania.getElemento().getIdElemento());
        detalleCampania.getDetalleCampaniaPK().setRuc(detalleCampania.getCampania().getCampaniaPK().getRuc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campania campania = detalleCampania.getCampania();
            if (campania != null) {
                campania = em.getReference(campania.getClass(), campania.getCampaniaPK());
                detalleCampania.setCampania(campania);
            }
            Elemento elemento = detalleCampania.getElemento();
            if (elemento != null) {
                elemento = em.getReference(elemento.getClass(), elemento.getIdElemento());
                detalleCampania.setElemento(elemento);
            }
            em.persist(detalleCampania);
            if (campania != null) {
                campania.getDetalleCampaniaCollection().add(detalleCampania);
                campania = em.merge(campania);
            }
            if (elemento != null) {
                elemento.getDetalleCampaniaCollection().add(detalleCampania);
                elemento = em.merge(elemento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleCampania(detalleCampania.getDetalleCampaniaPK()) != null) {
                throw new PreexistingEntityException("DetalleCampania " + detalleCampania + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleCampania detalleCampania) throws NonexistentEntityException, Exception {
        detalleCampania.getDetalleCampaniaPK().setSecCampania(detalleCampania.getCampania().getCampaniaPK().getSecCampania());
        detalleCampania.getDetalleCampaniaPK().setIdElemento(detalleCampania.getElemento().getIdElemento());
        detalleCampania.getDetalleCampaniaPK().setRuc(detalleCampania.getCampania().getCampaniaPK().getRuc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleCampania persistentDetalleCampania = em.find(DetalleCampania.class, detalleCampania.getDetalleCampaniaPK());
            Campania campaniaOld = persistentDetalleCampania.getCampania();
            Campania campaniaNew = detalleCampania.getCampania();
            Elemento elementoOld = persistentDetalleCampania.getElemento();
            Elemento elementoNew = detalleCampania.getElemento();
            if (campaniaNew != null) {
                campaniaNew = em.getReference(campaniaNew.getClass(), campaniaNew.getCampaniaPK());
                detalleCampania.setCampania(campaniaNew);
            }
            if (elementoNew != null) {
                elementoNew = em.getReference(elementoNew.getClass(), elementoNew.getIdElemento());
                detalleCampania.setElemento(elementoNew);
            }
            detalleCampania = em.merge(detalleCampania);
            if (campaniaOld != null && !campaniaOld.equals(campaniaNew)) {
                campaniaOld.getDetalleCampaniaCollection().remove(detalleCampania);
                campaniaOld = em.merge(campaniaOld);
            }
            if (campaniaNew != null && !campaniaNew.equals(campaniaOld)) {
                campaniaNew.getDetalleCampaniaCollection().add(detalleCampania);
                campaniaNew = em.merge(campaniaNew);
            }
            if (elementoOld != null && !elementoOld.equals(elementoNew)) {
                elementoOld.getDetalleCampaniaCollection().remove(detalleCampania);
                elementoOld = em.merge(elementoOld);
            }
            if (elementoNew != null && !elementoNew.equals(elementoOld)) {
                elementoNew.getDetalleCampaniaCollection().add(detalleCampania);
                elementoNew = em.merge(elementoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleCampaniaPK id = detalleCampania.getDetalleCampaniaPK();
                if (findDetalleCampania(id) == null) {
                    throw new NonexistentEntityException("The detalleCampania with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleCampaniaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleCampania detalleCampania;
            try {
                detalleCampania = em.getReference(DetalleCampania.class, id);
                detalleCampania.getDetalleCampaniaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleCampania with id " + id + " no longer exists.", enfe);
            }
            Campania campania = detalleCampania.getCampania();
            if (campania != null) {
                campania.getDetalleCampaniaCollection().remove(detalleCampania);
                campania = em.merge(campania);
            }
            Elemento elemento = detalleCampania.getElemento();
            if (elemento != null) {
                elemento.getDetalleCampaniaCollection().remove(detalleCampania);
                elemento = em.merge(elemento);
            }
            em.remove(detalleCampania);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleCampania> findDetalleCampaniaEntities() {
        return findDetalleCampaniaEntities(true, -1, -1);
    }

    public List<DetalleCampania> findDetalleCampaniaEntities(int maxResults, int firstResult) {
        return findDetalleCampaniaEntities(false, maxResults, firstResult);
    }

    private List<DetalleCampania> findDetalleCampaniaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleCampania.class));
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

    public DetalleCampania findDetalleCampania(DetalleCampaniaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleCampania.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleCampaniaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleCampania> rt = cq.from(DetalleCampania.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
