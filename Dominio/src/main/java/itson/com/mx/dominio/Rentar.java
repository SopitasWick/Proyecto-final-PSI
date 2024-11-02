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
public class Rentar extends Transaccion{
 protected Date fechaInicial;
 protected Date fechaDevolucion;

    public Rentar() {
    }

    public Rentar(Date fechaInicial, Date fechaDevolucion) {
        this.fechaInicial = fechaInicial;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Rentar(Date fechaInicial, Date fechaDevolucion, int idTransaccion, Carrito[] carritos, Cliente cliente, Administrador administrador) {
        super(idTransaccion, carritos, cliente, administrador);
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

    @Override
    public String toString() {
        return "Rentar{" + "fechaInicial=" + fechaInicial + ", fechaDevolucion=" + fechaDevolucion + '}';
    }
 
}
