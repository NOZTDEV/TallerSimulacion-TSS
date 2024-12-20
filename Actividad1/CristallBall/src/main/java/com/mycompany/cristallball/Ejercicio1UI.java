package com.mycompany.cristallball;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.LogNormalDistribution;
import org.apache.commons.math3.distribution.TriangularDistribution;
import java.text.DecimalFormat;

public class Ejercicio1UI extends javax.swing.JFrame {

    // Áreas de texto para mostrar resultados
    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private JTextArea textAreaC;

    public Ejercicio1UI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Inicializando etiquetas
        JLabel jLabel1 = new JLabel();
        JLabel jLabel3 = new JLabel();  // Nueva etiqueta para número de iteraciones

        // Inicializando campo de texto
        numeroIteraciones = new JTextField();  // Nuevo campo de texto para número de iteraciones

        simularEjercicio = new JButton();
        jTabbedPane1 = new JTabbedPane();

        // Inicializando áreas de texto
        textAreaA = new JTextArea(10, 30);
        textAreaB = new JTextArea(10, 30);
        textAreaC = new JTextArea(10, 30);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Introducir Datos");

        jLabel3.setText("Número de iteraciones:");  // Etiqueta para número de iteraciones

        // Añadiendo validación para que solo se puedan ingresar números de tipo double
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
            int iteraciones = Integer.parseInt(numeroIteraciones.getText());
            textAreaA.append("Número de iteraciones: " + iteraciones + "\n");
            List<Double> totalGP = new ArrayList<>();
            List<Double> totalBE = new ArrayList<>();
            int i=0;
            while(i < iteraciones){
                double markup = 0;
                double costoAlmacenarLbs = 0;
                double procesamientoPedidos = 0;
                List<Double> veal = new ArrayList<>();
                List<Double> pork = new ArrayList<>();
                List<Double> beef = new ArrayList<>();
                List<Double> casing = new ArrayList<>();
                List<Double> summer = new ArrayList<>();
                List<Double> bratwurst = new ArrayList<>();
                List<Double> italian = new ArrayList<>();
                List<Double> peperoni = new ArrayList<>();
                List<Double> polish = new ArrayList<>();
                List<Double> precios = new ArrayList<>();
                List<Double> demandaMedia = new ArrayList<>();
                List<Double> demandaMensual = new ArrayList<>();
                List<Double> unidadesProdu = new ArrayList<>();
                textAreaA.append("Iteración " + (i+1) + "\n");
                if(i == 0){
                    //dist
                    markup = 0.3;
                    costoAlmacenarLbs = 0.75;
                    procesamientoPedidos = 0.05;
                    //horizontal
                    veal.addAll(List.of(0.0, 4.0, 1.0, 0.0, 0.0, 8.0));
                    pork.addAll(List.of(2.5, 1.0, 3.0, 4.0, 1.0, 3.25));
                    beef.addAll(List.of(1.0, 0.0, 1.5, 0.0, 3.0, 4.5));
                    casing.addAll(List.of(1.0, 1.5, 1.0, 2.0, 1.5, 0.5));//dis
                    //vertical
                    summer.addAll(List.of(0.0, 2.5, 1.0, 1.0));
                    bratwurst.addAll(List.of(4.0, 1.0, 0.0, 1.5));
                    italian.addAll(List.of(1.0, 3.0, 1.5, 1.0));
                    peperoni.addAll(List.of(0.0, 4.0, 0.0, 2.0));
                    polish.addAll(List.of(0.0, 1.0, 3.0, 1.5));
                    precios.addAll(List.of(8.0, 3.25, 4.5, 0.5));//dis
                    //Demanda
                    demandaMedia.addAll(List.of(3600.0, 7000.0, 4225.0, 5500.0, 8500.0));
                    demandaMensual.addAll(List.of(3600.0, 7000.0, 4225.0, 5500.0, 8500.0));//dis
                    unidadesProdu.addAll(List.of(1000.0, 1100.0, 750.0, 900.0, 1500.0, 0.0));
                }else{
                    //dist
                    NormalDistribution normalDistribution = new NormalDistribution(0.3, 0.03);
                    markup = normalDistribution.sample();
                    NormalDistribution normalDistributionC = new NormalDistribution(0.75, 0.05);
                    costoAlmacenarLbs = normalDistributionC.sample();
                    NormalDistribution normalDistributionP = new NormalDistribution(0.1, 0.08);
                    procesamientoPedidos = normalDistributionP.sample();
                    //horizontal
                    TriangularDistribution triangularDistribution11 = new TriangularDistribution(6.5, 8.0, 10);
                    double costo1 = triangularDistribution11.sample();
                    TriangularDistribution triangularDistribution12 = new TriangularDistribution(2.75, 3.25, 4.75);
                    double costo2 = triangularDistribution12.sample();
                    TriangularDistribution triangularDistribution13 = new TriangularDistribution(3.0, 4.5, 5.0);
                    double costo3 = triangularDistribution13.sample();
                    TriangularDistribution triangularDistribution14 = new TriangularDistribution(0.45, 0.5, 0.7);
                    double costo4 = triangularDistribution14.sample();
                    
                    veal.addAll(List.of(0.0, 4.0, 1.0, 0.0, 0.0, costo1));
                    pork.addAll(List.of(2.5, 1.0, 3.0, 4.0, 1.0, costo2));
                    beef.addAll(List.of(1.0, 0.0, 1.5, 0.0, 3.0, costo3));
                    TriangularDistribution triangularDistribution1 = new TriangularDistribution(1.0, 1.0, 1.25);
                    double dato1 = triangularDistribution1.sample();
                    TriangularDistribution triangularDistribution2 = new TriangularDistribution(1.5, 1.5, 1.65);
                    double dato2 = triangularDistribution2.sample();
                    TriangularDistribution triangularDistribution3 = new TriangularDistribution(1.0, 1.0, 1.25);
                    double dato3 = triangularDistribution3.sample();
                    TriangularDistribution triangularDistribution4 = new TriangularDistribution(2.0, 2.0, 2.2);
                    double dato4 = triangularDistribution4.sample();
                    TriangularDistribution triangularDistribution5 = new TriangularDistribution(1.5, 1.5, 1.65);
                    double dato5 = triangularDistribution5.sample();
                    casing.addAll(List.of(dato1, dato2, dato3, dato4, dato5, costo4));//dis
                    System.out.println("1" + casing);
                    //vertical
                    summer.addAll(List.of(0.0, 2.5, 1.0, dato1));
                    bratwurst.addAll(List.of(4.0, 1.0, 0.0, dato2));
                    italian.addAll(List.of(1.0, 3.0, 1.5, dato3));
                    peperoni.addAll(List.of(0.0, 4.0, 0.0, dato4));
                    polish.addAll(List.of(0.0, 1.0, 3.0, dato5));
                    precios.addAll(List.of(costo1, costo2, costo3, costo4));//dis
                    System.out.println("2" + precios);
                    //Demanda
                    demandaMedia.addAll(List.of(3600.0, 7000.0, 4225.0, 5500.0, 8500.0));
                    
                    double mean1 = 3600;
                    double stdDev1 = 540;
                    double mu1 = Math.log(Math.pow(mean1, 2) / Math.sqrt(Math.pow(mean1, 2) + Math.pow(stdDev1, 2)));
                    double sigma1 = Math.sqrt(Math.log(1 + (Math.pow(stdDev1, 2) / Math.pow(mean1, 2))));
                    LogNormalDistribution logNormalDistribution1 = new LogNormalDistribution(mu1, sigma1);
                    double demanda1 = logNormalDistribution1.sample();
                    
                    //double mean2 = 7000;
                    //double stdDev2 = 1050;
                    double mu2 = Math.log(Math.pow(7000, 2) / Math.sqrt(Math.pow(7000, 2) + Math.pow(1050, 2)));
                    double sigma2 = Math.sqrt(Math.log(1 + (Math.pow(1050, 2) / Math.pow(7000, 2))));
                    LogNormalDistribution logNormalDistribution2 = new LogNormalDistribution(mu2, sigma2);
                    double demanda2 = logNormalDistribution2.sample();
                    
                    double mu3 = Math.log(Math.pow(4225, 2) / Math.sqrt(Math.pow(4225, 2) + Math.pow(634, 2)));
                    double sigma3 = Math.sqrt(Math.log(1 + (Math.pow(634, 2) / Math.pow(4225, 2))));
                    LogNormalDistribution logNormalDistribution3 = new LogNormalDistribution(mu3, sigma3);
                    double demanda3 = logNormalDistribution3.sample();
                    
                    double mu4 = Math.log(Math.pow(5500, 2) / Math.sqrt(Math.pow(5500, 2) + Math.pow(825, 2)));
                    double sigma4 = Math.sqrt(Math.log(1 + (Math.pow(825, 2) / Math.pow(5500, 2))));
                    LogNormalDistribution logNormalDistribution4 = new LogNormalDistribution(mu4, sigma4);
                    double demanda4 = logNormalDistribution4.sample();
                            
                    double mu5 = Math.log(Math.pow(8500, 2) / Math.sqrt(Math.pow(8500, 2) + Math.pow(1275, 2)));
                    double sigma5 = Math.sqrt(Math.log(1 + (Math.pow(1275, 2) / Math.pow(8500, 2))));
                    LogNormalDistribution logNormalDistribution5 = new LogNormalDistribution(mu5, sigma5);
                    double demanda5 = logNormalDistribution5.sample();

                    demandaMensual.addAll(List.of(demanda1, demanda2, demanda3, demanda4, demanda5));//dis
                    System.out.println("3" + demandaMensual);
                    unidadesProdu.addAll(List.of(1000.0, 1100.0, 750.0, 900.0, 1500.0, 0.0));
                }
                //Inventario
                List<Double> invVe = new ArrayList<>(List.of(12520.0, 0.0, MYS(veal,unidadesProdu)));
                invVe.add(invVe.get(0)+ invVe.get(1) - invVe.get(2));
                invVe.add(Math.max(invVe.get(3)* costoAlmacenarLbs, 0));
                textAreaA.append("Inventario 1: " + invVe + "\n");

                List<Double> invPo = new ArrayList<>(List.of(14100.0, 0.0, MYS(pork,unidadesProdu)));
                invPo.add(invPo.get(0)+ invPo.get(1) - invPo.get(2));
                invPo.add(Math.max(invPo.get(3)* costoAlmacenarLbs, 0));
                textAreaA.append("Inventario 2: " + invPo + "\n");

                List<Double> invBe = new ArrayList<>(List.of(6480.0, 200.0, MYS(beef,unidadesProdu)));
                invBe.add(invBe.get(0)+ invBe.get(1) - invBe.get(2));
                invBe.add(Math.max(invBe.get(3)* costoAlmacenarLbs, 0));
                textAreaA.append("Inventario 3: " + invBe + "\n");

                List<Double> invCa = new ArrayList<>(List.of(10800.0, 0.0, MYS(casing,unidadesProdu)));
                invCa.add(invCa.get(0)+ invCa.get(1) - invCa.get(2));
                invCa.add(Math.max(invCa.get(3)* costoAlmacenarLbs, 0));
                textAreaA.append("Inventario 4: " + invCa + "\n");
                textAreaA.append("       SummerSausage-Bratwurst-ItalianSausage-Pepperoni-PolishSausage\n");
                
                DecimalFormat df = new DecimalFormat("#.##");
                //Costos
                List<Double> costoUnitario = new ArrayList<>(List.of(MYS(summer,precios), MYS(bratwurst,precios), MYS(italian,precios),
                        MYS(peperoni,precios), MYS(polish,precios)));
                textAreaA.append("Costos:                " + String.join("              ", costoUnitario.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> peso = new ArrayList<>(List.of(sumar(summer),sumar(bratwurst),sumar(italian),sumar(peperoni),sumar(polish)));
                textAreaA.append("Peso:                  " + String.join("              ", peso.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> salchichasPaquete = new ArrayList<>(List.of(peso.get(0)*4,peso.get(1)*4,peso.get(2)*4,peso.get(3)*4,peso.get(4)*4));
                textAreaA.append("# Salchicas:           " + String.join("              ", salchichasPaquete.stream().map(d -> df.format(d)).toList()) + "\n");
                
                List<Double> costo20 = new ArrayList<>(List.of((costoUnitario.get(0)/salchichasPaquete.get(0))*20,(costoUnitario.get(1)/salchichasPaquete.get(1))*20,
                                        (costoUnitario.get(2)/salchichasPaquete.get(2))*20,(costoUnitario.get(3)/salchichasPaquete.get(3))*20,(costoUnitario.get(4)/salchichasPaquete.get(4))*20));
                textAreaA.append("Costo20:               " + String.join("              ", costo20.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> precioVent = new ArrayList<>(List.of(costo20.get(0)*(1+markup), costo20.get(1)*(1+markup),
                                        costo20.get(2)*(1+markup),costo20.get(3)*(1+markup),costo20.get(4)*(1+markup)));
                textAreaA.append("PreciVenta:            " + String.join("              ", precioVent.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> ganancia = new ArrayList<>(List.of(precioVent.get(0)-costo20.get(0), precioVent.get(1)-costo20.get(1),
                                        precioVent.get(2)-costo20.get(2),precioVent.get(3)-costo20.get(3),precioVent.get(4)-costo20.get(4)));
                textAreaA.append("Ganancia:              " + String.join("              ", ganancia.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> gananciaLbs = new ArrayList<>(List.of(ganancia.get(0)/peso.get(0), ganancia.get(1)/peso.get(1),
                                        ganancia.get(2)/peso.get(2),ganancia.get(3)/peso.get(3),ganancia.get(4)/peso.get(4)));
                textAreaA.append("GananciaLbs:           " + String.join("              ", gananciaLbs.stream().map(d -> df.format(d)).toList()) + "\n");

                // Tabla min y max
                textAreaA.append("Tabla minima y maxima: \n");
                textAreaA.append("UnidadesProcucir:      " + String.join("              ", unidadesProdu.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> min = new ArrayList<>(List.of((demandaMedia.get(0)/peso.get(0))*0.7,(demandaMedia.get(1)/peso.get(1))*0.7,
                                        (demandaMedia.get(2)/peso.get(2))*0.7,(demandaMedia.get(3)/peso.get(3))*0.7,(demandaMedia.get(4)/peso.get(4))*0.7));
                textAreaA.append("minimi:                " + String.join("              ", min.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> max = new ArrayList<>(List.of((demandaMedia.get(0)/peso.get(0))*1.5,(demandaMedia.get(1)/peso.get(1))*1.5,
                                        (demandaMedia.get(2)/peso.get(2))*1.5,(demandaMedia.get(3)/peso.get(3))*1.5,(demandaMedia.get(4)/peso.get(4))*1.5));
                textAreaA.append("Maximo:                " + String.join("              ", max.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> produccionLbs = new ArrayList<>(List.of(unidadesProdu.get(0)*peso.get(0),unidadesProdu.get(1)*peso.get(1),
                                        unidadesProdu.get(2)*peso.get(2),unidadesProdu.get(3)*peso.get(3),unidadesProdu.get(4)*peso.get(4)));
                textAreaA.append("Produccion/Lbs:        " + String.join("              ", produccionLbs.stream().map(d -> df.format(d)).toList()) + "\n");

                // Deficit y superhabit
                List<Double> suDe = new ArrayList<>(List.of(produccionLbs.get(0)-demandaMensual.get(0),produccionLbs.get(1)-demandaMensual.get(1),
                                        produccionLbs.get(2)-demandaMensual.get(2),produccionLbs.get(3)-demandaMensual.get(3),produccionLbs.get(4)-demandaMensual.get(4)));
                textAreaA.append("Superhabit/Deficit:    " + String.join("              ", suDe.stream().map(d -> df.format(d)).toList()) + "\n");
                
                List<Double> ventaP = new ArrayList<>(List.of(costoVentaP(suDe.get(0),gananciaLbs.get(0)),costoVentaP(suDe.get(1),gananciaLbs.get(1)),
                                        costoVentaP(suDe.get(2),gananciaLbs.get(2)),costoVentaP(suDe.get(3),gananciaLbs.get(3)),costoVentaP(suDe.get(4),gananciaLbs.get(4))));
                textAreaA.append("CostoVentaP:           " + String.join("              ", ventaP.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> costoA = new ArrayList<>(List.of(costoAlmace(suDe.get(0),costoAlmacenarLbs),costoAlmace(suDe.get(1),costoAlmacenarLbs),
                                        costoAlmace(suDe.get(2),costoAlmacenarLbs),costoAlmace(suDe.get(3),costoAlmacenarLbs),costoAlmace(suDe.get(4),costoAlmacenarLbs)));
                textAreaA.append("CostoAlmacen:          " + String.join("              ", costoA.stream().map(d -> df.format(d)).toList()) + "\n");

                // holding
                List<Double> hoding = new ArrayList<>(List.of(invVe.get(4),invPo.get(4),invBe.get(4),invCa.get(4)));
                List<Double> buySell = new ArrayList<>(List.of(invVe.get(1),invPo.get(1),invBe.get(1),invCa.get(1)));

                List<Double> costoInv = new ArrayList<>(List.of((sumar(hoding)*demandaMensual.get(0))/(sumar(demandaMensual)*-1),(sumar(hoding)*demandaMensual.get(1))/(sumar(demandaMensual)*-1),
                                        (sumar(hoding)*demandaMensual.get(2))/(sumar(demandaMensual)*-1),(sumar(hoding)*demandaMensual.get(3))/(sumar(demandaMensual)*-1),(sumar(hoding)*demandaMensual.get(4))/(sumar(demandaMensual)*-1)));
                textAreaA.append("Costo de Inventario:   " + String.join("              ", costoInv.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> invAdj = new ArrayList<>(List.of(MYS(precios,buySell)*(demandaMensual.get(0)/sumar(demandaMensual))*(-1)*(1-procesamientoPedidos),MYS(precios,buySell)*(demandaMensual.get(1)/sumar(demandaMensual))*(-1)*(1-procesamientoPedidos),
                                        MYS(precios,buySell)*(demandaMensual.get(2)/sumar(demandaMensual))*(-1)*(1-procesamientoPedidos),MYS(precios,buySell)*(demandaMensual.get(3)/sumar(demandaMensual))*(-1)*(1-procesamientoPedidos),
                                        MYS(precios,buySell)*(demandaMensual.get(4)/sumar(demandaMensual))*(-1)*(1-procesamientoPedidos)));
                textAreaA.append("Inventario Ajustado:   " + String.join("              ", invAdj.stream().map(d -> df.format(d)).toList()) + "\n");

                // Resultados
                List<Double> ga1 = new ArrayList<>(List.of(costoA.get(0), costoInv.get(0), invAdj.get(0)));
                List<Double> ga2 = new ArrayList<>(List.of(costoA.get(1), costoInv.get(1), invAdj.get(1)));
                List<Double> ga3 = new ArrayList<>(List.of(costoA.get(2), costoInv.get(2), invAdj.get(2)));
                List<Double> ga4 = new ArrayList<>(List.of(costoA.get(3), costoInv.get(3), invAdj.get(3)));
                List<Double> ga5 = new ArrayList<>(List.of(costoA.get(4), costoInv.get(4), invAdj.get(4)));

                List<Double> gananciaP = new ArrayList<>(List.of(sumar(ga1), sumar(ga2), sumar(ga3), sumar(ga4), sumar(ga5)));
                textAreaA.append("Ganancia potencial:   " + String.join("              ", gananciaP.stream().map(d -> df.format(d)).toList()) + "\n");

                List<Double> beneficio = new ArrayList<>(List.of((gananciaLbs.get(0)*demandaMensual.get(0))+sumar(ga1), (gananciaLbs.get(1)*demandaMensual.get(1))+sumar(ga2),
                                        (gananciaLbs.get(2)*demandaMensual.get(2))+sumar(ga3), (gananciaLbs.get(3)*demandaMensual.get(3))+sumar(ga4), (gananciaLbs.get(4)*demandaMensual.get(4))+sumar(ga5)));
                textAreaA.append("Beneficio proyectado: " + String.join("              ", beneficio.stream().map(d -> df.format(d)).toList()) + "\n");


                totalGP.add(sumar(gananciaP));
                totalBE.add(sumar(beneficio));
                textAreaA.append("-----------------------------------------------------------\n");
                i++;
            }
            System.out.println(totalBE);
            SwingUtilities.invokeLater(() -> {
                BeneficioOperativoChart chart = new BeneficioOperativoChart("Gráfica de Beneficio Operativo", totalBE, totalGP);
                chart.setSize(800, 600);
                chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                chart.setVisible(true);
            });
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un valor numérico válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static double MYS(List<Double> list1, List<Double> list2) {
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Ambas listas deben tener el mismo tamaño.");
        }
        double suma = 0.0;
        
        for (int i = 0; i < list1.size(); i++) {
            suma += list1.get(i) * list2.get(i);
        }
        return suma;
    }
    
    public static double sumar(List<Double> lista) {
        double suma = 0.0;
        for (double elemento : lista) {
            suma += elemento;
        }
        return suma;
    }
    
    public static double costoVentaP(double num1, double num2) {
        double res;
        if(num1<0){
            res = num1*num2;
        }else{
            res = 0;
        }
        return res;
    }
    
    public static double costoAlmace(double num1, double num2) {
        double res;
        if(num1>0){
            res = num1*num2*(-1);
        }else{
            res = 0;
        }
        return res;
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
    private JTextField numeroIteraciones;  // Nuevo campo para número de iteraciones
    private JButton simularEjercicio;
    private JTabbedPane jTabbedPane1;
}