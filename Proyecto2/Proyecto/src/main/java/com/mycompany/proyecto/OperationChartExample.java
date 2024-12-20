package com.mycompany.proyecto;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.renderer.category.StackedBarRenderer;

public class OperationChartExample extends JFrame {
    private double[][] datos; // Matriz para almacenar los datos de cada área

    // Constructor que recibe los datos como parámetros
    public OperationChartExample(double area4L, double area4V, double area4B,
                                 double area5L, double area5V, double area5B,
                                 double area6L, double area6V, double area6B,
                                 double area7L, double area7V,
                                 double area8L, double area8V) {
        setTitle("Gráfico de Operación, Inactividad, Bloqueo y Espera");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialización de la matriz de datos
        datos = new double[5][4]; // 5 áreas, 4 estados (operación, inactivo, bloqueado, esperando)

        // Llenar datos
        llenarDatos(area4L, area4V, area4B, "Torneado", 0);
        llenarDatos(area5L, area5V, area5B, "Fresado", 1);
        llenarDatos(area6L, area6V, area6B, "Taladro", 2);
        llenarDatos(area7L, area7V, 0.0, "Rectificado", 3);
        llenarDatos(area8L, area8V, 0.0, "Inspección", 4);

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private void llenarDatos(double operacion, double inactivo, double bloqueado, String nombreArea, int index) {
        datos[index][0] = operacion;                             // Operación
        datos[index][1] = inactivo;                              // Inactivo
        datos[index][2] = bloqueado;                             // Bloqueado
        datos[index][3] = 100.0 - operacion - inactivo - bloqueado; // Esperando
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Nombres de las áreas
        String[] areas = { "Torneado", "Fresado", "Taladro", "Rectificado", "Inspección" };
        String[] estados = { "Operación", "Inactivo", "Bloqueado", "Esperando" };

        // Agregar datos al dataset
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < estados.length; j++) {
                dataset.addValue(datos[i][j], estados[j], areas[i]);
            }
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Porcentajes de areas de capacidad limitada",   // Título del gráfico
                "Áreas",                               // Etiqueta de categoría (eje X)
                "Porcentaje",                          // Etiqueta de valor (eje Y)
                dataset,                               // Conjunto de datos
                PlotOrientation.HORIZONTAL,            // Orientación
                true,                                  // Leyenda
                true,                                  // Tooltips
                false                                  // URLs
        );

        CategoryPlot plot = chart.getCategoryPlot();
        StackedBarRenderer renderer = new StackedBarRenderer();

        // Colores para cada serie
        renderer.setSeriesPaint(0, Color.GREEN);   // Operación
        renderer.setSeriesPaint(1, Color.BLUE);    // Inactivo
        renderer.setSeriesPaint(2, new Color(128, 0, 128)); // Bloqueado (Morado)
        renderer.setSeriesPaint(3, Color.YELLOW);  // Esperando

        plot.setRenderer(renderer);

        return chart;
    }
    /*
    public static void main(String[] args) {
        // Crear instancia del gráfico con datos de ejemplo
        OperationChartExample example = new OperationChartExample(
                60.0, 20.0, 10.0, // Torneado
                50.0, 30.0, 15.0, // Fresado
                45.0, 25.0, 20.0, // Taladro
                70.0, 20.0,       // Rectificado (sin bloqueado)
                80.0, 15.0        // Inspección (sin bloqueado)
        );
        example.setVisible(true);
    }
    */
}


