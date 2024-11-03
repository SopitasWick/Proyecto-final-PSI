/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA.JPAControllers;

import JPA.Reparacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.Transaccion;
import JPA.JPAControllers.exceptions.IllegalOrphanException;
import JPA.JPAControllers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class ReparacionJpaController implements Serializable {
    private EntityManagerFactory emf;

    public ReparacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("itson.com.mx_PersistenciasBD_jar_1.0-AdministrarCatalogoPU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reparacion reparacion) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Transaccion idTransaccionCarritoOrphanCheck = reparacion.getIdTransaccionCarrito();
        if (idTransaccionCarritoOrphanCheck != null) {
            Reparacion oldReparacionOfIdTransaccionCarrito = idTransaccionCarritoOrphanCheck.getReparacion();
            if (oldReparacionOfIdTransaccionCarrito != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Transaccion " + idTransaccionCarritoOrphanCheck + " already has an item of type Reparacion whose idTransaccionCarrito column cannot be null. Please make another selection for the idTransaccionCarrito field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion idTransaccionCarrito = reparacion.getIdTransaccionCarrito();
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito = em.getReference(idTransaccionCarrito.getClass(), idTransaccionCarrito.getIdTransaccion());
                reparacion.setIdTransaccionCarrito(idTransaccionCarrito);
            }
            em.persist(reparacion);
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito.setReparacion(reparacion);
                idTransaccionCarrito = em.merge(idTransaccionCarrito);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reparacion reparacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reparacion persistentReparacion = em.find(Reparacion.class, reparacion.getIdReparacion());
            Transaccion idTransaccionCarritoOld = persistentReparacion.getIdTransaccionCarrito();
            Transaccion idTransaccionCarritoNew = reparacion.getIdTransaccionCarrito();
            List<String> illegalOrphanMessages = null;
            if (idTransaccionCarritoNew != null && !idTransaccionCarritoNew.equals(idTransaccionCarritoOld)) {
                Reparacion oldReparacionOfIdTransaccionCarrito = idTransaccionCarritoNew.getReparacion();
                if (oldReparacionOfIdTransaccionCarrito != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Transaccion " + idTransaccionCarritoNew + " already has an item of type Reparacion whose idTransaccionCarrito column cannot be null. Please make another selection for the idTransaccionCarrito field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTransaccionCarritoNew != null) {
                idTransaccionCarritoNew = em.getReference(idTransaccionCarritoNew.getClass(), idTransaccionCarritoNew.getIdTransaccion());
                reparacion.setIdTransaccionCarrito(idTransaccionCarritoNew);
            }
            reparacion = em.merge(reparacion);
            if (idTransaccionCarritoOld != null && !idTransaccionCarritoOld.equals(idTransaccionCarritoNew)) {
                idTransaccionCarritoOld.setReparacion(null);
                idTransaccionCarritoOld = em.merge(idTransaccionCarritoOld);
            }
            if (idTransaccionCarritoNew != null && !idTransaccionCarritoNew.equals(idTransaccionCarritoOld)) {
                idTransaccionCarritoNew.setReparacion(reparacion);
                idTransaccionCarritoNew = em.merge(idTransaccionCarritoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reparacion.getIdReparacion();
                if (findReparacion(id) == null) {
                    throw new NonexistentEntityException("The reparacion with id " + id + " no longer exists.");
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
            Reparacion reparacion;
            try {
                reparacion = em.getReference(Reparacion.class, id);
                reparacion.getIdReparacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reparacion with id " + id + " no longer exists.", enfe);
            }
            Transaccion idTransaccionCarrito = reparacion.getIdTransaccionCarrito();
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito.setReparacion(null);
                idTransaccionCarrito = em.merge(idTransaccionCarrito);
            }
            em.remove(reparacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reparacion> findReparacionEntities() {
        return findReparacionEntities(true, -1, -1);
    }

    public List<Reparacion> findReparacionEntities(int maxResults, int firstResult) {
        return findReparacionEntities(false, maxResults, firstResult);
    }

    private List<Reparacion> findReparacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reparacion.class));
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

    public Reparacion findReparacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reparacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getReparacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reparacion> rt = cq.from(Reparacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}