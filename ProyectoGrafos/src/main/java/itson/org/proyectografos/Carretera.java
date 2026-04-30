
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
    public double peso;
    public int grosor = 4;
    
    public Carretera(Localidad origen, Localidad destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.colorActual = new Color(100, 100, 100, 150); // Gris semi-transparente
    }
    
    public void dibujar(Graphics2D g2d) {
        g2d.setColor(colorActual);
        g2d.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(origen.x, origen.y, destino.x, destino.y);
        
        int xMedio = (origen.x + destino.x) / 2;
        int yMedio = (origen.y + destino.y) / 2;

        g2d.setColor(Color.BLACK); 
        String valorPeso = String.valueOf((int)peso);

        g2d.drawString(valorPeso, xMedio, yMedio);

    }

    public Localidad getOrigen() {
        return origen;
    }

    public void setOrigen(Localidad origen) {
        this.origen = origen;
    }

    public Localidad getDestino() {
        return destino;
    }

    public void setDestino(Localidad destino) {
        this.destino = destino;
    }

    public Color getColorActual() {
        return colorActual;
    }

    public void setColorActual(Color colorActual) {
        this.colorActual = colorActual;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getGrosor() {
        return grosor;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }
    
    
    
}
