package com.example.listadodeeventos;

import android.graphics.Bitmap;

import java.util.Date;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private float precio;
    private Date fecha;
    private int aforo;
    private transient Bitmap imagen;

    public Evento() {
    }

    public Evento(int id, String nombre, String descripcion, String direccion, float precio, Date fecha, int aforo, Bitmap imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.precio = precio;
        this.fecha = fecha;
        this.aforo = aforo;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getPrecio() {
        return  precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", aforo=" + aforo +
                ", imagen=" + imagen +
                '}';
    }
}
