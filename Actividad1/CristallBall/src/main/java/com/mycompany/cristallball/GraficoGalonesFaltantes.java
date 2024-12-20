package com.mycompany.cristallball;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraficoGalonesFaltantes extends JFrame {

    public GraficoGalonesFaltantes(List<Double> galones) {
        // Configuración del título y tamaño de la ventana
        super("Gráfico de Galones Faltantes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Collections.sort(galones);
        // Crear el dataset
        CategoryDataset dataset = crearDataset(galones);
        
        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "Galones Faltantes", // Título
                "Galones",           // Eje X
                "Probabilidad",      // Eje Y
                dataset              // Datos
        );

        // Personalizar colores de las barras
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                String category = dataset.getColumnKey(column).toString();
                
                // Solo convertir a número si es un galón
                try {
                    double galonValue = Double.parseDouble(category);
                    return galonValue < 0 ? Color.RED : Color.BLUE; // Barras rojas para galones negativos, azules para positivos
                } catch (NumberFormatException e) {
                    return Color.GRAY; // Usar un color neutral para categorías no numéricas
                }
            }
        };
        chart.getCategoryPlot().setRenderer(renderer);

        // Agregar el gráfico a un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset crearDataset(List<Double> galones) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Contar la frecuencia de cada galón
        int totalCount = galones.size();
        for (Double galon : galones) {
            dataset.addValue(calcularProbabilidad(galones, galon, totalCount), "Probabilidad", galon.toString());
        }

        // Calcular probabilidad de galones negativos
        double probabilidadNegativos = calcularProbabilidadNegativos(galones, totalCount);
        dataset.addValue(probabilidadNegativos, "Probabilidad Negativos", "Probabilidad Negativos");

        return dataset;
    }

    private double calcularProbabilidad(List<Double> galones, double galon, int totalCount) {
        int count = Collections.frequency(galones, galon); // Contar cuántas veces aparece el galón
        return (double) count / totalCount; // Calcular probabilidad
    }

    private double calcularProbabilidadNegativos(List<Double> galones, int totalCount) {
        long countNegativos = galones.stream().filter(g -> g < 0).count(); // Contar galones negativos
        return (double) countNegativos / totalCount; // Calcular probabilidad de negativos
    }
/*
    public static void main(String[] args) {
        // Lista de galones faltantes (ejemplo)
        List<Double> galones = new ArrayList<>(List.of(10.0, -5.0, 5.0, -3.0, -1.0, 2.0, -8.0, 7.0, 4.0, -6.0));

        // Ordenar galones de menor a mayor
        Collections.sort(galones);

        // Crear y mostrar el gráfico
        SwingUtilities.invokeLater(() -> {
            GraficoGalonesFaltantes grafico = new GraficoGalonesFaltantes(galones);
            grafico.setVisible(true);
        });
    }
*/
}




