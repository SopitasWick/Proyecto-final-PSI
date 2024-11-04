/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.com.mx.persistenciasbd;

import Implementaciones.Persistencia;
import JPA.Administrador;
import JPA.Carrito;
import JPA.JPAControllers.AdministradorJpaController;
import JPA.JPAControllers.CarritoJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author usuario
 */
public class PersistenciasBD {

    public static void main(String[] args) {
//PRUEBA ADMINISTRADOR
//        int idPrueba = 1234;
//        String usuarioPrueba="chuy";
//        String password="123456";
//        Administrador admin = new Administrador(idPrueba,usuarioPrueba,password);
//        
//        AdministradorJpaController jpc = new AdministradorJpaController();
//       
//        try {
//            jpc.create(admin);
//            System.out.println("SE AGREGO NUEVO ADMINISTRADOR!");
//        } catch (Exception ex) {
//            Logger.getLogger(PersistenciasBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
// 
//PRUEBA CARRITO
 //  Carrito carrito = new Carrito(11234, (float) 15.5,"muy bonito","renta","no disponible");
// CarritoJpaController jpa = new CarritoJpaController();
//        try {
//            jpa.create(carrito);
//           //jpa.edit(carrito);
//           //jpa.destroy(11234);
//           System.out.println("SE CREO EL CARRITO CARRITO!");
//           System.out.println("CARRITO CONSULTADO DE LA BASE DE DATOS: " + jpa.findCarrito(11234).toString());
//        } catch (Exception ex) {
//            Logger.getLogger(PersistenciasBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//PRUEBA CARRITO CLASE PERSISTENCIA
//Persistencia persistencia = new Persistencia();
//Carrito carrito2 = new Carrito(1256, (float) 15900.5,"muy hermoso demas","compra","disponible");
//        try {
//            persistencia.ActualizarRegistroCarrito(carrito);
//            persistencia.EliminarRegistroCarrito(1256);
//            persistencia.GuardarRegistroCarrito(carrito2);
//        List <Carrito> listaCarritos = persistencia.ConsultarListaCarritos();
//            System.out.println("Consultando carrito con id: " + persistencia.ConsultarCarritoId(1256));
//            for (Carrito carrito : listaCarritos) {
//          System.out.println("Carrito: " + carrito.getIdCarrito());
//             }
//        } catch (Exception ex) {
//            Logger.getLogger(PersistenciasBD.class.getName()).log(Level.SEVERE, null, ex);
//        }

  }
}
