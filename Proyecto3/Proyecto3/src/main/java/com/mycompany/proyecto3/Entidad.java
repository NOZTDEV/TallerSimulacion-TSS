package com.mycompany.proyecto3;

public class Entidad {
    private String nombre;
    private int id;
    private double tiempo1;
    private double tiempo2;
    private double tiempo3;
    private double tiempo4;
    private double tiempo5;
    private double tiempo6;
    private double tiempo7;
    private double tiempo8;
    private double tiempo9;
    private double tiempo10;
    private double tiempo11;
    private double tiempo12;
    private double tiempo13;
    private double tiempo14;
    private boolean inArea1;
    private boolean inArea2;
    private boolean inArea3;
    private boolean inArea4;

    // Constructor
    public Entidad(String nombre, int id, boolean inArea1,boolean inArea2,boolean inArea3) {
        this.nombre = nombre;
        this.id = id;
        this.inArea1 = inArea1;
        this.inArea1 = inArea2;
        this.inArea1 = inArea3;
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
    public double getTiempo7() {return tiempo7;}
    public double getTiempo8() {return tiempo8;}
    public double getTiempo9() {return tiempo9;}
    public double getTiempo10() {return tiempo10;}
    public double getTiempo11() {return tiempo11;}
    public double getTiempo12() {return tiempo12;}
    public double getTiempo13() {return tiempo13;}
    public double getTiempo14() {return tiempo14;}
    public boolean getInArea1() {return inArea1;}
    public boolean getInArea2() {return inArea2;}
    public boolean getInArea3() {return inArea3;}
    public boolean getInArea4() {return inArea4;}
    //Acciones
    public void cambiarNombre(String nombre) {this.nombre = nombre;}
    public void setTiempo1(double tiempo1) {this.tiempo1 = tiempo1;}
    public void setTiempo2(double tiempo2) {this.tiempo2 = tiempo2;}
    public void setTiempo3(double tiempo3) {this.tiempo3 = tiempo3;}
    public void setTiempo4(double tiempo4) {this.tiempo4 = tiempo4;}
    public void setTiempo5(double tiempo5) {this.tiempo5 = tiempo5;}
    public void setTiempo6(double tiempo6) {this.tiempo6 = tiempo6;}
    public void setTiempo7(double tiempo7) {this.tiempo7 = tiempo7;}
    public void setTiempo8(double tiempo8) {this.tiempo8 = tiempo8;}
    public void setTiempo9(double tiempo9) {this.tiempo9 = tiempo9;}
    public void setTiempo10(double tiempo10) {this.tiempo10 = tiempo10;}
    public void setTiempo11(double tiempo11) {this.tiempo11 = tiempo11;}
    public void setTiempo12(double tiempo12) {this.tiempo12 = tiempo12;}
    public void setTiempo13(double tiempo13) {this.tiempo13 = tiempo13;}
    public void setTiempo14(double tiempo14) {this.tiempo14 = tiempo14;}
    public void setInArea1(boolean inArea1) {this.inArea1=inArea1;}
    public void setInArea2(boolean inArea2) {this.inArea2=inArea2;}
    public void setInArea3(boolean inArea3) {this.inArea3=inArea3;}
    public void setInArea4(boolean inArea4) {this.inArea4=inArea4;}
}
