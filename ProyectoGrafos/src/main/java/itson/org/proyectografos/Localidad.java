
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
    public double distanciaMinima = Double.MAX_VALUE; // Para Dijkstra
    public Localidad antecesor; // Para reconstruir el camino
    public boolean visitado = false; // Para BFS/DFS
    
    
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
    
    public void reiniciar() {
        this.distanciaMinima = Double.MAX_VALUE;
        this.antecesor = null;
        this.visitado = false;
        this.colorActual = new Color(0, 100, 255, 200);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColorActual() {
        return colorActual;
    }

    public void setColorActual(Color colorActual) {
        this.colorActual = colorActual;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public double getDistanciaMinima() {
        return distanciaMinima;
    }

    public void setDistanciaMinima(double distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }

    public Localidad getAntecesor() {
        return antecesor;
    }

    public void setAntecesor(Localidad antecesor) {
        this.antecesor = antecesor;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    
    
}
