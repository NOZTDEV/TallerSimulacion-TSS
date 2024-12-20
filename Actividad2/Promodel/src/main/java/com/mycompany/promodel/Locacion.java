package com.mycompany.promodel;
import java.util.ArrayList;
import java.util.List;

public class Locacion {  
    private String nombre;
    private int capacidad;
    private static int unidades = 0;  // Contador de cuántas instancias de Locacion hay
    private List<Entidad> entidades;  // Lista de entidades en la locación
    // Constructor
    public Locacion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.entidades = new ArrayList<>();  // Inicializa la lista de entidades
        unidades++;  // Incrementa el número de unidades cada vez que se crea una instancia
    }
    // Métodos para agregar y eliminar entidades
    public boolean agregarEntidad(Entidad entidad) {
        if (entidades.size() < capacidad) { // Verifica si hay espacio disponible
            entidades.add(entidad);
            return true;
        } else {
            System.out.println("Capacidad máxima alcanzada en " + nombre);
            return false;
        }
    }
    public void eliminarEntidad(Entidad entidad) {
        entidades.remove(entidad);
    }
    // Getters
    public String getNombre() {
        return nombre;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public List<Entidad> getEntidades() {
        return entidades;
    }
    public Entidad getEntidadPorIndice(int indice) {
        if (indice >= 0 && indice < entidades.size()) {
            return entidades.get(indice);
        } else {
            System.out.println("Índice fuera de rango.");
            return null;  // Retorna null si el índice está fuera de los límites
        }
    }
    public static int getUnidades() {
        return unidades;
    }
    // Método para mostrar las entidades actuales en la locación
    public void mostrarEntidades() {
        System.out.println("Entidades en " + nombre + ":");
        for (Entidad entidad : entidades) {
            System.out.println(" - " + entidad.getNombre());
        }
    }
    public int getCantidadEntidades() {
        return entidades.size();
    }
}


