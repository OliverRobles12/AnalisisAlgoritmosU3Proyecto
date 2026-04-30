
package itson.org.proyectografos.controlador;

import itson.org.proyectografos.Algoritmos;
import itson.org.proyectografos.Grafo;
import itson.org.proyectografos.Localidad;
import itson.org.proyectografos.PanelMapa;

/**
 *
 * @author oliro
 */
public class Controlador {

    private PanelMapa panelMapa;
    private Grafo grafo;
    
    public Controlador(Grafo grafo, PanelMapa panelMapa) {
        this.grafo = grafo;
        this.panelMapa = panelMapa;
    }
    
    public void animarBFS(Localidad inicio) {
        // Creamos un hilo 
        new Thread(() -> {
            // Llamada al método estático de la nueva clase
            Algoritmos.recorridoBFS(grafo, inicio, () -> {
                panelMapa.repaint(); 
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }              
            });
        }).start();
    }  
    
    /**
     * Crea un hilo secundario para ejecutar el algoritmo DFS de forma animada.
     * @param inicio Localidad desde donde empieza la exploración profunda.
     */
    public void animarDFS(Localidad inicio) {
        // Usamos un hilo para que la animación fluya mientras el usuario ve el mapa
        new Thread(() -> {
            // Ejecutamos la lógica del DFS
            Algoritmos.recorridoDFS(grafo, inicio, () -> {
                panelMapa.repaint(); // Repintar
                try {
                    Thread.sleep(600); // 600 milisegundos de pausa entre cada paso
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            });
        }).start();
    }
    
    
    public void animarKruskal() {
        new Thread(() -> {
            // Llamamos al método desde Algoritmos y le pasamos el grafo
            Algoritmos.kruskalMST(grafo, () -> {
                panelMapa.repaint();
                try {
                    Thread.sleep(600); // 600ms de pausa para ver crecer el árbol
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }
    
    
}
