/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA.JPAControllers;

import JPA.Cliente;
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
public class ClienteJpaController implements Serializable {
private EntityManagerFactory emf;
    public ClienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("itson.com.mx_PersistenciasBD_jar_1.0-AdministrarCatalogoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion transaccion = cliente.getTransaccion();
            if (transaccion != null) {
                transaccion = em.getReference(transaccion.getClass(), transaccion.getIdTransaccion());
                cliente.setTransaccion(transaccion);
            }
            em.persist(cliente);
            if (transaccion != null) {
                Cliente oldIdClienteOfTransaccion = transaccion.getIdCliente();
                if (oldIdClienteOfTransaccion != null) {
                    oldIdClienteOfTransaccion.setTransaccion(null);
                    oldIdClienteOfTransaccion = em.merge(oldIdClienteOfTransaccion);
                }
                transaccion.setIdCliente(cliente);
                transaccion = em.merge(transaccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getIdCliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Transaccion transaccionOld = persistentCliente.getTransaccion();
            Transaccion transaccionNew = cliente.getTransaccion();
            List<String> illegalOrphanMessages = null;
            if (transaccionOld != null && !transaccionOld.equals(transaccionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Transaccion " + transaccionOld + " since its idCliente field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (transaccionNew != null) {
                transaccionNew = em.getReference(transaccionNew.getClass(), transaccionNew.getIdTransaccion());
                cliente.setTransaccion(transaccionNew);
            }
            cliente = em.merge(cliente);
            if (transaccionNew != null && !transaccionNew.equals(transaccionOld)) {
                Cliente oldIdClienteOfTransaccion = transaccionNew.getIdCliente();
                if (oldIdClienteOfTransaccion != null) {
                    oldIdClienteOfTransaccion.setTransaccion(null);
                    oldIdClienteOfTransaccion = em.merge(oldIdClienteOfTransaccion);
                }
                transaccionNew.setIdCliente(cliente);
                transaccionNew = em.merge(transaccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Transaccion transaccionOrphanCheck = cliente.getTransaccion();
            if (transaccionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Transaccion " + transaccionOrphanCheck + " in its transaccion field has a non-nullable idCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
