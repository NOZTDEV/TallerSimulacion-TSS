package com.mycompany.proyecto;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
//import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
//import javax.swing.table.DefaultTableModel;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;

public class Ejecucion1 extends Thread {
    private JTextArea textAreaA;
    private JTextArea textAreaB;
    private int duracion;
    private int replicas;
    private int delay;
    private int delayAnimacion;
    //Variables a recibir
    private int Mtorneado;
    private int Mfresado;
    private int Mtaladro;
    private int Mrectificado;
    private int mediaLlegadas;
    private int mediaInspect;
    private SimulacionPanel simPanel;

    //DecimalFormat df = new DecimalFormat("#.##"); //variable para 2 decimales

    public Ejecucion1(JTextArea textAreaA, JTextArea textAreaB, int duracion, int replicas, int delay, int delayAnimacion,
                      int Mtorneado, int Mfresado, int Mtaladro, int Mrectificado, int llegadas, int inspect){ //constructor
        this.textAreaA = textAreaA;
        this.textAreaB = textAreaB;
        this.duracion = duracion;
        this.replicas = replicas;
        this.delay = delay;
        this.delayAnimacion = delayAnimacion;
        //Variables a recibir
        this.Mtorneado = Mtorneado;
        this.Mfresado = Mfresado;
        this.Mtaladro = Mtaladro;
        this.Mrectificado = Mrectificado;
        this.mediaLlegadas = llegadas;
        this.mediaInspect = inspect;
        initGrafico(); //iniciar grafica
    }

    private void initGrafico() {
        JFrame frame = new JFrame("Grafica de calda");
        simPanel = new SimulacionPanel();
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(simPanel);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        //Locaciones
        List<Entidad> almacen = new  ArrayList<>();
        List<Entidad> grua = new ArrayList<>();
        List<Entidad> horno = new  ArrayList<>();
        List<Entidad> banda1 = new ArrayList<>();
        List<Entidad> areaCarga = new ArrayList<>();
        List<Entidad> torneado = new ArrayList<>();
        List<Entidad> robot = new ArrayList<>();
        List<Entidad> fresado = new ArrayList<>();
        List<Entidad> taladro = new ArrayList<>();
        List<Entidad> rectificado = new ArrayList<>();
        List<Entidad> descarga = new ArrayList<>();
        List<Entidad> banda2 = new ArrayList<>();
        List<Entidad> inspeccion = new ArrayList<>();
        //Variables auxiliares
        double tiempoSalida = 0;
        double tiempoSalidaH = 0;
        double tiempoEntradaH = 0;
        double tiempoCarga = 0;
        boolean bloqueado = false;
        boolean bloqueado2 = false;
        boolean bloqueado3 = false;

        //Variables para datos
        int piezasTotales = 0;  //guarda las piezas totales
        List<Double> porcentajeUti = new ArrayList<>();//guaradar porcentajes
        double porUti = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti2 = new ArrayList<>();//guaradar porcentajes
        double porUti2 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti3 = new ArrayList<>();//guaradar porcentajes
        double porUti3 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti4 = new ArrayList<>();//guaradar porcentajes
        double porUti4 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti5 = new ArrayList<>();//guaradar porcentajes
        double porUti5 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti6 = new ArrayList<>();//guaradar porcentajes
        double porUti6 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti7 = new ArrayList<>();//guaradar porcentajes
        double porUti7 = 0;//mostrar porcentaje al final
        List<Double> porcentajeUti8 = new ArrayList<>();//guaradar porcentajes
        double porUti8 = 0;//mostrar porcentaje al final
        int contArea0V = 0;
        int contArea1L = 0, contArea1V = 0;
        int contArea2L = 0, contArea2V = 0;
        int contArea3L = 0, contArea3V= 0;
        //dispocitivos con capacidad limitada
        int contArea4L = 0, contArea4V= 0, contArea4B = 0;
        int contArea5L = 0, contArea5V= 0, contArea5B = 0;
        int contArea6L = 0, contArea6V= 0, contArea6B = 0;
        int contArea7L = 0, contArea7V= 0, contArea7B = 0;
        int contArea8L = 0, contArea8V= 0, contArea8B = 0;
        for(int k = 1; k<= replicas; k++){
            for(int i = 1; i <= duracion; i++){//simular las horas
                arriboAlmacen(almacen);// Arribos por hora a almacen
                simPanel.llegadaPieza("almacen");
                //Simular entre hora
                if (i != duracion) { // Si no es la última hora
                    BigDecimal j = new BigDecimal("0.00"); //valor preciso
                    BigDecimal one = new BigDecimal("1.0"); //valor preciso

                    while (j.compareTo(one) < 0) { //j es menor a one
                        BigDecimal horaExacta = new BigDecimal(i).add(j).setScale(2, RoundingMode.HALF_UP); //i+j para hora
                        double tiempoActual = horaExacta.doubleValue(); //convierte a doble y guardar el valor de hora actual
                        //textAreaA.append("Hora " + tiempoActual+"\n"); //imprime hora
                        //Guardar porcentajes
                        porcentajeUti.add(dosDecimales((((double)horno.size())/10)*100));
                        porcentajeUti2.add(dosDecimales((((double)areaCarga.size())/100)*100));
                        porcentajeUti3.add(dosDecimales((((double)torneado.size())/Mtorneado)*100));
                        porcentajeUti4.add(dosDecimales((((double)fresado.size())/Mfresado)*100));
                        porcentajeUti5.add(dosDecimales((((double)taladro.size())/Mtaladro)*100));
                        porcentajeUti6.add(dosDecimales((((double)rectificado.size())/Mrectificado)*100));
                        porcentajeUti7.add(dosDecimales((((double)descarga.size())/100)*100));
                        porcentajeUti8.add(dosDecimales((((double)inspeccion.size())/1)*100));
                        //maquinas con gran capacidad
                        if(horno.size() == 10){contArea1L++;}
                        if(areaCarga.size() == 100){contArea2L++;}
                        if(descarga.size() == 100){contArea3L++;}
                        if(almacen.isEmpty()){contArea0V++;}
                        if(horno.isEmpty()){contArea1V++;}
                        if(areaCarga.isEmpty()){contArea2V++;}
                        if(descarga.isEmpty()){contArea3V++;}
                        //Maqunas con capacidad limitada
                        if(!torneado.isEmpty()){contArea4L++;}
                        if(!fresado.isEmpty()){contArea5L++;}
                        if(!taladro.isEmpty()){contArea6L++;}
                        if(!rectificado.isEmpty()){contArea7L++;}
                        if(!inspeccion.isEmpty()){contArea8L++;}
                        if(torneado.isEmpty()){contArea4V++;}
                        if(fresado.isEmpty()){contArea5V++;}
                        if(taladro.isEmpty()){contArea6V++;}
                        if(rectificado.isEmpty()){contArea7V++;}
                        if(inspeccion.isEmpty()){contArea8V++;}
                        if(bloqueado == true){contArea4B++;}
                        if(bloqueado2 == true){contArea5B++;}
                        if(bloqueado3 == true){contArea6B++;}
                        //Area Inspeccion
                        if(!inspeccion.isEmpty()){ //inspeccion no esta vacio
                            Entidad pieza = inspeccion.get(0);      //guardamos la pieza
                            if(pieza.getTiempo6() == tiempoActual){ //si es tiempo de salia
                                pieza.setTiempo6(0);                //reseteamos tiempo
                                inspeccion.remove(pieza);           //eliminamos de inspeccion
                                simPanel.llegadaPieza("inspeccionVacio");
                                textAreaA.append("Pieza salio de inspeccion\n");
                                piezasTotales++;                    //aumentamos el contador de piezas totales
                            }
                        }

                        //Area descarga
                        if(!descarga.isEmpty() && inspeccion.isEmpty()){//descarga no esta vacia y hay espacio en inspeccion
                            for(int c = 0; c < descarga.size(); c++){   //Recorremos cada pieza
                                Entidad pieza = descarga.get(c);        //guardamos la pieza
                                descarga.remove(pieza);                 //eliminamos de descarga
                                banda2.add(pieza);                      //añadimos a banda 2
                                simPanel.llegadaPieza("banda2");
                                banda2.remove(pieza);                   //eliminamos de banda 2
                                inspeccion.add(pieza);                  //añadimos a inspeccion
                                if(inspeccion.size()==1){               //si inspeccion esta lleno
                                    simPanel.llegadaPieza("inspeccionLleno");
                                    procesarAreaIns(pieza, tiempoActual);//procesamos en isnpeccion
                                    break;                              //detenemos el ciclo
                                }
                            }
                            if(descarga.isEmpty()){simPanel.llegadaPieza("descargaVacio");}
                        }



                        //Area rectificado
                        if(!rectificado.isEmpty()){ // taladro no esta vacio
                            for(int r = 0; r < rectificado.size(); r++){    //recorremos cada pieza
                                Entidad pieza = rectificado.get(r);         //guardamos la pieza actual
                                if(pieza.getTiempo4() == tiempoActual){     //es tiempo de salida de taladro
                                    pieza.setTiempo4(0);                    //receteamos tiempo salida
                                    textAreaA.append("Pieza salio de rectificado\n");
                                    rectificado.remove(pieza);              //eliminamos de rectificado
                                    robot.add(pieza);                       //añadimos a robot
                                    simPanel.llegadaPieza("rectificadoVacio");
                                    simPanel.moverMaquina("robot", 310, 190);
                                    simPanel.llegadaPieza("robotEnd");
                                    retrasoGrafica(delayAnimacion);
                                    robot.remove(pieza);                    //eliminamos de robot
                                    descarga.add(pieza);                    //añadimos a descarga
                                    simPanel.moverMaquina("robot", 310, 330);
                                    simPanel.llegadaPieza("robotSal");
                                    simPanel.llegadaPieza("descargaLleno");
                                    procesarAreaD(pieza, tiempoActual);     //procesamos en descarga
                                }
                            }
                        }

                        //Area taladro
                        if(!taladro.isEmpty()){ // taladro no esta vacio
                            for(int ta = 0; ta < taladro.size(); ta++){ //recorremos cada pieza
                                Entidad pieza = taladro.get(ta);        //guardamos la pieza actual
                                if(pieza.getTiempo3() == tiempoActual){ //es tiempo de salida de fresado
                                    if(rectificado.size() < Mrectificado){ //hay espacio en rectificado
                                        bloqueado3 = false;             //desbloqueamos
                                        pieza.setTiempo3(0);            //receteamos tiempo salida
                                        textAreaA.append("Pieza salio de taladro\n");
                                        taladro.remove(pieza);          //eliminamos de taladro
                                        robot.add(pieza);               //añadimos a robot
                                        simPanel.llegadaPieza("taladroVacio");
                                        simPanel.moverMaquina("robot", 310, 50);
                                        simPanel.llegadaPieza("robotEnd");
                                        retrasoGrafica(delayAnimacion);
                                        robot.remove(pieza);            //eliminamos de robot
                                        rectificado.add(pieza);         //añadimos a rectificado
                                        simPanel.moverMaquina("robot", 310, 190);
                                        simPanel.llegadaPieza("robotSal");
                                        simPanel.llegadaPieza("rectificadoLleno");
                                        procesarAreaR(pieza, tiempoActual); //procesamos en rectificado
                                    }else{
                                        bloqueado3 = true;              //bloquemos taladro
                                        textAreaA.append("Pieza bloqueada en taladrado\n");
                                        pieza.setTiempo3(dosDecimales(tiempoActual+0.01)); //actualizamos el tiempo
                                    }
                                }
                            }
                        }

                        //Area fresado
                        if(!fresado.isEmpty()){ // fresado no esta vacio
                            for(int f = 0; f < fresado.size(); f++){    //recorremos cada pieza
                                Entidad pieza = fresado.get(f);         //guardamos la pieza actual
                                if(pieza.getTiempo2() == tiempoActual){ //es tiempo de salida de torneado
                                    if(taladro.size() < Mtaladro){      //hay espacio en taladro
                                        bloqueado2 = false;             //desbloqueamos
                                        pieza.setTiempo2(0);            //receteamos tiempo salida
                                        textAreaA.append("Pieza salio de Fresado\n");
                                        fresado.remove(pieza);          //eliminamos de fresado
                                        robot.add(pieza);               //añadimos a robot
                                        simPanel.llegadaPieza("fresadoVacio");
                                        simPanel.moverMaquina("robot", 410, 50);
                                        simPanel.llegadaPieza("robotEnd");
                                        retrasoGrafica(delayAnimacion);
                                        robot.remove(pieza);            //eliminamos de robot
                                        taladro.add(pieza);             //añadimos a taladro
                                        simPanel.moverMaquina("robot", 310, 50);
                                        simPanel.llegadaPieza("robotSal");
                                        simPanel.llegadaPieza("taladroLleno");
                                        procesarAreaTa(pieza, tiempoActual); //procesamos en taladro
                                    }else{
                                        bloqueado2 = true;              //bloquemos fresado
                                        textAreaA.append("Pieza bloqueada en fresado\n");
                                        pieza.setTiempo2(dosDecimales(tiempoActual+0.01)); //actualizamos el tiempo
                                    }
                                }
                            }
                        }


                        //Area torneado
                        if(!torneado.isEmpty()){ // torneado no esta vacio
                            for(int t = 0; t < torneado.size(); t++){   //recorremos cada pieza
                                Entidad pieza = torneado.get(t);        //guardamos la pieza actual
                                if(pieza.getTiempo1() == tiempoActual){ //es tiempo de salida de carga
                                    if(fresado.size() < Mfresado){      //hay espacio en fresado
                                        bloqueado = false;              //desbloqueamos
                                        pieza.setTiempo1(0);            //receteamos tiempo salida
                                        textAreaA.append("Pieza salio de torneado\n");
                                        torneado.remove(pieza);         //eliminamos de torneado
                                        robot.add(pieza);               //añadimos a robot
                                        simPanel.llegadaPieza("torneadoVacio");
                                        simPanel.llegadaPieza("robotEnd");
                                        simPanel.moverMaquina("robot", 410, 190);
                                        retrasoGrafica(delayAnimacion);
                                        robot.remove(pieza);            //eliminamos de robot
                                        fresado.add(pieza);             //añadimos a fresado
                                        simPanel.moverMaquina("robot", 410, 50);
                                        simPanel.llegadaPieza("robotSal");
                                        simPanel.llegadaPieza("fresadoLleno");
                                        procesarAreaF(pieza, tiempoActual); //procesamos en fresado
                                    }else{
                                        bloqueado = true;               //bloquemos torneado
                                        textAreaA.append("Pieza bloqueada en torneado\n");
                                        pieza.setTiempo1(dosDecimales(tiempoActual+0.01)); //actualizamos el tiempo
                                    }
                                }
                            }
                        }

                        //Area carga
                        if(!areaCarga.isEmpty()){ // carga no esta vacio
                            for(int c = 0; c < areaCarga.size(); c++){  //recorremos cada pieza
                                Entidad pieza = areaCarga.get(c);       //guardamos la pieza actual
                                if(torneado.size() < Mtorneado){        //si torneado tiene espeacio
                                    areaCarga.remove(pieza);            //eliminamos de carga
                                    robot.add(pieza);                   //añadimos a robot
                                    simPanel.llegadaPieza("robotEnd");
                                    simPanel.moverMaquina("robot", 410, 330);
                                    retrasoGrafica(delayAnimacion);
                                    robot.remove(pieza);                //removemos de robot
                                    torneado.add(pieza);                //añadimos a torneado
                                    simPanel.moverMaquina("robot", 410, 190);
                                    simPanel.llegadaPieza("robotSal");
                                    simPanel.llegadaPieza("torneadoLleno");
                                    procesarAreaT(pieza, tiempoActual); //procesamos en torneado
                                }else{
                                    break; //si no hay espacio paramos la revision de piezas
                                }
                            }
                        }else{
                            simPanel.llegadaPieza("cargaVacio");
                        }


                        //Salir del horno
                        if(tiempoActual == tiempoSalidaH){      //tiempo de salor del horno?
                            textAreaA.append("Piezas salieron del horno\n");
                            for (int h = 9; h >= 0; h--) {      // Recorre de atrás hacia adelante
                                retrasoGrafica(delayAnimacion);
                                Entidad pieza = horno.get(h);   // obtenemos el elemnto actual
                                horno.remove(pieza);            // Eliminamos del horno
                                grua.add(pieza);                // Añadimos a la grua
                                simPanel.llegadaPieza("gruaEnd");
                                simPanel.moverMaquina("grua", 600, 280);
                                retrasoGrafica(delayAnimacion);
                                grua.remove(pieza);             // Eliminamos de la grua
                                simPanel.llegadaPieza("gruaSal");
                                simPanel.moverMaquina("grua", 720, 350);
                                banda1.add(pieza);              // Añadimos a la banda
                                simPanel.llegadaPieza("banda1");
                                banda1.remove(pieza);           // Eliminamos de la banda
                                areaCarga.add(pieza);           // Añadimos al area de carga
                                simPanel.llegadaPieza("cargaLleno");
                            }
                            simPanel.llegadaPieza("hornoVacio");
                            tiempoEntradaH = dosDecimales(tiempoActual + 0.13); //Damos un nuevo tiempo entrada al Horno
                            textAreaA.append("Piezas en area de carga\n");
                        }

                        //Llenado de horno
                        if(horno.size()<10 && tiempoActual == tiempoEntradaH){ // Horno tiene espacio? y tiempo de entrar a horno
                            for (int a = 0; a < almacen.size(); a++) {  // Recorre desde 0 hasta almacen.size() - 1
                                retrasoGrafica(delayAnimacion);
                                Entidad pieza = almacen.get(a);         // Obtén el elemento actual
                                almacen.remove(pieza);                  // Elimina del almacen
                                grua.add(pieza);                        // Añade a la grua
                                simPanel.llegadaPieza("gruaEnd");
                                simPanel.moverMaquina("grua", 720, 350);
                                retrasoGrafica(delayAnimacion);
                                grua.remove(pieza);                     // Elimina de la frua
                                horno.add(pieza);                       // Añade al horno
                                simPanel.llegadaPieza("gruaSal");
                                simPanel.moverMaquina("grua", 720, 480);
                                if (horno.size() == 10) {               // Verifica si el horno se llenó
                                    simPanel.llegadaPieza("hornoLleno");
                                    break;                              // Detén el bucle si el horno está lleno
                                }
                                a--; // Retrocede el índice para compensar la eliminación del elemento
                            }
                        }

                        //Horno
                        if(horno.size() == 10 && tiempoActual == tiempoEntradaH){   //tiempo es el tiempo de entrada y horno lleno
                            textAreaA.append("El horno se llenó \n");
                            tiempoSalida = procesar(tiempoActual);                  //tiempo de proceso del horno
                            tiempoSalidaH = dosDecimales(tiempoSalida + 0.2);      //tiempo donde el horno estara vacio
                            textAreaA.append("El horno termina en la hora: " + tiempoSalida + "\n");
                            textAreaA.append("El horno esta vacio en la hora: " + tiempoSalidaH + "\n");
                        }

                        //Primer llenado
                        if(horno.size()<10 && tiempoEntradaH == 0){     // Horno tiene espacio?
                            for (int a = 0; a < almacen.size(); a++) {  // Recorre desde 0 hasta almacen.size() - 1
                                retrasoGrafica(delayAnimacion);
                                Entidad pieza = almacen.get(a);         // Obtén el elemento actual
                                almacen.remove(pieza);                  // Elimina del almacen
                                grua.add(pieza);                        // Añade a la grua
                                simPanel.llegadaPieza("gruaEnd");
                                simPanel.moverMaquina("grua", 720, 350);
                                retrasoGrafica(delayAnimacion);
                                grua.remove(pieza);                     // Elimina de la frua
                                horno.add(pieza);                       // Añade al horno
                                simPanel.llegadaPieza("gruaSal");
                                simPanel.moverMaquina("grua", 720, 480);
                                if (horno.size() == 10) {               // Verifica si el horno se llenó
                                    simPanel.llegadaPieza("hornoLleno");
                                    tiempoEntradaH = dosDecimales(tiempoActual + 0.13); //actualizamos tiempo entrada
                                    break;                              // Detén el bucle si el horno está lleno
                                }
                                a--; // Retrocede el índice para compensar la eliminación del elemento
                            }
                        }
                        simPanel.actualizarEstado(almacen, horno, areaCarga, torneado, fresado, taladro, rectificado, piezasTotales);
                        //------------------------------------------------------------------------
                        j = j.add(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP); //j++
                        retrasoGrafica(delay);
                    }
                }
                if(i==duracion){simPanel.actualizarEstado(almacen, horno, areaCarga, torneado, fresado, taladro, rectificado, piezasTotales);}
            }
        }
        //resultados
        porUti = dosDecimales(calcularPorUti(porcentajeUti));
        porUti2 = dosDecimales(calcularPorUti(porcentajeUti2));
        porUti3 = dosDecimales(calcularPorUti(porcentajeUti3));
        porUti4 = dosDecimales(calcularPorUti(porcentajeUti4));
        porUti5 = dosDecimales(calcularPorUti(porcentajeUti5));
        porUti6 = dosDecimales(calcularPorUti(porcentajeUti6));
        porUti7 = dosDecimales(calcularPorUti(porcentajeUti7));
        porUti8 = dosDecimales(calcularPorUti(porcentajeUti8));

        double area0V = dosDecimales(((double)contArea0V/(duracion*100))*100);
        double area1L = dosDecimales(((double)contArea1L/(duracion*100))*100);
        double area1V = dosDecimales(((double)contArea1V/(duracion*100))*100);
        double area2L = dosDecimales(((double)contArea2L/(duracion*100))*100);
        double area2V = dosDecimales(((double)contArea2V/(duracion*100))*100);
        double area3L = dosDecimales(((double)contArea3L/(duracion*100))*100);
        double area3V = dosDecimales(((double)contArea3V/(duracion*100))*100);

        BarChartExample example = new BarChartExample(
                0.0, area0V,  // Almacén
                area1L, area1V,  // Horno
                0.0, 93.0,  // Banda 1
                area2L, area2V,  // Área de Carga
                area3L, area3V,  // Área de Descarga
                0.0, 91.23   // Banda 2
        );
        example.setVisible(true);

        double area4V = dosDecimales(((double)contArea4V/(duracion*100))*100);
        double area4B = dosDecimales(((double)contArea4B/(duracion*100))*100);
        double area4L = dosDecimales((((double)contArea4L/(duracion*100))*100)-area4B);

        double area5V = dosDecimales(((double)contArea5V/(duracion*100))*100);
        double area5B = dosDecimales(((double)contArea5B/(duracion*100))*100);
        double area5L = dosDecimales((((double)contArea5L/(duracion*100))*100)-area5B);

        double area6V = dosDecimales(((double)contArea6V/(duracion*100))*100);
        double area6B = dosDecimales(((double)contArea6B/(duracion*100))*100);
        double area6L = dosDecimales((((double)contArea6L/(duracion*100))*100)-area6B);

        double area7L = dosDecimales(((double)contArea7L/(duracion*100))*100);
        double area7V = dosDecimales(((double)contArea7V/(duracion*100))*100);
        double area8L = dosDecimales(((double)contArea8L/(duracion*100))*100);
        double area8V = dosDecimales(((double)contArea8V/(duracion*100))*100);
        OperationChartExample example2 = new OperationChartExample(
                area4L, area4V, area4B, // Torneado
                area5L, area5V, area5B, // Fresado
                area6L, area6V, area6B, // Taladro
                area7L, area7V,       // Rectificado (sin bloqueado)
                area8L, area8V        // Inspección (sin bloqueado)
        );
        example2.setVisible(true);
        //Tabla
        Object[][] data = {
                {"Piezas totales producidas", piezasTotales},
                {"Piezas en almacén", almacen.size()},
                {"Piezas en horno", horno.size()},
                {"Piezas en carga", areaCarga.size()},
                {"Piezas en torneado", torneado.size()},
                {"Piezas en fresado", fresado.size()},
                {"Piezas en taladro", taladro.size()},
                {"Piezas en rectificado", rectificado.size()},
                {"Piezas en descarga", descarga.size()},
                {"Piezas en inspección", inspeccion.size()},
                {"Porcentajes de utilizacion de las locaciones", ""},
                {"% Utilización horno", porUti + "%"},
                {"% Utilización área de carga", porUti2 + "%"},
                {"% Utilización de torneado", porUti3 + "%"},
                {"% Utilización de fresado", porUti4 + "%"},
                {"% Utilización de taladro", porUti5 + "%"},
                {"% Utilización de rectificado", porUti6 + "%"},
                {"% Utilización de área de descarga", porUti7 + "%"},
                {"% Utilización de inspeccion", porUti8 + "%"},
                {"Porcentajes de areas de gran capacidad", ""},
                {"% Almacen lleno", "0.0%"},
                {"% Almacen vacio", area0V + "%"},
                {"% Almacen ocupado", dosDecimales(100-area0V) + "%"},
                {"% Horno lleno", area1L + "%"},
                {"% Horno vacio", area1V + "%"},
                {"% Horno ocupado", dosDecimales(100-(area1L+area1V)) + "%"},
                {"% Banda 1 lleno", "0.0%"},
                {"% Banda 1 vacio", "93.0%"},
                {"% Banda 1 ocupado", "7.0%"},
                {"% Area de carga lleno", area2L + "%"},
                {"% Area de carga vacio", area2V + "%"},
                {"% Area de carga ocupado", dosDecimales(100-(area2L+area2V)) + "%"},
                {"% Area de descarga lleno", area3L + "%"},
                {"% Area de descarga vacio", area3V + "%"},
                {"% Area de descarga ocupado", dosDecimales(100-(area3L+area3V)) + "%"},
                {"% Banda 2 lleno", "0.0%"},
                {"% Banda 2 vacio", "91.23%"},
                {"% Banda 2 ocupado", "8.77%"},
                {"Porcentajes de areas de capacidad limitada", ""},
                {"% Operacion de torneado", dosDecimales(area4L) + "%"},
                {"% Inactividad de torneado", area4V + "%"},
                {"% Bloqueo de torneado", area4B + "%"},
                {"% espera de torneado", dosDecimales(100-(area4L+area4V+area4B)) + "%"},
                {"% Operacion de fresado", dosDecimales(area5L) + "%"},
                {"% Inactividad de fresado", area5V + "%"},
                {"% Bloqueo de fresado", area5B + "%"},
                {"% espera de fresado", dosDecimales(100-(area5L+area5V+area5B)) + "%"},
                {"% Operacion de taladro", dosDecimales(area6L) + "%"},
                {"% Inactividad de taladro", area6V + "%"},
                {"% Bloqueo de taladro", area6B + "%"},
                {"% espera de taladro", dosDecimales(100-(area6L+area6V+area6B)) + "%"},
                {"% Operacion de rectificado", dosDecimales(area7L) + "%"},
                {"% Inactividad de rectificado", area7V + "%"},
                {"% Bloqueo de rectificado", "0.0%"},
                {"% espera de rectificado", dosDecimales(100-(area7L+area7V+0)) + "%"},
                {"% Operacion de inspeccion", dosDecimales(area8L) + "%"},
                {"% Inactividad de inspeccion", area8V + "%"},
                {"% Bloqueo de inspeccion", "0.0%"},
                {"% espera de inspeccion", dosDecimales(100-(area8L+area8V+0)) + "%"},
        };

        // Mostrar la tabla
        SwingUtilities.invokeLater(() -> {
            new ResultadosTabla(data, new String[]{"Descripción", "Valor"}).setVisible(true);
        });
    }


    class SimulacionPanel extends JPanel {
        //Locaciones
        private Area almacen = new Area("Almacen", 760, 450, 100, 100, Color.GREEN);
        private Area horno = new Area("Horno", 760, 300, 100, 100, Color.GREEN);
        private Area banda1 = new Area("Banda1", 550, 320, 150, 50, Color.GREEN);
        private Area carga = new Area("Area carga", 450, 300, 100, 100, Color.GREEN);
        private Area torneado = new Area("Torneado", 450, 160, 100, 100, Color.GREEN);
        private Area fresado = new Area("Fresado", 450, 20, 100, 100, Color.GREEN);
        private Area taladro = new Area("Taladro", 200, 20, 100, 100, Color.GREEN);
        private Area rectificado = new Area("Rectificado", 200, 160, 100, 100, Color.GREEN);
        private Area descarga = new Area("Area descarga", 200, 300, 100, 100, Color.GREEN);
        private Area banda2 = new Area("Banda2", 50, 320, 150, 50, Color.GREEN);
        private Area inspeccion = new Area("Inspeccion", 50, 400, 100, 100, Color.GREEN);
        //Recursos
        private Area grua = new Area("Grua", 720, 480, 30, 40, Color.GREEN);
        private Area robot = new Area("Robot", 410, 330, 30, 40, Color.GREEN);
        //Contadores
        private Area contAlmacen = new Area("", 750, 410, 100, 20, Color.GRAY);
        private Area contHorno = new Area("", 750, 260, 100, 20, Color.GRAY);
        private Area contCarga = new Area("", 450, 410, 100, 20, Color.GRAY);
        private Area contPieza = new Area("", 50, 510, 100, 20, Color.GRAY);
        private Area contTorneado = new Area("", 550, 210, 60, 20, Color.GRAY);
        private Area contFresado = new Area("", 550, 70, 60, 20, Color.GRAY);
        private Area contTaladro = new Area("", 140, 70, 60, 20, Color.GRAY);
        private Area contRectificado = new Area("", 140, 210, 60, 20, Color.GRAY);
        //Contenedores
        private Area Calmacen = new Area("", 800, 500, 30, 30, new Color(0, 0, 0, 0));
        private Area Chorno = new Area("", 790, 340, 30, 30, new Color(0, 0, 0, 0));
        private Area Cbanda1 = new Area("", 610, 340, 30, 30, new Color(0, 0, 0, 0));
        private Area Ccarga = new Area("", 500, 350, 30, 30, new Color(0, 0, 0, 0));
        private Area Ctorneado = new Area("", 500, 210, 30, 30, new Color(0, 0, 0, 0));
        private Area Cfresado = new Area("", 500, 70, 30, 30, new Color(0, 0, 0, 0));
        private Area Ctaladro = new Area("", 250, 70, 30, 30, new Color(0, 0, 0, 0));
        private Area Crectificado = new Area("", 250, 210, 30, 30, new Color(0, 0, 0, 0));
        private Area Cdescarga = new Area("", 250, 350, 30, 30, new Color(0, 0, 0, 0));
        private Area Cbanda2 = new Area("", 110, 340, 30, 30, new Color(0, 0, 0, 0));
        private Area Cinspeccion = new Area("", 100, 450, 30, 30, new Color(0, 0, 0, 0));

        //variables de imagenes
        private BufferedImage almacenImg;
        private BufferedImage hornoImg;
        private BufferedImage cintaImg;
        private BufferedImage cargaImg;
        private BufferedImage torneadoImg;
        private BufferedImage fresadoImg;
        private BufferedImage taladroImg;
        private BufferedImage rectificadoImg;
        private BufferedImage inspeccionImg;
        private BufferedImage gruaImg;
        private BufferedImage robotImg;
        private BufferedImage piezaImg;
        private BufferedImage piezaGImg;
        private BufferedImage piezaRImg;
        private BufferedImage motorImage;

        public SimulacionPanel() {
            this.setPreferredSize(new Dimension(800, 600));
            this.setBackground(Color.WHITE);
            // Cargar la imagen al inicializar el panel
            try {
                motorImage = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/motor.jpg"));
                almacenImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/almacenA.jpeg"));
                hornoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/hornoA.jpeg"));
                cintaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/images (1).jpeg"));
                cargaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/cargaA.jpeg"));
                torneadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/torneadoA.jpeg"));
                fresadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/fresadoA.jpeg"));
                taladroImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/taladroA.jpeg"));
                rectificadoImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/rectificadoA.jpeg"));
                inspeccionImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/inspeccionA.jpeg"));
                gruaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/grua.png"));
                robotImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/robot.png"));
                piezaImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/piezaA.png"));
                piezaGImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/gruaPieza.png"));
                piezaRImg = ImageIO.read(getClass().getResourceAsStream("/com/mycompany/proyecto/img/robotPieza.png"));


            } catch (IOException e) {
            }
            grua.setBackgroundImage(gruaImg);
            robot.setBackgroundImage(robotImg);
        }


        public void llegadaPieza(String nombre) {
            // Cambia la imagen de fondo del área seleccionada y configura el temporizador para volver al color original
            switch (nombre) {
                case "almacen":
                    Calmacen.setBackgroundImage(piezaImg);
                    repaint();
                    new Timer(100, e -> {
                        Calmacen.setBackgroundImage(null); // Quita la imagen de fondo
                        repaint();
                    }).start();
                    break;
                case "gruaEnd":
                    grua.setBackgroundImage(piezaGImg);
                    repaint();
                    break;
                case "gruaSal":
                    grua.setBackgroundImage(gruaImg);
                    repaint();
                    break;
                case "hornoLleno":
                    Chorno.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "hornoVacio":
                    Chorno.setBackgroundImage(null);
                    repaint();
                    break;
                case "banda1":
                    Cbanda1.setBackgroundImage(piezaImg);
                    repaint();
                    new Timer(300, e -> {
                        Cbanda1.setBackgroundImage(null);
                        repaint();
                    }).start();
                    break;
                case "cargaLleno":
                    Ccarga.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "cargaVacio":
                    Ccarga.setBackgroundImage(null);
                    repaint();
                    break;
                case "robotEnd":
                    robot.setBackgroundImage(piezaRImg);
                    repaint();
                    break;
                case "robotSal":
                    robot.setBackgroundImage(robotImg);
                    repaint();
                    break;
                case "torneadoLleno":
                    Ctorneado.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "torneadoVacio":
                    Ctorneado.setBackgroundImage(null);
                    repaint();
                    break;
                case "fresadoLleno":
                    Cfresado.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "fresadoVacio":
                    Cfresado.setBackgroundImage(null);
                    repaint();
                    break;
                case "taladroLleno":
                    Ctaladro.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "taladroVacio":
                    Ctaladro.setBackgroundImage(null);
                    repaint();
                    break;
                case "rectificadoLleno":
                    Crectificado.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "rectificadoVacio":
                    Crectificado.setBackgroundImage(null);
                    repaint();
                    break;
                case "descargaLleno":
                    Cdescarga.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "descargaVacio":
                    Cdescarga.setBackgroundImage(null);
                    repaint();
                    break;
                case "banda2":
                    Cbanda2.setBackgroundImage(piezaImg);
                    repaint();
                    new Timer(300, e -> {
                        Cbanda2.setBackgroundImage(null);
                        repaint();
                    }).start();
                    break;
                case "inspeccionLleno":
                    Cinspeccion.setBackgroundImage(piezaImg);
                    repaint();
                    break;
                case "inspeccionVacio":
                    Cinspeccion.setBackgroundImage(null);
                    repaint();
                    break;
                default:
                    return;
            }
        }

        public void moverMaquina(String nombre, int nuevaX, int nuevaY) {
            switch (nombre){
                case "grua":
                    grua.setX(nuevaX);
                    grua.setY(nuevaY);
                    repaint(); // Actualiza la gráfica
                    break;
                case "robot":
                    robot.setX(nuevaX);
                    robot.setY(nuevaY);
                    repaint(); // Actualiza la gráfica
                    break;
            }

        }

        public void actualizarEstado(List<Entidad> almacen, List<Entidad> horno,
                                     List<Entidad> carga, List<Entidad> torneado, List<Entidad> fresado, List<Entidad> taladro,
                                     List<Entidad> rectificado, int piezas) {
            contAlmacen.setContador(almacen.size());
            contHorno.setContador(horno.size());
            contCarga.setContador(carga.size());
            contTorneado.setContador(torneado.size());
            contFresado.setContador(fresado.size());
            contTaladro.setContador(taladro.size());
            contRectificado.setContador(rectificado.size());
            contPieza.setContador(piezas);
            repaint(); // Actualiza la gráfica con los nuevos valores
        }

        @Override
        protected void paintComponent(Graphics g) { //Dibujar las areas
            super.paintComponent(g);
            drawArea(g, almacen);
            drawArea(g, horno);
            drawArea(g, banda1);
            drawArea(g, carga);
            drawArea(g, torneado);
            drawArea(g, fresado);
            drawArea(g, taladro);
            drawArea(g, rectificado);
            drawArea(g, descarga);
            drawArea(g, banda2);
            drawArea(g, inspeccion);
            drawArea(g, grua);
            drawArea(g, robot);
            drawArea(g, contAlmacen);
            drawArea(g, contHorno);
            drawArea(g, contCarga);
            drawArea(g, contPieza);
            drawArea(g, contTorneado);
            drawArea(g, contFresado);
            drawArea(g, contTaladro);
            drawArea(g, contRectificado);
            drawArea(g, Calmacen);
            drawArea(g, Chorno);
            drawArea(g, Cbanda1);
            drawArea(g, Ccarga);
            drawArea(g, Ctorneado);
            drawArea(g, Cfresado);
            drawArea(g, Ctaladro);
            drawArea(g, Crectificado);
            drawArea(g, Cdescarga);
            drawArea(g, Cbanda2);
            drawArea(g, Cinspeccion);
        }

        private void drawArea(Graphics g, Area area) {// metodo para dibujar las areas
            //Damos imagend de fondo
            almacen.setBackgroundImage(almacenImg);
            horno.setBackgroundImage(hornoImg);
            banda1.setBackgroundImage(cintaImg);
            carga.setBackgroundImage(cargaImg);
            torneado.setBackgroundImage(torneadoImg);
            fresado.setBackgroundImage(fresadoImg);
            taladro.setBackgroundImage(taladroImg);
            rectificado.setBackgroundImage(rectificadoImg);
            descarga.setBackgroundImage(cargaImg);
            banda2.setBackgroundImage(cintaImg);
            inspeccion.setBackgroundImage(inspeccionImg);
            if (area.getBackgroundImage() != null) {
                g.drawImage(area.getBackgroundImage(), area.getX(), area.getY(), area.getWidth(), area.getHeight(), null);
            } else {
                g.setColor(area.getColor());
                g.fillRect(area.getX(), area.getY(), area.getWidth(), area.getHeight());
            }

            g.setColor(Color.BLACK);
            g.drawString(area.getName(), area.getX() + 5, area.getY() - 5);

            if (area == contAlmacen) {
                g.drawString("Piezas: " + area.getContador(), area.getX() + 5, area.getY() + 15);
            }
            if (area == contHorno) {
                g.drawString("Piezas horno: " + area.getContador(), area.getX() + 5, area.getY() + 15);
            }
            if (area == contCarga) {
                g.drawString("Piezas carga: " + area.getContador(), area.getX() + 5, area.getY() + 15);
            }
            if (area == contPieza) {
                g.drawString("Total: " + area.getContador(), area.getX() + 5, area.getY() + 15);
            }
            if (area == contTorneado || area == contFresado || area == contTaladro || area == contRectificado) {
                g.drawString("Piezas: " + area.getContador(), area.getX() + 5, area.getY() + 15);
            }
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

    public void arriboAlmacen (List<Entidad> locacion){
        PoissonDistribution llegada = new PoissonDistribution(mediaLlegadas);
        double llegadas = llegada.sample();
        textAreaA.append("Llegaron " + llegadas +" piezas\n"); //imprime hora
        for(int i = 1; i <= llegadas; i++){//creamos la cantidad de motores requerida
            Entidad entidad = new Entidad("Pieza", i, true);//con sus datos
            locacion.add(entidad);//y los ponemos en estiraje
        }
    }

    public double procesar(Double tiempoActual){
        return dosDecimales(tiempoActual + 1.67); // damos el tiempo de procesamiento del horno en horas
    }

    public void procesarAreaC(Entidad pieza, double tiempoActual){
        textAreaA.append("La pieza tarda unos 0.5 minutos \n");
        double tiempoSalida = dosDecimales(tiempoActual + 0.01);// procesamos en tiempo de horas
        pieza.setTiempo1(tiempoSalida);                         // le asignamos el tiempo a la pieza
        textAreaA.append("La pieza sale de carga en la hora: "+tiempoSalida+"\n");
    }

    public void procesarAreaT(Entidad pieza, double tiempoActual){
        textAreaA.append("La pieza tarda unos 5.2 minutos \n");
        double tiempoSalida = dosDecimales(tiempoActual + 0.09);// procesamos en tiempo de horas
        pieza.setTiempo1(tiempoSalida);                         // le asignamos el tiempo a la pieza
        textAreaA.append("La pieza sale de torneado en la hora: "+tiempoSalida+"\n");
    }

    public void procesarAreaF(Entidad pieza, double tiempoActual){
        textAreaA.append("La pieza tarda unos 9.17 minutos \n");
        double tiempoSalida = dosDecimales(tiempoActual + 0.15);// procesamos en tiempo de horas
        pieza.setTiempo2(tiempoSalida);                         // le asignamos el tiempo a la pieza
        textAreaA.append("La pieza sale de fresado en la hora: "+tiempoSalida+"\n");
    }

    public void procesarAreaTa(Entidad pieza, double tiempoActual){
        textAreaA.append("La pieza tarda unos 1.6 minutos \n");
        double tiempoSalida = dosDecimales(tiempoActual + 0.03);// procesamos en tiempo de horas
        pieza.setTiempo3(tiempoSalida);                         // le asignamos el tiempo a la pieza
        textAreaA.append("La pieza sale de taladro en la hora: "+tiempoSalida+"\n");
    }

    public void procesarAreaR(Entidad pieza, double tiempoActual){
        textAreaA.append("La pieza tarda unos 2.85 minutos \n");
        double tiempoSalida = dosDecimales(tiempoActual + 0.05);// procesamos en tiempo de horas
        pieza.setTiempo4(tiempoSalida);                         // le asignamos el tiempo a la pieza
        textAreaA.append("La pieza sale de rectificado en la hora: "+tiempoSalida+"\n");
    }

    public void procesarAreaD(Entidad pieza, double tiempoActual){
        textAreaA.append("La pieza tarda unos 0.5 minutos \n");
        double tiempoSalida = dosDecimales(tiempoActual + 0.01);// procesamos en tiempo de horas
        pieza.setTiempo5(tiempoSalida);                         // le asignamos el tiempo a la pieza
        textAreaA.append("La pieza sale de descarga en la hora: "+tiempoSalida+"\n");
    }

    public void procesarAreaIns(Entidad pieza, double tiempoActual){
        textAreaA.append("La pieza tarda un tiempo exponencial \n");
        ExponentialDistribution inspec = new ExponentialDistribution(0.05);//Llamamos a la distribucion exponencial
        double tiempoSalida = dosDecimales(inspec.sample()+0.01);// procesamos en tiempo de horas
        tiempoSalida = dosDecimales(tiempoSalida + tiempoActual);// leguardamos el valor de salida
        pieza.setTiempo6(tiempoSalida);                         // le damos el tiempo a la pieza
        textAreaA.append("La pieza sale de inspeccion en la hora: "+tiempoSalida+"\n");
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
