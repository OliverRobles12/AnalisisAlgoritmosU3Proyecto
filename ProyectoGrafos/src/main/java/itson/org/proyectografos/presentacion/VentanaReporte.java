package itson.org.proyectografos.presentacion;

import javax.swing.*;
import java.awt.*;

public class VentanaReporte extends JFrame {

    public VentanaReporte(String titulo, String contenido) {
        // Configuramos la ventana
        setTitle(titulo);
        setSize(450, 600); // Un buen tamaño para leer listas
        setLocationRelativeTo(null); // Centrar en la pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana, no toda la app

        JTextArea txtReporte = new JTextArea(contenido);
        txtReporte.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtReporte.setEditable(false);
        txtReporte.setMargin(new Insets(15, 15, 15, 15)); 

        JScrollPane scrollPane = new JScrollPane(txtReporte);
        add(scrollPane, BorderLayout.CENTER);

        // Botón bonito para cerrar
        JButton btnCerrar = new JButton("Cerrar Reporte");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBackground(Color.decode("#c9a163")); // Tu color dorado
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose()); // Cierra la ventana al hacer clic

        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBoton.add(btnCerrar);
        add(panelBoton, BorderLayout.SOUTH);
    }
}