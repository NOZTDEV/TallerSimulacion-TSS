package com.mycompany.promodel;

public class Entidad {
    private String nombre;
    private int id;
    private int tiempoLlegada;
    // Constructor
    public Entidad(String nombre, int id, int tiempoLlegada) {
    this.nombre = nombre;
    this.id = id;
    this.tiempoLlegada = tiempoLlegada;
}
    // Getter
    public String getNombre() {
        return nombre;
    }
    
    public void cambiarNombre(String nombre){
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }
    public int getTiempoLlegada() {
        return tiempoLlegada;
    }
    
    public void tiempoLlegada(){
    }
}

