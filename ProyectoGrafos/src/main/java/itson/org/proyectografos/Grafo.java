/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.proyectografos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Clase que representa un grafo, en este caso, compuesto por localidades y
 * carreteras.
 * En este grafo, las localidades funcionan como nodos y las carreteras como
 * aristas. Debido a que las carreteras se consideran de doble sentido, la clase
 * permite obtener vecinos revisando tanto el origen como el destino de cada
 * carretera.
 *
 * @author regina
 */
public class Grafo {
    private List<Localidad> nodos;
    private List<Carretera> aristas;

     /**
     * Constructor que inicializa un grafo con una lista de nodos y una lista de
     * aristas.
     *
     * @param nodos lista de localidades que forman parte del grafo.
     * @param aristas lista de carreteras que conectan las localidades del grafo.
     */
    public Grafo(List<Localidad> nodos, List<Carretera> aristas) {
        this.nodos = nodos;
        this.aristas = aristas;
    }
    
     /**
     * Obtiene las localidades vecinas de una localidad específica.
     * Como las carreteras se manejan como conexiones de doble sentido, este
     * método verifica si la localidad recibida se encuentra como origen o como
     * destino de cada carretera. Si coincide con el origen, se agrega el destino
     * como vecino; si coincide con el destino, se agrega el origen como vecino.
     *
     * @param nodoActual localidad de la cual se desean obtener los vecinos.
     * @return lista de localidades vecinas conectadas directamente con la
     * localidad recibida.
     */
    public List<Localidad> obtenerVecinos(Localidad nodoActual) {
        List<Localidad> vecinos = new ArrayList<>();
        
        for (Carretera c : aristas) {
            if (c.getOrigen().equals(nodoActual)) {
                vecinos.add(c.getDestino());
            } else if (c.getDestino().equals(nodoActual)) {
                vecinos.add(c.getOrigen());
            }
        }
        return vecinos;
    }

     /**
     * Realiza un recorrido en anchura, BFS, a partir de una localidad inicial.
     * 
     * El algoritmo BFS visita primero la localidad de origen, después sus
     * vecinos directos, luego los vecinos de esos vecinos, y así sucesivamente.
     *
     * @param s localidad inicial desde donde comenzará el recorrido BFS.
     * @return lista de localidades en el orden en que fueron visitadas.
     */
    public List<Localidad> recorridoBFS(Localidad s, Runnable actualizacionVisual) {    
        // Lista que guarda el resultado final para mandarlo a la interfaz grafica
        List<Localidad> ordenVisitados = new ArrayList<>();
        
        // Inicializamos la cola de espera
        Queue<Localidad> Q = new LinkedList<>();

        // Reseteamos todo el mapa, es decir los atributos de cada localidad que nos ayudaran con la busqueda.
        for (Localidad u : nodos) {
            u.setColorActual(Color.WHITE); // Blanco que indica no viistado
            u.setDistanciaMinima(Double.MAX_VALUE); 
            u.setAntecesor(null);
        }
        
        // Avisamos a la interfaz que tome foto del mapa en blanco
        if (actualizacionVisual != null) actualizacionVisual.run();

        // Marcamos el punto de partida
        s.setColorActual(Color.GRAY); // Gris indica que ya fue visitado pero no todos sus vecinos
        s.setDistanciaMinima(0); // Como es el nodo fuente su distancia es de 0
        Q.add(s);
        
        // Avisamos a la interfaz que tome foto del origen en gris
        if (actualizacionVisual != null) actualizacionVisual.run();

        while (!Q.isEmpty()) {
            Localidad u = Q.poll();
            ordenVisitados.add(u);

            for (Localidad v : obtenerVecinos(u)) {
                if (v.getColorActual().equals(Color.WHITE)) {
                    
                    // Al descubrir un vecino, lo pintamos de gris
                    v.setColorActual(Color.GRAY);
                    // le setteamos su distancia del nodo fuente
                    v.setDistanciaMinima(u.getDistanciaMinima() + 1);
                    // su nodo antecesor
                    v.setAntecesor(u);
                    // por ultimo lo agregamos a la cola para poder encontrar a todos sus vecinos
                    Q.add(v);
                    // Avisamos a la interfaz que tome foto del vecino en gris
                    if (actualizacionVisual != null) actualizacionVisual.run();
                }
            }
            
            // Cuando se termina de revisar todos los vecinos de un nodo, el color se actualiza a negro.
            u.setColorActual(Color.BLACK);
            // Avisamos a la interfaz que tome foto del nodo negro
            if (actualizacionVisual != null) actualizacionVisual.run();
        }

        return ordenVisitados;
    }
    
    // --- Getters y Setters ---
    public List<Localidad> getNodos() {
        return nodos;
    }

    public void setNodos(List<Localidad> nodos) {
        this.nodos = nodos;
    }

    public List<Carretera> getAristas() {
        return aristas;
    }

    public void setAristas(List<Carretera> aristas) {
        this.aristas = aristas;
    }
}
