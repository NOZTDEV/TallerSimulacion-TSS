package com.mycompany.cristallball;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class BeneficioOperativoChart extends JFrame {

    public BeneficioOperativoChart(String title, List<Double> beneficioOperativo, List<Double> gananciaPotencial) {
        super(title);

        // Convertir ganancia potencial a números positivos
        List<Double> gananciaPotencialPositiva = gananciaPotencial.stream()
                .map(Math::abs)  // Aplicar Math.abs para convertir a positivo
                .collect(Collectors.toList());

        // Crear el dataset
        CategoryDataset dataset = createDataset(beneficioOperativo, gananciaPotencialPositiva);

        // Crear la gráfica de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Beneficio Operativo vs Ganancia Potencial", // Título de la gráfica
                "Beneficio Operativo",                       // Etiqueta del eje X
                "Ganancia Potencial",                        // Etiqueta del eje Y
                dataset,                                     // Datos
                PlotOrientation.VERTICAL,                    // Orientación
                true,                                        // Incluir leyenda
                true,                                        // Tooltips
                false                                        // URLs
        );

        // Añadir la gráfica a un panel y mostrarla
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<Double> beneficioOperativo, List<Double> gananciaPotencial) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Verificar que ambas listas tengan el mismo tamaño
        if (beneficioOperativo.size() != gananciaPotencial.size()) {
            throw new IllegalArgumentException("Las listas de beneficio operativo y ganancia potencial deben tener el mismo tamaño.");
        }

        // Añadir los datos al dataset
        for (int i = 0; i < beneficioOperativo.size(); i++) {
            double beneficio = beneficioOperativo.get(i);
            double ganancia = gananciaPotencial.get(i);
            dataset.addValue(Double.valueOf(ganancia), "Ganancia Potencial", Double.valueOf(beneficio)); // Convertir a Double
        }

        return dataset;
    }
}