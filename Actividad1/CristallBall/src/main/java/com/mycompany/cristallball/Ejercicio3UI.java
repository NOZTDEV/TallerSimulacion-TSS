package com.mycompany.cristallball;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.LogNormalDistribution;

public class Ejercicio3UI extends javax.swing.JFrame {

    // Áreas de texto para mostrar resultados
    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private JTextArea textAreaC;

    public Ejercicio3UI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Inicializando etiquetas
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();  // Nueva etiqueta para número de iteraciones

        // Inicializando campo de texto
        costoTenenciaInventario = new JTextField();
        numeroIteraciones = new JTextField();  // Nuevo campo de texto para número de iteraciones

        simularEjercicio = new JButton();
        jTabbedPane1 = new JTabbedPane();

        // Inicializando áreas de texto
        textAreaA = new JTextArea(10, 30);
        textAreaB = new JTextArea(10, 30);
        textAreaC = new JTextArea(10, 30);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Introducir Datos");

        jLabel2.setText("Costo de tenencia de inventario:");
        jLabel3.setText("Número de iteraciones:");  // Etiqueta para número de iteraciones

        // Añadiendo validación para que solo se puedan ingresar números de tipo double
        addDoubleInputValidation(costoTenenciaInventario);
        addDoubleInputValidation(numeroIteraciones);  // Validación para número de iteraciones

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
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(costoTenenciaInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)  // Nueva etiqueta
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numeroIteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))  // Nuevo campo de texto
                            .addComponent(simularEjercicio))
                        .addGap(0, 30, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(costoTenenciaInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)  // Nueva etiqueta para número de iteraciones
                    .addComponent(numeroIteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))  // Nuevo campo de texto
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
            // Obtener valor del campo de texto
            double costoTenencia = Double.parseDouble(costoTenenciaInventario.getText());
            int iteraciones = Integer.parseInt(numeroIteraciones.getText());
            textAreaA.append("Costo de tenenica: " + costoTenencia + "\n");
            textAreaA.append("Número de iteraciones: " + iteraciones + "\n");
            //Costo de transporte a:
            double costoTransporteDeposito1 = 15;
            double costoTransporteDeposito2 = 12.5;
            //Costo de transporte a:
            double costoTransporteVentaSD11 = 6.5;
            double costoTransporteVentaSD12 = 7.5;
            double costoTransporteVentaSD13 = 9;
            double costoTransporteVentaSD21 = 9;
            double costoTransporteVentaSD22 = 8;
            double costoTransporteVentaSD23 = 7;
            List<Double> costosTotales1 = new ArrayList<>();
            List<Double> faltanteEnPeorCasos1 = new ArrayList<>();
            int i = 0;
            while(i < iteraciones){
                textAreaA.append("Iteración: " + (i+1) + "\n");
                //Punto de venta
                double invInicialRO1 = 120;
                double invInicialRO2 = 180;
                double invInicialRO3 = 80;
                List<Double> SD1 = new ArrayList<>(List.of(80.0, 100.0, 45.0));
                List<Double> SD2 = new ArrayList<>(List.of(200.0, 310.0, 985.0));
                double suministroRecividoRO1 = SD1.get(0) + SD2.get(0);
                double suministroRecividoRO2 = SD1.get(1) + SD2.get(1);
                double suministroRecividoRO3 = SD1.get(2) + SD2.get(2);
                //Distribucion logaritmica normal
                List<Double> demandaCumplida = new ArrayList<>();
                if(i == 0){
                    //List<Double> demandaCumplida = new ArrayList<>(List.of(400.0, 500.0, 650.0));
                    demandaCumplida.add(400.0);
                    demandaCumplida.add(500.0);
                    demandaCumplida.add(650.0);
                }else{
                    LogNormalDistribution logNormalDistribution1 = new LogNormalDistribution(Math.log(400), 50);
                    double demanda1 = logNormalDistribution1.sample();
                    LogNormalDistribution logNormalDistribution2 = new LogNormalDistribution(Math.log(500), 75);
                    double demanda2 = logNormalDistribution2.sample();
                    LogNormalDistribution logNormalDistribution3 = new LogNormalDistribution(Math.log(650), 100);
                    double demanda3 = logNormalDistribution3.sample();
                    //List<Double> demandaCumplida = new ArrayList<>(List.of(demanda1, demanda2, demanda3));
                    demandaCumplida.add(demanda1);
                    demandaCumplida.add(demanda2);
                    demandaCumplida.add(demanda3);
                }
                double invFinalRO1 = invInicialRO1 + suministroRecividoRO1 - demandaCumplida.get(0);
                double invFinalRO2 = invInicialRO2 + suministroRecividoRO2 - demandaCumplida.get(1);
                double invFinalRO3 = invInicialRO3 + suministroRecividoRO3 - demandaCumplida.get(2);
                double costoInvRO1 = 0;
                double costoInvRO2 = 0;
                double costoInvRO3 = 0;
                if(invFinalRO1 > 0){
                    costoInvRO1 = invFinalRO1 * costoTenencia;
                }
                if (invFinalRO2 > 0){
                    costoInvRO2 = invFinalRO2 * costoTenencia;
                }
                if (invFinalRO3 > 0){
                    costoInvRO3 = invFinalRO3 * costoTenencia;
                }
                double costoTransporteRO1 = (SD1.get(0) * costoTransporteVentaSD11) + (SD2.get(0) * costoTransporteVentaSD21);
                double costoTransporteRO2 = (SD1.get(1) * costoTransporteVentaSD12) + (SD2.get(1) * costoTransporteVentaSD22);
                double costoTransporteRO3 = (SD1.get(2) * costoTransporteVentaSD13) + (SD2.get(2) * costoTransporteVentaSD23);

                double desavastecimientoRO1 = demandaCumplida.get(0) - invInicialRO1 - suministroRecividoRO1;
                double desavastecimientoRO2 = demandaCumplida.get(1) - invInicialRO2 - suministroRecividoRO2;
                double desavastecimientoRO3 = demandaCumplida.get(2) - invInicialRO3 - suministroRecividoRO3;
                List<Double> totales = deposito(SD1, SD2, costoTenencia, costoTransporteDeposito1, costoTransporteDeposito2, i);
                double costoTrans = costoTransporteRO1 + costoTransporteRO2 + costoTransporteRO3;
                double costoInv = costoInvRO1 + costoInvRO2 + costoInvRO3;
                double desavastecimiento = Math.max(desavastecimientoRO1, Math.max(desavastecimientoRO2, desavastecimientoRO3));
                double costoInventarios = costoInv + totales.get(0);
                double costoTransporter = costoTrans + totales.get(1);
                double costoTotal = costoInventarios + costoTransporter;
                textAreaA.append("Costo de inventarios: " + costoInventarios + "\n");
                textAreaA.append("Costo de transporte: " + costoTransporter + "\n");
                textAreaA.append("Costo total: " + costoTotal + "\n");
                textAreaA.append("Desavastecimiento en el peor de los casos: " + desavastecimiento + "\n");
                textAreaA.append("--------------------------------------------------------------------\n");
                costosTotales1.add(costoTotal);
                faltanteEnPeorCasos1.add(desavastecimiento);
                i++;
            }
            SwingUtilities.invokeLater(() -> {
                Grafico chart = new Grafico(costosTotales1,calcularMedia(costosTotales1));
                chart.setSize(800, 600);
                chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chart.setVisible(true);
            });
            SwingUtilities.invokeLater(() -> {
                GraficoGalonesFaltantes grafico = new GraficoGalonesFaltantes(faltanteEnPeorCasos1);
                grafico.setVisible(true);
            });
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un valor numérico válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Refineria
    private double refineria(double demandaSD1, double demandaSD2, double costoTenencia, int i){
        double invInicial = 200;
        //Distribución normal
        double produccion = 0;
        if(i == 0){
            produccion = 2000;
        }else{
            NormalDistribution normalDistribution = new NormalDistribution(2000, 450);
            produccion = normalDistribution.sample();
        }
        double demandaCumplida = demandaSD1 + demandaSD2;
        double invFinal = invInicial + produccion - demandaCumplida;
        double costoInv = invFinal * costoTenencia;
        return costoInv;
    }
    
    //Deposito
    private List<Double> deposito(List<Double> SD1, List<Double> SD2, double costoTenencia, double costo1, double costo2, int i){
        double invInicialSD1 = 50;
        double invInicialSD2 = 100;
        double suministroRecibidoSD1 = 165;
        double suministroRecibidoSD2 = 1965;
        double costoRefineria = refineria(suministroRecibidoSD1, suministroRecibidoSD2, costoTenencia, i);
        double demandaCumplidaSD1 = 0;
        for (Double num : SD1) {
            demandaCumplidaSD1 += num;
        }
        double demandaCumplidaSD2 = 0;
        for (Double num : SD2) {
            demandaCumplidaSD2 += num;
        }
        double invFinalSD1 = invInicialSD1 + suministroRecibidoSD1 - demandaCumplidaSD1;
        double invFinalSD2 = invInicialSD2 + suministroRecibidoSD2 - demandaCumplidaSD2;
        double costoInvSD1 = 0;
        double costoInvSD2 = 0;
        if(invFinalSD1 > 0){
            costoInvSD1 = invFinalSD1 * costoTenencia;
        }
        if (invFinalSD2 > 0){
            costoInvSD2 = invFinalSD2 * costoTenencia;
        }
        double costoTransporteSD1 = suministroRecibidoSD1 * costo1;
        double costoTransporteSD2 = suministroRecibidoSD2 * costo2;
        double costoInv = costoInvSD1 + costoInvSD2 + costoRefineria;
        double costoTransporte = costoTransporteSD1 + costoTransporteSD2;
        List<Double> totales = new ArrayList<>(List.of(costoInv, costoTransporte));
        return totales;
    }
    
    public static double calcularMedia(List<Double> costos) {
        if (costos == null || costos.isEmpty()) {
            throw new IllegalArgumentException("La lista de costos no puede estar vacía.");
        }
        double suma = 0.0;
        for (double costo : costos) {
            suma += costo; // Sumar cada costo
        }
        return suma / costos.size(); // Calcular la media
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
    private JTextField costoTenenciaInventario;
    private JTextField numeroIteraciones;  // Nuevo campo para número de iteraciones
    private JButton simularEjercicio;
    private JTabbedPane jTabbedPane1;
}