/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Interfaces.IAdministrar;
import JPA.Carrito;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Administrar implements IAdministrar{
Persistencia persistencia = new Persistencia();
    @Override
    public void RegistrarCarrito(Carrito carrito) throws Exception {
        //LLAMAR METODO CORRESPONDIENTE DE Persistencia
        persistencia.GuardarRegistroCarrito(carrito);
    }

    @Override
    public void ActualizarCarrito(Carrito carrito) throws Exception {
        //LLAMAR METODO CORRESPONDIENTE DE Persistencia
        persistencia.ActualizarRegistroCarrito(carrito);
    }

    @Override
    public void EliminarCarrito(Integer id) throws Exception {
        //LLAMAR METODO CORRESPONDIENTE DE Persistencia
        persistencia.EliminarRegistroCarrito(id);
    }

    @Override
    public List<Carrito> ConsultarCatalogoCarritos() throws Exception {
        //LLAMAR METODO CORRESPONDIENTE DE Persistencia
        return persistencia.ConsultarListaCarritos();
    }

    @Override
    public Carrito ConsultarCarritoId(Integer id) throws Exception {
        //LLAMAR METODO CORRESPONDIENTE DE Persistencia
        return persistencia.ConsultarCarritoId(id);
    }
    
}
