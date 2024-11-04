/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Interfaces.ICarritoDAO;
import Interfaces.IPersistencia;
import JPA.Carrito;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Persistencia implements IPersistencia{
ICarritoDAO carritoDAO;
//Persistencia Carritos

    public Persistencia() {
        this.carritoDAO = new CarritoDAO();
    }

    @Override
    public void GuardarRegistroCarrito(Carrito carrito) throws Exception {
        carritoDAO.GuardarCarrito(carrito);
    }

    @Override
    public void ActualizarRegistroCarrito(Carrito carrito) throws Exception {
     carritoDAO.ActualizarCarrito(carrito);
    }

    @Override
    public void EliminarRegistroCarrito(Integer id) throws Exception {
      carritoDAO.EliminarCarrito(id);
    }

    @Override
    public List<Carrito> ConsultarListaCarritos() throws Exception {
       return carritoDAO.ConsultarListaCarritos();
    }

    @Override
    public Carrito ConsultarCarritoId(Integer id) {
     return carritoDAO.ConsultarCarritoId(id);
    }
    
}
