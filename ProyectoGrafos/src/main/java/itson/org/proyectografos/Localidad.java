
package itson.org.proyectografos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author oliro
 */
public class Localidad {

    public String nombre;
    public int x, y; // Cordenadas en pixeles
    public Color colorActual;
    public int radio = 12;
    
    public Localidad(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.colorActual = new Color(0, 100, 255, 200); // Azul semi-transparente por defecto
    }
    
    public void dibujar(Graphics2D g2d) {
        g2d.setColor(colorActual);
        // Círculo relleno
        g2d.fillOval(x - radio, y - radio, radio * 2, radio * 2);
        
        // Borde del círculo
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x - radio, y - radio, radio * 2, radio * 2);

        // Nombre de la localidad
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(nombre, x + radio + 5, y + 5);
    }
    
}
