/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Interfaces.ICarritoDAO;
import JPA.Carrito;
import JPA.JPAControllers.CarritoJpaController;

import java.util.List;

/**
 *
 * @author usuario
 */
public class CarritoDAO implements ICarritoDAO{
CarritoJpaController jpa = new CarritoJpaController();

    @Override
    public void GuardarCarrito(Carrito carrito) throws Exception {
       jpa.create(carrito);
    }

    @Override
    public void ActualizarCarrito(Carrito carrito) throws Exception {
       jpa.edit(carrito);
    }

    @Override
    public void EliminarCarrito(Integer id) throws Exception {
       jpa.destroy(id);
    }

    @Override
    public List<Carrito> ConsultarListaCarritos() throws Exception {
        return jpa.findCarritoEntities();
    }

    @Override
    public Carrito ConsultarCarritoId(Integer id) {
           return jpa.findCarrito(id);
    }
    
}
