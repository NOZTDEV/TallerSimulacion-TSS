package com.mycompany.cristallball;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.List;

public class Grafico extends JFrame {
    public Grafico(List<Double> costos, double media) {
        // Configuración del título y tamaño de la ventana
        super("Gráfico de Costos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear el dataset
        CategoryDataset dataset = crearDataset(costos, media);
        
        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "Costos y Media", // Título
                "Ítems",          // Eje X
                "Costos",         // Eje Y
                dataset           // Datos
        );
        
        // Agregar el gráfico a un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset crearDataset(List<Double> costos, double media) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Agregar los costos al dataset
        for (int i = 0; i < costos.size(); i++) {
            dataset.addValue(costos.get(i), "Costos", "Item " + (i + 1));
        }

        // Agregar la media al dataset
        dataset.addValue(media, "Media", "Media");

        return dataset;
    }
}