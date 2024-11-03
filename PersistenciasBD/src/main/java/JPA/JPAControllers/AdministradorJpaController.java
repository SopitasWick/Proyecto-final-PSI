/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA.JPAControllers;


import JPA.Administrador;
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
public class AdministradorJpaController implements Serializable {
private EntityManagerFactory emf;

    public AdministradorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("itson.com.mx_PersistenciasBD_jar_1.0-AdministrarCatalogoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrador administrador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion transaccion = administrador.getTransaccion();
            if (transaccion != null) {
                transaccion = em.getReference(transaccion.getClass(), transaccion.getIdTransaccion());
                administrador.setTransaccion(transaccion);
            }
            em.persist(administrador);
            if (transaccion != null) {
                Administrador oldIdAdministradorOfTransaccion = transaccion.getIdAdministrador();
                if (oldIdAdministradorOfTransaccion != null) {
                    oldIdAdministradorOfTransaccion.setTransaccion(null);
                    oldIdAdministradorOfTransaccion = em.merge(oldIdAdministradorOfTransaccion);
                }
                transaccion.setIdAdministrador(administrador);
                transaccion = em.merge(transaccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAdministrador(administrador.getIdAdministrador()) != null) {
                throw new PreexistingEntityException("Administrador " + administrador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrador administrador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador persistentAdministrador = em.find(Administrador.class, administrador.getIdAdministrador());
            Transaccion transaccionOld = persistentAdministrador.getTransaccion();
            Transaccion transaccionNew = administrador.getTransaccion();
            List<String> illegalOrphanMessages = null;
            if (transaccionOld != null && !transaccionOld.equals(transaccionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Transaccion " + transaccionOld + " since its idAdministrador field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (transaccionNew != null) {
                transaccionNew = em.getReference(transaccionNew.getClass(), transaccionNew.getIdTransaccion());
                administrador.setTransaccion(transaccionNew);
            }
            administrador = em.merge(administrador);
            if (transaccionNew != null && !transaccionNew.equals(transaccionOld)) {
                Administrador oldIdAdministradorOfTransaccion = transaccionNew.getIdAdministrador();
                if (oldIdAdministradorOfTransaccion != null) {
                    oldIdAdministradorOfTransaccion.setTransaccion(null);
                    oldIdAdministradorOfTransaccion = em.merge(oldIdAdministradorOfTransaccion);
                }
                transaccionNew.setIdAdministrador(administrador);
                transaccionNew = em.merge(transaccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrador.getIdAdministrador();
                if (findAdministrador(id) == null) {
                    throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.");
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
            Administrador administrador;
            try {
                administrador = em.getReference(Administrador.class, id);
                administrador.getIdAdministrador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Transaccion transaccionOrphanCheck = administrador.getTransaccion();
            if (transaccionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Administrador (" + administrador + ") cannot be destroyed since the Transaccion " + transaccionOrphanCheck + " in its transaccion field has a non-nullable idAdministrador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(administrador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrador> findAdministradorEntities() {
        return findAdministradorEntities(true, -1, -1);
    }

    public List<Administrador> findAdministradorEntities(int maxResults, int firstResult) {
        return findAdministradorEntities(false, maxResults, firstResult);
    }

    private List<Administrador> findAdministradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrador.class));
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

    public Administrador findAdministrador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrador> rt = cq.from(Administrador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}