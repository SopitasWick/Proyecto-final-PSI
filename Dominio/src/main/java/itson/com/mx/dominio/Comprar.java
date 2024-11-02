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
public class Comprar extends Transaccion{
    protected Date fecha;

    public Comprar() {
    }

    public Comprar(Date fecha) {
        this.fecha = fecha;
    }

    public Comprar(Date fecha, int idTransaccion, Carrito[] carritos, Cliente cliente, Administrador administrador) {
        super(idTransaccion, carritos, cliente, administrador);
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Comprar{" + "fecha=" + fecha + '}';
    }
}
