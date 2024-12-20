package com.mycompany.proyecto3;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EjerUI extends javax.swing.JFrame {

    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private JTextArea textAreaC;

    public EjerUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Inicializando etiquetas
        JLabel jLabel1 = new JLabel();
        JLabel jLabelSimulacionHoras = new JLabel("Duración en horas:");
        JLabel jLabelReplicas = new JLabel("Número de réplicas:");
        JLabel jLabelDelay = new JLabel("Delay (segundos):");
        JLabel jLabelCapacidadLupulo = new JLabel("Lúpulo por coccion:");
        JLabel jLabelLitrosMosto = new JLabel("Litros de mosto por mezcla:");
        JLabel jLabelBotellasCaja = new JLabel("Botellas por caja:");

        // Inicializando campos de texto
        JTextField simulacionHoras = new JTextField("70");
        JTextField replicas = new JTextField("1");
        JTextField delay = new JTextField("1000");
        JTextField capacidadLupulo = new JTextField("4"); // Renombrado
        JTextField litrosMosto = new JTextField("10");
        JTextField botellasCaja = new JTextField("6");

        JButton simularEjercicio = new JButton();
        JTabbedPane jTabbedPane1 = new JTabbedPane();

        // Inicializando áreas de texto
        textAreaA = new JTextArea(10, 30);
        textAreaB = new JTextArea(10, 30);
        textAreaC = new JTextArea(10, 30);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Introducir Datos");

        // Añadiendo validación para que solo se puedan ingresar números
        addDoubleInputValidation(simulacionHoras);
        addDoubleInputValidation(replicas);
        addDoubleInputValidation(delay);
        addDoubleInputValidation(capacidadLupulo); // Ajustado
        addDoubleInputValidation(litrosMosto);
        addDoubleInputValidation(botellasCaja);

        simularEjercicio.setText("Simular");
        simularEjercicio.addActionListener(evt -> simularEjercicioAction(evt, simulacionHoras, replicas, delay, capacidadLupulo, litrosMosto, botellasCaja));

        // Configurando las áreas de texto como no editables
        textAreaA.setEditable(false);
        textAreaB.setEditable(false);
        textAreaC.setEditable(false);

        // Añadiendo las áreas de texto a cada panel de la pestaña correspondiente
        jTabbedPane1.addTab("Resultados A", new JScrollPane(textAreaA));
        jTabbedPane1.addTab("Resultados B", new JScrollPane(textAreaB));
        jTabbedPane1.addTab("Resultados C", new JScrollPane(textAreaC));

        // Configuración del diseño de la interfaz
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
                                                                        .addComponent(jLabelSimulacionHoras)
                                                                        .addComponent(jLabelReplicas)
                                                                        .addComponent(jLabelDelay)
                                                                        .addComponent(jLabelCapacidadLupulo) // Ajustado
                                                                        .addComponent(jLabelLitrosMosto)
                                                                        .addComponent(jLabelBotellasCaja))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(simulacionHoras)
                                                                        .addComponent(replicas)
                                                                        .addComponent(delay)
                                                                        .addComponent(capacidadLupulo) // Ajustado
                                                                        .addComponent(litrosMosto)
                                                                        .addComponent(botellasCaja, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
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
                                        .addComponent(jLabelSimulacionHoras)
                                        .addComponent(simulacionHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelReplicas)
                                        .addComponent(replicas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelDelay)
                                        .addComponent(delay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelCapacidadLupulo) // Ajustado
                                        .addComponent(capacidadLupulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) // Ajustado
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelLitrosMosto)
                                        .addComponent(litrosMosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelBotellasCaja)
                                        .addComponent(botellasCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(simularEjercicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void simularEjercicioAction(java.awt.event.ActionEvent evt, JTextField simulacionHoras, JTextField replicas, JTextField delay, JTextField capacidadLupulo, JTextField litrosMosto, JTextField botellasCaja) {
        try {
            int duracionHoras = Integer.parseInt(simulacionHoras.getText());
            int numReplicas = Integer.parseInt(replicas.getText());
            int delaySegundos = Integer.parseInt(delay.getText());
            int capacidad = Integer.parseInt(capacidadLupulo.getText()); // Ajustado
            int litros = Integer.parseInt(litrosMosto.getText());
            int botellas = Integer.parseInt(botellasCaja.getText());
            
            Ejecucion hilo = new Ejecucion(textAreaA, textAreaB, duracionHoras, numReplicas, delaySegundos, capacidad, litros, botellas);
            hilo.start();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addDoubleInputValidation(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.')) {
                    e.consume();
                }
            }
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new EjerUI().setVisible(true));
    }
}
