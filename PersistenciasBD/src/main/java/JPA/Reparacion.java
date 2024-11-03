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
@Table(name = "reparacion")
@NamedQueries({
    @NamedQuery(name = "Reparacion.findAll", query = "SELECT r FROM Reparacion r"),
    @NamedQuery(name = "Reparacion.findByFechaEntrega", query = "SELECT r FROM Reparacion r WHERE r.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Reparacion.findByResumenReparacion", query = "SELECT r FROM Reparacion r WHERE r.resumenReparacion = :resumenReparacion"),
    @NamedQuery(name = "Reparacion.findByIdReparacion", query = "SELECT r FROM Reparacion r WHERE r.idReparacion = :idReparacion")})
public class Reparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @Column(name = "resumenReparacion")
    private String resumenReparacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReparacion")
    private Integer idReparacion;
    @JoinColumn(name = "idTransaccionCarrito", referencedColumnName = "idTransaccion")
    @OneToOne(optional = false)
    private Transaccion idTransaccionCarrito;

    public Reparacion() {
    }

    public Reparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
    }

    public Reparacion(Integer idReparacion, Date fechaEntrega, String resumenReparacion) {
        this.idReparacion = idReparacion;
        this.fechaEntrega = fechaEntrega;
        this.resumenReparacion = resumenReparacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getResumenReparacion() {
        return resumenReparacion;
    }

    public void setResumenReparacion(String resumenReparacion) {
        this.resumenReparacion = resumenReparacion;
    }

    public Integer getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
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
        hash += (idReparacion != null ? idReparacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparacion)) {
            return false;
        }
        Reparacion other = (Reparacion) object;
        if ((this.idReparacion == null && other.idReparacion != null) || (this.idReparacion != null && !this.idReparacion.equals(other.idReparacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Reparacion[ idReparacion=" + idReparacion + " ]";
    }
    
}
