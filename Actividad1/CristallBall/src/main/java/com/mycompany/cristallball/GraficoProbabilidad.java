package com.mycompany.cristallball;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.util.List;

public class GraficoProbabilidad extends JFrame {

    public GraficoProbabilidad(List<Double> probabilidades) {
        // Crear el dataset para el gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < probabilidades.size(); i++) {
            dataset.addValue(probabilidades.get(i), "Probabilidad", "Tarea " + (i + 1));
        }

        // Crear el gráfico de barras
        JFreeChart barChart = ChartFactory.createBarChart(
                "Probabilidades por Tarea",
                "Tareas", // Etiqueta del eje X
                "Probabilidad", // Etiqueta del eje Y
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Crear un panel con el gráfico
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }
}

