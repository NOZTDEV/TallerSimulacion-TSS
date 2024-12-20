package com.mycompany.proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.math3.distribution.ExponentialDistribution;

public class Ejecucion1 extends Thread {
    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private int duracion;
    private int delay;
    //Variables a recibir
    private int Nreparadores;
    private int Nreservas;
    private int Nmontacargas;
    private int mediaOperacion;
    private int mediaReparacion;
    private SimulacionPanel simPanel;
    
    DecimalFormat df = new DecimalFormat("#.##"); //variabla para 2 decimales

    public Ejecucion1(JTextArea textAreaA, JTextArea textAreaB, int duracion, int delay, int Nreparadores, int Nreservas, int Nmontacargas, int op, int re){ //constructor
        this.textAreaA = textAreaA;
        this.textAreaB = textAreaB;
        this.duracion = duracion;
        this.delay = delay;
        this.Nreparadores = Nreparadores;
        this.Nreservas = Nreservas;
        this.Nmontacargas = Nmontacargas;
        this.mediaOperacion = op;
        this.mediaReparacion = re;
        initGrafico(); //iniciar grafica
    }
    
    private void initGrafico() {
        JFrame frame = new JFrame("Grafica de motores");
        simPanel = new SimulacionPanel();
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(simPanel);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        int tiempoSimulacion = duracion; //convertir llamamos el tiempo de duración
        //Locaciones
        List<Entidad> estirocortadoras = new  ArrayList<>(); //Locacion estirocortadoras
        List<Entidad> espera = new ArrayList<>(); //Locacion area de espera
        List<Entidad> reparacion = new ArrayList<>(); //Locacion area de reparacion
        List<Entidad> almacen = new ArrayList<>(); //Locacion area de almacen
        List<Entidad> montacarga1 = new ArrayList<>(); //Recurso montacarga 1
        List<Entidad> montacarga2 = new ArrayList<>(); //Recurso montacarga 2
        //Variables para calculas los datos
        double toneladasEsp = 0; //toneladas teoricas
        double toneladasRea = 0; //toneladas reales
        int estirocortadoraMin = 0; //Para guardar el minimo de motores
        int esperaMax = 0; //Para maximo de motores en area espera
        List<Double> porcentajeUti = new ArrayList<>();//guaradar porcentajes
        double porUti = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti2 = new ArrayList<>();//guaradar porcentajes
        double porUti2 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti3 = new ArrayList<>();//guaradar porcentajes
        double porUti3 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti4 = new ArrayList<>();//guaradar porcentajes
        double porUti4 = 0;//mostrar porcentaje al final
        int contadorArea1L = 0;
        int contadorArea1V = 0;
        int contadorArea2L = 0;
        int contadorArea2V = 0;
        int contadorArea3L = 0;
        int contadorArea3V = 0;
        int contadorArea4L = 0;
        int contadorArea4V = 0;
        
        for(int i = 1; i <= tiempoSimulacion; i++){//simular las horas
            //Arribos
            if(i == 1){//Es inicio de simulacion
                arriboEstirocortadoras(50, estirocortadoras); //Arribo de 50 motores de recerva a estirocortadoras
                arriboAlmacen(Nreservas, almacen, 50); //Arribo de 3 motores reparados a almacen
                textAreaA.append("Motores en estirocortadora: "+estirocortadoras.size()+"\n"); //muestra motores al inicio
                textAreaA.append("Motores en Almacen: "+almacen.size()+"\n"); //muestra motores en almacen al inicio
                estirocortadoraMin = estirocortadoras.size();
            }
            
            //Calculo de toneladas esperadas y reales
            toneladasEsp = dosDecimales(toneladasEsp + 10274.0);//Aumentamos las toneladas teoricas cada hora
            toneladasRea = dosDecimales(toneladasRea + calcularCantidadReal(estirocortadoras.size()));//aumentamos las toneladas realaes cada hora
            //Calcular minimos y maximos
            if(estirocortadoras.size()< estirocortadoraMin){//si el numero de motores es menor al anterior lo guardamos
                estirocortadoraMin = estirocortadoras.size();
            }
            if(espera.size()> esperaMax){//si el numero de motores es mayor al anterior lo guardamos
                esperaMax = espera.size();
            }
            //Calcular porcentajes de utilización
            porcentajeUti.add(dosDecimales((((double)estirocortadoras.size())/50)*100));
            porcentajeUti2.add(dosDecimales((((double)espera.size())/100)*100));
            porcentajeUti3.add(dosDecimales((((double)reparacion.size())/Nreparadores)*100));
            porcentajeUti4.add(dosDecimales((((double)almacen.size())/50)*100));
            //calcular si esta lleno o vacio
            if(estirocortadoras.size() == 50){contadorArea1L++;}
            if(espera.size() == 100){contadorArea2L++;}
            if(reparacion.size() == Nreparadores){contadorArea3L++;}
            if(almacen.size() == 50){contadorArea4L++;}
            if(estirocortadoras.isEmpty()){contadorArea1V++;}
            if(espera.isEmpty()){contadorArea2V++;}
            if(reparacion.isEmpty()){contadorArea3V++;}
            if(almacen.isEmpty()){contadorArea4V++;}
            simPanel.actuzalizarToneladas(toneladasEsp,toneladasRea);
            //Simular entre hora
            if (i != tiempoSimulacion) { // Si no es la última hora
                BigDecimal j = new BigDecimal("0.00"); //valor preciso
                BigDecimal one = new BigDecimal("1.0"); //valor preciso
                
                while (j.compareTo(one) < 0) { //j es menor a one
                    BigDecimal horaExacta = new BigDecimal(i).add(j).setScale(2, RoundingMode.HALF_UP); //i+j para hora
                    double tiempoActual = horaExacta.doubleValue(); //convierte a doble y guardar el valor de hora actual
                    //textAreaA.append("Hora " + tiempoActual+"\n"); //imprime hora
                   
                    //Simulacion en almacen
                    if(!almacen.isEmpty()){ //esta no vacia
                        for(int a = 1; a <= almacen.size(); a++){ //recorremos cada motor
                            Entidad motor = almacen.get(a - 1); //llamamos al motor que toca
                            if(estirocortadoras.size()<50){ //hay espacio en estiraje
                                if(montacarga2.isEmpty()){ //montacargas 2 esta libre
                                    almacen.remove(motor); //eliminamos de almacen
                                    montacarga2.add(motor); //esta en montacargas 2
                                    simPanel.llegadaMotor("montacarga2");
                                    simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga2", 160, 370);
                                    try {
                                        Thread.sleep(100); // Agregar retraso de 1 segundo
                                    } catch (InterruptedException v) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                    montacarga2.remove(motor); //eliminalso demontacargas 2
                                    motor.setInArea1(true); //motor esta en estiraje verdadero
                                    estirocortadoras.add(motor); //agragamos motor a estiraje.
                                    simPanel.llegadaMotor("estirocortadora");
                                    simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga2", 160, 170);
                                    try {
                                        Thread.sleep(100); // Agregar retraso de 1 segundo
                                    } catch (InterruptedException v) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    
                    //Simulacion en reparacion
                    if(!reparacion.isEmpty()){//reparacion esta no vacia
                        for(int r = 1; r<=reparacion.size();r++){//recoremos cada motor
                            Entidad motor = reparacion.get(r - 1);//llamamos al motor que toca
                            if(motor.getTiempo2() == tiempoActual){//si el tiempo de salida es el actual
                                textAreaA.append("Motor " + motor.getId() + " reparado " + motor.getTiempo2()+"\n"); //mensaje reaparacion
                                motor.setTiempo2(0);//receteamos el tiempo de reparacion
                                reparacion.remove(motor);//eliminamos de reparacion
                                if(almacen.size() < 50){ //hay espacio en almacen
                                    montacarga2.add(motor);//va a montacargas 2
                                    simPanel.llegadaMotor("montacarga2");
                                    simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga2", 460, 370);
                                    try {
                                        Thread.sleep(100); // Agregar retraso de 1 segundo
                                    } catch (InterruptedException v) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                    montacarga2.remove(motor); //sale de montacargas2
                                    almacen.add(motor);//llega al almacen
                                    simPanel.llegadaMotor("almacen");
                                    simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga2", 160, 370);
                                    try {
                                        Thread.sleep(100); // Agregar retraso de 1 segundo
                                    } catch (InterruptedException v) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    
                    
                    //Simulacion en espera
                    if(!espera.isEmpty()){//espera esta no vacio
                        for(int e = 1; e<=espera.size();e++){//recorremos cada motor
                            Entidad motor = espera.get(e - 1);//llamamos al motor que toca
                            //reparacion en area de espera
                            if(motor.getInArea2() == true){//si motor esta esperando
                                if(reparacion.size() < Nreparadores){//hay espacio en reparacion
                                    textAreaA.append("Motor "+ motor.getId() + " sale de del area de espera\n");
                                    motor.setInArea2(false);//motor ya no esta esperando
                                    if(montacarga1.isEmpty()){ //montacarga esta vacio
                                        espera.remove(motor); //motor sale de espera
                                        montacarga1.add(motor); //motor entra a montacarga1
                                        simPanel.llegadaMotor("montacarga1");
                                        simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga1", 460, 120);
                                        try {
                                        Thread.sleep(100); // Agregar retraso de 1 segundo
                                        } catch (InterruptedException v) {
                                            Thread.currentThread().interrupt();
                                            return;
                                        }
                                    }
                                    montacarga1.remove(motor); //motor sale de montacarga
                                    reparacion.add(motor); //motor entra a reparacion
                                    simPanel.llegadaMotor("reparacion");
                                    simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga1", 460, 300);
                                    try {
                                        Thread.sleep(100); // Agregar retraso de 1 segundo
                                    } catch (InterruptedException v) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                    repararMotor(motor, tiempoActual); //motor se repara
                                }
                            }
                        }
                    }
                    
                    //Simulamos para estirocortadoras
                    for(int m = 1; m <= estirocortadoras.size(); m++){//recorremos cada motor
                        //Damos los tiempos de funcionamiento
                        Entidad motor = estirocortadoras.get(m - 1); // llamamos al motor que toca
                        if(motor.getTiempo1()==0 && motor.getInArea1()==true){ //motor no tiene tiempo operacion y esta en estiraje
                            operacionMotor(motor, tiempoActual);//Damos tiempo de operacion
                        }
                        //Simular fallos
                        if(motor.getTiempo1() == tiempoActual){//llega el tiempo del motor de fallar
                            
                            textAreaA.append("motor " + motor.getId()+ " se arruino\n");
                            motor.setTiempo1(0); //motor deja de funcionar
                            if(montacarga1.isEmpty()){ //montacarga esta desocupado
                                estirocortadoras.remove(motor); //Se elimina de area estiro
                                montacarga1.add(motor); //se da al montacarga1
                                simPanel.llegadaMotor("montacarga1");
                                simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga1", 160, 120);
                                try {
                                    Thread.sleep(100); // Agregar retraso de 1 segundo
                                } catch (InterruptedException v) {
                                    Thread.currentThread().interrupt();
                                    return;
                                }
                                motor.setInArea1(false); //motor ya no esta en area estiro
                                motor.cambiarNombre("motor arruinado"); //se cambia el nombre a motor arruinado
                                if(espera.size()<100){//hay espacio en espera
                                    montacarga1.remove(motor); //motor sale de montacarga1
                                    espera.add(motor); //motor entra a espera
                                    simPanel.llegadaMotor("espera");
                                    simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga1", 460, 120);
                                    try {
                                        Thread.sleep(100); // Agregar retraso de 1 segundo
                                    } catch (InterruptedException v) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                    if(reparacion.size() < Nreparadores){ //Hay espacio en reparacion
                                        if(montacarga1.isEmpty()){ //montacarga esta vacio
                                            espera.remove(motor); //motor sale de espera
                                            montacarga1.add(motor); //motor entra a montacarga1
                                            simPanel.llegadaMotor("montacarga1");
                                        }
                                        montacarga1.remove(motor); //motor sale de montacarga
                                        reparacion.add(motor); //motor entra a reparacion
                                        simPanel.llegadaMotor("reparacion");
                                        simPanel.actualizarEstadoAreas(estirocortadoras, espera, reparacion, almacen, "montacarga1", 460, 300);
                                        try {
                                            Thread.sleep(100); // Agregar retraso de 1 segundo
                                        } catch (InterruptedException v) {
                                            Thread.currentThread().interrupt();
                                            return;
                                        }
                                        repararMotor(motor, tiempoActual); //reapara el motor
                                    }else{
                                        motor.setInArea2(true);//motor esta esperando
                                        textAreaA.append("Motor " + motor.getId() + " esta en area de espera\n");
                                    }
                                }
                            }
                        }
                    }
                    
                    j = j.add(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP); //j++
                    try {
                        Thread.sleep(delay); // Agregar retraso de 1 segundo
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
        //Impresion de datos
        textAreaB.append("Resultados: \n");
        textAreaB.append("Montacargas utilizados: " + Nmontacargas +"\n");
        textAreaB.append("Toneladas teoricas: " + df.format(toneladasEsp)+"\n");
        textAreaB.append("Toneladas realaes: " + df.format(toneladasRea)+"\n");
        textAreaB.append("Cantidad de motores en locaciones: \n");
        textAreaB.append("Motores en estirocortadoras: " + estirocortadoras.size()+"\n");
        //mostrarMotores(estirocortadoras);
        textAreaB.append("Motores en espera: " + espera.size()+"\n");
        textAreaB.append("Motores en reparacion: " + reparacion.size()+"\n");
        textAreaB.append("Motores en almacen: " + almacen.size()+"\n");
        textAreaB.append("Motores minimos en area de estirocortadoras: " + estirocortadoraMin+"\n");
        textAreaB.append("Motores maximos en area de espera: " + esperaMax+"\n");
        //mostrarMotores(almacen);
        textAreaB.append("Porcentajes: \n");
        porUti = dosDecimales(calcularPorUti(porcentajeUti));
        textAreaB.append("Porcentaje de utilizacion estirocortadoras: " + porUti + "%\n");
        porUti2 = dosDecimales(calcularPorUti(porcentajeUti2));
        textAreaB.append("Porcentaje de utilizacion area espera: " + porUti2 + "%\n");
        porUti3 = dosDecimales(calcularPorUti(porcentajeUti3));
        textAreaB.append("Porcentaje de utilizacion reparacion: " + porUti3 + "%\n");
        porUti4 = dosDecimales(calcularPorUti(porcentajeUti4));
        textAreaB.append("Porcentaje de utilizacion reserva: " + porUti4 + "%\n");
        double area1L = dosDecimales(((double)contadorArea1L/tiempoSimulacion)*100);
        double area1V = dosDecimales(((double)contadorArea1V/tiempoSimulacion)*100);
        double area2L = dosDecimales(((double)contadorArea2L/tiempoSimulacion)*100);
        double area2V = dosDecimales(((double)contadorArea2V/tiempoSimulacion)*100);
        double area3L = dosDecimales(((double)contadorArea3L/tiempoSimulacion)*100);
        double area3V = dosDecimales(((double)contadorArea3V/tiempoSimulacion)*100);
        double area4L = dosDecimales(((double)contadorArea4L/tiempoSimulacion)*100);
        double area4V = dosDecimales(((double)contadorArea4V/tiempoSimulacion)*100);
        textAreaB.append("Area de estirocortadoras esta lleno el: " + dosDecimales(((double)contadorArea1L/tiempoSimulacion)*100) + "%\n");
        textAreaB.append("Area de estirocortadoras esta vacio el: " + dosDecimales(((double)contadorArea1V/tiempoSimulacion)*100) + "%\n");
        textAreaB.append("Area de espera esta lleno el: " + dosDecimales(((double)contadorArea2L/tiempoSimulacion)*100) + "%\n");
        textAreaB.append("Area de espera esta vacio el: " + dosDecimales(((double)contadorArea2V/tiempoSimulacion)*100) + "%\n");
        textAreaB.append("Area de reparacion esta lleno el: " + dosDecimales(((double)contadorArea3L/tiempoSimulacion)*100) + "%\n");
        textAreaB.append("Area de reparacion esta vacio el: " + dosDecimales(((double)contadorArea3V/tiempoSimulacion)*100) + "%\n");
        textAreaB.append("Area de almacen esta lleno el: " + dosDecimales(((double)contadorArea4L/tiempoSimulacion)*100) + "%\n");
        textAreaB.append("Area de almacen esta vacio el: " + dosDecimales(((double)contadorArea4V/tiempoSimulacion)*100) + "%\n");
        mostrarResultadosEnTabla(Nmontacargas, dosDecimales(toneladasEsp/1000), dosDecimales(toneladasRea/1000), estirocortadoras.size(), espera.size(),
                reparacion.size(), almacen.size(), estirocortadoraMin, esperaMax, porUti, porUti2, porUti3, porUti4,
                area1L, area1V, area2L, area2V, area3L, area3V, area4L, area4V, tiempoSimulacion);
        BarChartExample example = new BarChartExample(area1L, area1V, area2L, area2V, area3L, area3V, area4L, area4V);
        example.setVisible(true);
    }
    
    class SimulacionPanel extends JPanel {
        private Area estirocortadoras = new Area("Área Estirocortadoras", 50, 50, 100, 100, Color.GREEN);
        private Area espera = new Area("Área Espera", 500, 50, 100, 100, Color.GREEN);
        private Area reparacion = new Area("Área Reparacion", 500, 300, 100, 100, Color.GREEN);
        private Area almacen = new Area("Área Almacen", 50, 300, 100, 100, Color.GREEN);
        private Area montacarga1 = new Area("Mon 1", 160, 120, 30, 30, Color.DARK_GRAY);
        private Area montacarga2 = new Area("Mon 2", 460, 370, 30, 30, Color.YELLOW);
        private Area contadorTT = new Area("Toneladas totales", 650, 50, 150, 30, Color.lightGray);
        private Area contadorTR = new Area("Toneladas reales", 650, 150, 150, 30, Color.lightGray);
        private BufferedImage motorImage;
        
        public SimulacionPanel() {
            this.setPreferredSize(new Dimension(800, 600));
            this.setBackground(Color.WHITE);

            // Cargar la imagen al inicializar el panel
            try {
                motorImage = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/motor.jpg")); // Cambia "path/to/motorImage.png" por la ruta de tu imagen
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Método para actualizar el estado de cada área según el tamaño de las listas
        public void actualizarEstadoAreas(List<Entidad> estirocortadorasList, List<Entidad> esperaList, 
                List<Entidad> reparacionList, List<Entidad> almacenList, String montacarga, int x, int y) {
            //System.out.println("Montacargas 1: " + montacargas1.size() + ", Montacargas 2: " + montacargas2.size());
            estirocortadoras.setContador(estirocortadorasList.size());
            espera.setContador(esperaList.size());
            reparacion.setContador(reparacionList.size());
            almacen.setContador(almacenList.size());
            if (montacarga.equals("montacarga1")) {
                simPanel.moverMontacarga(simPanel.montacarga1, x, y);
            } else if (montacarga.equals("montacarga2")) {
                simPanel.moverMontacarga(simPanel.montacarga2, x, y);
            }

            repaint(); // Actualiza la gráfica con los nuevos valores
        }
        
        public void moverMontacarga(Area montacarga, int nuevaX, int nuevaY) {
            montacarga.setX(nuevaX);
            montacarga.setY(nuevaY);
            repaint(); // Actualiza la gráfica
        }
        
        public void actuzalizarToneladas(double esperada, double real){
            contadorTT.setContadorD(dosDecimales(esperada/1000));
            contadorTR.setContadorD(dosDecimales(real/1000));
            repaint();
        }
        
        public void llegadaMotor(String nombre) {
        // Cambia la imagen de fondo del área seleccionada y configura el temporizador para volver al color original
        switch (nombre) {
            case "estirocortadora":
                estirocortadoras.setBackgroundImage(motorImage);
                repaint();
                new Timer(1000, e -> {
                    estirocortadoras.setBackgroundImage(null); // Quita la imagen de fondo
                    repaint();
                }).start();
                break;
            case "espera":
                espera.setBackgroundImage(motorImage);
                repaint();
                new Timer(1000, e -> {
                    espera.setBackgroundImage(null);
                    repaint();
                }).start();
                break;
            case "reparacion":
                reparacion.setBackgroundImage(motorImage);
                repaint();
                new Timer(1000, e -> {
                    reparacion.setBackgroundImage(null);
                    repaint();
                }).start();
                break;
            case "almacen":
                almacen.setBackgroundImage(motorImage);
                repaint();
                new Timer(1000, e -> {
                    almacen.setBackgroundImage(null);
                    repaint();
                }).start();
                break;
            case "montacarga1":
                montacarga1.setBackgroundImage(motorImage);
                repaint();
                new Timer(2000, e -> {
                    montacarga1.setBackgroundImage(null);
                    repaint();
                }).start();
                break;
            case "montacarga2":
                montacarga2.setBackgroundImage(motorImage);
                repaint();
                new Timer(2000, e -> {
                    montacarga2.setBackgroundImage(null);
                    repaint();
                }).start();
                break;
            default:
                return;
        }
    }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawArea(g, estirocortadoras);
            drawArea(g, espera);
            drawArea(g, reparacion);
            drawArea(g, almacen);
            drawArea(g, montacarga1);
            drawArea(g, montacarga2);
            drawArea(g, contadorTT);
            drawArea(g, contadorTR);
        }

        private void drawArea(Graphics g, Area area) {
            if (area.getBackgroundImage() != null) {
                g.drawImage(area.getBackgroundImage(), area.getX(), area.getY(), area.getWidth(), area.getHeight(), null);
            } else {
                g.setColor(area.getColor());
                g.fillRect(area.getX(), area.getY(), area.getWidth(), area.getHeight());
            }

            g.setColor(Color.BLACK);
            g.drawString(area.getName(), area.getX() + 5, area.getY() - 5);

            if (area == estirocortadoras || area == espera || area == reparacion || area == almacen) {
                g.drawString("Motores: " + area.getContador(), area.getX() + 5, area.getY() + 20);
            }
            if (area == contadorTT || area == contadorTR){
                g.drawString("Toneladas: " + area.getContadorD(), area.getX() + 5, area.getY() + 20);
            }
        }

        private static class Area {
            private String name;
            private int x, y, width, height;
            private Color color;
            private int contador;
            private double contadorD;
            private BufferedImage backgroundImage; // Añadir imagen de fondo

            public Area(String name, int x, int y, int width, int height, Color color) {
                this.name = name;
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.color = color;
            }

            public String getName() { return name; }
            public int getX() { return x; }
            public int getY() { return y; }
            public int getWidth() { return width; }
            public int getHeight() { return height; }
            public Color getColor() { return color; }
            public int getContador() { return contador; }
            public double getContadorD() { return contadorD; }
            public void setContador(int contador) { this.contador = contador; }
            public void setContadorD(double contadorD) { this.contadorD = contadorD; }
            public void setX(int x) { this.x = x; }
            public void setY(int y) { this.y = y; }
            public void setColor(Color color) { this.color = color; }

            public BufferedImage getBackgroundImage() { return backgroundImage; }
            public void setBackgroundImage(BufferedImage image) { this.backgroundImage = image; }
        }
    }

    public void arriboEstirocortadoras (int capacidad, List<Entidad> locacion){
        for(int i = 1; i <= capacidad; i++){//creamos la cantidad de motores requerida
            Entidad entidad = new Entidad("motor reserva", i, true);//con sus datos
            locacion.add(entidad);//y los ponemos en estiraje
        }
    }
    public void arriboAlmacen (int capacidad, List<Entidad> locacion, int idAnterior){
        for(int i = 1; i <= capacidad; i++){//creamos la cantidad de motores requerida
            Entidad entidad = new Entidad("motor reparado", idAnterior+i, false);//con sus datos
            locacion.add(entidad);//y los ponemos en la almacen
        }
    }
    public void operacionMotor(Entidad motor, double tiempoActual){
        ExponentialDistribution distribution = new ExponentialDistribution(mediaOperacion); //iniciamos la distribucion exponenecial
        double expoPrueba = distribution.sample()+1; //guardamos el valor de la distribución
        BigDecimal roundedExpoPrueba = new BigDecimal(expoPrueba + tiempoActual).setScale(2, RoundingMode.HALF_UP);//lo convertimos a 2 decimales
        double tiempoOperacion = roundedExpoPrueba.doubleValue();//lo convertimos en double para calculos
        motor.setTiempo1(tiempoOperacion); // le damos el valor al motor
        textAreaA.append("Motor " + motor.getId() + " funciona " + motor.getTiempo1()+"\n");
    }
    public void repararMotor (Entidad motor, double valorActual){
        ExponentialDistribution distribution = new ExponentialDistribution(mediaReparacion); //iniciamos la distribucion exponenecial
        double reparacionP = distribution.sample()+1; //guardamos el valor de la distribución
        BigDecimal roundedExpoPrueba = new BigDecimal(reparacionP).setScale(2, RoundingMode.HALF_UP);//lo convertimos a 2 decimales
        double tiempoReparacion = roundedExpoPrueba.doubleValue();//lo convertimos en double para calculos
        motor.setTiempo2(tiempoReparacion);//guardamos el tiempo de reparacion
        textAreaA.append("Motor " + motor.getId() + " tiempo de reparacion " + motor.getTiempo2()+"\n");//msotramos el tiempo reparacion
        BigDecimal horasalidaP = new BigDecimal(tiempoReparacion+valorActual).setScale(2, RoundingMode.HALF_UP);//calculamos tiempo salida
        double tiempoSalida = horasalidaP.doubleValue();//guardamos tiempo salida
        motor.setTiempo2(tiempoSalida);//lo damos el tiempo salida al motor
        textAreaA.append("Motor " + motor.getId() + " saldra en la hora " + motor.getTiempo2()+"\n"); //mostramos a que hora saldra
    }
    public double dosDecimales(double numero){
        BigDecimal numeroNo = new BigDecimal(numero).setScale(2, RoundingMode.HALF_UP);//Solo dos decimales
        double numero2 = numeroNo.doubleValue(); //guarda el valor
        return numero2;//retorna el valor
    }
    public void mostrarMotores (List<Entidad> motores){
        for(int i = 1; i<=motores.size(); i++){ //recorre todos los motores
            textAreaA.append("En lista motor " + motores.get(i-1).getId()+"\n"); //imprime los motores
        }
    }
    public double calcularPorUti(List<Double> listaPorcentajes) {
        if (listaPorcentajes.isEmpty()) {
            return 0.0; // Devuelve 0 si la lista está vacía
        }
        double sumaPorcentajes = 0.0;
        for (double porcentaje : listaPorcentajes) {
            sumaPorcentajes += porcentaje;
        }
        // Calcula el promedio de la lista de porcentajes
        return sumaPorcentajes / listaPorcentajes.size();
    }
    public double calcularCantidadReal(int motores){
        if(motores == 50){
            return dosDecimales(10274.0);
        }else if (motores == 49){
            return dosDecimales(10274.0-(205.48*1));
        }else if (motores == 48){
            return dosDecimales(10274.0-(205.48*2));
        }else if (motores == 47){
            return dosDecimales(10274.0-(205.48*3));
        }else if (motores == 46){
            return dosDecimales(10274.0-(205.48*4));
        }else if (motores == 45){
            return dosDecimales(10274.0-(205.48*5));
        }else if (motores == 44){
            return dosDecimales(10274.0-(205.48*6));
        }else if (motores == 43){
            return dosDecimales(10274.0-(205.48*7));
        }else if (motores == 42){
            return dosDecimales(10274.0-(205.48*8));
        }else if (motores == 41){
            return dosDecimales(10274.0-(205.48*9));
        }else if (motores == 40){
            return dosDecimales(10274.0-(205.48*10));
        }else if (motores == 39){
            return dosDecimales(10274.0-(205.48*11));
        }else if (motores == 38){
            return dosDecimales(10274.0-(205.48*12));
        }else if (motores == 37){
            return dosDecimales(10274.0-(205.48*13));
        }else if (motores == 36){
            return dosDecimales(10274.0-(205.48*14));
        }else if (motores == 35){
            return dosDecimales(10274.0-(205.48*15));
        }else if (motores == 34){
            return dosDecimales(10274.0-(205.48*16));
        }else if (motores == 33){
            return dosDecimales(10274.0-(205.48*17));
        }else if (motores == 32){
            return dosDecimales(10274.0-(205.48*18));
        }else if (motores == 31){
            return dosDecimales(10274.0-(205.48*19));
        }else if (motores == 30){
            return dosDecimales(10274.0-(205.48*20));
        }else if (motores == 29){
            return dosDecimales(10274.0-(205.48*21));
        }else if (motores == 28){
            return dosDecimales(10274.0-(205.48*22));
        }else if (motores == 27){
            return dosDecimales(10274.0-(205.48*23));
        }else if (motores == 26){
            return dosDecimales(10274.0-(205.48*24));
        }else if (motores == 25){
            return dosDecimales(10274.0-(205.48*25));
        }else if (motores == 24){
            return dosDecimales(10274.0-(205.48*26));
        }else if (motores == 23){
            return dosDecimales(10274.0-(205.48*27));
        }else if (motores == 22){
            return dosDecimales(10274.0-(205.48*28));
        }else if (motores == 21){
            return dosDecimales(10274.0-(205.48*29));
        }else if (motores == 20){
            return dosDecimales(10274.0-(205.48*30));
        }else if (motores == 19){
            return dosDecimales(10274.0-(205.48*31));
        }else if (motores == 18){
            return dosDecimales(10274.0-(205.48*32));
        }else if (motores == 17){
            return dosDecimales(10274.0-(205.48*33));
        }else if (motores == 16){
            return dosDecimales(10274.0-(205.48*34));
        }else if (motores == 15){
            return dosDecimales(10274.0-(205.48*35));
        }else if (motores == 14){
            return dosDecimales(10274.0-(205.48*36));
        }else if (motores == 13){
            return dosDecimales(10274.0-(205.48*37));
        }else if (motores == 12){
            return dosDecimales(10274.0-(205.48*38));
        }else if (motores == 11){
            return dosDecimales(10274.0-(205.48*39));
        }else if (motores == 10){
            return dosDecimales(10274.0-(205.48*40));
        }else if (motores == 9){
            return dosDecimales(10274.0-(205.48*41));
        }else if (motores == 8){
            return dosDecimales(10274.0-(205.48*42));
        }else if (motores == 7){
            return dosDecimales(10274.0-(205.48*43));
        }else if (motores == 6){
            return dosDecimales(10274.0-(205.48*44));
        }else if (motores == 5){
            return dosDecimales(10274.0-(205.48*45));
        }else if (motores == 4){
            return dosDecimales(10274.0-(205.48*46));
        }else if (motores == 3){
            return dosDecimales(10274.0-(205.48*47));
        }else if (motores == 2){
            return dosDecimales(10274.0-(205.48*48));
        }else if (motores == 1){
            return dosDecimales(10274.0-(205.48*49));
        }else{
            return dosDecimales(10274.0-(205.48*50));
        }
    }
    
    public class ResultadosFrame extends JFrame {

        public ResultadosFrame(
            int Nmontacargas, double toneladasEsp, double toneladasRea,
            int estirocortadorasSize, int esperaSize, int reparacionSize, int almacenSize,
            int estirocortadoraMin, int esperaMax,
            double porcentajeUti, double porcentajeUti2, double porcentajeUti3, double porcentajeUti4,
            double contadorArea1L, double contadorArea1V,
            double contadorArea2L, double contadorArea2V,
            double contadorArea3L, double contadorArea3V,
            double contadorArea4L, double contadorArea4V,
            int tiempoSimulacion) {

            // Configura el JFrame
            setTitle("Resultados");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Crea el modelo de tabla y JTable
            String[] columnNames = {"Descripción", "Valor"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Agrega filas al modelo
            model.addRow(new Object[]{"Montacargas utilizados", Nmontacargas});
            model.addRow(new Object[]{"Toneladas teoricas", df.format(toneladasEsp)});
            model.addRow(new Object[]{"Toneladas reales", df.format(toneladasRea)});
            model.addRow(new Object[]{"Motores en estirocortadoras", estirocortadorasSize});
            model.addRow(new Object[]{"Motores en espera", esperaSize});
            model.addRow(new Object[]{"Motores en reparacion", reparacionSize});
            model.addRow(new Object[]{"Motores en almacen", almacenSize});
            model.addRow(new Object[]{"Min. motores estirocortadoras", estirocortadoraMin});
            model.addRow(new Object[]{"Max. motores en espera", esperaMax});
            model.addRow(new Object[]{"Utilización estirocortadoras", porcentajeUti + "%"});
            model.addRow(new Object[]{"Utilización espera", porcentajeUti2 + "%"});
            model.addRow(new Object[]{"Utilización reparación", porcentajeUti3 + "%"});
            model.addRow(new Object[]{"Utilización reserva", porcentajeUti4 + "%"});
            model.addRow(new Object[]{"Estirocortadoras lleno", contadorArea1L + "%"});
            model.addRow(new Object[]{"Estirocortadoras vacío", contadorArea1V + "%"});
            model.addRow(new Object[]{"Espera lleno", contadorArea2L + "%"});
            model.addRow(new Object[]{"Espera vacío", contadorArea2V + "%"});
            model.addRow(new Object[]{"Reparación lleno", contadorArea3L + "%"});
            model.addRow(new Object[]{"Reparación vacío", contadorArea3V + "%"});
            model.addRow(new Object[]{"Almacén lleno", contadorArea4L + "%"});
            model.addRow(new Object[]{"Almacén vacío", contadorArea4V + "%"});
            model.addRow(new Object[]{"Tiempo simulado", tiempoSimulacion + "%"});
            double ganancia = dosDecimales(toneladasRea*2000);
            model.addRow(new Object[]{"Ganancia real",  df.format(ganancia) + "$"});
            double costo = (((50+Nreservas)*89041.09)+(Nmontacargas*53424.66)+(Nreparadores*17808.22));
            model.addRow(new Object[]{"costos", df.format(costo) + "$"});
            double utilidad = dosDecimales(ganancia-costo);
            model.addRow(new Object[]{"Utilidas", df.format(utilidad) + "$"});

            // Agrega el modelo al JTable y configura la tabla en un JScrollPane
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            // Muestra la ventana
            setVisible(true);
        }
    }

    // Método para crear la ventana de resultados
    public void mostrarResultadosEnTabla(
        int Nmontacargas, double toneladasEsp, double toneladasRea,
        int estirocortadorasSize, int esperaSize, int reparacionSize, int almacenSize,
        int estirocortadoraMin, int esperaMax,
        double porcentajeUti, double porcentajeUti2, double porcentajeUti3, double porcentajeUti4,
        double contadorArea1L, double contadorArea1V,
        double contadorArea2L, double contadorArea2V,
        double contadorArea3L, double contadorArea3V,
        double contadorArea4L, double contadorArea4V,
        int tiempoSimulacion) {

        // Crea una nueva instancia de ResultadosFrame
        new ResultadosFrame(
            Nmontacargas, toneladasEsp, toneladasRea,
            estirocortadorasSize, esperaSize, reparacionSize, almacenSize,
            estirocortadoraMin, esperaMax,
            porcentajeUti, porcentajeUti2, porcentajeUti3, porcentajeUti4,
            contadorArea1L, contadorArea1V,
            contadorArea2L, contadorArea2V,
            contadorArea3L, contadorArea3V,
            contadorArea4L, contadorArea4V,
            tiempoSimulacion
        );
    }

}