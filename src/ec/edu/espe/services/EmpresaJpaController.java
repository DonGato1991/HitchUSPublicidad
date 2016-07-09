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
import ec.edu.espe.models.TargetEdad;
import java.util.ArrayList;
import java.util.Collection;
import ec.edu.espe.models.Campania;
import ec.edu.espe.models.Empresa;
import ec.edu.espe.models.FacturaEmpresa;
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
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresa empresa) throws PreexistingEntityException, Exception {
        if (empresa.getTargetEdadCollection() == null) {
            empresa.setTargetEdadCollection(new ArrayList<TargetEdad>());
        }
        if (empresa.getCampaniaCollection() == null) {
            empresa.setCampaniaCollection(new ArrayList<Campania>());
        }
        if (empresa.getFacturaEmpresaCollection() == null) {
            empresa.setFacturaEmpresaCollection(new ArrayList<FacturaEmpresa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TargetEdad> attachedTargetEdadCollection = new ArrayList<TargetEdad>();
            for (TargetEdad targetEdadCollectionTargetEdadToAttach : empresa.getTargetEdadCollection()) {
                targetEdadCollectionTargetEdadToAttach = em.getReference(targetEdadCollectionTargetEdadToAttach.getClass(), targetEdadCollectionTargetEdadToAttach.getTargetEdadPK());
                attachedTargetEdadCollection.add(targetEdadCollectionTargetEdadToAttach);
            }
            empresa.setTargetEdadCollection(attachedTargetEdadCollection);
            Collection<Campania> attachedCampaniaCollection = new ArrayList<Campania>();
            for (Campania campaniaCollectionCampaniaToAttach : empresa.getCampaniaCollection()) {
                campaniaCollectionCampaniaToAttach = em.getReference(campaniaCollectionCampaniaToAttach.getClass(), campaniaCollectionCampaniaToAttach.getCampaniaPK());
                attachedCampaniaCollection.add(campaniaCollectionCampaniaToAttach);
            }
            empresa.setCampaniaCollection(attachedCampaniaCollection);
            Collection<FacturaEmpresa> attachedFacturaEmpresaCollection = new ArrayList<FacturaEmpresa>();
            for (FacturaEmpresa facturaEmpresaCollectionFacturaEmpresaToAttach : empresa.getFacturaEmpresaCollection()) {
                facturaEmpresaCollectionFacturaEmpresaToAttach = em.getReference(facturaEmpresaCollectionFacturaEmpresaToAttach.getClass(), facturaEmpresaCollectionFacturaEmpresaToAttach.getIdFactura());
                attachedFacturaEmpresaCollection.add(facturaEmpresaCollectionFacturaEmpresaToAttach);
            }
            empresa.setFacturaEmpresaCollection(attachedFacturaEmpresaCollection);
            em.persist(empresa);
            for (TargetEdad targetEdadCollectionTargetEdad : empresa.getTargetEdadCollection()) {
                Empresa oldEmpresaOfTargetEdadCollectionTargetEdad = targetEdadCollectionTargetEdad.getEmpresa();
                targetEdadCollectionTargetEdad.setEmpresa(empresa);
                targetEdadCollectionTargetEdad = em.merge(targetEdadCollectionTargetEdad);
                if (oldEmpresaOfTargetEdadCollectionTargetEdad != null) {
                    oldEmpresaOfTargetEdadCollectionTargetEdad.getTargetEdadCollection().remove(targetEdadCollectionTargetEdad);
                    oldEmpresaOfTargetEdadCollectionTargetEdad = em.merge(oldEmpresaOfTargetEdadCollectionTargetEdad);
                }
            }
            for (Campania campaniaCollectionCampania : empresa.getCampaniaCollection()) {
                Empresa oldEmpresaOfCampaniaCollectionCampania = campaniaCollectionCampania.getEmpresa();
                campaniaCollectionCampania.setEmpresa(empresa);
                campaniaCollectionCampania = em.merge(campaniaCollectionCampania);
                if (oldEmpresaOfCampaniaCollectionCampania != null) {
                    oldEmpresaOfCampaniaCollectionCampania.getCampaniaCollection().remove(campaniaCollectionCampania);
                    oldEmpresaOfCampaniaCollectionCampania = em.merge(oldEmpresaOfCampaniaCollectionCampania);
                }
            }
            for (FacturaEmpresa facturaEmpresaCollectionFacturaEmpresa : empresa.getFacturaEmpresaCollection()) {
                Empresa oldRucOfFacturaEmpresaCollectionFacturaEmpresa = facturaEmpresaCollectionFacturaEmpresa.getRuc();
                facturaEmpresaCollectionFacturaEmpresa.setRuc(empresa);
                facturaEmpresaCollectionFacturaEmpresa = em.merge(facturaEmpresaCollectionFacturaEmpresa);
                if (oldRucOfFacturaEmpresaCollectionFacturaEmpresa != null) {
                    oldRucOfFacturaEmpresaCollectionFacturaEmpresa.getFacturaEmpresaCollection().remove(facturaEmpresaCollectionFacturaEmpresa);
                    oldRucOfFacturaEmpresaCollectionFacturaEmpresa = em.merge(oldRucOfFacturaEmpresaCollectionFacturaEmpresa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpresa(empresa.getRuc()) != null) {
                throw new PreexistingEntityException("Empresa " + empresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresa empresa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getRuc());
            Collection<TargetEdad> targetEdadCollectionOld = persistentEmpresa.getTargetEdadCollection();
            Collection<TargetEdad> targetEdadCollectionNew = empresa.getTargetEdadCollection();
            Collection<Campania> campaniaCollectionOld = persistentEmpresa.getCampaniaCollection();
            Collection<Campania> campaniaCollectionNew = empresa.getCampaniaCollection();
            Collection<FacturaEmpresa> facturaEmpresaCollectionOld = persistentEmpresa.getFacturaEmpresaCollection();
            Collection<FacturaEmpresa> facturaEmpresaCollectionNew = empresa.getFacturaEmpresaCollection();
            List<String> illegalOrphanMessages = null;
            for (TargetEdad targetEdadCollectionOldTargetEdad : targetEdadCollectionOld) {
                if (!targetEdadCollectionNew.contains(targetEdadCollectionOldTargetEdad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TargetEdad " + targetEdadCollectionOldTargetEdad + " since its empresa field is not nullable.");
                }
            }
            for (Campania campaniaCollectionOldCampania : campaniaCollectionOld) {
                if (!campaniaCollectionNew.contains(campaniaCollectionOldCampania)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Campania " + campaniaCollectionOldCampania + " since its empresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TargetEdad> attachedTargetEdadCollectionNew = new ArrayList<TargetEdad>();
            for (TargetEdad targetEdadCollectionNewTargetEdadToAttach : targetEdadCollectionNew) {
                targetEdadCollectionNewTargetEdadToAttach = em.getReference(targetEdadCollectionNewTargetEdadToAttach.getClass(), targetEdadCollectionNewTargetEdadToAttach.getTargetEdadPK());
                attachedTargetEdadCollectionNew.add(targetEdadCollectionNewTargetEdadToAttach);
            }
            targetEdadCollectionNew = attachedTargetEdadCollectionNew;
            empresa.setTargetEdadCollection(targetEdadCollectionNew);
            Collection<Campania> attachedCampaniaCollectionNew = new ArrayList<Campania>();
            for (Campania campaniaCollectionNewCampaniaToAttach : campaniaCollectionNew) {
                campaniaCollectionNewCampaniaToAttach = em.getReference(campaniaCollectionNewCampaniaToAttach.getClass(), campaniaCollectionNewCampaniaToAttach.getCampaniaPK());
                attachedCampaniaCollectionNew.add(campaniaCollectionNewCampaniaToAttach);
            }
            campaniaCollectionNew = attachedCampaniaCollectionNew;
            empresa.setCampaniaCollection(campaniaCollectionNew);
            Collection<FacturaEmpresa> attachedFacturaEmpresaCollectionNew = new ArrayList<FacturaEmpresa>();
            for (FacturaEmpresa facturaEmpresaCollectionNewFacturaEmpresaToAttach : facturaEmpresaCollectionNew) {
                facturaEmpresaCollectionNewFacturaEmpresaToAttach = em.getReference(facturaEmpresaCollectionNewFacturaEmpresaToAttach.getClass(), facturaEmpresaCollectionNewFacturaEmpresaToAttach.getIdFactura());
                attachedFacturaEmpresaCollectionNew.add(facturaEmpresaCollectionNewFacturaEmpresaToAttach);
            }
            facturaEmpresaCollectionNew = attachedFacturaEmpresaCollectionNew;
            empresa.setFacturaEmpresaCollection(facturaEmpresaCollectionNew);
            empresa = em.merge(empresa);
            for (TargetEdad targetEdadCollectionNewTargetEdad : targetEdadCollectionNew) {
                if (!targetEdadCollectionOld.contains(targetEdadCollectionNewTargetEdad)) {
                    Empresa oldEmpresaOfTargetEdadCollectionNewTargetEdad = targetEdadCollectionNewTargetEdad.getEmpresa();
                    targetEdadCollectionNewTargetEdad.setEmpresa(empresa);
                    targetEdadCollectionNewTargetEdad = em.merge(targetEdadCollectionNewTargetEdad);
                    if (oldEmpresaOfTargetEdadCollectionNewTargetEdad != null && !oldEmpresaOfTargetEdadCollectionNewTargetEdad.equals(empresa)) {
                        oldEmpresaOfTargetEdadCollectionNewTargetEdad.getTargetEdadCollection().remove(targetEdadCollectionNewTargetEdad);
                        oldEmpresaOfTargetEdadCollectionNewTargetEdad = em.merge(oldEmpresaOfTargetEdadCollectionNewTargetEdad);
                    }
                }
            }
            for (Campania campaniaCollectionNewCampania : campaniaCollectionNew) {
                if (!campaniaCollectionOld.contains(campaniaCollectionNewCampania)) {
                    Empresa oldEmpresaOfCampaniaCollectionNewCampania = campaniaCollectionNewCampania.getEmpresa();
                    campaniaCollectionNewCampania.setEmpresa(empresa);
                    campaniaCollectionNewCampania = em.merge(campaniaCollectionNewCampania);
                    if (oldEmpresaOfCampaniaCollectionNewCampania != null && !oldEmpresaOfCampaniaCollectionNewCampania.equals(empresa)) {
                        oldEmpresaOfCampaniaCollectionNewCampania.getCampaniaCollection().remove(campaniaCollectionNewCampania);
                        oldEmpresaOfCampaniaCollectionNewCampania = em.merge(oldEmpresaOfCampaniaCollectionNewCampania);
                    }
                }
            }
            for (FacturaEmpresa facturaEmpresaCollectionOldFacturaEmpresa : facturaEmpresaCollectionOld) {
                if (!facturaEmpresaCollectionNew.contains(facturaEmpresaCollectionOldFacturaEmpresa)) {
                    facturaEmpresaCollectionOldFacturaEmpresa.setRuc(null);
                    facturaEmpresaCollectionOldFacturaEmpresa = em.merge(facturaEmpresaCollectionOldFacturaEmpresa);
                }
            }
            for (FacturaEmpresa facturaEmpresaCollectionNewFacturaEmpresa : facturaEmpresaCollectionNew) {
                if (!facturaEmpresaCollectionOld.contains(facturaEmpresaCollectionNewFacturaEmpresa)) {
                    Empresa oldRucOfFacturaEmpresaCollectionNewFacturaEmpresa = facturaEmpresaCollectionNewFacturaEmpresa.getRuc();
                    facturaEmpresaCollectionNewFacturaEmpresa.setRuc(empresa);
                    facturaEmpresaCollectionNewFacturaEmpresa = em.merge(facturaEmpresaCollectionNewFacturaEmpresa);
                    if (oldRucOfFacturaEmpresaCollectionNewFacturaEmpresa != null && !oldRucOfFacturaEmpresaCollectionNewFacturaEmpresa.equals(empresa)) {
                        oldRucOfFacturaEmpresaCollectionNewFacturaEmpresa.getFacturaEmpresaCollection().remove(facturaEmpresaCollectionNewFacturaEmpresa);
                        oldRucOfFacturaEmpresaCollectionNewFacturaEmpresa = em.merge(oldRucOfFacturaEmpresaCollectionNewFacturaEmpresa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = empresa.getRuc();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getRuc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TargetEdad> targetEdadCollectionOrphanCheck = empresa.getTargetEdadCollection();
            for (TargetEdad targetEdadCollectionOrphanCheckTargetEdad : targetEdadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresa (" + empresa + ") cannot be destroyed since the TargetEdad " + targetEdadCollectionOrphanCheckTargetEdad + " in its targetEdadCollection field has a non-nullable empresa field.");
            }
            Collection<Campania> campaniaCollectionOrphanCheck = empresa.getCampaniaCollection();
            for (Campania campaniaCollectionOrphanCheckCampania : campaniaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresa (" + empresa + ") cannot be destroyed since the Campania " + campaniaCollectionOrphanCheckCampania + " in its campaniaCollection field has a non-nullable empresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<FacturaEmpresa> facturaEmpresaCollection = empresa.getFacturaEmpresaCollection();
            for (FacturaEmpresa facturaEmpresaCollectionFacturaEmpresa : facturaEmpresaCollection) {
                facturaEmpresaCollectionFacturaEmpresa.setRuc(null);
                facturaEmpresaCollectionFacturaEmpresa = em.merge(facturaEmpresaCollectionFacturaEmpresa);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
