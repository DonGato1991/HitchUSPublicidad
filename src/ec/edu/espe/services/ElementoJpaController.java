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
import ec.edu.espe.models.DetalleCampania;
import ec.edu.espe.models.Elemento;
import ec.edu.espe.services.exceptions.IllegalOrphanException;
import ec.edu.espe.services.exceptions.NonexistentEntityException;
import ec.edu.espe.services.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author homer
 */
public class ElementoJpaController implements Serializable {

    public ElementoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Elemento elemento) throws PreexistingEntityException, Exception {
        if (elemento.getDetalleCampaniaCollection() == null) {
            elemento.setDetalleCampaniaCollection(new ArrayList<DetalleCampania>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DetalleCampania> attachedDetalleCampaniaCollection = new ArrayList<DetalleCampania>();
            for (DetalleCampania detalleCampaniaCollectionDetalleCampaniaToAttach : elemento.getDetalleCampaniaCollection()) {
                detalleCampaniaCollectionDetalleCampaniaToAttach = em.getReference(detalleCampaniaCollectionDetalleCampaniaToAttach.getClass(), detalleCampaniaCollectionDetalleCampaniaToAttach.getDetalleCampaniaPK());
                attachedDetalleCampaniaCollection.add(detalleCampaniaCollectionDetalleCampaniaToAttach);
            }
            elemento.setDetalleCampaniaCollection(attachedDetalleCampaniaCollection);
            em.persist(elemento);
            for (DetalleCampania detalleCampaniaCollectionDetalleCampania : elemento.getDetalleCampaniaCollection()) {
                Elemento oldElementoOfDetalleCampaniaCollectionDetalleCampania = detalleCampaniaCollectionDetalleCampania.getElemento();
                detalleCampaniaCollectionDetalleCampania.setElemento(elemento);
                detalleCampaniaCollectionDetalleCampania = em.merge(detalleCampaniaCollectionDetalleCampania);
                if (oldElementoOfDetalleCampaniaCollectionDetalleCampania != null) {
                    oldElementoOfDetalleCampaniaCollectionDetalleCampania.getDetalleCampaniaCollection().remove(detalleCampaniaCollectionDetalleCampania);
                    oldElementoOfDetalleCampaniaCollectionDetalleCampania = em.merge(oldElementoOfDetalleCampaniaCollectionDetalleCampania);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findElemento(elemento.getIdElemento()) != null) {
                throw new PreexistingEntityException("Elemento " + elemento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Elemento elemento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Elemento persistentElemento = em.find(Elemento.class, elemento.getIdElemento());
            Collection<DetalleCampania> detalleCampaniaCollectionOld = persistentElemento.getDetalleCampaniaCollection();
            Collection<DetalleCampania> detalleCampaniaCollectionNew = elemento.getDetalleCampaniaCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleCampania detalleCampaniaCollectionOldDetalleCampania : detalleCampaniaCollectionOld) {
                if (!detalleCampaniaCollectionNew.contains(detalleCampaniaCollectionOldDetalleCampania)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleCampania " + detalleCampaniaCollectionOldDetalleCampania + " since its elemento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<DetalleCampania> attachedDetalleCampaniaCollectionNew = new ArrayList<DetalleCampania>();
            for (DetalleCampania detalleCampaniaCollectionNewDetalleCampaniaToAttach : detalleCampaniaCollectionNew) {
                detalleCampaniaCollectionNewDetalleCampaniaToAttach = em.getReference(detalleCampaniaCollectionNewDetalleCampaniaToAttach.getClass(), detalleCampaniaCollectionNewDetalleCampaniaToAttach.getDetalleCampaniaPK());
                attachedDetalleCampaniaCollectionNew.add(detalleCampaniaCollectionNewDetalleCampaniaToAttach);
            }
            detalleCampaniaCollectionNew = attachedDetalleCampaniaCollectionNew;
            elemento.setDetalleCampaniaCollection(detalleCampaniaCollectionNew);
            elemento = em.merge(elemento);
            for (DetalleCampania detalleCampaniaCollectionNewDetalleCampania : detalleCampaniaCollectionNew) {
                if (!detalleCampaniaCollectionOld.contains(detalleCampaniaCollectionNewDetalleCampania)) {
                    Elemento oldElementoOfDetalleCampaniaCollectionNewDetalleCampania = detalleCampaniaCollectionNewDetalleCampania.getElemento();
                    detalleCampaniaCollectionNewDetalleCampania.setElemento(elemento);
                    detalleCampaniaCollectionNewDetalleCampania = em.merge(detalleCampaniaCollectionNewDetalleCampania);
                    if (oldElementoOfDetalleCampaniaCollectionNewDetalleCampania != null && !oldElementoOfDetalleCampaniaCollectionNewDetalleCampania.equals(elemento)) {
                        oldElementoOfDetalleCampaniaCollectionNewDetalleCampania.getDetalleCampaniaCollection().remove(detalleCampaniaCollectionNewDetalleCampania);
                        oldElementoOfDetalleCampaniaCollectionNewDetalleCampania = em.merge(oldElementoOfDetalleCampaniaCollectionNewDetalleCampania);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = elemento.getIdElemento();
                if (findElemento(id) == null) {
                    throw new NonexistentEntityException("The elemento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Elemento elemento;
            try {
                elemento = em.getReference(Elemento.class, id);
                elemento.getIdElemento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The elemento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleCampania> detalleCampaniaCollectionOrphanCheck = elemento.getDetalleCampaniaCollection();
            for (DetalleCampania detalleCampaniaCollectionOrphanCheckDetalleCampania : detalleCampaniaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Elemento (" + elemento + ") cannot be destroyed since the DetalleCampania " + detalleCampaniaCollectionOrphanCheckDetalleCampania + " in its detalleCampaniaCollection field has a non-nullable elemento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(elemento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Elemento> findElementoEntities() {
        return findElementoEntities(true, -1, -1);
    }

    public List<Elemento> findElementoEntities(int maxResults, int firstResult) {
        return findElementoEntities(false, maxResults, firstResult);
    }

    private List<Elemento> findElementoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Elemento.class));
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

    public Elemento findElemento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Elemento.class, id);
        } finally {
            em.close();
        }
    }

    public int getElementoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Elemento> rt = cq.from(Elemento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
