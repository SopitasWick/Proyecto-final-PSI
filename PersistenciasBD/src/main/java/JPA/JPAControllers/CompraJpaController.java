/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA.JPAControllers;

import JPA.Compra;
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
public class CompraJpaController implements Serializable {
private EntityManagerFactory emf;

    public CompraJpaController() {
        this.emf = Persistence.createEntityManagerFactory("itson.com.mx_PersistenciasBD_jar_1.0-AdministrarCatalogoPU");
    }
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Transaccion idTransaccionCarritoOrphanCheck = compra.getIdTransaccionCarrito();
        if (idTransaccionCarritoOrphanCheck != null) {
            Compra oldCompraOfIdTransaccionCarrito = idTransaccionCarritoOrphanCheck.getCompra();
            if (oldCompraOfIdTransaccionCarrito != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Transaccion " + idTransaccionCarritoOrphanCheck + " already has an item of type Compra whose idTransaccionCarrito column cannot be null. Please make another selection for the idTransaccionCarrito field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion idTransaccionCarrito = compra.getIdTransaccionCarrito();
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito = em.getReference(idTransaccionCarrito.getClass(), idTransaccionCarrito.getIdTransaccion());
                compra.setIdTransaccionCarrito(idTransaccionCarrito);
            }
            em.persist(compra);
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito.setCompra(compra);
                idTransaccionCarrito = em.merge(idTransaccionCarrito);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getIdCompra());
            Transaccion idTransaccionCarritoOld = persistentCompra.getIdTransaccionCarrito();
            Transaccion idTransaccionCarritoNew = compra.getIdTransaccionCarrito();
            List<String> illegalOrphanMessages = null;
            if (idTransaccionCarritoNew != null && !idTransaccionCarritoNew.equals(idTransaccionCarritoOld)) {
                Compra oldCompraOfIdTransaccionCarrito = idTransaccionCarritoNew.getCompra();
                if (oldCompraOfIdTransaccionCarrito != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Transaccion " + idTransaccionCarritoNew + " already has an item of type Compra whose idTransaccionCarrito column cannot be null. Please make another selection for the idTransaccionCarrito field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTransaccionCarritoNew != null) {
                idTransaccionCarritoNew = em.getReference(idTransaccionCarritoNew.getClass(), idTransaccionCarritoNew.getIdTransaccion());
                compra.setIdTransaccionCarrito(idTransaccionCarritoNew);
            }
            compra = em.merge(compra);
            if (idTransaccionCarritoOld != null && !idTransaccionCarritoOld.equals(idTransaccionCarritoNew)) {
                idTransaccionCarritoOld.setCompra(null);
                idTransaccionCarritoOld = em.merge(idTransaccionCarritoOld);
            }
            if (idTransaccionCarritoNew != null && !idTransaccionCarritoNew.equals(idTransaccionCarritoOld)) {
                idTransaccionCarritoNew.setCompra(compra);
                idTransaccionCarritoNew = em.merge(idTransaccionCarritoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compra.getIdCompra();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getIdCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            Transaccion idTransaccionCarrito = compra.getIdTransaccionCarrito();
            if (idTransaccionCarrito != null) {
                idTransaccionCarrito.setCompra(null);
                idTransaccionCarrito = em.merge(idTransaccionCarrito);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}