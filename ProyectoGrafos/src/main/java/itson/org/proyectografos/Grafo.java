/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.proyectografos;

import java.util.ArrayList;
import java.util.List;

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
     * @param nodoActual
     * @return 
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
     * Obtiene las carreteras conectadas directamente a una localidad.
     * Útil para algoritmos que requieren los pesos (Dijkstra, Prim).
     * @param nodoActual
     * @return 
     */
    public List<Carretera> obtenerCarreterasAdyacentes(Localidad nodoActual) {
        List<Carretera> adyacentes = new ArrayList<>();
        for (Carretera c : aristas) {
            if (c.getOrigen().equals(nodoActual) || c.getDestino().equals(nodoActual)) {
                adyacentes.add(c);
            }
        }
        return adyacentes;
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
