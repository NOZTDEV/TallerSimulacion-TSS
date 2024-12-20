package com.mycompany.promodel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Ejer1UI extends javax.swing.JFrame {

    // Áreas de texto para mostrar resultados
    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private JTextArea textAreaC;

    public Ejer1UI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Inicializando etiquetas
        JLabel jLabel1 = new JLabel();
        JLabel jLabel3 = new JLabel();  // Etiqueta para número de iteraciones
        JLabel jLabel4 = new JLabel();  // Etiqueta para tiempo de simulación

        // Inicializando campos de texto
        numeroIteraciones = new JTextField("1000");  // Campo de texto para número de iteraciones
        tiempoSimulacion = new JTextField("1");   // Campo de texto para tiempo de simulación

        simularEjercicio = new JButton();
        jTabbedPane1 = new JTabbedPane();

        // Inicializando áreas de texto
        textAreaA = new JTextArea(10, 30);
        textAreaB = new JTextArea(10, 30);
        textAreaC = new JTextArea(10, 30);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Introducir Datos");

        jLabel3.setText("Delay para la simulacion:");  // Etiqueta para número de iteraciones
        jLabel4.setText("Tiempo de simulacion en dias:");   // Etiqueta para tiempo de simulación

        // Añadiendo validación para que solo se puedan ingresar números de tipo double
        addDoubleInputValidation(numeroIteraciones);  // Validación para número de iteraciones
        addDoubleInputValidation(tiempoSimulacion);   // Validación para tiempo de simulación

        simularEjercicio.setText("Simular");
        simularEjercicio.addActionListener(evt -> simularEjercicioA(evt));

        // Configurando las áreas de texto como no editables
        textAreaA.setEditable(false);
        textAreaB.setEditable(false);
        textAreaC.setEditable(false);

        // Añadiendo las áreas de texto a cada panel de la pestaña correspondiente
        jTabbedPane1.addTab("Inciso A", new JScrollPane(textAreaA));
        jTabbedPane1.addTab("Inciso B", new JScrollPane(textAreaB));
        jTabbedPane1.addTab("Inciso C", new JScrollPane(textAreaC));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)  // Etiqueta para número de iteraciones
                                    .addComponent(jLabel4)) // Etiqueta para tiempo de simulación
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(numeroIteraciones, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)  // Campo de texto para número de iteraciones
                                    .addComponent(tiempoSimulacion)))  // Campo de texto para tiempo de simulación
                            .addComponent(simularEjercicio))
                        .addGap(0, 30, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)  // Etiqueta para número de iteraciones
                    .addComponent(numeroIteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))  // Campo de texto para número de iteraciones
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)  // Etiqueta para tiempo de simulación
                    .addComponent(tiempoSimulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))  // Campo de texto para tiempo de simulación
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(simularEjercicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }                     

    private void simularEjercicioA(java.awt.event.ActionEvent evt) {                                                  
        try {
            int duracion = Integer.parseInt(tiempoSimulacion.getText());
            int delay = Integer.parseInt(numeroIteraciones.getText());
            Ejecucion1 hilo = new Ejecucion1(duracion, delay, textAreaA, textAreaB);
            hilo.start(); // Ejecuta el hilo con el valor de duración recibido
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce valores numéricos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para agregar validación de entrada solo para números dobles
    private void addDoubleInputValidation(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != '-') {
                    e.consume();
                }
                if (c == '.' && field.getText().contains(".")) {
                    e.consume();
                }
                if (c == '-' && field.getText().length() > 0) {
                    e.consume();
                }
            }
        });
    }
    
    // Variables declaration
    private JTextField numeroIteraciones;  // Campo para número de iteraciones
    private JTextField tiempoSimulacion;   // Campo para tiempo de simulación
    private JButton simularEjercicio;
    private JTabbedPane jTabbedPane1;
}

