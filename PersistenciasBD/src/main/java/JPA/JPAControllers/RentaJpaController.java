/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA.JPAControllers;

import JPA.Renta;
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
public class RentaJpaController implements Serializable {
 private EntityManagerFactory emf;
 
    public RentaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("itson.com.mx_PersistenciasBD_jar_1.0-AdministrarCatalogoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Renta renta) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Transaccion idTransaccionCarritoOrphanCheck = renta.getIdTransaccionCarrito();
        if (idTransaccionCarritoOrphanCheck != null) {
            Renta oldRentaOfIdTransaccionCarrito = idTransaccionCarritoOrphanCheck.getRenta();
            if (oldRentaOfIdTransaccionCarrito != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Transaccion " + idTransaccionCarritoOrphanCheck + " already has an item of type Renta whose idTransaccionCarrito column cannot be null. Please make another selection for the idTransaccionCarrito field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion idTransaccionCarrito = renta.getIdTransaccionCarrito();
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito = em.getReference(idTransaccionCarrito.getClass(), idTransaccionCarrito.getIdTransaccion());
                renta.setIdTransaccionCarrito(idTransaccionCarrito);
            }
            em.persist(renta);
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito.setRenta(renta);
                idTransaccionCarrito = em.merge(idTransaccionCarrito);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Renta renta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Renta persistentRenta = em.find(Renta.class, renta.getIdRenta());
            Transaccion idTransaccionCarritoOld = persistentRenta.getIdTransaccionCarrito();
            Transaccion idTransaccionCarritoNew = renta.getIdTransaccionCarrito();
            List<String> illegalOrphanMessages = null;
            if (idTransaccionCarritoNew != null && !idTransaccionCarritoNew.equals(idTransaccionCarritoOld)) {
                Renta oldRentaOfIdTransaccionCarrito = idTransaccionCarritoNew.getRenta();
                if (oldRentaOfIdTransaccionCarrito != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Transaccion " + idTransaccionCarritoNew + " already has an item of type Renta whose idTransaccionCarrito column cannot be null. Please make another selection for the idTransaccionCarrito field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTransaccionCarritoNew != null) {
                idTransaccionCarritoNew = em.getReference(idTransaccionCarritoNew.getClass(), idTransaccionCarritoNew.getIdTransaccion());
                renta.setIdTransaccionCarrito(idTransaccionCarritoNew);
            }
            renta = em.merge(renta);
            if (idTransaccionCarritoOld != null && !idTransaccionCarritoOld.equals(idTransaccionCarritoNew)) {
                idTransaccionCarritoOld.setRenta(null);
                idTransaccionCarritoOld = em.merge(idTransaccionCarritoOld);
            }
            if (idTransaccionCarritoNew != null && !idTransaccionCarritoNew.equals(idTransaccionCarritoOld)) {
                idTransaccionCarritoNew.setRenta(renta);
                idTransaccionCarritoNew = em.merge(idTransaccionCarritoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = renta.getIdRenta();
                if (findRenta(id) == null) {
                    throw new NonexistentEntityException("The renta with id " + id + " no longer exists.");
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
            Renta renta;
            try {
                renta = em.getReference(Renta.class, id);
                renta.getIdRenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The renta with id " + id + " no longer exists.", enfe);
            }
            Transaccion idTransaccionCarrito = renta.getIdTransaccionCarrito();
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito.setRenta(null);
                idTransaccionCarrito = em.merge(idTransaccionCarrito);
            }
            em.remove(renta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Renta> findRentaEntities() {
        return findRentaEntities(true, -1, -1);
    }

    public List<Renta> findRentaEntities(int maxResults, int firstResult) {
        return findRentaEntities(false, maxResults, firstResult);
    }

    private List<Renta> findRentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Renta.class));
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

    public Renta findRenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Renta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Renta> rt = cq.from(Renta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}