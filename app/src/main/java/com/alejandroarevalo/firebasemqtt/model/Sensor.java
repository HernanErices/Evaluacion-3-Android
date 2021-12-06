package com.alejandroarevalo.firebasemqtt.model;

public class Sensor {

    private String Uid;
    private String Nombre;
    private String Tipo;
    private String Valor;
    private String Ubicación;
    private String Fecha;
    private String Observacion;
    private String Hora;;


    public Sensor() {
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getUbicación() {
        return Ubicación;
    }

    public void setUbicación(String ubicación) {
        Ubicación = ubicación;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "Nombre='" + Nombre + '\'' +
                ", Valor='" + Valor + '\'' +
                '}';
    }
}



