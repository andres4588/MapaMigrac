package com.example.sistemas.mapamigrac.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sistemas on 27/10/17.
 */

public class Oficina {
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("horario_de_atencion")
    @Expose
    private String horarioDeAtencion;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("regional")
    @Expose
    private String regional;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("latitud")
    @Expose
    private double latitud;
    @SerializedName("longitud")
    @Expose
    private double longitud;


    public Oficina(String direccion, String horarioDeAtencion, String nombre, String regional, String telefono, String tipo, double latitud, double longitud) {
        super();
        this.direccion = direccion;
        this.horarioDeAtencion = horarioDeAtencion;
        this.nombre = nombre;
        this.regional = regional;
        this.telefono = telefono;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorarioDeAtencion() {
        return horarioDeAtencion;
    }

    public void setHorarioDeAtencion(String horarioDeAtencion) {
        this.horarioDeAtencion = horarioDeAtencion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


}


