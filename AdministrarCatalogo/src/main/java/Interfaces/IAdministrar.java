/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import JPA.Carrito;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IAdministrar {
    //Operaciones Administrador
    public void RegistrarCarrito(Carrito carrito)throws Exception;
    public void ActualizarCarrito(Carrito carrito)throws Exception;
    public void EliminarCarrito(Integer id)throws Exception;
    public List<Carrito>ConsultarCatalogoCarritos()throws Exception;
    public Carrito ConsultarCarritoId(Integer id)throws Exception;
}