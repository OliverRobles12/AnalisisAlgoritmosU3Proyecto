
package itson.org.proyectografos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author oliro
 */
public class Carretera {

    public Localidad origen;
    public Localidad destino;
    public Color colorActual;
    public int grosor = 4;
    
    public Carretera(Localidad origen, Localidad destino) {
        this.origen = origen;
        this.destino = destino;
        this.colorActual = new Color(100, 100, 100, 150); // Gris semi-transparente
    }
    
    public void dibujar(Graphics2D g2d) {
        g2d.setColor(colorActual);
        g2d.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(origen.x, origen.y, destino.x, destino.y);
    }
    
}
