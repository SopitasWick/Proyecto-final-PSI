/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.com.mx.dominio;

import java.util.Arrays;

/**
 *
 * @author usuario
 */
public class Transaccion {
    protected int idTransaccion;
    protected Carrito carritos[];
    protected Cliente cliente;
    protected Administrador administrador;

    public Transaccion() {
    }

    public Transaccion(int idTransaccion, Carrito[] carritos, Cliente cliente, Administrador administrador) {
        this.idTransaccion = idTransaccion;
        this.carritos = carritos;
        this.cliente = cliente;
        this.administrador = administrador;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Carrito[] getCarritos() {
        return carritos;
    }

    public void setCarritos(Carrito[] carritos) {
        this.carritos = carritos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "idTransaccion=" + idTransaccion + ", carritos=" + Arrays.toString(carritos) + ", cliente=" + cliente + ", administrador=" + administrador + '}';
    }
    
}
