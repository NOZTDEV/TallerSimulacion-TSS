package com.mycompany.promodel;

import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ejecucion1 extends Thread {
    private int duracion;
    private SimulacionPanel simulacionPanel;
    private int numeroEstudiantesEnFila = 0; // Variable para el conteo de estudiantes en fila
    private int contadorFila = 0; // Contador para estudiantes que pasaron por la fila
    private int[] contadorEmpleado = new int[4]; // Contadores para los empleados
    private int delay = 0;
    private JTextArea textAreaA;
    private JTextArea textAreaB;

    public Ejecucion1(int duracion, int delay, JTextArea textAreaA, JTextArea textAreaB) {
        this.duracion = duracion;
        this.simulacionPanel = new SimulacionPanel();
        this.delay = delay;
        this.textAreaA = textAreaA;
        this.textAreaB = textAreaB;
        initGrafico();
    }

    private void initGrafico() {
        JFrame frame = new JFrame("Simulación Dinámica de Atención a Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(simulacionPanel, BorderLayout.CENTER);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        List<Integer> tiempoAtencion = new ArrayList<>();
        List<Integer> tiempoSis = new ArrayList<>();
        double[] porcentaje = {0.05, 0.20, 0.10, 0.50, 0.05, 0.10, 0.0};
        int[] duracionInt = {60 * 60, 180 * 60, 120 * 60, 120 * 60, 240 * 60, 60 * 60, 660 * 60};
        Locacion empleado1 = new Locacion("Empleado 1", 1);
        Locacion empleado2 = new Locacion("Empleado 2", 1);
        Locacion empleado3 = new Locacion("Empleado 3", 1);
        Locacion empleado4 = new Locacion("Empleado 4", 1);
        for(int dias = 1; dias <= duracion; dias++){
            PoissonDistribution distribucionPoisson = new PoissonDistribution(700);
            int estudiantesLlegadosTotales = distribucionPoisson.sample();
            int tiempoTotal = (1440 * 60)-(13*60*60); // 1 día en minutos
            int int1 = (int) (porcentaje[0] * estudiantesLlegadosTotales);
            int int2 = (int) (porcentaje[1] * estudiantesLlegadosTotales);
            int int3 = (int) (porcentaje[2] * estudiantesLlegadosTotales);
            int int4 = (int) (porcentaje[3] * estudiantesLlegadosTotales);
            int int5 = (int) (porcentaje[4] * estudiantesLlegadosTotales);
            int int6 = (int) (porcentaje[5] * estudiantesLlegadosTotales);
            int int7 = (int) (porcentaje[6] * estudiantesLlegadosTotales);
            int suma = int1 + int2 + int3 + int4 + int5 + int6 + int7;
            Locacion fila = new Locacion("Fila Principal", suma);

            int tiempoSalida1 = 0;
            int tiempoSalida2 = 0;
            int tiempoSalida3 = 0;
            int tiempoSalida4 = 0;
            int id = 0;

            for (int hora = 1; hora <= tiempoTotal; hora++) {
                try {
                    Thread.sleep(delay); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                simulacionPanel.setFilaCount(numeroEstudiantesEnFila);
                simulacionPanel.setEmpleadoCounts(
                    empleado1.getCantidadEntidades(),
                    empleado2.getCantidadEntidades(),
                    empleado3.getCantidadEntidades(),
                    empleado4.getCantidadEntidades()
                );
                simulacionPanel.setContadorFila(contadorFila); // Actualiza el contador de la fila
                simulacionPanel.setContadorEmpleado(contadorEmpleado); // Actualiza los contadores de empleados
                simulacionPanel.repaint();

                switch (hora) {
                    case 1 -> {
                        int llegadas = simularLlegadaEstudiantes((int) (porcentaje[0] * estudiantesLlegadosTotales), duracionInt[0], 0, fila, textAreaA);
                        numeroEstudiantesEnFila += llegadas;
                        contadorFila = contadorFila + llegadas;
                    }
                    case 61 -> {
                        int llegadas = simularLlegadaEstudiantes((int) (porcentaje[1] * estudiantesLlegadosTotales), duracionInt[1], 60 * 60, fila, textAreaA);
                        numeroEstudiantesEnFila += llegadas;
                        contadorFila = contadorFila + llegadas;
                    }
                    case 241 -> {
                        int llegadas = simularLlegadaEstudiantes((int) (porcentaje[2] * estudiantesLlegadosTotales), duracionInt[2], 240 * 60, fila, textAreaA);
                        numeroEstudiantesEnFila += llegadas;
                        contadorFila = contadorFila + llegadas;
                    }
                    case 361 -> {
                        int llegadas = simularLlegadaEstudiantes((int) (porcentaje[3] * estudiantesLlegadosTotales), duracionInt[3], 360 * 60, fila, textAreaA);
                        numeroEstudiantesEnFila += llegadas;
                        contadorFila = contadorFila + llegadas;
                    }
                    case 480 -> {
                        int llegadas = simularLlegadaEstudiantes((int) (porcentaje[4] * estudiantesLlegadosTotales), duracionInt[4], 480 * 60, fila, textAreaA);
                        numeroEstudiantesEnFila += llegadas;
                        contadorFila = contadorFila + llegadas;
                    }
                    case 721 -> {
                        int llegadas = simularLlegadaEstudiantes((int) (porcentaje[5] * estudiantesLlegadosTotales), duracionInt[5], 720 * 60, fila, textAreaA);
                        numeroEstudiantesEnFila += llegadas;
                        contadorFila = contadorFila + llegadas;
                    }
                    case 781 -> {
                        int llegadas = simularLlegadaEstudiantes((int) (porcentaje[6] * estudiantesLlegadosTotales), duracionInt[6], 780 * 60, fila, textAreaA);
                        numeroEstudiantesEnFila += llegadas;
                        contadorFila = contadorFila + llegadas;
                    }
                    default -> {
                    }
                }

                if (id < suma) {
                    if (empleado1.getCantidadEntidades() == 0 && hora >= tiempoSalida1) {
                        tiempoSalida1 = simularAtencionEmpleado(fila.getEntidadPorIndice(id), hora, empleado1, id + 1, textAreaA);
                        tiempoAtencion.add(tiempoSalida1);
                        tiempoSis.add(tiempoSalida1);
                        numeroEstudiantesEnFila--;
                        contadorEmpleado[0]++; // Incrementa el contador del empleado 1
                        id++;
                    } else if (empleado2.getCantidadEntidades() == 0 && hora >= tiempoSalida2) {
                        tiempoSalida2 = simularAtencionEmpleado(fila.getEntidadPorIndice(id), hora, empleado2, id + 1, textAreaA);
                        tiempoAtencion.add(tiempoSalida2);
                        numeroEstudiantesEnFila--;
                        contadorEmpleado[1]++; // Incrementa el contador del empleado 2
                        id++;
                    } else if (empleado3.getCantidadEntidades() == 0 && hora >= tiempoSalida3) {
                        tiempoSalida3 = simularAtencionEmpleado(fila.getEntidadPorIndice(id), hora, empleado3, id + 1, textAreaA);
                        tiempoAtencion.add(tiempoSalida3);
                        numeroEstudiantesEnFila--;
                        contadorEmpleado[2]++; // Incrementa el contador del empleado 3
                        id++;
                    } else if (empleado4.getCantidadEntidades() == 0 && hora >= tiempoSalida4) {
                        tiempoSalida4 = simularAtencionEmpleado(fila.getEntidadPorIndice(id), hora, empleado4, id + 1, textAreaA);
                        tiempoAtencion.add(tiempoSalida3);
                        numeroEstudiantesEnFila--;
                        contadorEmpleado[3]++; // Incrementa el contador del empleado 4
                        id++;
                    }
                }

                if (hora == tiempoSalida1) {
                    estudianteSale(empleado1, empleado1.getEntidadPorIndice(0), textAreaA);
                }
                if (hora == tiempoSalida2) {
                    estudianteSale(empleado2, empleado2.getEntidadPorIndice(0), textAreaA);
                }
                if (hora == tiempoSalida3) {
                    estudianteSale(empleado3, empleado3.getEntidadPorIndice(0), textAreaA);
                }
                if (hora == tiempoSalida4) {
                    estudianteSale(empleado4, empleado4.getEntidadPorIndice(0), textAreaA);
                }
                
            }
            
            textAreaB.append("Locaciones: \n");
            textAreaB.append("Clientes que llegaron a la fila: " + suma + "\n");
            textAreaB.append("Clientes que atendio el empleado 1: " + contadorEmpleado[0] + "\n");
            textAreaB.append("Clientes que atendio el empleado 2: " + contadorEmpleado[1] + "\n");
            textAreaB.append("Clientes que atendio el empleado 3: " + contadorEmpleado[2] + "\n");
            textAreaB.append("Clientes que atendio el empleado 4: " + contadorEmpleado[3] + "\n");
            textAreaB.append("Capacidad de Fila: 99999.999 \n");
            textAreaB.append("Capacidad de empleado1: 1 \n");
            textAreaB.append("Capacidad de empleado2: 1 \n");
            textAreaB.append("Capacidad de empleado3: 1 \n");
            textAreaB.append("Capacidad de empleado4: 1 \n");
            double por1 = ((double)contadorEmpleado[0]/suma)*100;
            double por2 = ((double)contadorEmpleado[1]/suma)*100;
            double por3 = ((double)contadorEmpleado[2]/suma)*100;
            double por4 = ((double)contadorEmpleado[3]/suma)*100;
            textAreaB.append("Porcentaje de utilizacion de Empleado 1: " + por1 + "%"+ "\n");
            textAreaB.append("Porcentaje de utilizacion de Empleado 2: " + por2 + "%"+ "\n");
            textAreaB.append("Porcentaje de utilizacion de Empleado 3: " + por3+ "%"+ "\n");
            textAreaB.append("Porcentaje de utilizacion de Empleado 4: " + por4+ "%"+ "\n");
            textAreaB.append("Entidades: \n");
            textAreaB.append("Estudianrtes: " + suma + "\n");
            double media = calcularMedia(tiempoAtencion);
            
            textAreaB.append("Tiempo promedio en operacion pieza1: " + media+ "\n");
            //double sis = calcularMedia(tiempoSis);
            //textAreaB.append("Tiempo promedio en sistema pieza1: " + sis + "\n");
        }
    }

    class SimulacionPanel extends JPanel {
        private int filaCount;
        private int[] empleadoCounts = new int[4];
        private int contadorFila;
        private int[] contadorEmpleado = new int[4];
        public void setFilaCount(int count) {
            this.filaCount = count;
        }
        public void setEmpleadoCounts(int emp1, int emp2, int emp3, int emp4) {
            this.empleadoCounts[0] = emp1;
            this.empleadoCounts[1] = emp2;
            this.empleadoCounts[2] = emp3;
            this.empleadoCounts[3] = emp4;
        }
        public void setContadorFila(int contador) {
            this.contadorFila = contador;
        }
        public void setContadorEmpleado(int[] contadores) {
            System.arraycopy(contadores, 0, this.contadorEmpleado, 0, contadores.length);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Cambia el color de la fila a celeste si hay estudiantes en la fila
            if (filaCount > 0) {
                g.setColor(Color.CYAN); // Celeste
            } else {
                g.setColor(Color.YELLOW);
            }
            g.fillRect(50, 50, 100, 50);
            g.setColor(Color.BLACK);
            g.drawString("Fila: " + filaCount, 60, 80);
            g.drawString("Total en fila: " + contadorFila, 60, 40); // Muestra el contador encima de la fila

            int xOffset = 200;
            for (int i = 0; i < 4; i++) {
                // Cambia el color del empleado a gris si está atendiendo
                if (empleadoCounts[i] == 1) {
                    g.setColor(Color.GRAY); // Gris cuando está atendiendo
                } else {
                    g.setColor(Color.GREEN); // Verde si no está atendiendo
                }
                g.fillRect(xOffset, 50, 90, 50);
                g.setColor(Color.BLACK);
                g.drawString("Empleado " + (i + 1) + ": " + empleadoCounts[i], xOffset + 5, 80);
                g.drawString("Atendidos: " + contadorEmpleado[i], xOffset + 5, 40); // Muestra el contador encima del empleado
                xOffset += 150;
            }
       }
}


    public int simularLlegadaEstudiantes(int numeroEntidades, int tiempoTotal, int minutoInicio, Locacion fila, JTextArea textArea) {
        Random random = new Random();
        int tiempoAnterior = minutoInicio;
        int nuevosEstudiantes = 0;

        for (int i = 1; i <= numeroEntidades; i++) {
            int tiempoLlegada = tiempoAnterior + random.nextInt(30) + 1;
            if (tiempoLlegada > tiempoTotal + minutoInicio) break;

            int estudianteId = fila.getCantidadEntidades() + 1;
            Entidad entidad = new Entidad("Entidad " + estudianteId, estudianteId, tiempoLlegada);

            if (fila.agregarEntidad(entidad)) {
                nuevosEstudiantes++;
                textArea.append("Estudiante " + estudianteId + " ha llegado en el segundo " + tiempoLlegada + "\n");
            }
            tiempoAnterior = tiempoLlegada;
        }
        return nuevosEstudiantes;
    }

    public int simularAtencionEmpleado(Entidad estudiante, int tiempoActual, Locacion empleado, int estudianteId, JTextArea textArea) {
        empleado.agregarEntidad(estudiante);
        UniformRealDistribution distribucionUniforme = new UniformRealDistribution(25, 65);
        int tiempoAtencion = (int) distribucionUniforme.sample();
        int tiempoSalida = tiempoActual + tiempoAtencion;

        textArea.append(empleado.getNombre() + " atiende a Estudiante " + estudianteId + " en el segundo " + tiempoActual+ "\n");
        textArea.append("Tiempo estimado de atencion para Estudiante " + estudianteId + " es de " + tiempoAtencion + " segunodos."+ "\n");
        textArea.append("Estudiante " + estudianteId + " saldro a los " + tiempoSalida + " segundos"+ "\n");

        return tiempoSalida;
    }

    public void estudianteSale(Locacion empleado, Entidad estudiante, JTextArea textArea) {
        if (estudiante != null) {
            textArea.append("Estudiante " + estudiante.getId() + " ha finalizado su atencion y sale."+ "\n");
            empleado.eliminarEntidad(estudiante);
        } else {
            textArea.append("No hay estudiante en la fila del " + empleado.getNombre() + " para ser atendido."+ "\n");
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
}