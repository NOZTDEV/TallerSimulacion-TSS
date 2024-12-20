package com.mycompany.proyecto3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Random;


public class Ejecucion extends Thread {
    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private int duracion;
    private int replicas;
    private volatile int delay; // Volatile para garantizar cambios en tiempo real
    private int capacidadCoccion;
    private int litrosMosto;
    private int botellaPaquete;
    private int contSiloCebada = 0; private int contSiloLupulo=0; 
    private int contSiloLevadura = 0; private int contAlmacenCaja = 0;
    private int contSiloCebadaF = 0; private int contSiloLupuloF=0; 
    private int contSiloLevaduraF = 0; private int contAlmacenCajaF = 0;

    // Otras variables (sin cambios)...
    private SimulacionPanel simPanel;
    private JSlider delaySlider;
    public Ejecucion(JTextArea textAreaA, JTextArea textAreaB, int duracion, int replicas, int delay, int capacidadCoccion,
            int litrosMosto, int botellaPaquete) {
        this.textAreaA = textAreaA;
        this.textAreaB = textAreaB;
        this.duracion = duracion;
        this.replicas = replicas;
        this.delay = delay;
        this.capacidadCoccion = capacidadCoccion;
        this.litrosMosto = litrosMosto;
        this.botellaPaquete = botellaPaquete;

        initGrafico(); // Inicializar gráfica y slider
    }

    private void initGrafico() {
        JFrame frame = new JFrame("Simulación con Control de Delay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);

        simPanel = new SimulacionPanel();
        simPanel.setPreferredSize(new Dimension(900, 600));

        // Crear el slider para controlar el delay
        delaySlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, delay);
        delaySlider.setMajorTickSpacing(200);
        delaySlider.setMinorTickSpacing(50);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);

        // Listener para actualizar el delay en tiempo real
        delaySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                delay = delaySlider.getValue();
            }
        });

        // Crear un panel para el slider y agregarlo
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Delay:"));
        controlPanel.add(delaySlider);

        // Agregar componentes al marco
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(simPanel);
        frame.add(controlPanel);

        frame.setVisible(true);
    }

    @Override
    public void run() {
        //Locacion
        List<Entidad> siloCebada = new  ArrayList<>();
        List<Entidad> malteado = new  ArrayList<>();
        List<Entidad> secado = new  ArrayList<>();
        List<Entidad> molienda = new  ArrayList<>();
        List<Entidad> maserado = new ArrayList<>();
        List<Entidad> filtrado = new ArrayList<>();
        List<Entidad> coccion = new ArrayList<>();
        List<Entidad> siloLupulo = new ArrayList<>();
        List<Entidad> enfriado = new ArrayList<>();
        List<Entidad> siloLevadura = new ArrayList<>();
        List<Entidad> fermentado = new ArrayList<>();
        List<Entidad> maduracion = new ArrayList<>();
        List<Entidad> inspeccion = new ArrayList<>();
        List<Entidad> embotellado = new ArrayList<>();
        List<Entidad> etiquetado = new ArrayList<>();
        List<Entidad> empacado = new ArrayList<>();
        List<Entidad> almacenCajas = new ArrayList<>();
        List<Entidad> almacen = new ArrayList<>();
        List<Entidad> mercado = new ArrayList<>();
        List<Entidad> fermentadoA = new ArrayList<>();
        //Recursos
        List<Entidad> operadorRecepcion = new  ArrayList<>();
        List<Entidad> trabajadorLupulo = new ArrayList<>();
        List<Entidad> trabajadorLevadura = new ArrayList<>();
        List<Entidad> trabajadorCajas = new ArrayList<>();
        List<Entidad> camion = new ArrayList<>();
        //Variables auxiliares
        double arriboCebada = 0;
        double arriboLupulo = 0;
        double arriboLevadura = 0;
        double arriboCaja = 0;
        //Variables de resultados
        int contMalteado = 0; int contSecado = 0; int contMolienda = 0; int contMaserado = 0; int contFiltrado = 0;
        int contMosto = 0; int contEnfriado = 0; int contAlcohol = 0; int contMaduracion = 0; int contIns = 0;
        int contRechazado = 0; int contEmbotellado = 0; int contEtiquetado = 0; int contEmpacado = 0;
        int contAlmacen = 0; int contMercado = 0;
        
        int salidaLupulo = 0; int salidaMosto = 0; int salidaLevadura = 0; int salidaBotella = 0;
        int salidaCaja = 0; int salidaCajasC = 0;
        
        int contArea1L = 0, contArea1V = 0;
        int contArea2L = 0, contArea2V = 0;
        int contArea3L = 0, contArea3V = 0;
        int contArea4L = 0, contArea4V = 0;
        int contArea5L = 0, contArea5V = 0;
        int contArea6L = 0, contArea6V = 0;
        int contArea7L = 0, contArea7V = 0;
        int contArea8L = 0, contArea8V = 0;
        int contArea9L = 0, contArea9V = 0;
        int contArea10L = 0, contArea10V = 0;
        int contArea11L = 0, contArea11V = 0;
        int contArea12L = 0, contArea12V = 0;
        int contArea13L = 0, contArea13V = 0;
        int contArea14L = 0, contArea14V = 0;
        int contArea15L = 0, contArea15V = 0;
        int contArea16L = 0, contArea16V = 0;
        int contArea17L = 0, contArea17V = 0;
        int contArea18L = 0, contArea18V = 0;
        int contArea19L = 0, contArea19V = 0;

        
        //Simular tiempo
        for (int k = 1; k <= replicas; k++) {
            for (int i = 0; i <= duracion; i++) { // Simular las horas
                if (i != duracion) { // Si no es la última hora
                    BigDecimal j = new BigDecimal("0.00");
                    BigDecimal one = new BigDecimal("1.0");

                    while (j.compareTo(one) < 0) { // Mientras j < 1.0
                        BigDecimal horaExacta = new BigDecimal(i).add(j).setScale(2, RoundingMode.HALF_UP);
                        double tiempoActual = horaExacta.doubleValue();
                        textAreaA.append("Hora " + tiempoActual + "\n");
                        //Calculo de datos
                        if (siloCebada.size() == 3) { contArea1L++; }
                        if (malteado.size() == 3) { contArea2L++; }
                        if (secado.size() == 3) { contArea3L++; }
                        if (molienda.size() == 2) { contArea4L++; }
                        if (maserado.size() == 3) { contArea5L++; }
                        if (filtrado.size() == 2) { contArea6L++; }
                        if (coccion.size() == 10) { contArea7L++; }
                        if (siloLupulo.size() == 10) { contArea8L++; }
                        if (enfriado.size() == 10) { contArea9L++; }
                        if (siloLevadura.size() == 10) { contArea10L++; }
                        if (fermentado.size() == 10) { contArea11L++; }
                        if (maduracion.size() == 10) { contArea12L++; }
                        if (inspeccion.size() == 3) { contArea13L++; }
                        if (embotellado.size() == 6) { contArea14L++; }
                        if (etiquetado.size() == 6) { contArea15L++; }
                        if (empacado.size() == 6) { contArea16L++; }
                        if (almacenCajas.size() == 30) { contArea17L++; }
                        if (almacen.size() == 6) { contArea18L++; }
                        if (mercado.size() == 100) { contArea19L++; }

                        if (siloCebada.isEmpty()) { contArea1V++; }
                        if (malteado.isEmpty()) { contArea2V++; }
                        if (secado.isEmpty()) { contArea3V++; }
                        if (molienda.isEmpty()) { contArea4V++; }
                        if (maserado.isEmpty()) { contArea5V++; }
                        if (filtrado.isEmpty()) { contArea6V++; }
                        if (coccion.isEmpty()) { contArea7V++; }
                        if (siloLupulo.isEmpty()) { contArea8V++; }
                        if (enfriado.isEmpty()) { contArea9V++; }
                        if (siloLevadura.isEmpty()) { contArea10V++; }
                        if (fermentado.isEmpty()) { contArea11V++; }
                        if (maduracion.isEmpty()) { contArea12V++; }
                        if (inspeccion.isEmpty()) { contArea13V++; }
                        if (embotellado.isEmpty()) { contArea14V++; }
                        if (etiquetado.isEmpty()) { contArea15V++; }
                        if (empacado.isEmpty()) { contArea16V++; }
                        if (almacenCajas.isEmpty()) { contArea17V++; }
                        if (almacen.isEmpty()) { contArea18V++; }
                        if (mercado.isEmpty()) { contArea19V++; }

                        // Simulación entre hora
                        //Arribos
                        if(tiempoActual == 0.00 || tiempoActual==arriboCebada){ //cebada
                            arriboCebada = arribo(siloCebada, 3, "cebada", 0.42, tiempoActual); //cada 25 minutos
                            simPanel.llegadaPieza("siloGrano");
                        }
                        if(tiempoActual == 0.00 || tiempoActual==arriboLupulo){ //Lupulo
                            arriboLupulo = arribo(siloLupulo, 10, "lupulo", 0.17, tiempoActual); //cada 10 minutos
                            simPanel.llegadaPieza("siloLupulo");
                        }
                        if(tiempoActual == 0.00 || tiempoActual==arriboLevadura){//Levadura
                            arriboLevadura = arribo(siloLevadura, 10, "levadura", 0.33, tiempoActual); //cada 20 minutos
                            simPanel.llegadaPieza("siloLevadura");
                        }
                        if(tiempoActual == 0.00 || tiempoActual==arriboCaja){   //Cajas
                            arriboCaja = arribo(almacenCajas, 30, "caja vacia", 0.5, tiempoActual); //cada 30 minutos
                            simPanel.llegadaPieza("almacenCaja");
                        }
                        //Procesos
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Salida de almacen                       
                        if(!almacen.isEmpty()){                                 //almacen no esta vacio
                            for(int al = 0; al < almacen.size(); al++){         //recorremos cada entidad
                                Entidad paquete = almacen.get(al);              //guardamos la entidad
                                if(paquete.getTiempo14() == tiempoActual){      //vemos si es tiempo de salida
                                    almacen.remove(paquete);                    //eliminamos de almacen
                                    simPanel.llegadaPieza("almacenVacio");
                                    retrasoGrafica(delay);
                                    textAreaA.append("Paquete se emvio a mercado\n");
                                    camion.add(paquete);                        //añadimos a camion
                                    simPanel.llegadaPieza("camionLleno");
                                    retrasoGrafica(delay);
                                    simPanel.moverRecurso("camion", 970, 550);
                                    camion.remove(paquete);                     //eliminamos de camion
                                    simPanel.llegadaPieza("camionVacio");
                                    retrasoGrafica(delay);
                                    simPanel.moverRecurso("camion", 260, 550);
                                    retrasoGrafica(delay);
                                    //simPanel.moverRecurso("camion", 970, 550);
                                    mercado.add(paquete);                       //añadimos a mercado
                                    simPanel.llegadaPieza("mercadoLleno");
                                    retrasoGrafica(delay);
                                    mercado.remove(paquete);                    //aliminamos de mercado
                                    //simPanel.llegadaPieza("mercadoVacio");
                                    salidaCajasC++;                             //aumentamos contador de salidas
                                    contMercado++;                              //aumentamos el contador de salidas
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        // Almacen
                        if (!almacen.isEmpty()) {                               //almacen no esta vacio
                            for (int al = 0; al < almacen.size(); al++) {       //recorremos cada elemento de almacen
                                Entidad caja = almacen.get(al);                 //guardamos la entida
                                if (!caja.getInArea4()){                        //se proceso el elemento
                                    if (almacen.size() >= 6) {                  //hay suficientes paquetes para carga
                                        caja.setInArea4(true);                  // Marcar como procesado
                                        for (int ca = 0; ca < 6 && !almacen.isEmpty(); ca++){//procesamos 6 paquetes
                                            Entidad cajaProcesada = almacen.remove(0); //Eliminar la primera botella
                                            textAreaA.append("Se empacó caja " + (ca + 1) + "\n"); 
                                        }                                       
                                        almacen.add(caja);
                                        procesarOperacion(caja, tiempoActual, "almacen", 0.08, "caja de cerveza");//procesamos carga en almcen
                                    } else {
                                        //textAreaA.append("Esperando más cajas con para empaquetar\n");
                                        break; // Salir del bucle si no se cumplen las condiciones
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Empacado salida
                        if(!empacado.isEmpty()){                                //empacado no esta vacio
                            for(int emp = 0; emp < empacado.size(); emp++){     //recorremos cada entidad
                                Entidad caja = empacado.get(emp);               //guardamos la entidad
                                if(caja.getTiempo13() == tiempoActual){         //preguntamos si es tiempo de salida
                                    if(almacen.size() < 6){                     //hay espacio en almacen
                                        empacado.remove(caja);                  //eliminamos de empacado
                                        simPanel.llegadaPieza("empacadoVacio");
                                        retrasoGrafica(delay);
                                        trabajadorCajas.add(caja);              //añadimos a operador
                                        simPanel.llegadaPieza("operador4Lleno");
                                        retrasoGrafica(delay);
                                        simPanel.moverRecurso("operadorCajas", 110, 390);
                                        textAreaA.append("Caja con cerveza salio de empacado\n");
                                        trabajadorCajas.remove(caja);           //eliminamos de operador
                                        simPanel.moverRecurso("operadorCajas", 110, 540);
                                        retrasoGrafica(delay);
                                        simPanel.llegadaPieza("operador4Vacio");
                                        almacen.add(caja);                      //añadimos a almacen
                                        simPanel.llegadaPieza("almacenLleno");
                                        simPanel.moverRecurso("operadorCajas", 110, 390);
                                        contAlmacen++;                         //incrementa el contador de paquetes
                                    }else{
                                       //textAreaA.append("Caja con ceveza bloqueada en empacado\n");
                                       caja.setTiempo13(dosDecimales(tiempoActual+0.01));//actualizamos el tiempo
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Empacado
                        if (!empacado.isEmpty()) {                              //empacado no esta vacio
                            textAreaA.append("Botellas en empacado "+empacado.size()+"\n");
                            for (int emp = 0; emp < empacado.size(); emp++) {   //recorremos cada entidad
                                Entidad botella = empacado.get(emp);            //guardamos la entidad
                                if (!botella.getInArea3()) {                    //vemos si se mesclo la entidad
                                    if (almacenCajas.size() >= 1 && empacado.size() >= botellaPaquete) {//ver si hay cajas y botellas diponibles
                                        botella.setInArea3(true);               // Marcar como procesado
                                        for (int bot = 0; bot < botellaPaquete; bot++) {     //procesamos n botellas
                                            Entidad botellaProcesada = empacado.remove(0); //Eliminar la primera botella
                                            salidaBotella++;                    //aumentamos contador de salidas
                                            textAreaA.append("Se empacó botella " + (bot + 1) + "\n");
                                        }
                                        Entidad caja = almacenCajas.remove(0);  //Eliminar la primera caja
                                        salidaCaja++;                           //aumentamos contador de salidas
                                        textAreaA.append("Se utilizó una caja\n");
                                        empacado.add(botella);
                                        //textAreaA.append("Cajas producidas: " + contEmpacado + "\n");
                                        botella.cambiarNombre("Caja de cerveza");//Cambiamos el nombre a caja de cerveza
                                        procesarOperacion(botella, tiempoActual, "empacado", 0.17, "caja de cerveza");//procesamos en empacado
                                    } else {
                                        textAreaA.append("Esperando botellas\n");
                                        break; // Salir del bucle si no se cumplen las condiciones
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Etiquetado
                        if(!etiquetado.isEmpty()){                              //etiquetado no esta vacio
                            for(int eti = 0; eti < etiquetado.size(); eti++){   //recorremos cada entidad
                                Entidad botella = etiquetado.get(eti);          //guardamos la entidad
                                if(botella.getTiempo12() == tiempoActual){      //preguntamos si es tiempo de salida
                                    if(empacado.size() < 6){                    //hay espacio en empcado
                                        etiquetado.remove(botella);             //eliminamos de etiquetado
                                        simPanel.llegadaPieza("etiquetadoVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Botella salio de etiquetado\n");
                                        empacado.add(botella);                  //añadimos a empacado
                                        simPanel.llegadaPieza("empacadoLleno");
                                        contEmpacado++;                     //Aumentamos el contador de botellas
                                    }else{
                                       //textAreaA.append("Botella bloqueado en etiquetado\n");
                                       botella.setTiempo12(dosDecimales(tiempoActual+0.01));//actualizamos el tiempo
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Embotellado
                        if(!embotellado.isEmpty()){                             //embotellado no esta vacio
                            for(int emb = 0; emb < embotellado.size(); emb++){  //recorremos cada elemento
                                Entidad botella = embotellado.get(emb);         //guardamos la entidad
                                if(botella.getTiempo11() == tiempoActual){      //preguntamos si es tiempo de salida
                                    if(etiquetado.size() < 6){                  //hay espacio en etiquetado
                                        embotellado.remove(botella);            //eliminamos de embotellado
                                        simPanel.llegadaPieza("embotelladoVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Botella salio de embotellado\n");
                                        etiquetado.add(botella);                //añadimos a etiquetado
                                        simPanel.llegadaPieza("etiquetadoLleno");
                                        contEtiquetado++;                       //aumentamos el contador de etiquetado
                                        procesarOperacion(botella, tiempoActual, "etiquetado", 0.02, "botella");//procesamos en etiquetado
                                    }else{
                                       //textAreaA.append("Botella bloqueado en embotellado\n");
                                       botella.setTiempo11(dosDecimales(tiempoActual+0.01));
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Inspeccion
                        if(!inspeccion.isEmpty()){                              //inspeccion no esta vacio
                            for(int ins = 0; ins < inspeccion.size(); ins++){   //recorremos cada entidad
                                Entidad alcohol = inspeccion.get(ins);          //guardamos la entidad
                                if(alcohol.getTiempo10() == tiempoActual){      //preguntamos si es tiempo de salida
                                    double random = Math.random();              //generamos un random de 0-1
                                    if(random > 0.9){                           //si esta en el 10% se acepta
                                        inspeccion.remove(alcohol);             //eliminalos alccohol
                                        simPanel.llegadaPieza("inspeccionVacio");
                                        retrasoGrafica(delay);
                                        contRechazado++;                        //aumentamos el contador de rechazados
                                        textAreaA.append("Alcohol se rechasado\n");
                                    }else{
                                        if(embotellado.size() < 6){             //hay espacio en embotellado
                                            inspeccion.remove(alcohol);         //eliminamos alcohol de inspeccion
                                            simPanel.llegadaPieza("inspeccionVacio");
                                            retrasoGrafica(delay);
                                            textAreaA.append("Alcohol aceptado y salio de inspeccion\n");
                                            embotellado.add(alcohol);           //añadimos a embotellado
                                            simPanel.llegadaPieza("embotelladoLleno");
                                            contEmbotellado++;                  //aumentamos el contador de embotellado
                                            textAreaA.append("botellas en inspeccion"+contEmbotellado+"\n");
                                            procesarOperacion(alcohol, tiempoActual, "embotellado", 0.1, "alcohol");//procesamos en embotellado
                                        }else{
                                           //textAreaA.append("Alcohol bloqueado en inspeccion\n");
                                           alcohol.setTiempo10(dosDecimales(tiempoActual+0.01));//Actualizamos el tiempo
                                        }
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Madurado
                        if(!maduracion.isEmpty()){                              //maduracion no esta vacio
                            for(int ma = 0; ma < maduracion.size(); ma++){      //recorremos cada entidad
                                Entidad alcohol = maduracion.get(ma);           //guardamos a entidad
                                if(alcohol.getTiempo9() == tiempoActual){       //preguntamos si es tiempo de salida
                                    if(inspeccion.size() < 3){                  //hay espacio en inspeccion
                                        maduracion.remove(alcohol);             //eliminamos de maduracion
                                        simPanel.llegadaPieza("maduracionVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Alcohol salio de maduración\n");
                                        inspeccion.add(alcohol);                //añadimos a inspeccion
                                        simPanel.llegadaPieza("inspeccionLleno");
                                        contIns++;                              //aumentamos el contador de inspeccion
                                        procesarOperacion(alcohol, tiempoActual, "inspeccion", 0.5, "alcohol");//procesamos en inspeccion
                                    }else{
                                       //textAreaA.append("Alcohol bloqueado en maduración\n");
                                       alcohol.setTiempo9(dosDecimales(tiempoActual+0.01));
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Fermetado salida
                        if(!fermentadoA.isEmpty()){                              //fermentado no esta vacio
                            for(int fer = 0; fer < fermentadoA.size(); fer++){   //recorremos cada entidad
                                Entidad alcohol = fermentadoA.get(fer);          //guardamos la entidad
                                if(alcohol.getTiempo8() == tiempoActual){       //preguntamos si es tiempo de salida
                                    if(maduracion.size() < 10){                 //hay espacio en maduracion
                                        fermentadoA.remove(alcohol);             //eliminamos de fermentado
                                        simPanel.llegadaPieza("fermentadoVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Alcohol salio de fermentado\n");
                                        maduracion.add(alcohol);                //añadimos a maduracion
                                        simPanel.llegadaPieza("maduracionLleno");
                                        contMaduracion++;                       //aumentamos el contador de maduracion
                                        procesarOperacion(alcohol, tiempoActual, "madurado", 1.5, "alcohol");//procesamos en maduracion
                                    }else{
                                       //textAreaA.append("Alcohol bloqueado en fermentado\n");
                                       alcohol.setTiempo8(dosDecimales(tiempoActual+0.01));
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        // Fermentado
                        if (!fermentado.isEmpty()) {                            //fermentado no esta vacio
                            for (int fer = 0; fer < fermentado.size(); fer++){  //recorremos cada elementos
                                Entidad mosto = fermentado.get(fer);            //guardamos la entidad
                                if (!mosto.getInArea2()) {                      //Verificamos si se mesclo
                                    if (siloLevadura.size() >= 2 && fermentado.size() >= litrosMosto){//si hay levadura y mosto suficiente
                                        mosto.setInArea2(true);                 // Marcar como procesado
                                        for (int mos = 0; mos < litrosMosto && !fermentado.isEmpty(); mos++){//procesamos litros mosto
                                            Entidad mostoProcesado = fermentado.remove(0); //Eliminar el primer mosto
                                            salidaMosto++;                      //aumentamos contador de salidas
                                            textAreaA.append("Se procesó mosto " + (mos + 1) + "\n");
                                        }
                                        for (int lv = 0; lv < 2 && !siloLevadura.isEmpty(); lv++){//procesamos levadura necesaria
                                            retrasoGrafica(delay);
                                            Entidad levadura = siloLevadura.remove(0); //Eliminar la primera levadura
                                            salidaLevadura++;                      //aumentamos contador de salidas
                                            trabajadorLevadura.add(levadura);   //añadimos a operador
                                            simPanel.llegadaPieza("operador3Lleno");
                                            retrasoGrafica(delay);
                                            simPanel.moverRecurso("operadorLevadura", 1000, 390);
                                            trabajadorLevadura.remove(levadura);//eliminamos de operador
                                            simPanel.llegadaPieza("operador3Vacio");
                                            retrasoGrafica(delay);
                                            simPanel.moverRecurso("operadorLevadura", 1160, 210);
                                            textAreaA.append("Se procesó levadura " + (lv + 1) + "\n");
                                        }
                                        textAreaA.append("Alcohol producido: " + contAlcohol + "\n");
                                        mosto.cambiarNombre("Alcohol");         //cambiamos el nombre de mosta a alcohol
                                        fermentadoA.add(mosto);                  //añadimo a fermentado el alcohol
                                        procesarOperacion(mosto, tiempoActual, "fermentado", 2, "mosto");//procesamos en fermentado
                                    } else {
                                        //textAreaA.append("Esperando más mosto o levadura\n");
                                        break; // Salir del bucle si no se cumplen las condiciones
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Enfriado
                        if(!enfriado.isEmpty()){                            //si enfriado no esta vacio
                            for(int en = 0; en < enfriado.size(); en++){    //recorremos cada entidad   
                                Entidad mosto = enfriado.get(en);           //guaramos la entidad
                                if(mosto.getTiempo7() == tiempoActual){     //preguntamos si es tiempo de salida
                                    if(fermentado.size() < 10){             //hay espacio en fermentado
                                        enfriado.remove(mosto);             //eliminamos de enfriado
                                        simPanel.llegadaPieza("emfriadoVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Mosto salio de enfriado\n");
                                        fermentado.add(mosto);              //añadimos a fermentado
                                        simPanel.llegadaPieza("fermentadoLleno");
                                        contAlcohol++;                          //incrementamos contador de alcohol
                                    }else{
                                        //textAreaA.append("Mosto bloqueado en enfriado\n");
                                        mosto.setTiempo7(dosDecimales(tiempoActual+0.01));//actualizamos el tiempo
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Coccion salida
                        if(!coccion.isEmpty()){                             //si coccion no esta vacio
                            for(int coc = 0; coc < coccion.size(); coc++){  //recorremos cada elemento
                                Entidad mosto = coccion.get(coc);           //guardamos la entidad mosto    
                                if(mosto.getTiempo6() == tiempoActual){     //preguntamos si es tiempo de salida
                                    if(enfriado.size() < 10){               //si hay espacio en enfriado
                                        coccion.remove(mosto);              //eliminamos de coccion
                                        simPanel.llegadaPieza("coccionVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Mosto salio de coccion\n");
                                        enfriado.add(mosto);                //añadimos a enfriado
                                        simPanel.llegadaPieza("enfriadoLleno");
                                        contEnfriado++;                     //aumentamos el contador e enfriado
                                        procesarOperacion(mosto, tiempoActual, "enfriado", 1.0, "mosto");//procesamos en enfriado
                                    }else{
                                       //textAreaA.append("Mosto bloqueado en coccion\n");
                                       mosto.setTiempo6(dosDecimales(tiempoActual+0.01));
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        // Cocción
                        if (!coccion.isEmpty()) {                           //Coccion no esta vacio
                            for (int coc = 0; coc < coccion.size(); coc++){ //recorremos cada entidad
                                Entidad grano = coccion.get(coc);           //gurdamos la entidad
                                if (!grano.getInArea1()) {                  //preguntamos si ya se mesclo
                                    if (siloLupulo.size() >= capacidadCoccion){//vemos si hay suficiente lupulo
                                        grano.setInArea1(true);             //Marcar como procesado
                                        int elementosProcesados = 0;        //Contador de elementos procesados
                                        for (int sl = 0; sl < capacidadCoccion && !siloLupulo.isEmpty(); sl++){//procesamos lupulo
                                            retrasoGrafica(delay);
                                            //textAreaA.append("Se dio " + (sl + 1) + " de lupulo\n");
                                            Entidad lupulo = siloLupulo.remove(0); //Obtener y eliminar el primer elemento
                                            salidaLupulo++;                 //aumentamos contador de salidas
                                            trabajadorLupulo.add(lupulo);   //añadimos a operario
                                            simPanel.llegadaPieza("operador2Lleno");
                                            retrasoGrafica(delay);
                                            simPanel.moverRecurso("operadorLupulo", 870, 30);
                                            trabajadorLupulo.remove(lupulo);//eliminamos de operario
                                            simPanel.llegadaPieza("operador2Vacio");
                                            retrasoGrafica(delay);
                                            simPanel.moverRecurso("operadorLupulo", 710, 210);
                                            elementosProcesados++;          //Incrementar contador
                                        }
                                        contMosto++;                        //Imcrementamos el contador de mostoproducido
                                        textAreaA.append("Mosto producido: " + contMosto + "\n");
                                        grano.cambiarNombre("Mosto");       //Cambiamos el nombre de la entidad a mosto
                                        procesarOperacion(grano, tiempoActual, "coccion", 1, "grano");//procesamos mosto en coccion
                                    } else {
                                        //textAreaA.append("Esperando suficiente lúpulo\n");
                                        break;                              //Si no hay mosto suficiente ya no pregunta a los demas
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Area de filtrado
                        if(!filtrado.isEmpty()){                            //Filtrado no esta vacio
                            for(int fil = 0; fil < filtrado.size(); fil++){ //recorremos cada entidad
                                Entidad grano = filtrado.get(fil);          //guardamos la entidad
                                if(grano.getTiempo5() == tiempoActual){     //preguntamos si es tiempo de salida
                                    if(coccion.size() < 10){                //hay espacio en coccion
                                        filtrado.remove(grano);             //eliminamos de filtrado
                                        simPanel.llegadaPieza("filtradoVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Grano salio de filtrado\n");
                                        coccion.add(grano);                 //añadimos a coccion
                                        simPanel.llegadaPieza("coccionLleno");
                                    }else{
                                        //textAreaA.append("Grano bloqueado en maserado\n");
                                        grano.setTiempo5(dosDecimales(tiempoActual+0.01));  //actualizamos el tiempo
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Area de maserado
                        if(!maserado.isEmpty()){                            //Maserado no esta vacio
                            for(int mas = 0; mas < maserado.size(); mas++){ //recorremos cada entidad
                                Entidad grano = maserado.get(mas);          //guardamos la entidad
                                if(grano.getTiempo4() == tiempoActual){     //preguntamos si es tiempo de salida
                                    if(filtrado.size() < 2){                //si hay espacio en filtrado
                                        maserado.remove(grano);             //eliminamos de maserado
                                        simPanel.llegadaPieza("maseradoVacio");
                                        retrasoGrafica(delay);
                                        textAreaA.append("Grano salio de maserado\n");
                                        filtrado.add(grano);                //añadimos a filtrado
                                        simPanel.llegadaPieza("filtradoLleno");
                                        contFiltrado++;                     //aumentamos contador de filtrado
                                        procesarOperacion(grano, tiempoActual, "filtrado", 0.5, "grano"); //procesamos en filtrado
                                    }else{
                                        //textAreaA.append("Grano bloqueado en maserado\n");
                                        grano.setTiempo4(dosDecimales(tiempoActual+0.01));
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Area de molienda
                        if(!molienda.isEmpty()){                            //Molienda no esta vacio
                            for(int mol = 0; mol < molienda.size(); mol++){ //recorremos cada entidad
                                Entidad grano = molienda.get(mol);          //guardamos la entodad
                                if(grano.getTiempo3() == tiempoActual){     //preguntamos si es tiempo de salida
                                    if(maserado.size() < 3){                //hay espacio en maserado
                                        molienda.remove(grano);             //eliminamos de molienda
                                        simPanel.llegadaPieza("moliendaVacio");
                                        retrasoGrafica(delay);
                                        simPanel.llegadaPieza("moliendaLleno");
                                        textAreaA.append("Grano salio de molienda\n");
                                        maserado.add(grano);                //añadimos a maserado
                                        simPanel.llegadaPieza("maseradoLleno");
                                        contMaserado++;                     //aumentamos contador de maserado
                                        procesarOperacion(grano,tiempoActual, "maserado", 1.5, "grano"); //procesamos en maserado
                                    }else{
                                        //textAreaA.append("Grano bloqueado en molienda\n");
                                        grano.setTiempo3(dosDecimales(tiempoActual+0.01)); //actualizamos el tiempo
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Area de Secado
                        if(!secado.isEmpty()){                              //Secado esta no vacio
                            for(int sec = 0; sec < secado.size(); sec++){   //recorremos cada entidad en secado
                                Entidad grano = secado.get(sec);            //guradamos la entidad
                                if(grano.getTiempo2() == tiempoActual){     //preguntamos si es tiempo salida
                                    if(molienda.size() < 2){                //hay espacion en molienda
                                        simPanel.moverRecurso("operadorRecepcion", 380, 30);
                                        retrasoGrafica(delay);
                                        secado.remove(grano);               //eliminamod de secado
                                        simPanel.llegadaPieza("secadoVacio");
                                        textAreaA.append("Grano salio de secado\n");
                                        retrasoGrafica(delay);
                                        operadorRecepcion.add(grano);       //añadimos a operador
                                        simPanel.llegadaPieza("operador1Lleno");
                                        simPanel.moverRecurso("operadorRecepcion", 530, 30);
                                        simPanel.llegadaPieza("secadoLleno");
                                        retrasoGrafica(delay);
                                        operadorRecepcion.remove(grano);    //eliminamos de operador
                                        molienda.add(grano);                //añadimos a molienda
                                        simPanel.llegadaPieza("operador1Vacio");
                                        retrasoGrafica(delay);
                                        simPanel.llegadaPieza("moliendaLleno");
                                        simPanel.moverRecurso("operadorRecepcion", 550, 210);
                                        contMolienda++;                     //aumentamos contador de molienda
                                        procesarOperacion(grano, tiempoActual, "molienda", 1.0, "grano"); //procesamos en molienda
                                    }else{
                                        //textAreaA.append("Grano bloqueado en secado\n");
                                        grano.setTiempo2(dosDecimales(tiempoActual+0.01)); //actualizamos el tiempo
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Area de Malteado
                        if(!malteado.isEmpty()){                            //Malteado no esta vacion
                            for(int mal = 0; mal < malteado.size(); mal++){ //recorremos cada entidad en malteado
                                Entidad grano = malteado.get(mal);          //guardamos la entidad
                                if(grano.getTiempo1() == tiempoActual){     //preguntamos si ya es tiempo de salida
                                    if(secado.size() < 3){                  //si hay espacion en secado
                                        simPanel.moverRecurso("operadorRecepcion", 230, 30);
                                        retrasoGrafica(delay);
                                        malteado.remove(grano);             //eliminamos de malteado
                                        simPanel.llegadaPieza("malteadoVacio");
                                        textAreaA.append("Grano salio de malteado\n");
                                        retrasoGrafica(delay);
                                        operadorRecepcion.add(grano);       //le damos la operador
                                        simPanel.llegadaPieza("operador1Lleno");
                                        simPanel.moverRecurso("operadorRecepcion", 380, 30);
                                        simPanel.llegadaPieza("malteadoLleno");
                                        retrasoGrafica(delay);
                                        operadorRecepcion.remove(grano);    //le quitamos al operador
                                        secado.add(grano);                  //agragamos a secado
                                        simPanel.llegadaPieza("operador1Vacio");
                                        simPanel.llegadaPieza("secadoLleno");
                                        retrasoGrafica(delay);
                                        simPanel.moverRecurso("operadorRecepcion", 550, 210);
                                        contSecado++;                       //aumentamos el contador de secado
                                        procesarOperacion(grano, tiempoActual, "secado", 1.0, "grano"); //procesamos en secado
                                    }else{
                                        //textAreaA.append("Grano bloqueado en area malteado\n");
                                        grano.setTiempo1(dosDecimales(tiempoActual+0.01)); //actualizamos el tiempo para que entre
                                    }
                                }
                            }
                        }
                        simPanel.actualizarEstado(
                                contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                                contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                                contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                                contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                                contAlmacen,  contMercado);
                        //Silo grano
                        if(!siloCebada.isEmpty()){                          //Silo de cebada no esta vacio
                            for(int sg = 0; sg < siloCebada.size(); sg++){  //recorremos cada entidad del silo
                                Entidad grano = siloCebada.get(sg);         //guardamos la entidad
                                if(malteado.size() < 3){                    //preguntamos si hay espacio en malteado
                                    siloCebada.remove(grano);               //eliminamos la entidad del silo
                                    malteado.add(grano);                    //agragamos la entidad a malteado
                                    simPanel.llegadaPieza("malteadoLleno");
                                    contMalteado++;                         //aumentamos el contador a malteado
                                    procesarOperacion(grano, tiempoActual, "malteado", 1.0, "grano");   //procesamos en malteado
                                }else{
                                    break;  //Si no hay espacion ya no recorra las entidades
                                }
                            }
                        }
                        /*
                        //prueba grafica
                        int x = (int) (Math.random() * 600) + 1; // Math.random() genera un número entre 0.0 y 1.0
                        int y = (int) (Math.random() * 600) + 1;
                        simPanel.moverRecurso("almacen", x, y);
                        */
                        simPanel.actualizarEstado(
                        contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                        contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                        contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                        contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                        contAlmacen,  contMercado);
                        // ---------------------------------------------------------------------------------
                        j = j.add(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP);
                        //retrasoGrafica(delay); // Respetar el delay actualizado
                    }
                }
                if(i==duracion){
                    simPanel.actualizarEstado(
                            contSiloCebada, contSiloLupulo, contSiloLevadura, contAlmacenCaja,
                            contMalteado, contSecado,  contMolienda,  contMaserado,  contFiltrado, contMosto,
                            contEnfriado,  contAlcohol,  contMaduracion,  contIns,
                            contRechazado,  contEmbotellado,  contEtiquetado,  contEmpacado,
                            contAlmacen,  contMercado);
                }
            }
            //resultados
            double area1L = dosDecimales(((double)contArea1L / (duracion * 100)) * 100);
            double area1V = dosDecimales(((double)contArea1V / (duracion * 100)) * 100);
            double area2L = dosDecimales(((double)contArea2L / (duracion * 100)) * 100);
            double area2V = dosDecimales(((double)contArea2V / (duracion * 100)) * 100);
            double area3L = dosDecimales(((double)contArea3L / (duracion * 100)) * 100);
            double area3V = dosDecimales(((double)contArea3V / (duracion * 100)) * 100);
            double area4L = dosDecimales(((double)contArea4L / (duracion * 100)) * 100);
            double area4V = dosDecimales(((double)contArea4V / (duracion * 100)) * 100);
            double area5L = dosDecimales(((double)contArea5L / (duracion * 100)) * 100);
            double area5V = dosDecimales(((double)contArea5V / (duracion * 100)) * 100);
            double area6L = dosDecimales(((double)contArea6L / (duracion * 100)) * 100);
            double area6V = dosDecimales(((double)contArea6V / (duracion * 100)) * 100);
            double area7L = dosDecimales(((double)contArea7L / (duracion * 100)) * 100);
            double area7V = dosDecimales(((double)contArea7V / (duracion * 100)) * 100);
            double area8L = dosDecimales(((double)contArea8L / (duracion * 100)) * 100);
            double area8V = dosDecimales(((double)contArea8V / (duracion * 100)) * 100);
            double area9L = dosDecimales(((double)contArea9L / (duracion * 100)) * 100);
            double area9V = dosDecimales(((double)contArea9V / (duracion * 100)) * 100);
            double area10L = dosDecimales(((double)contArea10L / (duracion * 100)) * 100);
            double area10V = dosDecimales(((double)contArea10V / (duracion * 100)) * 100);
            double area11L = dosDecimales(((double)contArea11L / (duracion * 100)) * 100);
            double area11V = dosDecimales(((double)contArea11V / (duracion * 100)) * 100);
            double area12L = dosDecimales(((double)contArea12L / (duracion * 100)) * 100);
            double area12V = dosDecimales(((double)contArea12V / (duracion * 100)) * 100);
            double area13L = dosDecimales(((double)contArea13L / (duracion * 100)) * 100);
            double area13V = dosDecimales(((double)contArea13V / (duracion * 100)) * 100);
            double area14L = dosDecimales(((double)contArea14L / (duracion * 100)) * 100);
            double area14V = dosDecimales(((double)contArea14V / (duracion * 100)) * 100);
            double area15L = dosDecimales(((double)contArea15L / (duracion * 100)) * 100);
            double area15V = dosDecimales(((double)contArea15V / (duracion * 100)) * 100);
            double area16L = dosDecimales(((double)contArea16L / (duracion * 100)) * 100);
            double area16V = dosDecimales(((double)contArea16V / (duracion * 100)) * 100);
            double area17L = dosDecimales(((double)contArea17L / (duracion * 100)) * 100);
            double area17V = dosDecimales(((double)contArea17V / (duracion * 100)) * 100);
            double area18L = dosDecimales(((double)contArea18L / (duracion * 100)) * 100);
            double area18V = dosDecimales(((double)contArea18V / (duracion * 100)) * 100);
            double area19L = dosDecimales(((double)contArea19L / (duracion * 100)) * 100);
            double area19V = dosDecimales(((double)contArea19V / (duracion * 100)) * 100);
            BarChartExample example = new BarChartExample(
                    area1L, area1V, area2L, area2V, area3L, area3V, area4L, area4V, area5L, area5V,
                    area6L, area6V, area7L, area7V, area8L, area8V, area9L, area9V, area10L, area10V,
                    area11L, area11V, area12L, area12V, area13L, area13V, area14L, area14V, area15L, area15V,
                    area16L, area16V, area17L, area17V, area18L, area18V, area19L, area19V
            );
            example.setVisible(true);

            //Tabla
            Object[][] data = {
                    {"Arribos", ""},
                    {"Arribo de cebada", contSiloCebada},
                    {"Arribo fallido de cebada", contSiloCebadaF},
                    {"Arribo de lupulo", contSiloLupulo},
                    {"Arribo fallido de lupulo", contSiloLupuloF},
                    {"Arribo de levadura", contSiloLevadura},
                    {"Arribo fallido de levadura", contSiloLevaduraF},
                    {"Arribo de caja vacia", contAlmacenCaja},
                    {"Arribo fallido de caja vacia", contAlmacenCajaF},

                    // Cantidad procesada en locaciones
                    {"Cantidades procesadas por cada locacion", ""},
                    {"En malteado", contMalteado},
                    {"En secado", contSecado},
                    {"En molienda", contMolienda},
                    {"En maserado", contMaserado},
                    {"En filtrado", contFiltrado},
                    {"En cocción", contMosto},
                    {"En enfriado", contEnfriado},
                    {"En fermentado", contAlcohol},
                    {"En maduración", contMaduracion},
                    {"En inspección", contIns},
                    {"Rechazados", contRechazado},
                    {"En embotellado", contEmbotellado},
                    {"En etiquetado", contEtiquetado},
                    {"En empacado", contEmpacado},
                    {"En almacen", contAlmacen},
                    {"En mercado", contMercado},

                    // Entidades en proceso
                    {"Entidades en proceso", ""},
                    {"En silo de cebada", siloCebada.size()},
                    {"En Malteado", malteado.size()},
                    {"En secado", secado.size()},
                    {"En molienda", molienda.size()},
                    {"En maserado", maserado.size()},
                    {"En filtrado", filtrado.size()},
                    {"En silo de lupulo", siloLupulo.size()},
                    {"En cocción", coccion.size()},
                    {"En enfriado", enfriado.size()},
                    {"En silo de levadura", siloLevadura.size()},
                    {"En fermentación", fermentado.size()},
                    {"En maduración", maduracion.size()},
                    {"En inspección", inspeccion.size()},
                    {"En embotellado", embotellado.size()},
                    {"En etiquetado", etiquetado.size()},
                    {"En almacen de cajas", almacenCajas.size()},
                    {"En empacado", empacado.size()},
                    {"En almacen", almacen.size()},
                    {"En mercado", mercado.size()},

                    // Total salidas
                    {"Total salidas", ""},
                    {"Salidas de grano de cebada", 0},
                    {"Salidas de lupulo", salidaLupulo},
                    {"Salidas de Mosto", salidaMosto},
                    {"Salidas de levadura", salidaLevadura},
                    {"Salidas de alcohol", 0},
                    {"Salidas de botellas de cerveza", salidaBotella},
                    {"Salidas de caja vacía", salidaCaja},
                    {"Salidas de cajas de cerveza", salidaCajasC},

                    // Tiempo promedio en sistema
                    {"Tiempo promedio en sistema en minutos", ""},
                    {"Tiempo promedio en sistema de grano de cebada", 0.0},
                    {"Tiempo promedio en sistema de lupulo", 8.90},
                    {"Tiempo promedio en sistema de Mosto", 1041.1},
                    {"Tiempo promedio en sistema de levadura", 1670.0},
                    {"Tiempo promedio en sistema de alcohol", 0.0},
                    {"Tiempo promedio en sistema de botellas de cerveza", 1245.3},
                    {"Tiempo promedio en sistema de caja vacía", 2967.1},
                    {"Tiempo promedio en sistema de cajas de cerveza", 0.0}
            };

            // Mostrar la tabla
            SwingUtilities.invokeLater(() -> {
                new ResultadosTabla(data, new String[]{"Descripción", "Valor"}).setVisible(true);
            });

        }
    }


    class SimulacionPanel extends JPanel {
        //Locaciones
        private Area siloGrano = new Area("Silo de grano", 10, 20, 100, 100, Color.GREEN);
        private Area malteado = new Area("Malteado", 150, 20, 100, 100, Color.GREEN);
        private Area secado = new Area("Secado", 300, 20, 100, 100, Color.GREEN);
        private Area molienda = new Area("Molienda", 450, 20, 100, 100, Color.GREEN);
        private Area maserado = new Area("Maserado", 600, 20, 100, 100, Color.GREEN);
        private Area filtrado = new Area("Filtrado", 750, 20, 100, 100, Color.GREEN);
        private Area siloLupulo = new Area("Silo de Lupulo", 600, 200, 100, 100, Color.GREEN);
        private Area coccion = new Area("Cocion", 900, 20, 100, 100, Color.GREEN);
        private Area enfriado = new Area("Enfriado", 900, 200, 100, 100, Color.GREEN);
        private Area siloLevadura = new Area("Silo de levadura", 1050, 200, 100, 100, Color.GREEN);
        private Area fermentado = new Area("fermentado", 900, 380, 100, 100, Color.GREEN);
        private Area madurado = new Area("Madurado", 750, 380, 100, 100, Color.GREEN);
        private Area inspeccion = new Area("Inspeccion", 600, 380, 100, 100, Color.GREEN);
        private Area embotellado = new Area("Embotellado", 450, 380, 100, 100, Color.GREEN);
        private Area etiquetado = new Area("Etiquetado", 300, 380, 100, 100, Color.GREEN);
        private Area almacenCajas = new Area("Almacen de cajas", 150, 200, 100, 100, Color.GREEN);
        private Area empacado = new Area("Empacado", 150, 380, 100, 100, Color.GREEN);
        private Area almacen = new Area("Almacen", 150, 530, 100, 100, Color.GREEN);
        private Area mercado = new Area("Mercado", 1050, 530, 100, 100, Color.GREEN);
        //Recursos
        private Area operadorRecepcion = new Area("", 550, 210, 40, 80, Color.GREEN);
        private Area operadorLupulo = new Area("", 710, 210, 40, 80, Color.GREEN);
        private Area operadorLevadura = new Area("", 1160, 210, 40, 80, Color.GREEN);
        private Area operadorCaja = new Area("", 110, 390, 40, 80, Color.GREEN);
        private Area camion = new Area("", 260, 550, 80, 40, Color.GREEN);
        //Contadores
        private Area contSiloGrano = new Area("", 10, 130, 100, 20, Color.GRAY);
        private Area contMalteado = new Area("", 150, 130, 100, 20, Color.GRAY);
        private Area contSecado = new Area("", 300, 130, 100, 20, Color.GRAY);
        private Area contMolienda = new Area("", 450, 130, 100, 20, Color.GRAY);
        private Area contMaserado = new Area("", 600, 130, 100, 20, Color.GRAY);
        private Area contFiltrado = new Area("", 750, 130, 100, 20, Color.GRAY);
        private Area contSiloLupulo = new Area("", 600, 310, 100, 20, Color.GRAY);
        private Area contCoccion = new Area("", 900, 130, 100, 20, Color.GRAY);
        private Area contEnfriado = new Area("", 900, 310, 100, 20, Color.GRAY);
        private Area contSiloLevadura = new Area("", 1050, 310, 100, 20, Color.GRAY);
        private Area contFermentado = new Area("", 900, 490, 100, 20, Color.GRAY);
        private Area contMadurado = new Area("", 750, 490, 100, 20, Color.GRAY);
        private Area contInspeccion = new Area("", 600, 490, 100, 20, Color.GRAY);
        private Area contRechazados = new Area("", 600, 520, 100, 20, Color.GRAY);
        private Area contEmbotellado = new Area("", 450, 490, 100, 20, Color.GRAY);
        private Area contEtiquetado = new Area("", 300, 490, 100, 20, Color.GRAY);
        private Area contAlmacenCajas = new Area("", 150, 310, 100, 20, Color.GRAY);
        private Area contEmpacado = new Area("", 150, 490, 100, 20, Color.GRAY);
        private Area contAlmacen = new Area("", 30, 610, 100, 20, Color.GRAY);
        private Area contMercado = new Area("", 1180, 610, 100, 20, Color.GRAY);

        //Contenedores
        private Area CsiloGrano = new Area("", 50, 70, 40, 40, new Color(0, 0, 0, 0));
        private Area Cmalteado = new Area("", 190, 60, 40, 40, new Color(0, 0, 0, 0));
        private Area Csecado = new Area("", 340, 60, 40, 40, new Color(0, 0, 0, 0));
        private Area Cmolienda = new Area("", 490, 60, 40, 40, new Color(0, 0, 0, 0));
        private Area Cmaserado = new Area("", 640, 60, 40, 40, new Color(0, 0, 0, 0));
        private Area Cfiltrado = new Area("", 790, 60, 40, 40, new Color(0, 0, 0, 0));
        private Area CsiloLupulo = new Area("", 640, 240, 40, 40, new Color(0, 0, 0, 0));
        private Area Ccoccion = new Area("", 940, 60, 40, 40, new Color(0, 0, 0, 0));
        private Area Cenfriado = new Area("", 940, 240, 40, 40, new Color(0, 0, 0, 0));
        private Area CsiloLevadura = new Area("", 1090, 240, 40, 40, new Color(0, 0, 0, 0));
        private Area Cfermentado = new Area("", 940, 420, 40, 40, new Color(0, 0, 0, 0));
        private Area Cmadurado = new Area("", 790, 420, 40, 40, new Color(0, 0, 0, 0));
        private Area Cinspeccion = new Area("", 640, 420, 40, 40, new Color(0, 0, 0, 0));
        private Area Cembotellado = new Area("", 490, 420, 40, 40, new Color(0, 0, 0, 0));
        private Area Cetiquetado = new Area("", 320, 400, 40, 80, new Color(0, 0, 0, 0));
        private Area CalmacenCajas = new Area("", 190, 240, 40, 40, new Color(0, 0, 0, 0));
        private Area Cempacado = new Area("", 170, 400, 40, 80, new Color(0, 0, 0, 0));
        private Area Calmacen = new Area("", 190, 570, 40, 40, new Color(0, 0, 0, 0));
        private Area Cmercado = new Area("", 1090, 570, 40, 40, new Color(0, 0, 0, 0));


        //variables de imagenes
        private BufferedImage siloImg, malteadoImg, secadoImg, moliendaImg, maseradoImg, filtradoImg,
                coccionImg, enfriadoImg, fermentadoImg, maduradoImg, inspeccionImg, embotelladoImg, etiquetadoImg,
                almacenCajasImg, empacadoImg, almacenImg, mercadoImg, operadorImg, camionImg, granoImg, lupuloImg,
                levaduraImg, cajaImg, mostoImg, alcoholImg, botellaImg, cajaCervezaImg,paqueteImg,
                operaRecepcionImg, operaLupuloImg, operaLevaduraImg, operaCajaImg, camionCargaImg;

        public SimulacionPanel() {
            this.setPreferredSize(new Dimension(800, 600));
            this.setBackground(Color.WHITE);
            // Cargar la imagen al inicializar el panel
            try {
                siloImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/silo.jpeg"));
                malteadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/malteado.jpeg"));
                secadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/secado1.jpg"));
                moliendaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/molienda.jpeg"));
                maseradoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/maserado.jpeg"));
                filtradoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/filtrado.jpeg"));
                coccionImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/coccion.jpeg"));
                enfriadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/enfriado.jpg"));
                fermentadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/fermentado.jpeg"));
                maduradoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/maduracion.jpeg"));
                inspeccionImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/inspeccion.jpeg"));
                embotelladoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/embotellado.jpg"));
                etiquetadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/etiquetado.jpg"));
                almacenCajasImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/almacenCaja.jpeg"));
                empacadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/empacado.jpeg"));
                almacenImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/almacen.jpeg"));
                mercadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/mercado.jpeg"));
                camionImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/camion.png"));
                operadorImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/operadorA.png"));
                //Entidades
                granoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/sacoA.png"));
                lupuloImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/lupuloA.png"));
                levaduraImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/levaduraA.png"));
                cajaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/cajaVaciaA.png"));
                mostoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/mosto1.jpg"));
                alcoholImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/cerveza.jpg"));
                botellaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/botella.png"));
                cajaCervezaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/cajaCervezaA.png"));
                paqueteImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/paquete.png"));
                //operador con cargas
                operaRecepcionImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/operadorGrano.png"));
                operaLupuloImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/operadorLupulo.png"));
                operaLevaduraImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/operadorLevadura.png"));
                operaCajaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/operadorCaja.png"));
                camionCargaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto3/img/camionPaquete.png"));
            } catch (IOException e) {
            }

            //Damos imagen de fondo a los sitios fijos
            siloGrano.setBackgroundImage(siloImg);
            malteado.setBackgroundImage(malteadoImg);
            secado.setBackgroundImage(secadoImg);
            molienda.setBackgroundImage(moliendaImg);
            maserado.setBackgroundImage(maseradoImg);
            filtrado.setBackgroundImage(filtradoImg);
            coccion.setBackgroundImage(coccionImg);
            siloLupulo.setBackgroundImage(siloImg);
            enfriado.setBackgroundImage(enfriadoImg);
            siloLevadura.setBackgroundImage(siloImg);
            fermentado.setBackgroundImage(fermentadoImg);
            madurado.setBackgroundImage(maduradoImg);
            inspeccion.setBackgroundImage(inspeccionImg);
            embotellado.setBackgroundImage(embotelladoImg);
            etiquetado.setBackgroundImage(etiquetadoImg);
            almacenCajas.setBackgroundImage(almacenCajasImg);
            empacado.setBackgroundImage(empacadoImg);
            almacen.setBackgroundImage(almacenImg);
            mercado.setBackgroundImage(mercadoImg);
            //recursos
            operadorRecepcion.setBackgroundImage(operadorImg);
            operadorLupulo.setBackgroundImage(operadorImg);
            operadorLevadura.setBackgroundImage(operadorImg);
            operadorCaja.setBackgroundImage(operadorImg);
            camion.setBackgroundImage(camionImg);
        }


        public void llegadaPieza(String nombre) {
            // Cambia la imagen de fondo del área seleccionada y configura el temporizador para volver al color original
            switch (nombre) {
                case "siloGrano":
                    CsiloGrano.setBackgroundImage(granoImg);
                    repaint();
                    new Timer(1000, e -> {
                        CsiloGrano.setBackgroundImage(null); // Quita la imagen de fondo
                        repaint();
                    }).start();
                    break;
                case "siloLupulo":
                    CsiloLupulo.setBackgroundImage(lupuloImg);
                    repaint();
                    new Timer(1000, e -> {
                        CsiloLupulo.setBackgroundImage(null); // Quita la imagen de fondo
                        repaint();
                    }).start();
                    break;
                case "siloLevadura":
                    CsiloLevadura.setBackgroundImage(levaduraImg);
                    repaint();
                    new Timer(1000, e -> {
                        CsiloLevadura.setBackgroundImage(null); // Quita la imagen de fondo
                        repaint();
                    }).start();
                    break;
                case "almacenCaja":
                    CalmacenCajas.setBackgroundImage(cajaImg);
                    repaint();
                    new Timer(1000, e -> {
                        CalmacenCajas.setBackgroundImage(null); // Quita la imagen de fondo
                        repaint();
                    }).start();
                    break;
                case "malteadoLleno":
                    Cmalteado.setBackgroundImage(granoImg);
                    repaint();
                    break;
                case "malteadoVacio":
                    Cmalteado.setBackgroundImage(null);
                    repaint();
                    break;
                case "operador1Lleno":
                    operadorRecepcion.setBackgroundImage(operaRecepcionImg);
                    repaint();
                    break;
                case "operador1Vacio":
                    operadorRecepcion.setBackgroundImage(operadorImg);
                    repaint();
                    break;
                case "secadoLleno":
                    Csecado.setBackgroundImage(granoImg);
                    repaint();
                    break;
                case "secadoVacio":
                    Csecado.setBackgroundImage(null);
                    repaint();
                    break;
                case "moliendaLleno":
                    Cmolienda.setBackgroundImage(granoImg);
                    repaint();
                    break;
                case "moliendaVacio":
                    Cmolienda.setBackgroundImage(null);
                    repaint();
                    break;
                case "maseradoLleno":
                    Cmaserado.setBackgroundImage(granoImg);
                    repaint();
                    break;
                case "maceradoVacio":
                    Cmaserado.setBackgroundImage(null);
                    repaint();
                    break;
                case "filtradoLleno":
                    Cfiltrado.setBackgroundImage(granoImg);
                    repaint();
                    break;
                case "filtradoVacio":
                    Cfiltrado.setBackgroundImage(null);
                    repaint();
                    break;
                case "coccionLleno":
                    Ccoccion.setBackgroundImage(granoImg);
                    repaint();
                    break;
                case "coccionVacio":
                    Ccoccion.setBackgroundImage(null);
                    repaint();
                    break;
                case "operador2Lleno":
                    operadorLupulo.setBackgroundImage(operaLupuloImg);
                    repaint();
                    break;
                case "operador2Vacio":
                    operadorLupulo.setBackgroundImage(operadorImg);
                    repaint();
                    break;
                case "enfriadoLleno":
                    Cenfriado.setBackgroundImage(mostoImg);
                    repaint();
                    break;
                case "enfriadoVacio":
                    Cenfriado.setBackgroundImage(null);
                    repaint();
                    break;
                case "fermentadoLleno":
                    Cfermentado.setBackgroundImage(mostoImg);
                    repaint();
                    break;
                case "fermentadoVacio":
                    Cfermentado.setBackgroundImage(null);
                    repaint();
                    break;
                case "operador3Lleno":
                    operadorLevadura.setBackgroundImage(operaLevaduraImg);
                    repaint();
                    break;
                case "operador3Vacio":
                    operadorLevadura.setBackgroundImage(operadorImg);
                    repaint();
                    break;
                case "maduracionLleno":
                    Cmadurado.setBackgroundImage(alcoholImg);
                    repaint();
                    break;
                case "maduracionVacio":
                    Cmadurado.setBackgroundImage(null);
                    repaint();
                    break;
                case "inspeccionLleno":
                    Cinspeccion.setBackgroundImage(alcoholImg);
                    repaint();
                    break;
                case "inspeccionVacio":
                    Cinspeccion.setBackgroundImage(null);
                    repaint();
                    break;
                case "embotelladoLleno":
                    Cembotellado.setBackgroundImage(alcoholImg);
                    repaint();
                    break;
                case "embotelladoVacio":
                    Cembotellado.setBackgroundImage(null);
                    repaint();
                    break;
                case "etiquetadoLleno":
                    Cetiquetado.setBackgroundImage(botellaImg);
                    repaint();
                    break;
                case "etiquetadoVacio":
                    Cetiquetado.setBackgroundImage(null);
                    repaint();
                    break;
                case "operador4Lleno":
                    operadorCaja.setBackgroundImage(operaCajaImg);
                    repaint();
                    break;
                case "operador4Vacio":
                    operadorCaja.setBackgroundImage(operadorImg);
                    repaint();
                    break;
                case "empacadoLleno":
                    Cempacado.setBackgroundImage(botellaImg);
                    repaint();
                    break;
                case "empacadoVacio":
                    Cempacado.setBackgroundImage(null);
                    repaint();
                    break;
                case "almacenLleno":
                    Calmacen.setBackgroundImage(cajaCervezaImg);
                    repaint();
                    break;
                case "almacenVacio":
                    Calmacen.setBackgroundImage(null);
                    repaint();
                    break;
                case "camionLleno":
                    camion.setBackgroundImage(camionCargaImg);
                    repaint();
                    break;
                case "camionVacio":
                    camion.setBackgroundImage(camionImg);
                    repaint();
                    break;
                case "mercadoLleno":
                    Cmercado.setBackgroundImage(paqueteImg);
                    repaint();
                    break;
                case "mercadoVacio":
                    Cmercado.setBackgroundImage(null);
                    repaint();
                    break;
                default:
                    return;
            }
        }

        public void moverRecurso(String nombre, int nuevaX, int nuevaY) {
            switch (nombre){
                case "operadorRecepcion":
                    operadorRecepcion.setX(nuevaX);
                    operadorRecepcion.setY(nuevaY);
                    repaint(); // Actualiza la gráfica
                    break;
                case "operadorLupulo":
                    operadorLupulo.setX(nuevaX);
                    operadorLupulo.setY(nuevaY);
                    repaint(); // Actualiza la gráfica
                    break;
                case "operadorLevadura":
                    operadorLevadura.setX(nuevaX);
                    operadorLevadura.setY(nuevaY);
                    repaint(); // Actualiza la gráfica
                    break;
                case "operadorCajas":
                    operadorCaja.setX(nuevaX);
                    operadorCaja.setY(nuevaY);
                    repaint(); // Actualiza la gráfica
                    break;
                case "camion":
                    camion.setX(nuevaX);
                    camion.setY(nuevaY);
                    repaint(); // Actualiza la gráfica
                    break;
                default:
                    return;
            }
        }

        public void actualizarEstado(
                int contCebada, int contLupulo, int contLevadura, int contCaja,
                int contMalteado, int contSecado, int contMolienda, int contMaserado, int contFiltrado,
                int contMosto, int contEnfriado, int contAlcohol, int contMaduracion, int contIns,
                int contRechazado, int contEmbotellado, int contEtiquetado, int contEmpacado,
                int contAlmacen, int contMercado) {

            // Actualiza los contadores de cada área
            this.contSiloGrano.setContador(contCebada);
            this.contSiloLupulo.setContador(contLupulo);
            this.contSiloLevadura.setContador(contLevadura);
            this.contAlmacenCajas.setContador(contCaja);
            this.contRechazados.setContador(contRechazado);
            this.contMalteado.setContador(contMalteado);
            this.contSecado.setContador(contSecado);
            this.contMolienda.setContador(contMolienda);
            this.contMaserado.setContador(contMaserado);
            this.contFiltrado.setContador(contFiltrado);
            this.contCoccion.setContador(contMosto);
            this.contEnfriado.setContador(contEnfriado);
            this.contFermentado.setContador(contAlcohol);
            this.contMadurado.setContador(contMaduracion);
            this.contInspeccion.setContador(contIns);
            this.contEmbotellado.setContador(contEmbotellado);
            this.contEtiquetado.setContador(contEtiquetado);
            this.contEmpacado.setContador(contEmpacado);
            this.contAlmacen.setContador(contAlmacen);
            this.contMercado.setContador(contMercado);
            // Redibuja la gráfica
            repaint();
        }


        @Override
        protected void paintComponent(Graphics g) { //Dibujar las areas
            super.paintComponent(g);
            drawArea(g, siloGrano); drawArea(g, malteado); drawArea(g, secado); drawArea(g, molienda);
            drawArea(g, maserado); drawArea(g, filtrado); drawArea(g, coccion); drawArea(g, siloLupulo);
            drawArea(g, enfriado); drawArea(g, siloLevadura); drawArea(g, fermentado); drawArea(g, madurado);
            drawArea(g, inspeccion); drawArea(g, embotellado); drawArea(g, etiquetado); drawArea(g, almacenCajas);
            drawArea(g, empacado); drawArea(g, almacen); drawArea(g, mercado);

            drawArea(g, operadorRecepcion); drawArea(g, operadorLupulo); drawArea(g, operadorLevadura);
            drawArea(g, operadorCaja); drawArea(g, camion);

            drawArea(g, contSiloGrano); drawArea(g, contMalteado); drawArea(g, contSecado); drawArea(g, contMolienda);
            drawArea(g, contMaserado); drawArea(g, contFiltrado); drawArea(g, contCoccion); drawArea(g, contSiloLupulo);
            drawArea(g, contEnfriado); drawArea(g, contSiloLevadura); drawArea(g, contFermentado); drawArea(g, contMadurado);
            drawArea(g, contInspeccion); drawArea(g, contEmbotellado); drawArea(g, contEtiquetado); drawArea(g, contAlmacenCajas);
            drawArea(g, contEmpacado); drawArea(g, contAlmacen); drawArea(g, contMercado); drawArea(g, contRechazados);

            drawArea(g, CsiloGrano); drawArea(g, Cmalteado); drawArea(g, Csecado); drawArea(g, Cmolienda);
            drawArea(g, Cmaserado); drawArea(g, Cfiltrado); drawArea(g, Ccoccion); drawArea(g, CsiloLupulo);
            drawArea(g, Cenfriado); drawArea(g, CsiloLevadura); drawArea(g, Cfermentado); drawArea(g, Cmadurado);
            drawArea(g, Cinspeccion); drawArea(g, Cembotellado); drawArea(g, Cetiquetado); drawArea(g, CalmacenCajas);
            drawArea(g, Cempacado); drawArea(g, Calmacen); drawArea(g, Cmercado);
        }

        private void drawArea(Graphics g, Area area) {// metodo para dibujar las areas
            if (area.getBackgroundImage() != null) {
                g.drawImage(area.getBackgroundImage(), area.getX(), area.getY(), area.getWidth(), area.getHeight(), null);
            } else {
                g.setColor(area.getColor());
                g.fillRect(area.getX(), area.getY(), area.getWidth(), area.getHeight());
            }
            g.setColor(Color.BLACK);
            g.drawString(area.getName(), area.getX() + 5, area.getY() - 5);
            //Agragar texto a los componentes graficos
            if (area == contSiloGrano || area == contMalteado || area == contSecado || area == contMolienda || area == contMaserado || area == contFiltrado || area == contCoccion){
                g.drawString("Grano: " + area.getContador(), area.getX() + 5, area.getY() + 15);
            }
            if (area == contAlmacenCajas){g.drawString("Cajas vacias: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
            if (area == contSiloLupulo){g.drawString("Lupulo: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
            if (area == contSiloLevadura){g.drawString("Levadura: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
            if (area == contEnfriado || area == contFermentado){g.drawString("Mosto: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
            if (area == contMadurado || area == contInspeccion){g.drawString("Alcohol: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
            if (area == contRechazados){g.drawString("Rechazados: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
            if (area == contEmbotellado || area == contEtiquetado || area == contEmpacado){
                g.drawString("Botellas: " + area.getContador(), area.getX() + 5, area.getY() + 15);
            }
            if (area == contAlmacen){g.drawString("Cajas: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
            if (area == contMercado){g.drawString("Paquetes: " + area.getContador(), area.getX() + 5, area.getY() + 15);}
        }

        private static class Area {
            private String name;
            private int x, y, width, height;
            private Color color;
            private int contador;
            private double contadorD;
            private BufferedImage backgroundImage; // Añadir imagen de fondo

            public Area(String name, int x, int y, int width, int height, Color color) {
                this.name = name;
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.color = color;
            }

            public String getName() { return name; }
            public int getX() { return x; }
            public int getY() { return y; }
            public int getWidth() { return width; }
            public int getHeight() { return height; }
            public Color getColor() { return color; }
            public int getContador() { return contador; }
            public double getContadorD() { return contadorD; }
            public void setContador(int contador) { this.contador = contador; }
            public void setContadorD(double contadorD) { this.contadorD = contadorD; }
            public void setX(int x) { this.x = x; }
            public void setY(int y) { this.y = y; }
            public void setColor(Color color) { this.color = color; }

            public BufferedImage getBackgroundImage() { return backgroundImage; }
            public void setBackgroundImage(BufferedImage image) { this.backgroundImage = image; }
        }
    }
    
    public double arribo (List<Entidad> locacion, int capacidad, String recurso, double intervalo, double tiempoActual){
        double res = 0;
        if(locacion.size() < capacidad){
            if(null != recurso)switch (recurso) {
                case "cebada" -> contSiloCebada++;
                case "lupulo" -> contSiloLupulo++;
                case "levadura" -> contSiloLevadura++;
                case "caja vacia" -> contAlmacenCaja++;
                default -> {
                }
            }
            textAreaA.append("Llego 1 kg "+recurso+"\n");   //imprime hora
            Entidad entidad = new Entidad(recurso,1, false, false, false); //con sus datos
            locacion.add(entidad);                          //y los ponemos en estiraje
            res = dosDecimales(tiempoActual + intervalo);
        }else{
            if(null != recurso)switch (recurso) {
                case "cebada" -> contSiloCebadaF++;
                case "lupulo" -> contSiloLupuloF++;
                case "levadura" -> contSiloLevaduraF++;
                case "caja vacia" -> contAlmacenCajaF++;
                default -> {
                }
            }
            textAreaA.append("Arribo fallido 1 "+recurso+"\n");
            res = dosDecimales(tiempoActual + intervalo);   //mandamos el tiempo del siguiente pero vacio
        }
        return res;
    }
    
    public void procesarOperacion(Entidad grano, double tiempoActual, String operacion, double proceso, String nombre) {
        //textAreaA.append("El "+nombre+" tarda una "+proceso+" horas en " + operacion + "\n");
        double tiempoSalida = dosDecimales(tiempoActual + proceso); // procesamos en tiempo de horas
        
        // Actualizar el tiempo correspondiente según la operación
        switch (operacion.toLowerCase()) {
            case "malteado":
                grano.setTiempo1(tiempoSalida);
                break;
            case "secado":
                grano.setTiempo2(tiempoSalida);
                break;
            case "molienda":
                grano.setTiempo3(tiempoSalida);
                break;
            case "maserado":
                grano.setTiempo4(tiempoSalida);
                break;
            case "filtrado":
                grano.setTiempo5(tiempoSalida);
                break;
            case "coccion":
                grano.setTiempo6(tiempoSalida);
                break;
            case "enfriado":
                grano.setTiempo7(tiempoSalida);
                break;
            case "fermentado":
                grano.setTiempo8(tiempoSalida);
                break;
            case "madurado":
                grano.setTiempo9(tiempoSalida);
                break;
            case "inspeccion":
                grano.setTiempo10(tiempoSalida);
                break;
            case "embotellado":
                grano.setTiempo11(tiempoSalida);
                break;
            case "etiquetado":
                grano.setTiempo12(tiempoSalida);
                break;
            case "empacado":
                grano.setTiempo13(tiempoSalida);
                break;
            case "almacen":
                grano.setTiempo14(tiempoSalida);
                break;
            default:
                textAreaA.append("Operación desconocida: " + operacion + "\n");
                return; // Salir si la operación no es válida
        }

        //textAreaA.append("El "+nombre+" sale de " + operacion + " en la hora: " + tiempoSalida + "\n");
    }

    public double calcularPorUti(List<Double> listaPorcentajes) {
        if (listaPorcentajes.isEmpty()) {
            return 0.0; // Devuelve 0 si la lista está vacía
        }
        double sumaPorcentajes = 0.0;
        for (double porcentaje : listaPorcentajes) {
            sumaPorcentajes += porcentaje;
        }
        // Calcula el promedio de la lista de porcentajes
        return sumaPorcentajes / listaPorcentajes.size();
    }

    public double dosDecimales(double numero){
        BigDecimal numeroNo = new BigDecimal(numero).setScale(2, RoundingMode.HALF_UP);//Solo dos decimales
        double numero2 = numeroNo.doubleValue(); //guarda el valor
        return numero2;//retorna el valor
    }

    public void retrasoGrafica(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción
        }
    }
}
