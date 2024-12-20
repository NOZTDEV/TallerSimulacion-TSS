package com.mycompany.promodel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ejecucion3 extends Thread {
    private int tiempo;
    private int delay;
    private SimulacionPanel simPanel;
    private JTextArea textAreaA;
    private JTextArea textAreaB;

    public Ejecucion3(int tiempo, int delay, JTextArea textAreaA, JTextArea textAreaB) {
        this.tiempo = tiempo;
        this.delay = delay;
        this.textAreaA = textAreaA;
        this.textAreaB = textAreaB;
        initGrafico();
    }
    
    private void initGrafico() {
        JFrame frame = new JFrame("Simulación de Ejecución 3");
        simPanel = new SimulacionPanel();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(simPanel);
        frame.setVisible(true);
    }
    
    @Override
    public void run() {
        List<Entidad> edificio1 = new ArrayList<>();
        List<Entidad> edificio2 = new ArrayList<>();
        List<Entidad> carro = new ArrayList<>();
        
        int contadorEdificio1 = 0; // Contador para edificio1
        int contadorEdificio2 = 0; // Contador para edificio2
        int contadorCarro = 0; // Contador para carro
        
        for (int minuto = 1; minuto <= tiempo * 60; minuto++) {
            Entidad entidad = new Entidad("paquete", minuto, tiempo);
            edificio1.add(entidad);
            contadorEdificio1++;
            textAreaA.append("Paquete " + minuto + " llega a edificio 1" + "\n");

            // Actualiza y dibuja el gráfico
            simPanel.setFilaCounts(new int[]{edificio1.size(), carro.size(), edificio2.size()});
            simPanel.setContadores(contadorEdificio1, contadorCarro, contadorEdificio2);
            simPanel.setCarroPosition(50); // Posición debajo del edificio 1
            simPanel.repaint();
            
            try {
                Thread.sleep(delay); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            edificio1.remove(entidad);
            carro.add(entidad);
            contadorCarro++; // Incrementa el contador del carro
            textAreaA.append("Paquete está en carro y va a edificio 2" + "\n");

            // Actualiza y dibuja el gráfico
            simPanel.setFilaCounts(new int[]{edificio1.size(), carro.size(), edificio2.size()});
            simPanel.setContadores(contadorEdificio1, contadorCarro, contadorEdificio2);
            simPanel.setCarroPosition(350); // Mueve el carro a la posición debajo del edificio 2
            simPanel.repaint();

            try {
                Thread.sleep(delay); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            carro.remove(entidad);
            edificio2.add(entidad);
            contadorEdificio2++; // Incrementa el contador del edificio 2
            textAreaA.append("Paquete " + minuto + " llega a edificio 2" + "\n");

            // Actualiza y dibuja el gráfico
            simPanel.setFilaCounts(new int[]{edificio1.size(), carro.size(), edificio2.size()});
            simPanel.setContadores(contadorEdificio1, contadorCarro, contadorEdificio2);
            simPanel.repaint();

            try {
                Thread.sleep(delay); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            edificio2.remove(entidad);
            textAreaA.append("Paquete sale" + "\n");
            // En este punto, los contadores no necesitan cambiar ya que el paquete ya ha salido
            simPanel.setFilaCounts(new int[]{edificio1.size(), carro.size(), edificio2.size()});
            simPanel.setContadores(contadorEdificio1, contadorCarro, contadorEdificio2);
            simPanel.setCarroPosition(50); // Regresa a la posición debajo del edificio 1
            simPanel.repaint();
        }
        
        textAreaB.append("Locaciones: "+ "\n");
        textAreaB.append("Paquetes en edificio 1: " + contadorEdificio1+ "\n");
        textAreaB.append("Paquetes en edificio 2: " + contadorEdificio2+ "\n");
        textAreaB.append("Capacidad de edificio1: 1"+ "\n");
        textAreaB.append("Capacidad de edificio2: 1"+ "\n");
        textAreaB.append("Porcentaje de utilizacion de edificio 1: 100%"+ "\n");
        textAreaB.append("Porcentaje de utilizacion de edificio 2: 100%"+ "\n");
        
        textAreaB.append("Entidades: "+ "\n");
        textAreaB.append("Paquetes1: " + contadorCarro+ "\n");
        textAreaB.append("Tiempo promedio en operacion paquete: 0"+ "\n");
        textAreaB.append("Tiempo promedio en sistema paquete: 0"+ "\n");
    }

    class SimulacionPanel extends JPanel {
        private int[] filaCounts = new int[3];
        private int[] xPositions = {50, 50, 350}; // Posiciones X para las 3 listas
        private int[] yPositions = {30, 150, 30}; // Posiciones Y para las 3 listas
        private int carroPosition; // Posición del carro
        private int contadorEdificio1 = 0; // Contador de paquetes en edificio 1
        private int contadorEdificio2 = 0; // Contador de paquetes en edificio 2
        private int contadorCarro = 0; // Contador de paquetes en el carro

        public void setFilaCounts(int[] counts) {
            System.arraycopy(counts, 0, this.filaCounts, 0, counts.length);
        }

        public void setCarroPosition(int position) {
            this.carroPosition = position;
        }
        
        public void setContadores(int contadorEdificio1, int contadorCarro, int contadorEdificio2) {
            this.contadorEdificio1 = contadorEdificio1;
            this.contadorCarro = contadorCarro;
            this.contadorEdificio2 = contadorEdificio2;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int anchoFila = 100;
            int altoFila = 50;

            // Dibuja las filas
            for (int i = 0; i < 3; i++) {
                // Cambia el color de la fila basado en la cantidad de piezas
                if (filaCounts[i] > 0) {
                    g.setColor(Color.CYAN);
                } else {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect(xPositions[i], yPositions[i], anchoFila, altoFila);
                g.setColor(Color.BLACK);
                g.drawRect(xPositions[i], yPositions[i], anchoFila, altoFila);
                
                // Dibuja el nombre y el contador
                if (i == 0) {
                    g.drawString("Edificio 1: " + filaCounts[i], xPositions[i] + 10, yPositions[i] + 30);
                    g.drawString("Contador: " + contadorEdificio1, xPositions[i] + 10, yPositions[i] + 45);
                } else if (i == 1) {
                    g.drawString("Carro: " + filaCounts[i], xPositions[i] + 10, yPositions[i] + 30);
                    g.drawString("Contador: " + contadorCarro, xPositions[i] + 10, yPositions[i] + 45);
                } else {
                    g.drawString("Edificio 2: " + filaCounts[i], xPositions[i] + 10, yPositions[i] + 30);
                    g.drawString("Contador: " + contadorEdificio2, xPositions[i] + 10, yPositions[i] + 45);
                }
            }

            // Dibuja el carro
            g.setColor(Color.RED);
            g.fillRect(carroPosition, 80, 50, 30);
            g.setColor(Color.BLACK);
            g.drawRect(carroPosition, 80, 50, 30);
            g.drawString("Carro", carroPosition + 10, 100);
        }
    }
}
