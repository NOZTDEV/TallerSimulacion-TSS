package com.mycompany.cristallball;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.LogNormalDistribution;

public class Ejercicio2UI extends javax.swing.JFrame {

    // Áreas de texto para mostrar resultados
    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private JTextArea textAreaC;

    public Ejercicio2UI() {
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
            //Ejercicio 2
            double leadLag = 0;
            double duracionOp = 0;
            double duracionEsp = 0;
            double duracionPe = 0;
            double valorSimular = 0;
            double EST = 0;
            double EFT = 0;
            double LST = 0;
            double LFT = 0;
            double slack = 0;
            boolean rutaCritica = false;
            double probabilidad = 0;
            List<Integer> inicioMasTardio = new ArrayList<>();
            List<Double> probabilidades = new ArrayList<>();
            List<Integer> c1 = new ArrayList<>();
            List<Integer> c2 = new ArrayList<>();
            List<Integer> c3 = new ArrayList<>();
            List<Integer> c4 = new ArrayList<>();
            List<Integer> c5 = new ArrayList<>();
            List<Integer> c6 = new ArrayList<>();
            List<Integer> c7 = new ArrayList<>();
            List<Integer> c8 = new ArrayList<>();
            List<Integer> c9 = new ArrayList<>();
            List<Integer> c10 = new ArrayList<>();
            List<Integer> c11 = new ArrayList<>();
            List<Integer> c12 = new ArrayList<>();
            List<Integer> c13 = new ArrayList<>();
            List<Integer> c14 = new ArrayList<>();
            List<Integer> c15 = new ArrayList<>();
            List<Integer> c16 = new ArrayList<>();
            List<Integer> c17 = new ArrayList<>();
            List<Integer> c18 = new ArrayList<>();
            List<Integer> c19 = new ArrayList<>();
            List<Integer> c20 = new ArrayList<>();
            int i = 0;
            while(i < iteraciones){
                textAreaA.append("Iteración: " + (i+1) + "\n");
                if(i == 0){
                    textAreaA.append(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", 
                                        "Tarea", "Lead/Lag", "Dur.Opt.", "Dur.Esp.", "Dur.Pes.", "ValorSim.", 
                                        "EST", "EFT", "LST", "LFT", "Slack", "Ruta Crítica", "Probabilidad"));
                    List<Integer> predecesores0 = new ArrayList<>();
                    Tarea tarea0 = new Tarea("Task 0", predecesores0, 0, 0, 0, 0, 0);
                    List<Integer> EFTant0 = new ArrayList<>();
                    tarea0.calcularDatosIni(EFTant0);
                    
                    List<Integer> predecesores1 = new ArrayList<>(List.of(0));
                    Tarea tarea1 = new Tarea("Task 1", predecesores1, 0, 10, 15, 20, 15);
                    List<Integer> EFTant1 = new ArrayList<>(List.of(tarea0.getEFT()));
                    tarea1.calcularDatosIni(EFTant1);
                    List<Integer> predecesores2 = new ArrayList<>(List.of(1));
                    Tarea tarea2 = new Tarea("Task 2", predecesores2, -5, 15, 20, 22, 20);
                    List<Integer> EFTant2 = new ArrayList<>(List.of(tarea1.getEFT()));
                    tarea2.calcularDatosIni(EFTant2);
                    
                    List<Integer> predecesores3 = new ArrayList<>(List.of(2));
                    Tarea tarea3 = new Tarea("Task 3", predecesores3, 0, 21, 26, 30, 26);
                    List<Integer> EFTant3 = new ArrayList<>(List.of(tarea2.getEFT()));
                    tarea3.calcularDatosIni(EFTant3);
                    
                    List<Integer> predecesores4 = new ArrayList<>(List.of(2));
                    Tarea tarea4 = new Tarea("Task 4", predecesores4, 10, 15, 18, 23,18);
                    List<Integer> EFTant4 = new ArrayList<>(List.of(tarea2.getEST()));
                    tarea4.calcularDatosIni(EFTant4);
                    
                    List<Integer> predecesores5 = new ArrayList<>(List.of(4));
                    Tarea tarea5 = new Tarea("Task 5", predecesores5, 0, 13, 15, 17, 15);
                    List<Integer> EFTant5 = new ArrayList<>(List.of(tarea4.getEFT()));
                    tarea5.calcularDatosIni(EFTant5);
                    
                    List<Integer> predecesores6 = new ArrayList<>(List.of(3, 5));
                    Tarea tarea6 = new Tarea("Task 6", predecesores6, 0, 30, 38, 45, 38);
                    List<Integer> EFTant6 = new ArrayList<>(List.of(tarea3.getEFT(), tarea5.getEFT()));
                    tarea6.calcularDatosIni(EFTant6);
                    
                    List<Integer> predecesores7 = new ArrayList<>(List.of(6));
                    Tarea tarea7 = new Tarea("Task 7", predecesores7, -5, 20, 25, 30, 25);
                    List<Integer> EFTant7 = new ArrayList<>(List.of(tarea6.getEFT()));
                    tarea7.calcularDatosIni(EFTant7);
                    
                    List<Integer> predecesores8 = new ArrayList<>(List.of(6));
                    Tarea tarea8 = new Tarea("Task 8", predecesores8, 5, 10, 15, 20, 15);
                    List<Integer> EFTant8 = new ArrayList<>(List.of(tarea6.getEFT()));
                    tarea8.calcularDatosIni(EFTant8);
                    
                    List<Integer> predecesores9 = new ArrayList<>(List.of(6));
                    Tarea tarea9 = new Tarea("Task 9", predecesores9, 15, 11, 18, 22, 18);
                    List<Integer> EFTant9 = new ArrayList<>(List.of(tarea6.getEST()));
                    tarea9.calcularDatosIni(EFTant9);
                    
                    List<Integer> predecesores10 = new ArrayList<>(List.of(7,8,9));
                    Tarea tarea10 = new Tarea("Task 10", predecesores10, 0, 23, 30, 45, 30);
                    List<Integer> EFTant10 = new ArrayList<>(List.of(tarea7.getEFT(),tarea8.getEFT(),tarea9.getEFT()));
                    tarea10.calcularDatosIni(EFTant10);
                    
                    List<Integer> predecesores11 = new ArrayList<>(List.of(10));
                    Tarea tarea11 = new Tarea("Task 11", predecesores11, 5, 22, 28, 39, 28);
                    List<Integer> EFTant11 = new ArrayList<>(List.of(tarea10.getEFT()));
                    tarea11.calcularDatosIni(EFTant11);
                    
                    List<Integer> predecesores12 = new ArrayList<>(List.of(0));
                    Tarea tarea12 = new Tarea("Task 12", predecesores12, 0, 120, 140, 180, 140);
                    List<Integer> EFTant12 = new ArrayList<>(List.of(tarea0.getEFT()));
                    tarea12.calcularDatosIni(EFTant12);
                    
                    List<Integer> predecesores13 = new ArrayList<>(List.of(12));
                    Tarea tarea13 = new Tarea("Task 13", predecesores13, -5, 13, 18, 22, 18);
                    List<Integer> EFTant13 = new ArrayList<>(List.of(tarea12.getEFT()));
                    tarea13.calcularDatosIni(EFTant13);
                    
                    List<Integer> predecesores14 = new ArrayList<>(List.of(13));
                    Tarea tarea14 = new Tarea("Task 14", predecesores14, 10, 15, 20, 25, 20);
                    List<Integer> EFTant14 = new ArrayList<>(List.of(tarea13.getEST()));
                    tarea14.calcularDatosIni(EFTant14);
                    
                    List<Integer> predecesores15 = new ArrayList<>(List.of(14));
                    Tarea tarea15 = new Tarea("Task 15", predecesores15, 0, 10, 15, 20, 15);
                    List<Integer> EFTant15 = new ArrayList<>(List.of(tarea14.getEFT()));
                    tarea15.calcularDatosIni(EFTant15);
                    
                    List<Integer> predecesores16 = new ArrayList<>(List.of(11,15));
                    Tarea tarea16 = new Tarea("Task 16", predecesores16, 0, 30, 33, 60,33);
                    List<Integer> EFTant16 = new ArrayList<>(List.of(tarea11.getEFT(), tarea15.getEFT()));
                    tarea16.calcularDatosIni(EFTant16);
                    
                    List<Integer> predecesores17 = new ArrayList<>(List.of(16));
                    Tarea tarea17 = new Tarea("Task 17", predecesores17, 0, 5, 8, 11,8);
                    List<Integer> EFTant17 = new ArrayList<>(List.of(tarea16.getEFT()));
                    tarea17.calcularDatosIni(EFTant17);
                    
                    List<Integer> predecesores18 = new ArrayList<>(List.of(17));
                    Tarea tarea18 = new Tarea("Task 18", predecesores18, 0, 10, 15, 25,15);
                    List<Integer> EFTant18 = new ArrayList<>(List.of(tarea17.getEFT()));
                    tarea18.calcularDatosIni(EFTant18);
                    
                    List<Integer> predecesores19 = new ArrayList<>(List.of(17));
                    Tarea tarea19 = new Tarea("Task 19", predecesores19, 0, 13, 17, 19, 17);
                    List<Integer> EFTant19 = new ArrayList<>(List.of(tarea17.getEFT()));
                    tarea19.calcularDatosIni(EFTant19);
                    
                    List<Integer> predecesores20 = new ArrayList<>(List.of(18, 19));
                    Tarea tarea20 = new Tarea("Task 20", predecesores20, 0, 20, 25, 45, 25);
                    List<Integer> EFTant20 = new ArrayList<>(List.of(tarea18.getEFT(), tarea19.getEFT()));
                    tarea20.calcularDatosIni(EFTant20);
                    
                    List<Integer> LSTant20 = new ArrayList<>();
                    tarea20.calcularDatosV(LSTant20);
                    
                    List<Integer> LSTant19 = new ArrayList<>(List.of(tarea20.getLST()));
                    tarea19.calcularDatosV(LSTant19);
                    
                    List<Integer> LSTant18 = new ArrayList<>(List.of(tarea20.getLST()));
                    tarea18.calcularDatosV(LSTant18);
                    
                    List<Integer> LSTant17 = new ArrayList<>(List.of(tarea18.getLST(), tarea19.getLST()));
                    tarea17.calcularDatosV(LSTant17);
                    
                    List<Integer> LSTant16 = new ArrayList<>(List.of(tarea17.getLST()));
                    tarea16.calcularDatosV(LSTant16);
                    List<Integer> LSTant15 = new ArrayList<>(List.of(tarea16.getLST()));
                    tarea15.calcularDatosV(LSTant15);
                    List<Integer> LSTant14 = new ArrayList<>(List.of(tarea15.getLST()));
                    tarea14.calcularDatosV(LSTant14);
                    List<Integer> LSTant13 = new ArrayList<>(List.of(tarea14.getLST()));
                    tarea13.calcularDatosV1(LSTant13, tarea14.getLeadLag());
                    
                    List<Integer> LSTant12 = new ArrayList<>(List.of(tarea13.getLST()));
                    tarea12.calcularDatosV2(LSTant12, tarea13.getLeadLag());
                    
                    List<Integer> LSTant11 = new ArrayList<>(List.of(tarea16.getLST()));
                    tarea11.calcularDatosV(LSTant11);
                    
                    List<Integer> LSTant10 = new ArrayList<>(List.of(tarea11.getLST()));
                    tarea10.calcularDatosV2(LSTant10, tarea11.getLeadLag());
                    
                    List<Integer> LSTant9 = new ArrayList<>(List.of(tarea10.getLST()));
                    tarea9.calcularDatosV(LSTant9);
                    
                    List<Integer> LSTant8 = new ArrayList<>(List.of(tarea10.getLST()));
                    tarea8.calcularDatosV(LSTant8);

                    List<Integer> LSTant7 = new ArrayList<>(List.of(tarea10.getLST()));
                    tarea7.calcularDatosV(LSTant7);
                    
                    int num1 = tarea7.getLST()-tarea7.getLeadLag()- (int)tarea6.getValorSimulado();
                    int num2 = tarea8.getLST()-tarea8.getLeadLag()- (int)tarea6.getValorSimulado();
                    int num3 = tarea9.getLST()-tarea9.getLeadLag();
                    int min6 = Math.min(num1, Math.min(num2, num3));
                    tarea6.calcularDatosV3(min6);
                    
                    List<Integer> LSTant5 = new ArrayList<>(List.of(tarea6.getLST()));
                    tarea5.calcularDatosV(LSTant5);
                    List<Integer> LSTant4 = new ArrayList<>(List.of(tarea5.getLST()));
                    tarea4.calcularDatosV(LSTant4);
                    List<Integer> LSTant3 = new ArrayList<>(List.of(tarea6.getLST()));
                    tarea3.calcularDatosV(LSTant3);
                    
                    int num4 = tarea3.getLST() - (int)tarea2.getValorSimulado();
                    int num5 = tarea4.getLST()-tarea4.getLeadLag();
                    int min2 = Math.min(num4, num5);
                    tarea2.calcularDatosV3(min2);
                    
                    List<Integer> LSTant1 = new ArrayList<>(List.of(tarea2.getLST()));
                    tarea1.calcularDatosV2(LSTant1, tarea2.getLeadLag());
                    
                    int min3 = Math.min(tarea2.getLST(), tarea12.getLST());
                    tarea0.calcularDatosV3(min3);
                    
                    //Impresiones
                    textAreaA.append(tarea0.obtenerResumen());
                    textAreaA.append(tarea1.obtenerResumen());
                    textAreaA.append(tarea2.obtenerResumen());
                    textAreaA.append(tarea3.obtenerResumen());
                    textAreaA.append(tarea4.obtenerResumen());
                    textAreaA.append(tarea5.obtenerResumen());
                    textAreaA.append(tarea6.obtenerResumen());
                    textAreaA.append(tarea7.obtenerResumen());
                    textAreaA.append(tarea8.obtenerResumen());
                    textAreaA.append(tarea9.obtenerResumen());
                    textAreaA.append(tarea10.obtenerResumen());
                    textAreaA.append(tarea11.obtenerResumen());
                    textAreaA.append(tarea12.obtenerResumen());
                    textAreaA.append(tarea13.obtenerResumen());
                    textAreaA.append(tarea14.obtenerResumen());
                    textAreaA.append(tarea15.obtenerResumen());
                    textAreaA.append(tarea16.obtenerResumen());
                    textAreaA.append(tarea17.obtenerResumen());
                    textAreaA.append(tarea18.obtenerResumen());
                    textAreaA.append(tarea19.obtenerResumen());
                    textAreaA.append(tarea20.obtenerResumen());
                    textAreaA.append("-----------------------------------------------------------------------------\n");
                    inicioMasTardio.add(tarea20.getLFT());
                    if (tarea1.getCritica()) {
                        c1.add(1);
                    } else {
                        c1.add(0);
                    }
                    if (tarea2.getCritica()) {
                        c2.add(1);
                    } else {
                        c2.add(0);
                    }
                    if (tarea3.getCritica()) {
                        c3.add(1);
                    } else {
                        c3.add(0);
                    }
                    if (tarea4.getCritica()) {
                        c4.add(1);
                    } else {
                        c4.add(0);
                    }
                    if (tarea5.getCritica()) {
                        c5.add(1);
                    } else {
                        c5.add(0);
                    }
                    if (tarea6.getCritica()) {
                        c6.add(1);
                    } else {
                        c6.add(0);
                    }
                    if (tarea7.getCritica()) {
                        c7.add(1);
                    } else {
                        c7.add(0);
                    }
                    if (tarea8.getCritica()) {
                        c8.add(1);
                    } else {
                        c8.add(0);
                    }
                    if (tarea9.getCritica()) {
                        c9.add(1);
                    } else {
                        c9.add(0);
                    }
                    if (tarea10.getCritica()) {
                        c10.add(1);
                    } else {
                        c10.add(0);
                    }
                    if (tarea11.getCritica()) {
                        c11.add(1);
                    } else {
                        c11.add(0);
                    }
                    if (tarea12.getCritica()) {
                        c12.add(1);
                    } else {
                        c12.add(0);
                    }
                    if (tarea13.getCritica()) {
                        c13.add(1);
                    } else {
                        c13.add(0);
                    }
                    if (tarea14.getCritica()) {
                        c14.add(1);
                    } else {
                        c14.add(0);
                    }
                    if (tarea15.getCritica()) {
                        c15.add(1);
                    } else {
                        c15.add(0);
                    }
                    if (tarea16.getCritica()) {
                        c16.add(1);
                    } else {
                        c16.add(0);
                    }
                    if (tarea17.getCritica()) {
                        c17.add(1);
                    } else {
                        c17.add(0);
                    }
                    if (tarea18.getCritica()) {
                        c18.add(1);
                    } else {
                        c18.add(0);
                    }
                    if (tarea19.getCritica()) {
                        c19.add(1);
                    } else {
                        c19.add(0);
                    }
                    if (tarea20.getCritica()) {
                        c20.add(1);
                    } else {
                        c20.add(0);
                    }
                }else{
                    //List<Tarea> listaDeTareas = new ArrayList<>();
                    textAreaA.append(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", 
                                        "Tarea", "Lead/Lag", "Dur.Opt.", "Dur.Esp.", "Dur.Pes.", "ValorSim.", 
                                        "EST", "EFT", "LST", "LFT", "Slack", "Ruta Crítica", "Probabilidad"));
                    
                    List<Integer> predecesores0 = new ArrayList<>();
                    Tarea tarea0 = new Tarea("Task 0", predecesores0, 0, 0, 0, 0);
                    List<Integer> EFTant0 = new ArrayList<>();
                    tarea0.calcularDatos(EFTant0, 0);
                    
                    List<Integer> predecesores1 = new ArrayList<>(List.of(0));
                    Tarea tarea1 = new Tarea("Task 1", predecesores1, 0, 10, 15, 20);
                    List<Integer> EFTant1 = new ArrayList<>(List.of(tarea0.getEFT()));
                    tarea1.calcularDatos(EFTant1, 0);
                    
                    List<Integer> predecesores2 = new ArrayList<>(List.of(1));
                    Tarea tarea2 = new Tarea("Task 2", predecesores2, -5, 15, 20, 22);
                    List<Integer> EFTant2 = new ArrayList<>(List.of(tarea1.getEFT()));
                    tarea2.calcularDatos(EFTant2, tarea1.getLFT());
                    
                    List<Integer> predecesores3 = new ArrayList<>(List.of(2));
                    Tarea tarea3 = new Tarea("Task 3", predecesores3, 0, 21, 26, 30);
                    List<Integer> EFTant3 = new ArrayList<>(List.of(tarea2.getEFT()));
                    tarea3.calcularDatos(EFTant3, tarea2.getLFT());
                    
                    List<Integer> predecesores4 = new ArrayList<>(List.of(2));
                    Tarea tarea4 = new Tarea("Task 4", predecesores4, 10, 15, 18, 23);
                    List<Integer> EFTant4 = new ArrayList<>(List.of(tarea2.getEST()));
                    tarea4.calcularDatos(EFTant4, tarea3.getLFT());
                    
                    List<Integer> predecesores5 = new ArrayList<>(List.of(4));
                    Tarea tarea5 = new Tarea("Task 5", predecesores5, 0, 13, 15, 17);
                    List<Integer> EFTant5 = new ArrayList<>(List.of(tarea4.getEFT()));
                    tarea5.calcularDatos(EFTant5, tarea4.getLFT());
                    
                    List<Integer> predecesores6 = new ArrayList<>(List.of(3, 5));
                    Tarea tarea6 = new Tarea("Task 6", predecesores6, 0, 30, 38, 45);
                    List<Integer> EFTant6 = new ArrayList<>(List.of(tarea3.getEFT(), tarea5.getEFT()));
                    tarea6.calcularDatos(EFTant6, tarea5.getLFT());
                    
                    List<Integer> predecesores7 = new ArrayList<>(List.of(6));
                    Tarea tarea7 = new Tarea("Task 7", predecesores7, -5, 20, 25, 30);
                    List<Integer> EFTant7 = new ArrayList<>(List.of(tarea6.getEFT()));
                    tarea7.calcularDatos(EFTant7, tarea6.getLFT());
                    
                    List<Integer> predecesores8 = new ArrayList<>(List.of(6));
                    Tarea tarea8 = new Tarea("Task 8", predecesores8, 5, 10, 15, 20);
                    List<Integer> EFTant8 = new ArrayList<>(List.of(tarea6.getEFT()));
                    tarea8.calcularDatos(EFTant8, tarea7.getLFT());
                    
                    List<Integer> predecesores9 = new ArrayList<>(List.of(6));
                    Tarea tarea9 = new Tarea("Task 9", predecesores9, 15, 11, 18, 22);
                    List<Integer> EFTant9 = new ArrayList<>(List.of(tarea6.getEST()));
                    tarea9.calcularDatos(EFTant9, tarea8.getLFT());
                    
                    List<Integer> predecesores10 = new ArrayList<>(List.of(7,8,9));
                    Tarea tarea10 = new Tarea("Task 10", predecesores10, 0, 23, 30, 45);
                    List<Integer> EFTant10 = new ArrayList<>(List.of(tarea7.getEFT(),tarea8.getEFT(),tarea9.getEFT()));
                    tarea10.calcularDatos(EFTant10, tarea9.getLFT());
                    
                    List<Integer> predecesores11 = new ArrayList<>(List.of(10));
                    Tarea tarea11 = new Tarea("Task 11", predecesores11, 5, 22, 28, 39);
                    List<Integer> EFTant11 = new ArrayList<>(List.of(tarea10.getEFT()));
                    tarea11.calcularDatos(EFTant11, tarea10.getLFT());
                    
                    List<Integer> predecesores12 = new ArrayList<>(List.of(0));
                    Tarea tarea12 = new Tarea("Task 12", predecesores12, 0, 120, 140, 180);
                    List<Integer> EFTant12 = new ArrayList<>(List.of(tarea0.getEFT()));
                    tarea12.calcularDatos(EFTant12, tarea11.getLFT());
                    
                    List<Integer> predecesores13 = new ArrayList<>(List.of(12));
                    Tarea tarea13 = new Tarea("Task 13", predecesores13, -5, 13, 18, 22);
                    List<Integer> EFTant13 = new ArrayList<>(List.of(tarea12.getEFT()));
                    tarea13.calcularDatos(EFTant13, tarea12.getLFT());
                    
                    List<Integer> predecesores14 = new ArrayList<>(List.of(13));
                    Tarea tarea14 = new Tarea("Task 14", predecesores14, 10, 15, 20, 25);
                    List<Integer> EFTant14 = new ArrayList<>(List.of(tarea13.getEST()));
                    tarea14.calcularDatos(EFTant14, tarea13.getLFT());
                    
                    List<Integer> predecesores15 = new ArrayList<>(List.of(14));
                    Tarea tarea15 = new Tarea("Task 15", predecesores15, 0, 10, 15, 20);
                    List<Integer> EFTant15 = new ArrayList<>(List.of(tarea14.getEFT()));
                    tarea15.calcularDatos(EFTant15, tarea14.getLFT());
                    
                    List<Integer> predecesores16 = new ArrayList<>(List.of(11,15));
                    Tarea tarea16 = new Tarea("Task 16", predecesores16, 0, 30, 33, 60);
                    List<Integer> EFTant16 = new ArrayList<>(List.of(tarea11.getEFT(), tarea15.getEFT()));
                    tarea16.calcularDatos(EFTant16, tarea15.getLFT());
                    
                    List<Integer> predecesores17 = new ArrayList<>(List.of(16));
                    Tarea tarea17 = new Tarea("Task 17", predecesores17, 0, 5, 8, 11);
                    List<Integer> EFTant17 = new ArrayList<>(List.of(tarea16.getEFT()));
                    tarea17.calcularDatos(EFTant17, tarea16.getLFT());
                    
                    List<Integer> predecesores18 = new ArrayList<>(List.of(17));
                    Tarea tarea18 = new Tarea("Task 18", predecesores18, 0, 10, 15, 25);
                    List<Integer> EFTant18 = new ArrayList<>(List.of(tarea17.getEFT()));
                    tarea18.calcularDatos(EFTant18, tarea17.getLFT());
                    
                    List<Integer> predecesores19 = new ArrayList<>(List.of(17));
                    Tarea tarea19 = new Tarea("Task 19", predecesores19, 0, 13, 17, 19);
                    List<Integer> EFTant19 = new ArrayList<>(List.of(tarea17.getEFT()));
                    tarea19.calcularDatos(EFTant19, tarea18.getLFT());
                    
                    List<Integer> predecesores20 = new ArrayList<>(List.of(18, 19));
                    Tarea tarea20 = new Tarea("Task 20", predecesores20, 0, 20, 25, 45);
                    List<Integer> EFTant20 = new ArrayList<>(List.of(tarea18.getEFT(), tarea19.getEFT()));
                    tarea20.calcularDatos(EFTant20, tarea19.getLFT());
                    
                    List<Integer> LSTant20 = new ArrayList<>();
                    tarea20.calcularDatosV(LSTant20);
                    
                    List<Integer> LSTant19 = new ArrayList<>(List.of(tarea20.getLST()));
                    tarea19.calcularDatosV(LSTant19);
                    
                    List<Integer> LSTant18 = new ArrayList<>(List.of(tarea20.getLST()));
                    tarea18.calcularDatosV(LSTant18);
                    
                    List<Integer> LSTant17 = new ArrayList<>(List.of(tarea18.getLST(), tarea19.getLST()));
                    tarea17.calcularDatosV(LSTant17);
                    
                    List<Integer> LSTant16 = new ArrayList<>(List.of(tarea17.getLST()));
                    tarea16.calcularDatosV(LSTant16);
                    List<Integer> LSTant15 = new ArrayList<>(List.of(tarea16.getLST()));
                    tarea15.calcularDatosV(LSTant15);
                    List<Integer> LSTant14 = new ArrayList<>(List.of(tarea15.getLST()));
                    tarea14.calcularDatosV(LSTant14);
                    List<Integer> LSTant13 = new ArrayList<>(List.of(tarea14.getLST()));
                    tarea13.calcularDatosV1(LSTant13, tarea14.getLeadLag());
                    
                    List<Integer> LSTant12 = new ArrayList<>(List.of(tarea13.getLST()));
                    tarea12.calcularDatosV2(LSTant12, tarea13.getLeadLag());
                    
                    List<Integer> LSTant11 = new ArrayList<>(List.of(tarea16.getLST()));
                    tarea11.calcularDatosV(LSTant11);
                    
                    List<Integer> LSTant10 = new ArrayList<>(List.of(tarea11.getLST()));
                    tarea10.calcularDatosV2(LSTant10, tarea11.getLeadLag());
                    
                    List<Integer> LSTant9 = new ArrayList<>(List.of(tarea10.getLST()));
                    tarea9.calcularDatosV(LSTant9);
                    
                    List<Integer> LSTant8 = new ArrayList<>(List.of(tarea10.getLST()));
                    tarea8.calcularDatosV(LSTant8);

                    List<Integer> LSTant7 = new ArrayList<>(List.of(tarea10.getLST()));
                    tarea7.calcularDatosV(LSTant7);
                    
                    int num1 = tarea7.getLST()-tarea7.getLeadLag()- (int)tarea6.getValorSimulado();
                    int num2 = tarea8.getLST()-tarea8.getLeadLag()- (int)tarea6.getValorSimulado();
                    int num3 = tarea9.getLST()-tarea9.getLeadLag();
                    int min6 = Math.min(num1, Math.min(num2, num3));
                    tarea6.calcularDatosV3(min6);
                    
                    List<Integer> LSTant5 = new ArrayList<>(List.of(tarea6.getLST()));
                    tarea5.calcularDatosV(LSTant5);
                    List<Integer> LSTant4 = new ArrayList<>(List.of(tarea5.getLST()));
                    tarea4.calcularDatosV(LSTant4);
                    List<Integer> LSTant3 = new ArrayList<>(List.of(tarea6.getLST()));
                    tarea3.calcularDatosV(LSTant3);
                    
                    int num4 = tarea3.getLST() - (int)tarea2.getValorSimulado();
                    int num5 = tarea4.getLST()-tarea4.getLeadLag();
                    int min2 = Math.min(num4, num5);
                    tarea2.calcularDatosV3(min2);
                    
                    List<Integer> LSTant1 = new ArrayList<>(List.of(tarea2.getLST()));
                    tarea1.calcularDatosV2(LSTant1, tarea2.getLeadLag());
                    
                    int min3 = Math.min(tarea2.getLST(), tarea12.getLST());
                    tarea0.calcularDatosV3(min3);
                    
                    //Impresiones
                    textAreaA.append(tarea0.obtenerResumen());
                    textAreaA.append(tarea1.obtenerResumen());
                    textAreaA.append(tarea2.obtenerResumen());
                    textAreaA.append(tarea3.obtenerResumen());
                    textAreaA.append(tarea4.obtenerResumen());
                    textAreaA.append(tarea5.obtenerResumen());
                    textAreaA.append(tarea6.obtenerResumen());
                    textAreaA.append(tarea7.obtenerResumen());
                    textAreaA.append(tarea8.obtenerResumen());
                    textAreaA.append(tarea9.obtenerResumen());
                    textAreaA.append(tarea10.obtenerResumen());
                    textAreaA.append(tarea11.obtenerResumen());
                    textAreaA.append(tarea12.obtenerResumen());
                    textAreaA.append(tarea13.obtenerResumen());
                    textAreaA.append(tarea14.obtenerResumen());
                    textAreaA.append(tarea15.obtenerResumen());
                    textAreaA.append(tarea16.obtenerResumen());
                    textAreaA.append(tarea17.obtenerResumen());
                    textAreaA.append(tarea18.obtenerResumen());
                    textAreaA.append(tarea19.obtenerResumen());
                    textAreaA.append(tarea20.obtenerResumen());
                    textAreaA.append("-----------------------------------------------------------------------------\n");
                    inicioMasTardio.add(tarea20.getLFT());
                    if (tarea1.getCritica()) {
                        c1.add(1);
                    } else {
                        c1.add(0);
                    }
                    if (tarea2.getCritica()) {
                        c2.add(1);
                    } else {
                        c2.add(0);
                    }
                    if (tarea3.getCritica()) {
                        c3.add(1);
                    } else {
                        c3.add(0);
                    }
                    if (tarea4.getCritica()) {
                        c4.add(1);
                    } else {
                        c4.add(0);
                    }
                    if (tarea5.getCritica()) {
                        c5.add(1);
                    } else {
                        c5.add(0);
                    }
                    if (tarea6.getCritica()) {
                        c6.add(1);
                    } else {
                        c6.add(0);
                    }
                    if (tarea7.getCritica()) {
                        c7.add(1);
                    } else {
                        c7.add(0);
                    }
                    if (tarea8.getCritica()) {
                        c8.add(1);
                    } else {
                        c8.add(0);
                    }
                    if (tarea9.getCritica()) {
                        c9.add(1);
                    } else {
                        c9.add(0);
                    }
                    if (tarea10.getCritica()) {
                        c10.add(1);
                    } else {
                        c10.add(0);
                    }
                    if (tarea11.getCritica()) {
                        c11.add(1);
                    } else {
                        c11.add(0);
                    }
                    if (tarea12.getCritica()) {
                        c12.add(1);
                    } else {
                        c12.add(0);
                    }
                    if (tarea13.getCritica()) {
                        c13.add(1);
                    } else {
                        c13.add(0);
                    }
                    if (tarea14.getCritica()) {
                        c14.add(1);
                    } else {
                        c14.add(0);
                    }
                    if (tarea15.getCritica()) {
                        c15.add(1);
                    } else {
                        c15.add(0);
                    }
                    if (tarea16.getCritica()) {
                        c16.add(1);
                    } else {
                        c16.add(0);
                    }
                    if (tarea17.getCritica()) {
                        c17.add(1);
                    } else {
                        c17.add(0);
                    }
                    if (tarea18.getCritica()) {
                        c18.add(1);
                    } else {
                        c18.add(0);
                    }
                    if (tarea19.getCritica()) {
                        c19.add(1);
                    } else {
                        c19.add(0);
                    }
                    if (tarea20.getCritica()) {
                        c20.add(1);
                    } else {
                        c20.add(0);
                    }
                }
                i++;               
            }
            for (Integer numero : inicioMasTardio) {
                System.out.println(numero);
            }
            probabilidades.add(calcularProbabilidadDeUno(c1));
            probabilidades.add(calcularProbabilidadDeUno(c2));
            probabilidades.add(calcularProbabilidadDeUno(c3));
            probabilidades.add(calcularProbabilidadDeUno(c4));
            probabilidades.add(calcularProbabilidadDeUno(c5));
            probabilidades.add(calcularProbabilidadDeUno(c6));
            probabilidades.add(calcularProbabilidadDeUno(c7));
            probabilidades.add(calcularProbabilidadDeUno(c8));
            probabilidades.add(calcularProbabilidadDeUno(c9));
            probabilidades.add(calcularProbabilidadDeUno(c10));
            probabilidades.add(calcularProbabilidadDeUno(c11));
            probabilidades.add(calcularProbabilidadDeUno(c12));
            probabilidades.add(calcularProbabilidadDeUno(c13));
            probabilidades.add(calcularProbabilidadDeUno(c14));
            probabilidades.add(calcularProbabilidadDeUno(c15));
            probabilidades.add(calcularProbabilidadDeUno(c16));
            probabilidades.add(calcularProbabilidadDeUno(c17));
            probabilidades.add(calcularProbabilidadDeUno(c18));
            probabilidades.add(calcularProbabilidadDeUno(c19));
            probabilidades.add(calcularProbabilidadDeUno(c20));
            SwingUtilities.invokeLater(() -> {
                GraficoProbabilidad chart = new GraficoProbabilidad(probabilidades);
                chart.setSize(800, 600);
                chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chart.setVisible(true);
            });
            SwingUtilities.invokeLater(() -> {
                GraficoMasTardio chart = new GraficoMasTardio(inicioMasTardio);
                chart.setSize(800, 600);
                chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chart.setVisible(true);
            });
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un valor numérico válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static double calcularProbabilidadDeUno(List<Integer> lista) {
        int cantidadDeUnos = 0;
        // Contar los unos en la lista
        for (int numero : lista) {
            if (numero == 1) {
                cantidadDeUnos++;
            }
        }
        // Calcular la probabilidad
        return (double) cantidadDeUnos / lista.size();
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
