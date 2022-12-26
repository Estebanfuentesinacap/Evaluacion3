package com.example.evaluacion3;

public class Usuario {
    int id;
    String Nombre, Apellido, Usuario, Palabra, Clave;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String usuario, String palabra, String clave) {
        Nombre = nombre;
        Apellido = apellido;
        Usuario = usuario;
        Palabra = palabra;
        Clave = clave;
    }

    public boolean isNull(){
        if (Nombre.equals("")&&Apellido.equals("")&&Usuario.equals("")&&Clave.equals("")&&Palabra.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Clave=" + Clave +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Palabra='" + Palabra + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPalabra() {
        return Palabra;
    }

    public void setPalabra(String palabra) {
        Palabra = palabra;
    }
    

}
