package com.mycompany.cristallball;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.TriangularDistribution;
import java.util.Random;

class Tarea {
    String nombre;
    List<Integer> predecesores;  // Índice del predecesor
    int leadLag;
    int duracionOptimista;
    int duracionEsperada;
    int duracioPesimista;
    int valorSimular;
    int est, eft, lst, lft, slack;
    boolean esRutaCritica;
    double probabilidadPC;
    
    // Constructor
    public Tarea(String nombre, List<Integer> predecesores, int leadLag, int duracionOptimista, int duracionEsperada
            , int duracioPesimista) {
        this.nombre = nombre;
        this.predecesores = predecesores;
        this.leadLag = leadLag;
        this.duracionOptimista = duracionOptimista;
        this.duracionEsperada = duracionEsperada;
        this.duracioPesimista = duracioPesimista;
    }
    
    public Tarea(String nombre, List<Integer> predecesores, int leadLag, int duracionOptimista, int duracionEsperada
            , int duracioPesimista, int valorSimular) {
        this.nombre = nombre;
        this.predecesores = predecesores;
        this.leadLag = leadLag;
        this.duracionOptimista = duracionOptimista;
        this.duracionEsperada = duracionEsperada;
        this.duracioPesimista = duracioPesimista;
        this.valorSimular = valorSimular;
    }
    
    public int getLeadLag(){
        return leadLag;
    }
    
    public void calcularValorSimulado() {
        if(predecesores.isEmpty()){
            valorSimular = 0;
        }else{
            TriangularDistribution triangularDistribution = new TriangularDistribution(
                    duracionOptimista, duracionEsperada, duracioPesimista);
            valorSimular = (int) triangularDistribution.sample();
        }
    }
    
    public double getValorSimulado(){
        return valorSimular;
    }
    
    // Método para calcular el EST y EFT
    public void calcularESTyEFT(List<Integer> predecesores1) {//lista de EFTant
        if(predecesores1.isEmpty()){
            est = 0;
            eft = 0;
        }else{
            int tamanio = predecesores1.size();
            if(tamanio == 1){
                est = predecesores1.get(0) + leadLag; // Tiempo de inicio temprano
                eft = est + valorSimular; // Tiempo de finalización temprano
            }else{
                int maximo = predecesores1.get(0);
                for (int i = 1; i < predecesores1.size(); i++) {
                    if (predecesores1.get(i) > maximo) {
                        maximo = predecesores1.get(i);
                    }
                }
                est = maximo + leadLag;
                eft = est + valorSimular;
            }
        }
    }
    
    public int getEST(){
        return est;
    }
    
    public int getEFT(){
        return eft;
    }

    // Método para calcular el LST y LFT
    public void calcularLSTyLFT(List<Integer> antecesor1) {
        if(antecesor1.isEmpty()){
            lst = est;
            lft = lst + valorSimular;
        }else{
            int tamanio = antecesor1.size();
            if(tamanio == 1){
                lst = antecesor1.get(0) - valorSimular;
                lft = lst + valorSimular;
            }else{
                int minimo = antecesor1.get(0);
                for (int i = 1; i < antecesor1.size(); i++) {
                    if (antecesor1.get(i) < minimo) {
                        minimo = antecesor1.get(i);
                    }
                }
                lst = minimo - valorSimular;
                lft = lst + valorSimular;
            }
        }
    }
    
    public int getLFT(){
        return lft;
    }
    
    public int getLST(){
        return lst;
    }

    public void calcularSlack() {
        slack = lft - eft;
    }
    
    public int getSlack(){
        return slack;
    }
    
    public void critica() {
        
        if(slack <= 0) {
            esRutaCritica = true;
        } else {
            esRutaCritica = false;
        }
    }
    
    public boolean getCritica(){
        return esRutaCritica;
    }
    
    public void generarProbabilidadAleatoria() {
        Random random = new Random();
        probabilidadPC = random.nextInt(100) + 1;
    }
    
    public double getProbabilidad(){
        return probabilidadPC;
    }
    
    public void calcularDatosIni(List<Integer> EFTant){
        calcularESTyEFT(EFTant);
    }
    
    public void calcularDatos(List<Integer> EFTant, int LFTant){
        calcularValorSimulado();
        calcularESTyEFT(EFTant);
    }
    
    public void calcularDatosV(List<Integer> LFTant){
        calcularLSTyLFT(LFTant);
        calcularSlack();
        critica();
        generarProbabilidadAleatoria();
    }
    
    //Caso especial 1
    public void calcularDatosV1(List<Integer> LFTant, int lag){
        calcularLSTyLFT1(LFTant, lag);
        calcularSlack();
        critica();
    }
    public void calcularLSTyLFT1(List<Integer> antecesor1, int lag) {
        lst = antecesor1.get(0) - lag;
        lft = lst + valorSimular;
    }
    //Caso especial 2
    public void calcularDatosV2(List<Integer> LFTant, int lag){
        calcularLSTyLFT2(LFTant, lag);
        calcularSlack();
        critica();
    }
    public void calcularLSTyLFT2(List<Integer> antecesor1, int lag) {
        lst = antecesor1.get(0) - valorSimular - lag;
        lft = lst + valorSimular;
    }
    //Caso especial 3
    public void calcularDatosV3(int min){
        calcularLSTyLFT3(min);
        calcularSlack();
        critica();
    }
    public void calcularLSTyLFT3(int lag) {
        lst = lag;
        lft = lst + valorSimular;
    }
    
    public String obtenerResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append(String.format("%-30s%-30d%-30d%-20d%-20d%-25d%-20d%-25d%-20d%-25d%-25d%-20s%-20.2f\n", 
            nombre, leadLag, duracionOptimista, duracionEsperada, duracioPesimista, valorSimular, 
            est, eft, lst, lft, slack, esRutaCritica ? "Sí" : "No", probabilidadPC));

        return resumen.toString();
    }
}