package com.mycompany.proyecto;

public class Entidad {
    private String nombre;
    private int id;
    private double tiempo1;
    private double tiempo2;
    private double tiempo3;
    private double tiempo4;
    private double tiempo5;
    private double tiempo6;
    private boolean inArea1;
    private boolean inArea2;

    // Constructor
    public Entidad(String nombre, int id, boolean inArea1) {
        this.nombre = nombre;
        this.id = id;
        this.inArea1 = inArea1;
    }

    // Retornos
    public String getNombre() {return nombre;}
    public int getId() {return id;}
    public double getTiempo1() {return tiempo1;}
    public double getTiempo2() {return tiempo2;}
    public double getTiempo3() {return tiempo3;}
    public double getTiempo4() {return tiempo4;}
    public double getTiempo5() {return tiempo5;}
    public double getTiempo6() {return tiempo6;}
    public boolean getInArea1() {return inArea1;}
    public boolean getInArea2() {return inArea2;}
    //Acciones
    public void cambiarNombre(String nombre) {this.nombre = nombre;}
    public void setTiempo1(double tiempo1) {this.tiempo1 = tiempo1;}
    public void setTiempo2(double tiempo2) {this.tiempo2 = tiempo2;}
    public void setTiempo3(double tiempo3) {this.tiempo3 = tiempo3;}
    public void setTiempo4(double tiempo4) {this.tiempo4 = tiempo4;}
    public void setTiempo5(double tiempo5) {this.tiempo5 = tiempo5;}
    public void setTiempo6(double tiempo6) {this.tiempo6 = tiempo6;}
    public void setInArea1(boolean inArea1) {this.inArea1=inArea1;}
    public void setInArea2(boolean inArea2) {this.inArea2=inArea2;}
}
