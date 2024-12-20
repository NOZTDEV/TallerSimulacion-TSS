package com.mycompany.proyecto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
        JLabel jLabelSimulacionHoras = new JLabel("Duración de simulación (horas):");
        JLabel jLabelDelay = new JLabel("Delay (segundos):");
        JLabel jLabelReparadores = new JLabel("Número de reparadores:");
        JLabel jLabelMotoresRepuesto = new JLabel("Número de motores de repuesto:");
        JLabel jLabelMontacargas = new JLabel("Número de montacargas:");
        JLabel jLabelMediaOperacion = new JLabel("Media de operación (horas):");
        JLabel jLabelMediaReparacion = new JLabel("Media de reparación (horas):");

        // Inicializando campos de texto
        JTextField simulacionHoras = new JTextField("1040");  
        JTextField delay = new JTextField("1000");  
        JTextField numeroReparadores = new JTextField("5");
        JTextField numeroMotoresRepuesto = new JTextField("3");
        JTextField numeroMontacargas = new JTextField("2");
        JTextField mediaOperacion = new JTextField("189");
        JTextField mediaReparacion = new JTextField("45");

        simularEjercicio = new JButton();
        jTabbedPane1 = new JTabbedPane();

        // Inicializando áreas de texto
        textAreaA = new JTextArea(10, 30);
        textAreaB = new JTextArea(10, 30);
        textAreaC = new JTextArea(10, 30);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Introducir Datos");

        // Añadiendo validación para que solo se puedan ingresar números de tipo double
        addDoubleInputValidation(simulacionHoras);
        addDoubleInputValidation(delay);
        addDoubleInputValidation(numeroReparadores);
        addDoubleInputValidation(numeroMotoresRepuesto);
        addDoubleInputValidation(numeroMontacargas);
        addDoubleInputValidation(mediaOperacion);
        addDoubleInputValidation(mediaReparacion);

        simularEjercicio.setText("Simular");
        simularEjercicio.addActionListener(evt -> simularEjercicioA(evt, simulacionHoras, delay, numeroReparadores, numeroMotoresRepuesto, numeroMontacargas, mediaOperacion, mediaReparacion));

        // Configurando las áreas de texto como no editables
        textAreaA.setEditable(false);
        textAreaB.setEditable(false);
        textAreaC.setEditable(false);

        // Añadiendo las áreas de texto a cada panel de la pestaña correspondiente
        jTabbedPane1.addTab("Inciso A", new JScrollPane(textAreaA));
        jTabbedPane1.addTab("Inciso B", new JScrollPane(textAreaB));
        jTabbedPane1.addTab("Inciso C", new JScrollPane(textAreaC));

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
                                    .addComponent(jLabelDelay)
                                    .addComponent(jLabelReparadores)
                                    .addComponent(jLabelMotoresRepuesto)
                                    .addComponent(jLabelMontacargas)
                                    .addComponent(jLabelMediaOperacion)
                                    .addComponent(jLabelMediaReparacion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(simulacionHoras)
                                    .addComponent(delay)
                                    .addComponent(numeroReparadores)
                                    .addComponent(numeroMotoresRepuesto)
                                    .addComponent(numeroMontacargas)
                                    .addComponent(mediaOperacion)
                                    .addComponent(mediaReparacion, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
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
                    .addComponent(jLabelDelay)
                    .addComponent(delay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelReparadores)
                    .addComponent(numeroReparadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMotoresRepuesto)
                    .addComponent(numeroMotoresRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMontacargas)
                    .addComponent(numeroMontacargas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMediaOperacion)
                    .addComponent(mediaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMediaReparacion)
                    .addComponent(mediaReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(simularEjercicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }

    private void simularEjercicioA(java.awt.event.ActionEvent evt, JTextField simulacionHoras, JTextField delay, JTextField numeroReparadores, JTextField numeroMotoresRepuesto, JTextField numeroMontacargas, JTextField mediaOperacion, JTextField mediaReparacion) {                                                  
        try {
            int duracionHoras = Integer.parseInt(simulacionHoras.getText());
            int delaySegundos = Integer.parseInt(delay.getText());
            int reparadores = Integer.parseInt(numeroReparadores.getText());
            int motoresReserva = Integer.parseInt(numeroMotoresRepuesto.getText());
            int montacargas = Integer.parseInt(numeroMontacargas.getText());
            int mediaOp = Integer.parseInt(mediaOperacion.getText());
            int mediaRep = Integer.parseInt(mediaReparacion.getText());
            
            Ejecucion1 hilo = new Ejecucion1(textAreaA, textAreaB, duracionHoras, delaySegundos, reparadores, motoresReserva, montacargas, mediaOp, mediaRep);
            hilo.start();//iniciamos el programa
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce valores numéricos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para agregar validación de entrada solo para números dobles
    private void addDoubleInputValidation(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.')) {
                    e.consume();
                }
            }
        });
    }
    
    private javax.swing.JButton simularEjercicio;
    private javax.swing.JTabbedPane jTabbedPane1;
}
