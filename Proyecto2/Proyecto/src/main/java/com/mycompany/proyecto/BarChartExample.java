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

public class BarChartExample extends JFrame {
    private double[] lleno; // Almacena los valores de llenado
    private double[] vacio; // Almacena los valores de vacío
    private double[] restante; // Calculado automáticamente

    // Constructor que recibe los datos como parámetros
    public BarChartExample(double llenoAlmacen, double vacioAlmacen,
                           double llenoHorno, double vacioHorno,
                           double llenoBanda1, double vacioBanda1,
                           double llenoAreaCarga, double vacioAreaCarga,
                           double llenoAreaDescarga, double vacioAreaDescarga,
                           double llenoBanda2, double vacioBanda2) {
        setTitle("Gráfico de Barras areas de gran capacidad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialización de arreglos
        lleno = new double[] { llenoAlmacen, llenoHorno, llenoBanda1, llenoAreaCarga, llenoAreaDescarga, llenoBanda2 };
        vacio = new double[] { vacioAlmacen, vacioHorno, vacioBanda1, vacioAreaCarga, vacioAreaDescarga, vacioBanda2 };
        restante = new double[6];

        // Cálculo del restante para cada elemento
        for (int i = 0; i < restante.length; i++) {
            restante[i] = 100.0 - lleno[i] - vacio[i];
        }

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Nombres de las categorías
        String[] categorias = { "Almacén", "Horno", "Banda 1", "Área de Carga", "Área de Descarga", "Banda 2" };

        // Agregar valores al dataset
        for (int i = 0; i < categorias.length; i++) {
            dataset.addValue(lleno[i], "Lleno", categorias[i]);
            dataset.addValue(vacio[i], "Vacío", categorias[i]);
            dataset.addValue(restante[i], "Tercero", categorias[i]);
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Porcentajes de areas de gran capacidad",   // Título del gráfico
                "Áreas",                              // Etiqueta de categoría (eje X)
                "Porcentaje",                         // Etiqueta de valor (eje Y)
                dataset,                              // Conjunto de datos
                PlotOrientation.HORIZONTAL,           // Orientación
                true,                                 // Leyenda
                true,                                 // Tooltips
                false                                 // URLs
        );

        CategoryPlot plot = chart.getCategoryPlot();
        StackedBarRenderer renderer = new StackedBarRenderer();

        // Colores para cada serie
        renderer.setSeriesPaint(0, Color.YELLOW); // Lleno
        renderer.setSeriesPaint(1, Color.BLUE);   // Vacío
        renderer.setSeriesPaint(2, Color.CYAN);   // Tercero (restante)

        plot.setRenderer(renderer);

        return chart;
    }
    /*
    public static void main(String[] args) {
        // Creación de la instancia pasando los valores como parámetros
        BarChartExample example = new BarChartExample(
                30.0, 20.0,  // Almacén
                25.0, 30.0,  // Horno
                40.0, 15.0,  // Banda 1
                35.0, 25.0,  // Área de Carga
                45.0, 30.0,  // Área de Descarga
                20.0, 35.0   // Banda 2
        );
        example.setVisible(true);
    }
    */
}

