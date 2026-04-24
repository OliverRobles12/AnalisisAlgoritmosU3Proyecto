
package itson.org.proyectografos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author oliro
 */
public class PanelMapa extends JPanel {

    private static final Logger LOG = Logger.getLogger(PanelMapa.class.getName());
    
    private final URL urlImagen = getClass().getResource("/mapa_chiapas_contorno_carreteras.png");
    
    private BufferedImage imagenFondo;
    private List<Localidad> localidades;
    private List<Carretera> carreteras;

    public PanelMapa(List<Localidad> localidades, List<Carretera> carreteras) {        
        this.localidades = localidades;
        this.carreteras = carreteras;
        cargarImagen();
    }
    
    private void cargarImagen() {
        try {
            if (urlImagen != null) {
                imagenFondo = ImageIO.read(urlImagen);
            }   else {
                System.err.println("No se encontró la imagen en la ruta especificada.");
                throw new IOException("Ruta imagen nula");
            }
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
            System.err.println("No se pudo cargar la imagen de fondo.");
            imagenFondo = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imagenFondo.createGraphics();
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 800, 600);
            g.dispose();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        // 1. Dejar que Swing maneje la limpieza básica
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        // Activamos suavizado para que se vea bonito
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // PASO A: Dibujar la imagen de fondo primero (Capas inferiores)
        if (imagenFondo != null) {
            // Dibujamos la imagen escalada para que ocupe todo el panel
            g2d.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }

        // PASO B: Dibujar las carreteras por encima del mapa
        for (Carretera c : carreteras) {
            c.dibujar(g2d);
        }

        // PASO C: Dibujar los nodos por encima de las carreteras y del mapa
        for (Localidad loc : localidades) {
            loc.dibujar(g2d);
        }
    }
    
}
