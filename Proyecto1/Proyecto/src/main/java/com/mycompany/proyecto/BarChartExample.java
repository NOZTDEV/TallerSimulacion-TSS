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
    private double llenoEstirocortadoras, vacioEstirocortadoras;
    private double llenoEspera, vacioEspera;
    private double llenoReparacion, vacioReparacion;
    private double llenoReserva, vacioReserva;

    // Constructor que recibe los datos como parámetros
    public BarChartExample(double llenoEstirocortadoras, double vacioEstirocortadoras,
                           double llenoEspera, double vacioEspera,
                           double llenoReparacion, double vacioReparacion,
                           double llenoReserva, double vacioReserva) {
        setTitle("Gráfico de Barras Apiladas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Asignación de los datos recibidos
        this.llenoEstirocortadoras = llenoEstirocortadoras;
        this.vacioEstirocortadoras = vacioEstirocortadoras;
        this.llenoEspera = llenoEspera;
        this.vacioEspera = vacioEspera;
        this.llenoReparacion = llenoReparacion;
        this.vacioReparacion = vacioReparacion;
        this.llenoReserva = llenoReserva;
        this.vacioReserva = vacioReserva;

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Calculo de los valores restantes
        double restanteEstirocortadoras = 100.0 - llenoEstirocortadoras - vacioEstirocortadoras;
        double restanteEspera = 100.0 - llenoEspera - vacioEspera;
        double restanteReparacion = 100.0 - llenoReparacion - vacioReparacion;
        double restanteReserva = 100.0 - llenoReserva - vacioReserva;

        // Agregar valores al dataset
        dataset.addValue(llenoEstirocortadoras, "Lleno", "Estirocortadoras");
        dataset.addValue(vacioEstirocortadoras, "Vacío", "Estirocortadoras");
        dataset.addValue(restanteEstirocortadoras, "Tercero", "Estirocortadoras");

        dataset.addValue(llenoEspera, "Lleno", "Espera");
        dataset.addValue(vacioEspera, "Vacío", "Espera");
        dataset.addValue(restanteEspera, "Tercero", "Espera");

        dataset.addValue(llenoReparacion, "Lleno", "Reparación");
        dataset.addValue(vacioReparacion, "Vacío", "Reparación");
        dataset.addValue(restanteReparacion, "Tercero", "Reparación");

        dataset.addValue(llenoReserva, "Lleno", "Reserva");
        dataset.addValue(vacioReserva, "Vacío", "Reserva");
        dataset.addValue(restanteReserva, "Tercero", "Reserva");

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Porcentaje de Ocupación de Áreas",   // Título del gráfico
                "Áreas",                              // Etiqueta de categoría (eje X)
                "Porcentaje",                         // Etiqueta de valor (eje Y)
                dataset,                              // Conjunto de datos
                PlotOrientation.HORIZONTAL,           // Orientación (cambiado a HORIZONTAL)
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
        BarChartExample example = new BarChartExample(10.0, 30.0, 20.0, 25.0, 15.0, 35.0, 12.0, 28.0);
        example.setVisible(true);
    }
    */
}
