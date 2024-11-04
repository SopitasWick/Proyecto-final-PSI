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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "carrito")
@NamedQueries({
    @NamedQuery(name = "Carrito.findAll", query = "SELECT c FROM Carrito c"),
    @NamedQuery(name = "Carrito.findByIdCarrito", query = "SELECT c FROM Carrito c WHERE c.idCarrito = :idCarrito"),
    @NamedQuery(name = "Carrito.findByCosto", query = "SELECT c FROM Carrito c WHERE c.costo = :costo"),
    @NamedQuery(name = "Carrito.findByDescripcion", query = "SELECT c FROM Carrito c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Carrito.findByEstado", query = "SELECT c FROM Carrito c WHERE c.estado = :estado"),
    @NamedQuery(name = "Carrito.findByDisponibilidad", query = "SELECT c FROM Carrito c WHERE c.disponibilidad = :disponibilidad")})
public class Carrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCarrito")
    private Integer idCarrito;
    @Basic(optional = false)
    @Column(name = "costo")
    private float costo;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Basic(optional = false)
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idCarrito")
    private Transaccion transaccion;

    public Carrito() {
    }

    public Carrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Carrito(Integer idCarrito, float costo, String descripcion, String estado, String disponibilidad, byte[] foto) {
        this.idCarrito = idCarrito;
        this.costo = costo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.disponibilidad = disponibilidad;
        this.foto = foto;
    }

    public Carrito(Integer idCarrito, float costo, String descripcion, String estado, String disponibilidad) {
        this.idCarrito = idCarrito;
        this.costo = costo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.disponibilidad = disponibilidad;
    }
    

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrito != null ? idCarrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.idCarrito == null && other.idCarrito != null) || (this.idCarrito != null && !this.idCarrito.equals(other.idCarrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carrito{" + "idCarrito=" + idCarrito + ", costo=" + costo + ", descripcion=" + descripcion + ", estado=" + estado + ", disponibilidad=" + disponibilidad + '}';
    }
    
}
