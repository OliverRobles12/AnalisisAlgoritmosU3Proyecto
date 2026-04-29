
package itson.org.proyectografos;

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
        // Creamos el hilo para no trabar la ventana
        new Thread(() -> {
            
            // Llamamos al metodo de recorridoBFS en el grafo.
            grafo.recorridoBFS(inicio, () -> {
                
                panelMapa.repaint(); // Repintamos la pantalla
                try {
                    Thread.sleep(600); // Pausamos 600 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }              
            });
            
        }).start();
    }
    
}
