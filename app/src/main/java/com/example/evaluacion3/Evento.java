package com.example.evaluacion3;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

public class Evento {
    private int Id, Id_usuario;
    private String Titulo, Fecha, Lugar, Importancia, Observacion, Tiempo;

    public Evento(){;}

    public Evento(String tiempo, String titulo, String fecha, String lugar, String importancia, String observacion, int id_usuario) {
        Tiempo = tiempo;
        Titulo = titulo;
        Fecha = fecha;
        Lugar = lugar;
        Importancia = importancia;
        Observacion = observacion;
        Id_usuario = id_usuario;
    }

    public boolean isNull(){
        if(Titulo.equals("")&&Lugar.equals("")&&Fecha.equals("")&&Importancia.equals("")&&Observacion.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Evento{" +
                "Id=" + Id +
                ", tiempo=" + Tiempo +
                ", titulo='" + Titulo + '\'' +
                ", fecha='" + Fecha + '\'' +
                ", lugar='" + Lugar + '\'' +
                ", importancia='" + Importancia + '\'' +
                ", observacion='" + Observacion + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        Id_usuario = id_usuario;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getImportancia() {
        return Importancia;
    }

    public void setImportancia(String importancia) {
        Importancia = importancia;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }
}
