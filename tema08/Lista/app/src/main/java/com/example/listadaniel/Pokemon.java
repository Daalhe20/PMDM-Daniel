package com.example.listadaniel;

public class Pokemon {
    private String titulo;
    private String anio;
    private int imagen;
    public Pokemon(String titulo, String anio, int imagen) {
        this.titulo = titulo;
        this.anio = anio;
        this.imagen = imagen;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAnio() {
        return anio;
    }
    public int getImagen() {
        return imagen;
    }
}
