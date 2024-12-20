package com.mycompany.cristallball;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraficoMasTardio extends JFrame {

    public GraficoMasTardio(List<Integer> inicioMasTardio) {
        // Contar la frecuencia de cada valor
        Map<Integer, Integer> frecuenciaMap = new HashMap<>();
        for (int value : inicioMasTardio) {
            frecuenciaMap.put(value, frecuenciaMap.getOrDefault(value, 0) + 1);
        }

        // Crear el dataset para el gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar valores y su frecuencia al dataset
        for (Map.Entry<Integer, Integer> entry : frecuenciaMap.entrySet()) {
            int value = entry.getKey();
            int frecuencia = entry.getValue();
            dataset.addValue(frecuencia, "Frecuencia", String.valueOf(value));
        }

        // Crear el gráfico de barras
        JFreeChart barChart = ChartFactory.createBarChart(
                "Frecuencia de Valores",
                "Valores", // Etiqueta del eje X
                "Frecuencia", // Etiqueta del eje Y
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                false, true, false);

        // Cambiar el color de las barras según su valor
        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Iterar sobre las categorías y establecer el color según el valor
        int index = 0;
        for (Integer key : frecuenciaMap.keySet()) {
            // Establecer el color basado en el valor en el eje X
            if (key < 290) {
                renderer.setSeriesPaint(0, Color.BLUE); // Barra azul si el valor es menor a 290
            } else {
                renderer.setSeriesPaint(0, Color.RED); // Barra roja si el valor es 290 o mayor
            }
            index++;
        }

        // Crear un panel con el gráfico
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }
}