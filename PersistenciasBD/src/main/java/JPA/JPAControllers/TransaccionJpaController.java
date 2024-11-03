/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA.JPAControllers;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.Compra;
import JPA.Administrador;
import JPA.Carrito;
import JPA.Cliente;
import JPA.Reparacion;
import JPA.Renta;
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
public class TransaccionJpaController implements Serializable {
 private EntityManagerFactory emf;
    public TransaccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("itson.com.mx_PersistenciasBD_jar_1.0-AdministrarCatalogoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transaccion transaccion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Administrador idAdministradorOrphanCheck = transaccion.getIdAdministrador();
        if (idAdministradorOrphanCheck != null) {
            Transaccion oldTransaccionOfIdAdministrador = idAdministradorOrphanCheck.getTransaccion();
            if (oldTransaccionOfIdAdministrador != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Administrador " + idAdministradorOrphanCheck + " already has an item of type Transaccion whose idAdministrador column cannot be null. Please make another selection for the idAdministrador field.");
            }
        }
        Carrito idCarritoOrphanCheck = transaccion.getIdCarrito();
        if (idCarritoOrphanCheck != null) {
            Transaccion oldTransaccionOfIdCarrito = idCarritoOrphanCheck.getTransaccion();
            if (oldTransaccionOfIdCarrito != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Carrito " + idCarritoOrphanCheck + " already has an item of type Transaccion whose idCarrito column cannot be null. Please make another selection for the idCarrito field.");
            }
        }
        Cliente idClienteOrphanCheck = transaccion.getIdCliente();
        if (idClienteOrphanCheck != null) {
            Transaccion oldTransaccionOfIdCliente = idClienteOrphanCheck.getTransaccion();
            if (oldTransaccionOfIdCliente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Cliente " + idClienteOrphanCheck + " already has an item of type Transaccion whose idCliente column cannot be null. Please make another selection for the idCliente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compra = transaccion.getCompra();
            if (compra != null) {
                compra = em.getReference(compra.getClass(), compra.getIdCompra());
                transaccion.setCompra(compra);
            }
            Administrador idAdministrador = transaccion.getIdAdministrador();
            if (idAdministrador != null) {
                idAdministrador = em.getReference(idAdministrador.getClass(), idAdministrador.getIdAdministrador());
                transaccion.setIdAdministrador(idAdministrador);
            }
            Carrito idCarrito = transaccion.getIdCarrito();
            if (idCarrito != null) {
                idCarrito = em.getReference(idCarrito.getClass(), idCarrito.getIdCarrito());
                transaccion.setIdCarrito(idCarrito);
            }
            Cliente idCliente = transaccion.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                transaccion.setIdCliente(idCliente);
            }
            Reparacion reparacion = transaccion.getReparacion();
            if (reparacion != null) {
                reparacion = em.getReference(reparacion.getClass(), reparacion.getIdReparacion());
                transaccion.setReparacion(reparacion);
            }
            Renta renta = transaccion.getRenta();
            if (renta != null) {
                renta = em.getReference(renta.getClass(), renta.getIdRenta());
                transaccion.setRenta(renta);
            }
            em.persist(transaccion);
            if (compra != null) {
                Transaccion oldIdTransaccionCarritoOfCompra = compra.getIdTransaccionCarrito();
                if (oldIdTransaccionCarritoOfCompra != null) {
                    oldIdTransaccionCarritoOfCompra.setCompra(null);
                    oldIdTransaccionCarritoOfCompra = em.merge(oldIdTransaccionCarritoOfCompra);
                }
                compra.setIdTransaccionCarrito(transaccion);
                compra = em.merge(compra);
            }
            if (idAdministrador != null) {
                idAdministrador.setTransaccion(transaccion);
                idAdministrador = em.merge(idAdministrador);
            }
            if (idCarrito != null) {
                idCarrito.setTransaccion(transaccion);
                idCarrito = em.merge(idCarrito);
            }
            if (idCliente != null) {
                idCliente.setTransaccion(transaccion);
                idCliente = em.merge(idCliente);
            }
            if (reparacion != null) {
                Transaccion oldIdTransaccionCarritoOfReparacion = reparacion.getIdTransaccionCarrito();
                if (oldIdTransaccionCarritoOfReparacion != null) {
                    oldIdTransaccionCarritoOfReparacion.setReparacion(null);
                    oldIdTransaccionCarritoOfReparacion = em.merge(oldIdTransaccionCarritoOfReparacion);
                }
                reparacion.setIdTransaccionCarrito(transaccion);
                reparacion = em.merge(reparacion);
            }
            if (renta != null) {
                Transaccion oldIdTransaccionCarritoOfRenta = renta.getIdTransaccionCarrito();
                if (oldIdTransaccionCarritoOfRenta != null) {
                    oldIdTransaccionCarritoOfRenta.setRenta(null);
                    oldIdTransaccionCarritoOfRenta = em.merge(oldIdTransaccionCarritoOfRenta);
                }
                renta.setIdTransaccionCarrito(transaccion);
                renta = em.merge(renta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransaccion(transaccion.getIdTransaccion()) != null) {
                throw new PreexistingEntityException("Transaccion " + transaccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transaccion transaccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion persistentTransaccion = em.find(Transaccion.class, transaccion.getIdTransaccion());
            Compra compraOld = persistentTransaccion.getCompra();
            Compra compraNew = transaccion.getCompra();
            Administrador idAdministradorOld = persistentTransaccion.getIdAdministrador();
            Administrador idAdministradorNew = transaccion.getIdAdministrador();
            Carrito idCarritoOld = persistentTransaccion.getIdCarrito();
            Carrito idCarritoNew = transaccion.getIdCarrito();
            Cliente idClienteOld = persistentTransaccion.getIdCliente();
            Cliente idClienteNew = transaccion.getIdCliente();
            Reparacion reparacionOld = persistentTransaccion.getReparacion();
            Reparacion reparacionNew = transaccion.getReparacion();
            Renta rentaOld = persistentTransaccion.getRenta();
            Renta rentaNew = transaccion.getRenta();
            List<String> illegalOrphanMessages = null;
            if (compraOld != null && !compraOld.equals(compraNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Compra " + compraOld + " since its idTransaccionCarrito field is not nullable.");
            }
            if (idAdministradorNew != null && !idAdministradorNew.equals(idAdministradorOld)) {
                Transaccion oldTransaccionOfIdAdministrador = idAdministradorNew.getTransaccion();
                if (oldTransaccionOfIdAdministrador != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Administrador " + idAdministradorNew + " already has an item of type Transaccion whose idAdministrador column cannot be null. Please make another selection for the idAdministrador field.");
                }
            }
            if (idCarritoNew != null && !idCarritoNew.equals(idCarritoOld)) {
                Transaccion oldTransaccionOfIdCarrito = idCarritoNew.getTransaccion();
                if (oldTransaccionOfIdCarrito != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Carrito " + idCarritoNew + " already has an item of type Transaccion whose idCarrito column cannot be null. Please make another selection for the idCarrito field.");
                }
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                Transaccion oldTransaccionOfIdCliente = idClienteNew.getTransaccion();
                if (oldTransaccionOfIdCliente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Cliente " + idClienteNew + " already has an item of type Transaccion whose idCliente column cannot be null. Please make another selection for the idCliente field.");
                }
            }
            if (reparacionOld != null && !reparacionOld.equals(reparacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Reparacion " + reparacionOld + " since its idTransaccionCarrito field is not nullable.");
            }
            if (rentaOld != null && !rentaOld.equals(rentaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Renta " + rentaOld + " since its idTransaccionCarrito field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (compraNew != null) {
                compraNew = em.getReference(compraNew.getClass(), compraNew.getIdCompra());
                transaccion.setCompra(compraNew);
            }
            if (idAdministradorNew != null) {
                idAdministradorNew = em.getReference(idAdministradorNew.getClass(), idAdministradorNew.getIdAdministrador());
                transaccion.setIdAdministrador(idAdministradorNew);
            }
            if (idCarritoNew != null) {
                idCarritoNew = em.getReference(idCarritoNew.getClass(), idCarritoNew.getIdCarrito());
                transaccion.setIdCarrito(idCarritoNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                transaccion.setIdCliente(idClienteNew);
            }
            if (reparacionNew != null) {
                reparacionNew = em.getReference(reparacionNew.getClass(), reparacionNew.getIdReparacion());
                transaccion.setReparacion(reparacionNew);
            }
            if (rentaNew != null) {
                rentaNew = em.getReference(rentaNew.getClass(), rentaNew.getIdRenta());
                transaccion.setRenta(rentaNew);
            }
            transaccion = em.merge(transaccion);
            if (compraNew != null && !compraNew.equals(compraOld)) {
                Transaccion oldIdTransaccionCarritoOfCompra = compraNew.getIdTransaccionCarrito();
                if (oldIdTransaccionCarritoOfCompra != null) {
                    oldIdTransaccionCarritoOfCompra.setCompra(null);
                    oldIdTransaccionCarritoOfCompra = em.merge(oldIdTransaccionCarritoOfCompra);
                }
                compraNew.setIdTransaccionCarrito(transaccion);
                compraNew = em.merge(compraNew);
            }
            if (idAdministradorOld != null && !idAdministradorOld.equals(idAdministradorNew)) {
                idAdministradorOld.setTransaccion(null);
                idAdministradorOld = em.merge(idAdministradorOld);
            }
            if (idAdministradorNew != null && !idAdministradorNew.equals(idAdministradorOld)) {
                idAdministradorNew.setTransaccion(transaccion);
                idAdministradorNew = em.merge(idAdministradorNew);
            }
            if (idCarritoOld != null && !idCarritoOld.equals(idCarritoNew)) {
                idCarritoOld.setTransaccion(null);
                idCarritoOld = em.merge(idCarritoOld);
            }
            if (idCarritoNew != null && !idCarritoNew.equals(idCarritoOld)) {
                idCarritoNew.setTransaccion(transaccion);
                idCarritoNew = em.merge(idCarritoNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.setTransaccion(null);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.setTransaccion(transaccion);
                idClienteNew = em.merge(idClienteNew);
            }
            if (reparacionNew != null && !reparacionNew.equals(reparacionOld)) {
                Transaccion oldIdTransaccionCarritoOfReparacion = reparacionNew.getIdTransaccionCarrito();
                if (oldIdTransaccionCarritoOfReparacion != null) {
                    oldIdTransaccionCarritoOfReparacion.setReparacion(null);
                    oldIdTransaccionCarritoOfReparacion = em.merge(oldIdTransaccionCarritoOfReparacion);
                }
                reparacionNew.setIdTransaccionCarrito(transaccion);
                reparacionNew = em.merge(reparacionNew);
            }
            if (rentaNew != null && !rentaNew.equals(rentaOld)) {
                Transaccion oldIdTransaccionCarritoOfRenta = rentaNew.getIdTransaccionCarrito();
                if (oldIdTransaccionCarritoOfRenta != null) {
                    oldIdTransaccionCarritoOfRenta.setRenta(null);
                    oldIdTransaccionCarritoOfRenta = em.merge(oldIdTransaccionCarritoOfRenta);
                }
                rentaNew.setIdTransaccionCarrito(transaccion);
                rentaNew = em.merge(rentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transaccion.getIdTransaccion();
                if (findTransaccion(id) == null) {
                    throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.");
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
            Transaccion transaccion;
            try {
                transaccion = em.getReference(Transaccion.class, id);
                transaccion.getIdTransaccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Compra compraOrphanCheck = transaccion.getCompra();
            if (compraOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transaccion (" + transaccion + ") cannot be destroyed since the Compra " + compraOrphanCheck + " in its compra field has a non-nullable idTransaccionCarrito field.");
            }
            Reparacion reparacionOrphanCheck = transaccion.getReparacion();
            if (reparacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transaccion (" + transaccion + ") cannot be destroyed since the Reparacion " + reparacionOrphanCheck + " in its reparacion field has a non-nullable idTransaccionCarrito field.");
            }
            Renta rentaOrphanCheck = transaccion.getRenta();
            if (rentaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transaccion (" + transaccion + ") cannot be destroyed since the Renta " + rentaOrphanCheck + " in its renta field has a non-nullable idTransaccionCarrito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Administrador idAdministrador = transaccion.getIdAdministrador();
            if (idAdministrador != null) {
                idAdministrador.setTransaccion(null);
                idAdministrador = em.merge(idAdministrador);
            }
            Carrito idCarrito = transaccion.getIdCarrito();
            if (idCarrito != null) {
                idCarrito.setTransaccion(null);
                idCarrito = em.merge(idCarrito);
            }
            Cliente idCliente = transaccion.getIdCliente();
            if (idCliente != null) {
                idCliente.setTransaccion(null);
                idCliente = em.merge(idCliente);
            }
            em.remove(transaccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transaccion> findTransaccionEntities() {
        return findTransaccionEntities(true, -1, -1);
    }

    public List<Transaccion> findTransaccionEntities(int maxResults, int firstResult) {
        return findTransaccionEntities(false, maxResults, firstResult);
    }

    private List<Transaccion> findTransaccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transaccion.class));
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

    public Transaccion findTransaccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transaccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransaccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transaccion> rt = cq.from(Transaccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}