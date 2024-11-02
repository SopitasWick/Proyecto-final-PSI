/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.com.mx.dominio;

/**
 *
 * @author usuario
 */
public class Carrito {
    protected int id;
    protected float costo;
    protected String descripcion;
    protected boolean disponibilidad;
    protected String estado;
    protected byte foto [];

    public Carrito() {
    }

    public Carrito(int id, float costo, String descripcion, boolean disponibilidad, String estado, byte[] foto) {
        this.id = id;
        this.costo = costo;
        this.descripcion = descripcion;
        this.disponibilidad = disponibilidad;
        this.estado = estado;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Carrito{" + "id=" + id + ", costo=" + costo + ", descripcion=" + descripcion + ", disponibilidad=" + disponibilidad + ", estado=" + estado + '}';
    }
    
}
