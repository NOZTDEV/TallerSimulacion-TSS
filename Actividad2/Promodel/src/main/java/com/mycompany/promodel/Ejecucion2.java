package com.mycompany.promodel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

public class Ejecucion2 extends Thread {
    private int piezas;
    private int delay;
    private Random random = new Random();
    private SimulacionPanel simPanel;
    private JTextArea textAreaA;
    private JTextArea textAreaB;

    public Ejecucion2(int piezas, int delay, JTextArea textAreaA, JTextArea textAreaB) {
        this.piezas = piezas;
        this.delay = delay;
        this.textAreaA = textAreaA;
        this.textAreaB = textAreaB;
        initGrafico();
    }
    
    private void initGrafico() {
        JFrame frame = new JFrame("Simulación de Piezas");
        simPanel = new SimulacionPanel(); // Asignar directamente al campo simPanel
        simPanel.setPreferredSize(new Dimension(600, 400));
        frame.add(simPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        int tiempo = 0;
        int aux = 0;
        List<Integer> tiempoPro1 = new ArrayList<>();
        List<Integer> tiempoPro2 = new ArrayList<>();
        List<Integer> tiempoPro3 = new ArrayList<>();
        List<Integer> tiempoSis1 = new ArrayList<>();
        List<Integer> tiempoSis2 = new ArrayList<>();
        List<Integer> tiempoSis3 = new ArrayList<>();
        List<Entidad> cola1 = new ArrayList<>();
        List<Entidad> cola2 = new ArrayList<>();
        List<Entidad> cola3 = new ArrayList<>();
        List<Entidad> cola4 = new ArrayList<>();
        List<Entidad> basico = new ArrayList<>();
        List<Entidad> especialista = new ArrayList<>();
        List<Entidad> investigacion = new ArrayList<>();
        List<Entidad> informacion = new ArrayList<>();
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        int contador4 = 0;

        for (int pieza = 1; pieza <= piezas; pieza++) {
            textAreaA.append("Pieza " + pieza + "\n");
            ExponentialDistribution expDistribution = new ExponentialDistribution(20);
            int tiempoLlegada = (int) expDistribution.sample();
            tiempo += tiempoLlegada;

            Entidad entidad = new Entidad("pieza1", pieza, tiempoLlegada);
            cola1.add(entidad);
            textAreaA.append("Intervalo de llegada: " + tiempoLlegada + " minutos" + "\n");
            textAreaA.append("Llego en minuto: " + tiempo + " pieza id: " + entidad.getId() + " : " + entidad.getNombre() + "\n");

            // Actualizar la gráfica para el ingreso a cola1
            actualizarGrafico(cola1, cola2, cola3, cola4, contador1, contador2, contador3, contador4);
            aux = proceso1("basico", tiempo, basico, entidad, textAreaA);
            tiempo += aux;
            tiempoPro1.add(aux);
            textAreaA.append("Sale en: " + tiempo + " pieza id: " + entidad.getId() + " : " + entidad.getNombre()+ "\n");

            double numeroAleatorio = random.nextDouble();
            contador1++;
            if (numeroAleatorio < 0.4) {
                contador2++;
                entidad.cambiarNombre("pieza2");
                cola1.remove(entidad);
                cola2.add(entidad);

                // Actualizar la gráfica para el ingreso a cola2
                actualizarGrafico(cola1, cola2, cola3, cola4, contador1, contador2, contador3, contador4);
                aux = proceso1("especialista", tiempo, especialista, entidad, textAreaA);
                tiempo += aux;
                tiempoPro2.add(aux);
                textAreaA.append("Llego en minuto: " + tiempo + " pieza id: " + entidad.getId() + " : " + entidad.getNombre()+ "\n");

                double numeroAleatorio1 = random.nextDouble();
                if (numeroAleatorio1 < 0.2) {
                    entidad.cambiarNombre("pieza3");
                    contador3++;
                    cola2.remove(entidad);
                    cola3.add(entidad);

                    // Actualizar la gráfica para el ingreso a cola3
                    actualizarGrafico(cola1, cola2, cola3, cola4, contador1, contador2, contador3, contador4);
                    aux = proceso1("investigacion", tiempo, investigacion, entidad, textAreaA);
                    tiempo += aux;
                    tiempoPro3.add(aux);
                    textAreaA.append("Llego en minuto: " + tiempo + " pieza id: " + entidad.getId() + " : " + entidad.getNombre()+ "\n");
                    cola3.remove(entidad);
                    
                    contador4++;
                    cola4.add(entidad);
                    cola4.remove(entidad);
                    aux = proceso1("Informacion", tiempo, investigacion, entidad, textAreaA);
                    tiempo += aux;
                    if (entidad.getNombre() != null) {
                        String nombre = entidad.getNombre();
                        switch (nombre) {
                            case "pieza1":
                                agregarTiempo(tiempoPro1, tiempoSis1, aux, tiempo);
                                break;
                            case "pieza2":
                                agregarTiempo(tiempoPro2, tiempoSis2, aux, tiempo);
                                break;
                            case "pieza3":
                                agregarTiempo(tiempoPro3, tiempoSis3, aux, tiempo);
                                break;
                            default:
                                break;
                        }
                    }
                    textAreaA.append("Llego en minuto: " + tiempo + " pieza id: " + entidad.getId() + " : " + entidad.getNombre()+ "\n");
                } else {
                    cola2.remove(entidad);
                    contador4++;
                    cola4.add(entidad);
                    
                    cola4.remove(entidad);
                    // Actualizar la gráfica para el ingreso a cola4
                    actualizarGrafico(cola1, cola2, cola3, cola4, contador1, contador2, contador3, contador4);
                    aux = proceso1("Informacion", tiempo, investigacion, entidad, textAreaA);
                    tiempo += aux;
                    if (entidad.getNombre() != null) {
                        String nombre = entidad.getNombre();
                        switch (nombre) {
                            case "pieza1":
                                agregarTiempo(tiempoPro1, tiempoSis1, aux, tiempo);
                                break;
                            case "pieza2":
                                agregarTiempo(tiempoPro2, tiempoSis2, aux, tiempo);
                                break;
                            case "pieza3":
                                agregarTiempo(tiempoPro3, tiempoSis3, aux, tiempo);
                                break;
                            default:
                                break;
                        }
                    }
                    textAreaA.append("Llego en minuto: " + tiempo + " pieza id: " + entidad.getId() + " : " + entidad.getNombre()+ "\n");
                }
            } else {
                contador4++;
                cola1.remove(entidad);
                cola4.add(entidad);

                // Actualizar la gráfica para el ingreso a cola4
                actualizarGrafico(cola1, cola2, cola3, cola4, contador1, contador2, contador3, contador4);
                aux = proceso1("informacion", tiempo, informacion, entidad, textAreaA);
                tiempo += aux;
                if (entidad.getNombre() != null) {
                    String nombre = entidad.getNombre();
                    switch (nombre) {
                        case "pieza1":
                            agregarTiempo(tiempoPro1, tiempoSis1, aux, tiempo);
                            break;
                        case "pieza2":
                            agregarTiempo(tiempoPro2, tiempoSis2, aux, tiempo);
                            break;
                        case "pieza3":
                            agregarTiempo(tiempoPro3, tiempoSis3, aux, tiempo);
                            break;
                        default:
                            break;
                    }
                }
                
                textAreaA.append("Llego en minuto: " + tiempo + " pieza id: " + entidad.getId() + " : " + entidad.getNombre()+ "\n");
                cola4.remove(entidad);
            }

            // Actualizar gráfica al final de la iteración de la pieza actual
            actualizarGrafico(cola1, cola2, cola3, cola4, contador1, contador2, contador3, contador4);

            try {
                Thread.sleep(delay); // 2000 ms = 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
        }

            // Imprimir resultados finales
            textAreaB.append("Locaciones: "+ "\n");
            textAreaB.append("Piezas que pasaron por basico: " + contador1+ "\n");
            textAreaB.append("Piezas que pasaron por especialista: " + contador2+ "\n");
            textAreaB.append("Piezas que pasaron por investigacion: " + contador3+ "\n");
            textAreaB.append("Piezas que pasaron por informacion: " + contador4+ "\n");
            textAreaB.append("Capacidad de cola1: 99999.999"+ "\n");
            textAreaB.append("Capacidad de cola2: 99999.999"+ "\n");
            textAreaB.append("Capacidad de cola3: 99999.999"+ "\n");
            textAreaB.append("Capacidad de cola4: 99999.999"+ "\n");
            textAreaB.append("Capacidad de Basico: 1"+ "\n");
            textAreaB.append("Capacidad de especialista: 1"+ "\n");
            textAreaB.append("Capacidad de investigacion: 1"+ "\n");
            textAreaB.append("Capacidad de informacion: 1"+ "\n");
            double por1 = ((double)contador1/piezas)*100;
            double por2 = ((double)contador2/piezas)*100;
            double por3 = ((double)contador3/piezas)*100;
            double por4 = ((double)contador4/piezas)*100;
            textAreaB.append("Porcentaje de utilizacion de Basico: " + por1 + "%"+ "\n");
            textAreaB.append("Porcentaje de utilizacion de especialista: " + por2 + "%"+ "\n");
            textAreaB.append("Porcentaje de utilizacion de investigacion: " + por3+ "%"+ "\n");
            textAreaB.append("Porcentaje de utilizacion de informacion: " + por4+ "%"+ "\n");

            textAreaB.append("Entidades: "+ "\n");
            int aux1 = contador3;
            int aux2 = contador2 - aux1;
            int aux3 = contador1 - aux2 - aux1;
            textAreaB.append("Piezas1: " + aux3+ "\n");
            textAreaB.append("Piezas2: " + aux2+ "\n");
            textAreaB.append("Piezas3: " + aux1+ "\n");
            double media1, media2, media3;
            media1 = calcularMedia(tiempoPro1);
            media2 = calcularMedia(tiempoPro2);
            media3 = calcularMedia(tiempoPro3);
            textAreaB.append("Tiempo promedio en operacion pieza1: " + media1+ "\n");
            textAreaB.append("Tiempo promedio en operacion pieza2: " + media2+ "\n");
            textAreaB.append("Tiempo promedio en operacion pieza3: " + media3+ "\n");
            double sis1, sis2, sis3 = 0;
            sis1 = calcularMedia(tiempoSis1);
            sis2 = calcularMedia(tiempoSis2);
            sis3 = calcularMedia(tiempoSis3);
            textAreaB.append("Tiempo promedio en sistema pieza1: " + sis1+ "\n");
            textAreaB.append("Tiempo promedio en sistema pieza2: " + sis2+ "\n");
            textAreaB.append("Tiempo promedio en sistema pieza3: " + sis3+ "\n");
            
        }

    class SimulacionPanel extends JPanel {
        private int[] filaCounts = new int[4];
        private int[] contadorEmpleado = new int[4];
        private int[] xPositions = {50, 200, 350, 180}; // Posiciones X individuales
        private int[] yPositions = {50, 50, 50, 120}; // Posiciones Y individuales

        public void setFilaCounts(int[] counts) {
            System.arraycopy(counts, 0, this.filaCounts, 0, counts.length);
        }

        public void setContadorEmpleado(int[] contadores) {
            System.arraycopy(contadores, 0, this.contadorEmpleado, 0, contadores.length);
        }

        public void setPosition(int index, int x, int y) {
            // Actualizar las posiciones individuales de las filas
            if (index >= 0 && index < 4) {
                xPositions[index] = x;
                yPositions[index] = y;
                repaint(); // Vuelve a dibujar el panel para reflejar los cambios
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int anchoFila = 100;
            int altoFila = 50;

            for (int i = 0; i < 4; i++) {
                // Cambia el color de la fila basado en la cantidad de piezas
                if (filaCounts[i] > 0) {
                    g.setColor(Color.CYAN); // Celeste si hay piezas en la fila
                } else {
                    g.setColor(Color.YELLOW); // Amarillo si está vacía
                }
                // Dibuja cada cuadrado en su posición individual
                g.fillRect(xPositions[i], yPositions[i], anchoFila, altoFila);
                g.setColor(Color.BLACK);
                g.drawRect(xPositions[i], yPositions[i], anchoFila, altoFila); // Dibuja el borde
                g.drawString("Fila " + (i + 1) + ": " + filaCounts[i], xPositions[i] + 10, yPositions[i] + 30);
                g.drawString("Atendidos: " + contadorEmpleado[i], xPositions[i] + 10, yPositions[i] + 15); // Muestra el contador encima de la fila
            }
        }
    }

    
    public int proceso1(String locacion, int tiempoSis, List<Entidad> cola, Entidad pieza, JTextArea textAreaA) {
        cola.add(pieza);
        UniformRealDistribution uniforme = new UniformRealDistribution(10, 50);
        int demora = (int) uniforme.sample();
        textAreaA.append("Tiempo de atencion en " + locacion + " es: " + demora +"\n");
        cola.remove(0);
        return demora;
    }
    
    private void actualizarGrafico(List<Entidad> cola1, List<Entidad> cola2, List<Entidad> cola3, List<Entidad> cola4,
                               int contador1, int contador2, int contador3, int contador4) {
        int[] counts = {cola1.size(), cola2.size(), cola3.size(), cola4.size()};
        int[] contadorEmp = {contador1, contador2, contador3, contador4};
        simPanel.setFilaCounts(counts);
        simPanel.setContadorEmpleado(contadorEmp);
        simPanel.repaint();
        try {
            Thread.sleep(500); // Pausa para ver el cambio
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static double calcularMedia(List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            return 0;
        }
        int suma = 0;
        for (int numero : numeros) {
            suma += numero;
        }
        return (double) suma / numeros.size();
    }
    
    private void agregarTiempo(List<Integer> tiempoPro, List<Integer> tiempoSis, int aux, int tiempo) {
        tiempoPro.add(aux);
        if (tiempoSis.isEmpty()) {
            tiempoSis.add(tiempo);
        } else {
            tiempoSis.add(tiempo - tiempoSis.get(tiempoSis.size() - 1));
        }
    }
}

