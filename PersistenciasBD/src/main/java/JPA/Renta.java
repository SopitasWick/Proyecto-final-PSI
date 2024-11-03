/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "renta")
@NamedQueries({
    @NamedQuery(name = "Renta.findAll", query = "SELECT r FROM Renta r"),
    @NamedQuery(name = "Renta.findByFechaInicial", query = "SELECT r FROM Renta r WHERE r.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "Renta.findByFechaDevolucion", query = "SELECT r FROM Renta r WHERE r.fechaDevolucion = :fechaDevolucion"),
    @NamedQuery(name = "Renta.findByIdRenta", query = "SELECT r FROM Renta r WHERE r.idRenta = :idRenta")})
public class Renta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "fechaInicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Basic(optional = false)
    @Column(name = "fechaDevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRenta")
    private Integer idRenta;
    @JoinColumn(name = "idTransaccionCarrito", referencedColumnName = "idTransaccion")
    @OneToOne(optional = false)
    private Transaccion idTransaccionCarrito;

    public Renta() {
    }

    public Renta(Integer idRenta) {
        this.idRenta = idRenta;
    }

    public Renta(Integer idRenta, Date fechaInicial, Date fechaDevolucion) {
        this.idRenta = idRenta;
        this.fechaInicial = fechaInicial;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Integer getIdRenta() {
        return idRenta;
    }

    public void setIdRenta(Integer idRenta) {
        this.idRenta = idRenta;
    }

    public Transaccion getIdTransaccionCarrito() {
        return idTransaccionCarrito;
    }

    public void setIdTransaccionCarrito(Transaccion idTransaccionCarrito) {
        this.idTransaccionCarrito = idTransaccionCarrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRenta != null ? idRenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Renta)) {
            return false;
        }
        Renta other = (Renta) object;
        if ((this.idRenta == null && other.idRenta != null) || (this.idRenta != null && !this.idRenta.equals(other.idRenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Renta[ idRenta=" + idRenta + " ]";
    }
    
}
