 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA.JPAControllers;
import JPA.Carrito;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.Transaccion;
import JPA.JPAControllers.exceptions.IllegalOrphanException;
import JPA.JPAControllers.exceptions.NonexistentEntityException;
import JPA.JPAControllers.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class CarritoJpaController implements Serializable {
private EntityManagerFactory emf;
    public CarritoJpaController() {
      this.emf = Persistence.createEntityManagerFactory("itson.com.mx_PersistenciasBD_jar_1.0-AdministrarCatalogoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrito carrito) throws PreexistingEntityException, Exception {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();

        // Check for existing cart 
        Carrito existingCart = em.find(Carrito.class, carrito.getIdCarrito());
        if (existingCart != null) {
            throw new PreexistingEntityException("Carrito with ID " + carrito.getIdCarrito() + " already exists.");
        }

        // Persist the cart to the database
        em.persist(carrito);

        em.getTransaction().commit();
    } catch (Exception ex) {
        if (findCarrito(carrito.getIdCarrito()) != null) {
            throw new PreexistingEntityException("Carrito " + carrito + " already exists.", ex);
        }
        throw ex;
    } finally {
        if (em != null) {
            em.close();
        }
    }
}

    public void edit(Carrito carrito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrito persistentCarrito = em.find(Carrito.class, carrito.getIdCarrito());
            Transaccion transaccionOld = persistentCarrito.getTransaccion();
            Transaccion transaccionNew = carrito.getTransaccion();
            List<String> illegalOrphanMessages = null;
            if (transaccionOld != null && !transaccionOld.equals(transaccionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Transaccion " + transaccionOld + " since its idCarrito field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (transaccionNew != null) {
                transaccionNew = em.getReference(transaccionNew.getClass(), transaccionNew.getIdTransaccion());
                carrito.setTransaccion(transaccionNew);
            }
            carrito = em.merge(carrito);
            if (transaccionNew != null && !transaccionNew.equals(transaccionOld)) {
                Carrito oldIdCarritoOfTransaccion = transaccionNew.getIdCarrito();
                if (oldIdCarritoOfTransaccion != null) {
                    oldIdCarritoOfTransaccion.setTransaccion(null);
                    oldIdCarritoOfTransaccion = em.merge(oldIdCarritoOfTransaccion);
                }
                transaccionNew.setIdCarrito(carrito);
                transaccionNew = em.merge(transaccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carrito.getIdCarrito();
                if (findCarrito(id) == null) {
                    throw new NonexistentEntityException("The carrito with id " + id + " no longer exists.");
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
            Carrito carrito;
            try {
                carrito = em.getReference(Carrito.class, id);
                carrito.getIdCarrito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Transaccion transaccionOrphanCheck = carrito.getTransaccion();
            if (transaccionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carrito (" + carrito + ") cannot be destroyed since the Transaccion " + transaccionOrphanCheck + " in its transaccion field has a non-nullable idCarrito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(carrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrito> findCarritoEntities() {
        return findCarritoEntities(true, -1, -1);
    }

    public List<Carrito> findCarritoEntities(int maxResults, int firstResult) {
        return findCarritoEntities(false, maxResults, firstResult);
    }

    private List<Carrito> findCarritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrito.class));
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

    public Carrito findCarrito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrito> rt = cq.from(Carrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
