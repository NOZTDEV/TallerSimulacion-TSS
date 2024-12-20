package com.mycompany.proyecto3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultadosTabla extends JFrame {

    public ResultadosTabla(Object[][] data, String[] columnNames) {
        setTitle("Resultados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear la tabla con los datos y los nombres de las columnas
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar la tabla a la ventana
        add(scrollPane);
    }

    /*
    public static void main(String[] args) {
        // Datos simulados para mostrar en la tabla
        Object[][] data = {
                {"Piezas totales producidas", 100},
                {"Piezas en almacén", 20},
                {"Piezas en horno", 15},
                {"Piezas en carga", 10},
                {"Piezas en torneado", 12},
                {"Piezas en fresado", 8},
                {"Piezas en taladro", 9},
                {"Piezas en rectificado", 5},
                {"Piezas en descarga", 10},
                {"Piezas en inspección", 11},
                {"% Utilización horno", "85%"},
                {"% Utilización área de carga", "90%"},
                {"% Utilización torneado", "80%"}
                // Agrega más filas según sea necesario
        };

        // Nombres de las columnas
        String[] columnNames = {"Descripción", "Valor"};

        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            new ResultadosTabla(data, columnNames).setVisible(true);
        });
    }
    */
}
