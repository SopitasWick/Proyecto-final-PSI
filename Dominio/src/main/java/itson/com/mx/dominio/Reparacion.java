/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.com.mx.dominio;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Reparacion extends Transaccion{
   protected Date fechaEntrega; 
   protected String resumenReparacion;

    public Reparacion() {
    }

    public Reparacion(Date fechaEntrega, String resumenReparacion) {
        this.fechaEntrega = fechaEntrega;
        this.resumenReparacion = resumenReparacion;
    }

    public Reparacion(Date fechaEntrega, String resumenReparacion, int idTransaccion, Carrito[] carritos, Cliente cliente, Administrador administrador) {
        super(idTransaccion, carritos, cliente, administrador);
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

    @Override
    public String toString() {
        return "Reparacion{" + "fechaEntrega=" + fechaEntrega + ", resumenReparacion=" + resumenReparacion + '}';
    }
   
}
