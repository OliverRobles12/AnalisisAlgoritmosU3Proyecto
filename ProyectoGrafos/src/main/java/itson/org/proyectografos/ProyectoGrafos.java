
package itson.org.proyectografos;

import itson.org.proyectografos.presentacion.VentanaPrincipal;

/**
 *
 * @author oliro
 */
public class ProyectoGrafos {

    public static void main(String[] args) {
        
        Controlador ctrl = new Controlador();
        VentanaPrincipal vista = new VentanaPrincipal(ctrl);
        vista.setVisible(true);
        
    }
}
