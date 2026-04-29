
package itson.org.proyectografos;

import java.awt.Color;
import java.util.List;

/**
 *
 * @author oliro
 */
public class Controlador {

    private PanelMapa panelMapa;
    private List<Localidad> localidades;
    private List<Carretera> carreteras;
    
    public Controlador() {
        
    }
    
    public void ejecutarAlgoritmoAnimado() {
        // Creamos un hilo nuevo para no congelar la interfaz
        Thread hiloAlgoritmo = new Thread(() -> {
            try {
                // Ejemplo de lógica del algoritmo
                for (Localidad loc : localidades) {
                    // El algoritmo visita un nodo y le cambia el color a rojo
                    loc.colorActual = Color.RED;

                    // Le decimos al panel que se redibuje con los nuevos colores
                    panelMapa.repaint(); 

                    // Pausamos el algoritmo medio segundo para ver el cambio
                    Thread.sleep(500); 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        hiloAlgoritmo.start();
    }
    
}
