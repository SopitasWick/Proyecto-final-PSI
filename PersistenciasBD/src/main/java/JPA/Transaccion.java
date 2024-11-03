/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "transaccion")
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t"),
    @NamedQuery(name = "Transaccion.findByIdTransaccion", query = "SELECT t FROM Transaccion t WHERE t.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "Transaccion.findByNombreTransaccion", query = "SELECT t FROM Transaccion t WHERE t.nombreTransaccion = :nombreTransaccion")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTransaccion")
    private Integer idTransaccion;
    @Basic(optional = false)
    @Column(name = "nombre_transaccion")
    private String nombreTransaccion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idTransaccionCarrito")
    private Compra compra;
    @JoinColumn(name = "idAdministrador", referencedColumnName = "idAdministrador")
    @OneToOne(optional = false)
    private Administrador idAdministrador;
    @JoinColumn(name = "idCarrito", referencedColumnName = "idCarrito")
    @OneToOne(optional = false)
    private Carrito idCarrito;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @OneToOne(optional = false)
    private Cliente idCliente;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idTransaccionCarrito")
    private Reparacion reparacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idTransaccionCarrito")
    private Renta renta;

    public Transaccion() {
    }

    public Transaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Transaccion(Integer idTransaccion, String nombreTransaccion) {
        this.idTransaccion = idTransaccion;
        this.nombreTransaccion = nombreTransaccion;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getNombreTransaccion() {
        return nombreTransaccion;
    }

    public void setNombreTransaccion(String nombreTransaccion) {
        this.nombreTransaccion = nombreTransaccion;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Administrador getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Administrador idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Carrito getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Carrito idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    public Renta getRenta() {
        return renta;
    }

    public void setRenta(Renta renta) {
        this.renta = renta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Transaccion[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
