/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.services;

import ec.edu.espe.models.Campania;
import ec.edu.espe.models.CampaniaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.edu.espe.models.Empresa;
import ec.edu.espe.models.HistorialCampania;
import java.util.ArrayList;
import java.util.Collection;
import ec.edu.espe.models.DetalleCampania;
import ec.edu.espe.services.exceptions.IllegalOrphanException;
import ec.edu.espe.services.exceptions.NonexistentEntityException;
import ec.edu.espe.services.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author homer
 */
public class CampaniaJpaController implements Serializable {

    public CampaniaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CampaniaJpaController() {
    }
    
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("PublicidadHitchUsPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Campania campania) throws PreexistingEntityException, Exception {
        if (campania.getCampaniaPK() == null) {
            campania.setCampaniaPK(new CampaniaPK());
        }
        if (campania.getHistorialCampaniaCollection() == null) {
            campania.setHistorialCampaniaCollection(new ArrayList<HistorialCampania>());
        }
        if (campania.getDetalleCampaniaCollection() == null) {
            campania.setDetalleCampaniaCollection(new ArrayList<DetalleCampania>());
        }
        campania.getCampaniaPK().setRuc(campania.getEmpresa().getRuc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa empresa = campania.getEmpresa();
            if (empresa != null) {
                empresa = em.getReference(empresa.getClass(), empresa.getRuc());
                campania.setEmpresa(empresa);
            }
            Collection<HistorialCampania> attachedHistorialCampaniaCollection = new ArrayList<HistorialCampania>();
            for (HistorialCampania historialCampaniaCollectionHistorialCampaniaToAttach : campania.getHistorialCampaniaCollection()) {
                historialCampaniaCollectionHistorialCampaniaToAttach = em.getReference(historialCampaniaCollectionHistorialCampaniaToAttach.getClass(), historialCampaniaCollectionHistorialCampaniaToAttach.getIdHistorialCampania());
                attachedHistorialCampaniaCollection.add(historialCampaniaCollectionHistorialCampaniaToAttach);
            }
            campania.setHistorialCampaniaCollection(attachedHistorialCampaniaCollection);
            Collection<DetalleCampania> attachedDetalleCampaniaCollection = new ArrayList<DetalleCampania>();
            for (DetalleCampania detalleCampaniaCollectionDetalleCampaniaToAttach : campania.getDetalleCampaniaCollection()) {
                detalleCampaniaCollectionDetalleCampaniaToAttach = em.getReference(detalleCampaniaCollectionDetalleCampaniaToAttach.getClass(), detalleCampaniaCollectionDetalleCampaniaToAttach.getDetalleCampaniaPK());
                attachedDetalleCampaniaCollection.add(detalleCampaniaCollectionDetalleCampaniaToAttach);
            }
            campania.setDetalleCampaniaCollection(attachedDetalleCampaniaCollection);
            em.persist(campania);
            if (empresa != null) {
                empresa.getCampaniaCollection().add(campania);
                empresa = em.merge(empresa);
            }
            for (HistorialCampania historialCampaniaCollectionHistorialCampania : campania.getHistorialCampaniaCollection()) {
                Campania oldCampaniaOfHistorialCampaniaCollectionHistorialCampania = historialCampaniaCollectionHistorialCampania.getCampania();
                historialCampaniaCollectionHistorialCampania.setCampania(campania);
                historialCampaniaCollectionHistorialCampania = em.merge(historialCampaniaCollectionHistorialCampania);
                if (oldCampaniaOfHistorialCampaniaCollectionHistorialCampania != null) {
                    oldCampaniaOfHistorialCampaniaCollectionHistorialCampania.getHistorialCampaniaCollection().remove(historialCampaniaCollectionHistorialCampania);
                    oldCampaniaOfHistorialCampaniaCollectionHistorialCampania = em.merge(oldCampaniaOfHistorialCampaniaCollectionHistorialCampania);
                }
            }
            for (DetalleCampania detalleCampaniaCollectionDetalleCampania : campania.getDetalleCampaniaCollection()) {
                Campania oldCampaniaOfDetalleCampaniaCollectionDetalleCampania = detalleCampaniaCollectionDetalleCampania.getCampania();
                detalleCampaniaCollectionDetalleCampania.setCampania(campania);
                detalleCampaniaCollectionDetalleCampania = em.merge(detalleCampaniaCollectionDetalleCampania);
                if (oldCampaniaOfDetalleCampaniaCollectionDetalleCampania != null) {
                    oldCampaniaOfDetalleCampaniaCollectionDetalleCampania.getDetalleCampaniaCollection().remove(detalleCampaniaCollectionDetalleCampania);
                    oldCampaniaOfDetalleCampaniaCollectionDetalleCampania = em.merge(oldCampaniaOfDetalleCampaniaCollectionDetalleCampania);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCampania(campania.getCampaniaPK()) != null) {
                throw new PreexistingEntityException("Campania " + campania + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Campania campania) throws IllegalOrphanException, NonexistentEntityException, Exception {
        campania.getCampaniaPK().setRuc(campania.getEmpresa().getRuc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campania persistentCampania = em.find(Campania.class, campania.getCampaniaPK());
            Empresa empresaOld = persistentCampania.getEmpresa();
            Empresa empresaNew = campania.getEmpresa();
            Collection<HistorialCampania> historialCampaniaCollectionOld = persistentCampania.getHistorialCampaniaCollection();
            Collection<HistorialCampania> historialCampaniaCollectionNew = campania.getHistorialCampaniaCollection();
            Collection<DetalleCampania> detalleCampaniaCollectionOld = persistentCampania.getDetalleCampaniaCollection();
            Collection<DetalleCampania> detalleCampaniaCollectionNew = campania.getDetalleCampaniaCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleCampania detalleCampaniaCollectionOldDetalleCampania : detalleCampaniaCollectionOld) {
                if (!detalleCampaniaCollectionNew.contains(detalleCampaniaCollectionOldDetalleCampania)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleCampania " + detalleCampaniaCollectionOldDetalleCampania + " since its campania field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empresaNew != null) {
                empresaNew = em.getReference(empresaNew.getClass(), empresaNew.getRuc());
                campania.setEmpresa(empresaNew);
            }
            Collection<HistorialCampania> attachedHistorialCampaniaCollectionNew = new ArrayList<HistorialCampania>();
            for (HistorialCampania historialCampaniaCollectionNewHistorialCampaniaToAttach : historialCampaniaCollectionNew) {
                historialCampaniaCollectionNewHistorialCampaniaToAttach = em.getReference(historialCampaniaCollectionNewHistorialCampaniaToAttach.getClass(), historialCampaniaCollectionNewHistorialCampaniaToAttach.getIdHistorialCampania());
                attachedHistorialCampaniaCollectionNew.add(historialCampaniaCollectionNewHistorialCampaniaToAttach);
            }
            historialCampaniaCollectionNew = attachedHistorialCampaniaCollectionNew;
            campania.setHistorialCampaniaCollection(historialCampaniaCollectionNew);
            Collection<DetalleCampania> attachedDetalleCampaniaCollectionNew = new ArrayList<DetalleCampania>();
            for (DetalleCampania detalleCampaniaCollectionNewDetalleCampaniaToAttach : detalleCampaniaCollectionNew) {
                detalleCampaniaCollectionNewDetalleCampaniaToAttach = em.getReference(detalleCampaniaCollectionNewDetalleCampaniaToAttach.getClass(), detalleCampaniaCollectionNewDetalleCampaniaToAttach.getDetalleCampaniaPK());
                attachedDetalleCampaniaCollectionNew.add(detalleCampaniaCollectionNewDetalleCampaniaToAttach);
            }
            detalleCampaniaCollectionNew = attachedDetalleCampaniaCollectionNew;
            campania.setDetalleCampaniaCollection(detalleCampaniaCollectionNew);
            campania = em.merge(campania);
            if (empresaOld != null && !empresaOld.equals(empresaNew)) {
                empresaOld.getCampaniaCollection().remove(campania);
                empresaOld = em.merge(empresaOld);
            }
            if (empresaNew != null && !empresaNew.equals(empresaOld)) {
                empresaNew.getCampaniaCollection().add(campania);
                empresaNew = em.merge(empresaNew);
            }
            for (HistorialCampania historialCampaniaCollectionOldHistorialCampania : historialCampaniaCollectionOld) {
                if (!historialCampaniaCollectionNew.contains(historialCampaniaCollectionOldHistorialCampania)) {
                    historialCampaniaCollectionOldHistorialCampania.setCampania(null);
                    historialCampaniaCollectionOldHistorialCampania = em.merge(historialCampaniaCollectionOldHistorialCampania);
                }
            }
            for (HistorialCampania historialCampaniaCollectionNewHistorialCampania : historialCampaniaCollectionNew) {
                if (!historialCampaniaCollectionOld.contains(historialCampaniaCollectionNewHistorialCampania)) {
                    Campania oldCampaniaOfHistorialCampaniaCollectionNewHistorialCampania = historialCampaniaCollectionNewHistorialCampania.getCampania();
                    historialCampaniaCollectionNewHistorialCampania.setCampania(campania);
                    historialCampaniaCollectionNewHistorialCampania = em.merge(historialCampaniaCollectionNewHistorialCampania);
                    if (oldCampaniaOfHistorialCampaniaCollectionNewHistorialCampania != null && !oldCampaniaOfHistorialCampaniaCollectionNewHistorialCampania.equals(campania)) {
                        oldCampaniaOfHistorialCampaniaCollectionNewHistorialCampania.getHistorialCampaniaCollection().remove(historialCampaniaCollectionNewHistorialCampania);
                        oldCampaniaOfHistorialCampaniaCollectionNewHistorialCampania = em.merge(oldCampaniaOfHistorialCampaniaCollectionNewHistorialCampania);
                    }
                }
            }
            for (DetalleCampania detalleCampaniaCollectionNewDetalleCampania : detalleCampaniaCollectionNew) {
                if (!detalleCampaniaCollectionOld.contains(detalleCampaniaCollectionNewDetalleCampania)) {
                    Campania oldCampaniaOfDetalleCampaniaCollectionNewDetalleCampania = detalleCampaniaCollectionNewDetalleCampania.getCampania();
                    detalleCampaniaCollectionNewDetalleCampania.setCampania(campania);
                    detalleCampaniaCollectionNewDetalleCampania = em.merge(detalleCampaniaCollectionNewDetalleCampania);
                    if (oldCampaniaOfDetalleCampaniaCollectionNewDetalleCampania != null && !oldCampaniaOfDetalleCampaniaCollectionNewDetalleCampania.equals(campania)) {
                        oldCampaniaOfDetalleCampaniaCollectionNewDetalleCampania.getDetalleCampaniaCollection().remove(detalleCampaniaCollectionNewDetalleCampania);
                        oldCampaniaOfDetalleCampaniaCollectionNewDetalleCampania = em.merge(oldCampaniaOfDetalleCampaniaCollectionNewDetalleCampania);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CampaniaPK id = campania.getCampaniaPK();
                if (findCampania(id) == null) {
                    throw new NonexistentEntityException("The campania with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CampaniaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campania campania;
            try {
                campania = em.getReference(Campania.class, id);
                campania.getCampaniaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The campania with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleCampania> detalleCampaniaCollectionOrphanCheck = campania.getDetalleCampaniaCollection();
            for (DetalleCampania detalleCampaniaCollectionOrphanCheckDetalleCampania : detalleCampaniaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Campania (" + campania + ") cannot be destroyed since the DetalleCampania " + detalleCampaniaCollectionOrphanCheckDetalleCampania + " in its detalleCampaniaCollection field has a non-nullable campania field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresa empresa = campania.getEmpresa();
            if (empresa != null) {
                empresa.getCampaniaCollection().remove(campania);
                empresa = em.merge(empresa);
            }
            Collection<HistorialCampania> historialCampaniaCollection = campania.getHistorialCampaniaCollection();
            for (HistorialCampania historialCampaniaCollectionHistorialCampania : historialCampaniaCollection) {
                historialCampaniaCollectionHistorialCampania.setCampania(null);
                historialCampaniaCollectionHistorialCampania = em.merge(historialCampaniaCollectionHistorialCampania);
            }
            em.remove(campania);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Campania> findCampaniaEntities() {
        return findCampaniaEntities(true, -1, -1);
    }

    public List<Campania> findCampaniaEntities(int maxResults, int firstResult) {
        return findCampaniaEntities(false, maxResults, firstResult);
    }

    private List<Campania> findCampaniaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Campania.class));
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

    public Campania findCampania(CampaniaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Campania.class, id);
        } finally {
            em.close();
        }
    }

    public int getCampaniaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Campania> rt = cq.from(Campania.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
