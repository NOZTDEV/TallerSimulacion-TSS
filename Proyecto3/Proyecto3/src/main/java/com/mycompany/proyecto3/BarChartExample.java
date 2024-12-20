package com.mycompany.proyecto3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.renderer.category.StackedBarRenderer;

public class BarChartExample extends JFrame {
    private double[] lleno; // Almacena los valores de llenado
    private double[] vacio; // Almacena los valores de vacío
    private double[] restante; // Calculado automáticamente

    // Constructor que recibe los datos como parámetros
    public BarChartExample(double... valores) {
        setTitle("Gráfico de Barras Áreas de Gran Capacidad");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int areas = valores.length / 2; // Total de áreas basado en pares de lleno/vacío
        lleno = new double[areas];
        vacio = new double[areas];
        restante = new double[areas];

        // Rellenar los arreglos lleno y vacío con los parámetros recibidos
        for (int i = 0; i < areas; i++) {
            lleno[i] = valores[i * 2];
            vacio[i] = valores[i * 2 + 1];
            restante[i] = 100.0 - lleno[i] - vacio[i];
        }

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 800));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Nombres de las áreas
        String[] categorias = {
            "Silo Cebada", "Malteado", "Secado", "Molienda", "Macerado", 
            "Filtrado", "Cocción", "Silo Lúpulo", "Enfriado", "Silo Levadura",
            "Fermentado", "Maduración", "Inspección", "Embotellado", "Etiquetado", 
            "Empacado", "Almacén Cajas", "Almacén", "Mercado"
        };

        // Agregar valores al dataset
        for (int i = 0; i < categorias.length; i++) {
            dataset.addValue(vacio[i], "Vacío", categorias[i]); // Azul
            dataset.addValue(restante[i], "Tercero", categorias[i]); // Celeste
            dataset.addValue(lleno[i], "Lleno", categorias[i]); // Amarillo
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Porcentajes de Áreas de Gran Capacidad",   // Título del gráfico
                "Áreas",                                   // Etiqueta de categoría (eje X)
                "Porcentaje",                              // Etiqueta de valor (eje Y)
                dataset,                                   // Conjunto de datos
                PlotOrientation.HORIZONTAL,                // Orientación
                true,                                      // Leyenda
                true,                                      // Tooltips
                false                                      // URLs
        );

        CategoryPlot plot = chart.getCategoryPlot();
        StackedBarRenderer renderer = new StackedBarRenderer();

        // Colores para cada serie en el orden deseado
        renderer.setSeriesPaint(0, Color.BLUE);   // Vacío
        renderer.setSeriesPaint(1, Color.CYAN);  // Tercero (restante)
        renderer.setSeriesPaint(2, Color.YELLOW); // Lleno

        // Asignar generadores de tooltips para cada serie
        renderer.setSeriesToolTipGenerator(0, new StandardCategoryToolTipGenerator(
                "{0}: {1} = {2}%", new java.text.DecimalFormat("0.00")));
        renderer.setSeriesToolTipGenerator(1, new StandardCategoryToolTipGenerator(
                "{0}: {1} = {2}%", new java.text.DecimalFormat("0.00")));
        renderer.setSeriesToolTipGenerator(2, new StandardCategoryToolTipGenerator(
                "{0}: {1} = {2}%", new java.text.DecimalFormat("0.00")));

        plot.setRenderer(renderer);

        return chart;
    }
}
