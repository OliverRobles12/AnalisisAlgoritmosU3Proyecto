
package itson.org.proyectografos.controlador;

import itson.org.proyectografos.Algoritmos;
import itson.org.proyectografos.Carretera;
import itson.org.proyectografos.Grafo;
import itson.org.proyectografos.Localidad;
import itson.org.proyectografos.PanelMapa;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oliro
 */
public class Controlador {

    public final Integer TIEMPO_ESPERA = 600;
    
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
            List<Localidad> recorrido = Algoritmos.recorridoBFS(grafo, inicio, () -> {
                panelMapa.repaint(); 
                try {
                    Thread.sleep(TIEMPO_ESPERA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }              
            });
            
            StringBuilder reporte = new StringBuilder();
            reporte.append("==================================================\n");
            reporte.append("            RECORRIDO EN ANCHURA (BFS)            \n");
            reporte.append("==================================================\n");
            reporte.append("Punto de partida: ").append(inicio.getNombre()).append("\n");
            reporte.append("--------------------------------------------------\n");
            reporte.append(String.format("%-10s %-30s\n", "PASO", "CIUDAD VISITADA"));
            reporte.append("--------------------------------------------------\n");
            
            for (int i = 0; i < recorrido.size(); i++) {
                reporte.append(String.format("%-10d %-30s\n", (i + 1), recorrido.get(i).getNombre()));
            }
            
            reporte.append("==================================================\n");
            reporte.append("Nodos totales visitados: ").append(recorrido.size()).append("\n");

            javax.swing.SwingUtilities.invokeLater(() -> {
                itson.org.proyectografos.presentacion.VentanaReporte ventana = 
                    new itson.org.proyectografos.presentacion.VentanaReporte("Reporte BFS", reporte.toString());
                ventana.setVisible(true);
            });
        }).start();
    }  
    
    public void animarKruskal() {
        new Thread(() -> {
            List<Carretera> arbol = Algoritmos.kruskalMST(grafo, () -> {
                panelMapa.repaint();
                try { Thread.sleep(TIEMPO_ESPERA); } catch (InterruptedException e) { e.printStackTrace(); }
            });

            // --- ARMANDO EL FORMATO DEL REPORTE ---
            StringBuilder reporte = new StringBuilder();
            reporte.append("==================================================\n");
            reporte.append("       ÁRBOL DE EXPANSIÓN MÍNIMA (KRUSKAL)        \n");
            reporte.append("==================================================\n\n");
            
            // Encabezados de tabla alineados
            // %-20s significa "String alineado a la izquierda con 20 espacios"
            reporte.append(String.format("%-20s %-20s %-10s\n", "ORIGEN", "DESTINO", "DISTANCIA"));
            reporte.append("--------------------------------------------------\n");
            
            double pesoTotal = 0;
            for (Carretera c : arbol) {
                String origen = c.getOrigen().getNombre();
                String destino = c.getDestino().getNombre();
                // Si el nombre es muy largo, lo cortamos a 18 caracteres para no romper la tabla
                if(origen.length() > 18) origen = origen.substring(0, 15) + "...";
                if(destino.length() > 18) destino = destino.substring(0, 15) + "...";
                
                reporte.append(String.format("%-20s %-20s %-10.2f\n", origen, destino, c.peso));
                pesoTotal += c.peso;
            }
            
            reporte.append("--------------------------------------------------\n");
            reporte.append(String.format("%-41s %-10.2f\n", "DISTANCIA TOTAL DEL RECORRIDO ÓPTIMO:", pesoTotal));
            reporte.append("==================================================\n");

            // --- MOSTRAR LA NUEVA VENTANA ---
            javax.swing.SwingUtilities.invokeLater(() -> {
                itson.org.proyectografos.presentacion.VentanaReporte ventana = 
                    new itson.org.proyectografos.presentacion.VentanaReporte("Reporte de Kruskal", reporte.toString());
                ventana.setVisible(true);
            });
            
        }).start();
    }

    public void animarDFS(Localidad inicio) {
        new Thread(() -> {
            List<Localidad> recorrido = Algoritmos.recorridoDFS(grafo, inicio, () -> {
                panelMapa.repaint(); 
                try { Thread.sleep(TIEMPO_ESPERA); } catch (InterruptedException e) { e.printStackTrace(); }
            });

            // --- ARMANDO EL FORMATO DEL REPORTE ---
            StringBuilder reporte = new StringBuilder();
            reporte.append("==================================================\n");
            reporte.append("           RECORRIDO EN PROFUNDIDAD (DFS)         \n");
            reporte.append("==================================================\n");
            reporte.append("Punto de partida: ").append(inicio.getNombre()).append("\n");
            reporte.append("--------------------------------------------------\n");
            reporte.append(String.format("%-10s %-30s\n", "PASO", "CIUDAD VISITADA"));
            reporte.append("--------------------------------------------------\n");
            
            for (int i = 0; i < recorrido.size(); i++) {
                reporte.append(String.format("%-10d %-30s\n", (i + 1), recorrido.get(i).getNombre()));
            }
            reporte.append("==================================================\n");
            reporte.append("Nodos totales visitados: ").append(recorrido.size()).append("\n");

            // --- MOSTRAR LA NUEVA VENTANA ---
            javax.swing.SwingUtilities.invokeLater(() -> {
                itson.org.proyectografos.presentacion.VentanaReporte ventana = 
                    new itson.org.proyectografos.presentacion.VentanaReporte("Reporte DFS", reporte.toString());
                ventana.setVisible(true);
            });

        }).start();
    }
    
    /**
     * Crea un hilo secundario para ejecutar el algoritmo de Dijkstra.
     * @param inicio Localidad desde donde se calcularán las rutas mas cortas.
     */
    public void animarDijkstra(Localidad inicio) {
        new Thread(() -> {
            Map<Localidad, Double> distancias =  Algoritmos.dijkstra(grafo, inicio, () -> {
                panelMapa.repaint();
                try {
                    Thread.sleep(TIEMPO_ESPERA); // Pausa para ver la exploración
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // --- ARMANDO EL FORMATO DEL REPORTE ---
            StringBuilder reporte = new StringBuilder();
            reporte.append("==================================================\n");
            reporte.append("      RUTA MÁS CORTA (ALGORITMO DE DIJKSTRA)      \n");
            reporte.append("==================================================\n");
            reporte.append("Punto de partida: ").append(inicio.getNombre()).append("\n");
            reporte.append("--------------------------------------------------\n");
            reporte.append(String.format("%-30s %-15s\n", "DESTINO", "DISTANCIA MÍNIMA"));
            reporte.append("--------------------------------------------------\n");
            
            for (Map.Entry<Localidad, Double> entrada : distancias.entrySet()) {
                String destino = entrada.getKey().getNombre();
                if(destino.length() > 28) destino = destino.substring(0, 25) + "..."; // Evitar que se rompa la tabla
                Double distanciaMinima = entrada.getValue();
                
                reporte.append(String.format("%-30s %-15.2f\n", destino, distanciaMinima));
            }

            reporte.append("==================================================\n");

            // --- MOSTRAR LA NUEVA VENTANA ---
            javax.swing.SwingUtilities.invokeLater(() -> {
                itson.org.proyectografos.presentacion.VentanaReporte ventana = 
                    new itson.org.proyectografos.presentacion.VentanaReporte("Reporte Dijkstra", reporte.toString());
                ventana.setVisible(true);
            });
        }).start();
    }
    
}
