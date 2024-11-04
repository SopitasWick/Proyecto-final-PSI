/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


//import itson.com.mx.dominio.Transaccion;
import JPA.Carrito;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IPersistencia {
    //Carrito
    public void GuardarRegistroCarrito(Carrito carrito)throws Exception;
    public void ActualizarRegistroCarrito(Carrito carrito)throws Exception;
    public void EliminarRegistroCarrito(Integer id)throws Exception;
    public List<Carrito>ConsultarListaCarritos()throws Exception;
    public Carrito ConsultarCarritoId(Integer id);
    //Transaccion
        //public void GuardarTransaccion(Transaccion transaccion);
}
