package com.mycompany.proyecto;

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
        JLabel jLabelReplicas = new JLabel("Número de réplicas:");
        JLabel jLabelDelay = new JLabel("Delay (segundos):");
        JLabel jLabelAnimacionDelay = new JLabel("Delay de animación (segundos):"); // Nueva etiqueta
        JLabel jLabelTorneado = new JLabel("Máquinas en torneado:");
        JLabel jLabelFresado = new JLabel("Máquinas en fresado:");
        JLabel jLabelTaladro = new JLabel("Máquinas en taladro:");
        JLabel jLabelRectificado = new JLabel("Máquinas en rectificado:");
        JLabel jLabelMediaLlegada = new JLabel("Media de llegada (segundos):");
        JLabel jLabelTiempoInspeccion = new JLabel("Media de tiempo de inspección (segundos):");

        // Inicializando campos de texto
        JTextField simulacionHoras = new JTextField("1000");
        JTextField replicas = new JTextField("1");
        JTextField delay = new JTextField("1000");
        JTextField animacionDelay = new JTextField("10"); // Nuevo campo con valor por defecto
        JTextField maquinasTorneado = new JTextField("1");
        JTextField maquinasFresado = new JTextField("1");
        JTextField maquinasTaladro = new JTextField("1");
        JTextField maquinasRectificado = new JTextField("1");
        JTextField mediaLlegada = new JTextField("12");
        JTextField tiempoInspeccion = new JTextField("3");

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
        addDoubleInputValidation(replicas);
        addDoubleInputValidation(delay);
        addDoubleInputValidation(animacionDelay); // Validación para el nuevo campo
        addDoubleInputValidation(maquinasTorneado);
        addDoubleInputValidation(maquinasFresado);
        addDoubleInputValidation(maquinasTaladro);
        addDoubleInputValidation(maquinasRectificado);
        addDoubleInputValidation(mediaLlegada);
        addDoubleInputValidation(tiempoInspeccion);

        simularEjercicio.setText("Simular");
        simularEjercicio.addActionListener(evt -> simularEjercicioA(evt, simulacionHoras, replicas, delay, animacionDelay, maquinasTorneado, maquinasFresado, maquinasTaladro, maquinasRectificado, mediaLlegada, tiempoInspeccion));

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
                                                                        .addComponent(jLabelReplicas)
                                                                        .addComponent(jLabelDelay)
                                                                        .addComponent(jLabelAnimacionDelay) // Nueva etiqueta añadida al diseño
                                                                        .addComponent(jLabelTorneado)
                                                                        .addComponent(jLabelFresado)
                                                                        .addComponent(jLabelTaladro)
                                                                        .addComponent(jLabelRectificado)
                                                                        .addComponent(jLabelMediaLlegada)
                                                                        .addComponent(jLabelTiempoInspeccion))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(simulacionHoras)
                                                                        .addComponent(replicas)
                                                                        .addComponent(delay)
                                                                        .addComponent(animacionDelay) // Nuevo campo añadido al diseño
                                                                        .addComponent(maquinasTorneado)
                                                                        .addComponent(maquinasFresado)
                                                                        .addComponent(maquinasTaladro)
                                                                        .addComponent(maquinasRectificado)
                                                                        .addComponent(mediaLlegada)
                                                                        .addComponent(tiempoInspeccion, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
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
                                        .addComponent(jLabelAnimacionDelay) // Nueva etiqueta añadida
                                        .addComponent(animacionDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) // Nuevo campo añadido
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTorneado)
                                        .addComponent(maquinasTorneado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelFresado)
                                        .addComponent(maquinasFresado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTaladro)
                                        .addComponent(maquinasTaladro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelRectificado)
                                        .addComponent(maquinasRectificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelMediaLlegada)
                                        .addComponent(mediaLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTiempoInspeccion)
                                        .addComponent(tiempoInspeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(simularEjercicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void simularEjercicioA(java.awt.event.ActionEvent evt, JTextField simulacionHoras, JTextField replicas, JTextField delay, JTextField animacionDelay, JTextField maquinasTorneado, JTextField maquinasFresado, JTextField maquinasTaladro, JTextField maquinasRectificado, JTextField mediaLlegada, JTextField tiempoInspeccion) {
        try {
            int duracionHoras = Integer.parseInt(simulacionHoras.getText());
            int numReplicas = Integer.parseInt(replicas.getText());
            int delaySegundos = Integer.parseInt(delay.getText());
            int delayAnimacion = Integer.parseInt(animacionDelay.getText()); // Obtención de delay de animación
            int torneado = Integer.parseInt(maquinasTorneado.getText());
            int fresado = Integer.parseInt(maquinasFresado.getText());
            int taladro = Integer.parseInt(maquinasTaladro.getText());
            int rectificado = Integer.parseInt(maquinasRectificado.getText());
            int mediaLleg = Integer.parseInt(mediaLlegada.getText());
            int tiempoInsp = Integer.parseInt(tiempoInspeccion.getText());
            // Ejecución del hilo o lógica de simulación con los datos
            Ejecucion1 hilo = new Ejecucion1(textAreaA, textAreaB, duracionHoras, numReplicas, delaySegundos, delayAnimacion, torneado, fresado, taladro, rectificado, mediaLleg, tiempoInsp);
            //Ejecucion1 hilo = new Ejecucion1(textAreaA, textAreaB, duracionHoras, delaySegundos, torneado, fresado, taladro, mediaLleg, tiempoInsp);
            hilo.start();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();

            // Muestra un mensaje simple al usuario
            JOptionPane.showMessageDialog(this, "Ocurrió un error durante la simulación. Revisa los detalles del error.", "Error", JOptionPane.ERROR_MESSAGE);

            // Opcional: mostrar el stack trace en consola
            System.err.println(stackTrace);

            // Opcional: guardar el stack trace en un archivo
            try (FileWriter fileWriter = new FileWriter("error_log.txt", true)) {
                fileWriter.write(stackTrace);
            } catch (IOException ioException) {
                System.err.println("Error al escribir el archivo de log: " + ioException.getMessage());
            }
        }
    }


    // Método para agregar validación de entrada solo para números dobles
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

    private javax.swing.JButton simularEjercicio;
    private javax.swing.JTabbedPane jTabbedPane1;
}
