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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author emyla
 */
public class Algoritmos {
        
     /**
     * Realiza un recorrido en anchura, BFS, a partir de una localidad inicial.
     * 
     * El algoritmo BFS visita primero la localidad de origen, después sus
     * vecinos directos, luego los vecinos de esos vecinos, y así sucesivamente.
     *
     * @param grafo conjunto de nodos y aristas
     * @param s localidad inicial desde donde comenzará el recorrido BFS.
     * @return lista de localidades en el orden en que fueron visitadas.
     */
    public static List<Localidad> recorridoBFS(Grafo grafo, Localidad s, Runnable actualizacionVisual) {    
        // Lista que guarda el resultado final para mandarlo a la interfaz grafica
        List<Localidad> ordenVisitados = new ArrayList<>();
        
        // Inicializamos la cola de espera
        Queue<Localidad> Q = new LinkedList<>();

        // Reseteamos todo el mapa, es decir los atributos de cada localidad que nos ayudaran con la busqueda.
        prepararGrafo(grafo);
        
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

            for (Localidad v : grafo.obtenerVecinos(u)) {
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
    
    /**
     * Realiza un recorrido en profundidad (DFS).
     * Explora tan profundo como sea posible a lo largo de cada rama antes de retroceder.
     * @param grafo - Osea, recibe el conjunto de vertices y aristas
     * @param s - El nodo inicial
     * @param actualizacionVisual
     * @return 
     */
    public static List<Localidad> recorridoDFS(Grafo grafo, Localidad s, Runnable actualizacionVisual) {
        
        List<Localidad> ordenVisitados = new ArrayList<>(); // Inicializamos el conjunto de vertices
                
        // for each vertex u ∈ G.V
        // Osea, por cada vértice u perteneciente al conjunto de Vertices dentro del Grafo:
        for (Localidad u : grafo.getNodos()) {
            // u.color = WHITE
            u.setColorActual(Color.WHITE); // Se le asigna color blanco
            // u.π = NIL
            u.setAntecesor(null); // El antecesor de u es nulo
            
            // Reiniciamos los tiempos de descubrimiento y finalización
            u.setD(0);
            u.setF(0);
        }
        // TODO LO DE ARRIBA ES PREPARACIÓN DE VARIABLES
        
        // time = 0
        // Usamos un arreglo de 1 posición para pasarlo por referencia en la recursión
        int[] time = {0}; 
        
        // ejecutamos DFS-VISIT en la ciudad que el usuario eligió primero.
        if (s != null && s.getColorActual().equals(Color.WHITE)) { // validamos que el nodo s no sea nulo
            // y que su color sea blanco (no ha sido descubierto)
            dfsVisit(grafo, s, time, ordenVisitados, actualizacionVisual); // Ejecutamos el método recursivo
        }

        // for each vertex u ∈ G.V
        // Por cada vertice u perteneciente al conjunto de vertices del grafo...
        for (Localidad u : grafo.getNodos()) { 
            // if u.color == WHITE
            if (u.getColorActual().equals(Color.WHITE)) { // Se valida que el color sea blanco
                //DFS-VISIT(G, u)
                dfsVisit(grafo, u, time, ordenVisitados, actualizacionVisual); // Llamamos al método recursivo.
            }
        }
        // OJO. Primero ejecutamos el DFS en el vertice s (el que el usuario solicitó) y
        // al acabar, ahora si se ejecuta como normalmente lo conocemos (sin importar un orden en especifico,
        // mientras esten conectados y lleguemos "al fondo" de dicho caminito
        
        return ordenVisitados; // Retornamos la lista de localidades por las que pasamos (ordenada)
        // osea, los nodos
    }

    private static void dfsVisit(Grafo grafo, Localidad u, int[] time, List<Localidad> ordenVisitados, Runnable actualizacionVisual) {
        // time = time + 1
        time[0] = time[0] + 1;
        
        // u.d = time 
        u.setD(time[0]); //Guardamos el tiempo de descubrimiento
                
        // u.color = GRAY
        u.setColorActual(Color.GRAY); // Le asignamos color gris
        // Es decir, el vertice u fue descubierto más no explorado a produndidad total
        
        // Guardamos y pintamos
        ordenVisitados.add(u);
        if (actualizacionVisual != null) actualizacionVisual.run();

        //for each v ∈ G.Adj[u]
        // Osea, por cada nodo perteneciente a los adyacentes al nodo u dentro del Grafo:
        for (Localidad v : grafo.obtenerVecinos(u)) {
            // if v.color == WHITE. Si el color del adyacente es blanco, entonces:
            if (v.getColorActual().equals(Color.WHITE)) {
                //v.π = u
                v.setAntecesor(u); // Asignamos el antecesor del vertice v 
                //DFS-VISIT(G, v)
                dfsVisit(grafo, v, time, ordenVisitados, actualizacionVisual); // Ejecutamos de nuevo
                // hasta llegar al final final
            }
        }
        // Si salimos del ciclo, quiere decir que ya no hay un nodo siguiente
        // osea, llegamos al final, entones:
        
        //u.color = BLACK
        u.setColorActual(Color.BLACK); 
        // pintamos el nodo al terminar
        if (actualizacionVisual != null) actualizacionVisual.run();
        
        // time = time + 1
        time[0] = time[0] + 1;
        // Tiempo de finalización
        u.setF(time[0]);
    }
    
    /**
     * Ejecuta el algoritmo de Kruskal para encontrar el Árbol de Expansión Mínima.
     * @param grafo El grafo del cual se extraerá el MST
     * @param actualizacionVisual Función para animar el proceso
     * @return Lista de carreteras que conforman el MST
     */
    public static List<Carretera> kruskalMST(Grafo grafo, Runnable actualizacionVisual) {
        // A = {}
        // Creamos una lista de aristas vacío
        List<Carretera> A = new ArrayList<>();
        
        // Estructura para simular los conjuntos (Union-Find)
        Map<Localidad, Localidad> padre = new HashMap<>();
        
        // foreach v in G.V: MAKE-SET(v)
        // Por cada vertice v perteneciente en el conjunto de vertices del Grafo, crear un conjunto con v
        for (Localidad v : grafo.getNodos()) {
            padre.put(v, v); // Cada nodo es su propio padre al inicio
            v.setColorActual(Color.decode("#f3e3b9")); // Reiniciamos color del nodo
        }
        
        // Apagamos todas las carreteras al inicio para que resalte el MST
        for (Carretera c : grafo.getAristas()) {
            c.setColorActual(new Color(200, 200, 200, 80)); // Gris muy tenue
            c.grosor = 3;
        }
        if (actualizacionVisual != null) actualizacionVisual.run();

        // Creamos una copia de las aristas y las ordenamos
        // ordered by weight(u, v)
        List<Carretera> aristasOrdenadas = new ArrayList<>(grafo.getAristas());
        aristasOrdenadas.sort(Comparator.comparingDouble(c -> c.peso)); 

        // foreach (u, v) in G.E ordered by weight
        // Osea, por cada arista (que conecta a los nodos u y v) perteneciente al 
        // conjunto de Aristas, ya ordenadas por su peso de menor a mayor:
        for (Carretera arista : aristasOrdenadas) {
            Localidad u = arista.getOrigen();  // Extraemos el nodo u (origen)
            Localidad v = arista.getDestino(); // Extraemos el nodo v (destino)
            
            // FIND-SET(u) y FIND-SET(v)
            // Buscamos a qué conjunto (o "familia") pertenece actualmente el nodo u y el nodo v
            Localidad setU = findSet(u, padre);
            Localidad setV = findSet(v, padre);
            
            // if FIND-SET(u) != FIND-SET(v)
            // Si el conjunto de u es diferente al conjunto de v, significa que NO están 
            // conectados todavía. Por lo tanto, agregar esta arista NO formará un ciclo.
            if (setU != setV) {
                // A = A U {(u, v)}
                // Añadimos la arista (u, v) a nuestro conjunto A (el Árbol de Expansión Mínima)
                A.add(arista);
                
                // UNION(u, v)
                // Fusionamos los dos conjuntos. Ahora el padre del conjunto de U será el conjunto de V
                // indicando que todos esos nodos ya están conectados entre sí.
                padre.put(setU, setV);
                
                arista.setColorActual(Color.decode("#c9a163")); // Tu color dorado
                arista.grosor = 6; // Hacemos la línea más gruesa
                u.setColorActual(Color.ORANGE); // Resaltamos los nodos conectados
                v.setColorActual(Color.ORANGE);
                
                // Disparamos el repintado y la pausa
                if (actualizacionVisual != null) {
                    actualizacionVisual.run();
                }
            }
        }
        
        // --- COMPROBACIÓN EN CONSOLA ---
        // gracias gemini por
        System.out.println("\n=== RESULTADO DEL ÁRBOL DE EXPANSIÓN MÍNIMA (KRUSKAL) ===");
        double pesoTotal = 0;
        
        // Recorremos las carreteras que Kruskal eligió (las que están en A)
        for (Carretera c : A) {
            String origen = c.getOrigen().getNombre();
            String destino = c.getDestino().getNombre();
            double peso = c.peso; // Suponiendo que el atributo se llama peso
            
            System.out.println("- " + origen + " <---> " + destino + " (Distancia: " + peso + " km)");
            pesoTotal += peso;
        }
        
        System.out.println("-> Distancia TOTAL del recorrido óptimo: " + pesoTotal + " km");
        System.out.println("=========================================================\n");
        
        return A; // return A, es decir, retornamos la lista final de 
        // carreteras que conforman el MST
    }
    
    
//    DIJKSTRA(G, w, s)                    -> Recibe el Grafo, el peso y el origen
//    1  INITIALIZE-SINGLE-SOURCE(G, s)    -> Inicializamos. Preparamos G y s
//    2  S ← ∅                            -> S es la lista de ciudades que encontramos. Inicia en 0
//    3  Q ← V[G]                          -> Creamos una cola Q en la que meteremos TODOS los vértices del grafo
//    4  while Q ≠ ∅                      -> Mientras la cola sea diferente q vacía
//    5      do u ← EXTRACT-MIN(Q)         -> De las ciudades que quedan dentro de la cola, se extrae la que tenga la distancia más corta (vertice u)
//    6         S ← S ∪ {u}                -> Al conjunto de ciudades S (encontradas) le unir la ciudad u q acabas de sacar
//    7         for each vertex v ∈ Adj[u] -> Por cada vertice adyacente al vertice u:
//    8             do RELAX(u, v, w)       -> Relaaax, se aplica entre u y v, usando el peso de su carretera
    
    /**
     * Ejecutamos el algoritmo de DIJKSTRA, representamos visualmente en el grafo y regresamos las distancias minimas
     * para llegar a cada vertice desde la raiz
     * @param grafo El grafo con las localidades y carreteras.
     * @param inicio La localidad de origen.
     * @param actualizacionVisual 
     */
    public static Map<Localidad, Double> dijkstra(Grafo grafo, Localidad inicio, Localidad destino, Runnable actualizacionVisual) {
        // Mapa para guardar las distancias finales ordenadas
        Map<Localidad, Double> distanciasMap = new LinkedHashMap<>();

        // 1. Preparacion visual 
        for (Localidad loc : grafo.getNodos()) {
            loc.reiniciar(); 
            loc.setColorActual(Color.WHITE);
        }

        for (Carretera carr : grafo.getAristas()) {
            carr.setColorActual(new Color(150, 150, 150, 128)); 
        }

        // 2. Configurar el nodo de inicio
        inicio.setDistanciaMinima(0);
        inicio.setColorActual(Color.RED); 
        actualizacionVisual.run();

        // 3. Cola de prioridad ordenada por la distancia mínima acumulada
        PriorityQueue<Localidad> pq = new PriorityQueue<>(
                Comparator.comparingDouble(Localidad::getDistanciaMinima)
        );
        pq.add(inicio);

        // 4. Ciclo principal de Dijkstra
        while (!pq.isEmpty()) {
            Localidad actual = pq.poll();

            // Si ya fue procesado con un camino más corto, lo ignoramos
            if (actual.isVisitado()) continue;

            // Lo marcamos como visitado
            actual.setVisitado(true);

            // Agregamos el nodo y su distancia al mapa de resultados
            distanciasMap.put(actual, actual.getDistanciaMinima());

            // Pintamos el nodo actual (Si es el destino, lo pintamos de verde)
            if (actual.equals(destino)) {
                actual.setColorActual(Color.GREEN);
            } else if (actual != inicio) {
                actual.setColorActual(Color.decode("#f3e3b9")); 
            }
            actualizacionVisual.run();

            // Si ya procesamos el destino terminamos la busqueda
            if (actual.equals(destino)) {
                break; 
            }

            // Exploramos las carreteras adyacentes
            for (Carretera carretera : grafo.obtenerCarreterasAdyacentes(actual)) {
                // En la carretera ubicamos cual es el nodo vecino
                Localidad vecino = (carretera.getOrigen().equals(actual)) ? carretera.getDestino() : carretera.getOrigen();

                if (!vecino.isVisitado()) {
                    // Resaltamos la arista que estamos evaluando
                    Color colorPrevio = carretera.getColorActual();
                    carretera.setColorActual(Color.BLUE);
                    actualizacionVisual.run();

                    double nuevaDistancia = actual.getDistanciaMinima() + carretera.getPeso();

                    // Relajación de la arista, si encontramos un camino mas corto
                    if (nuevaDistancia < vecino.getDistanciaMinima()) {
                        vecino.setDistanciaMinima(nuevaDistancia);
                        // Agregamos el antecesor para reconstruir el camino
                        vecino.setAntecesor(actual); 
                        pq.add(vecino); 

                        // Pintamos la arista para mostrar que forma parte de la ruta más corta en exploración
                        carretera.setColorActual(Color.decode("#c9a163"));
                    } else {
                        // Si no mejoró la ruta, regresamos la carretera a su color anterior
                        carretera.setColorActual(colorPrevio);
                    }
                    actualizacionVisual.run();
                }
            }
        }

        // 5. Resaltar la ruta exacta elegida en Rojo
        if (distanciasMap.containsKey(destino)) {
            Localidad paso = destino;
            while (paso != null && paso.getAntecesor() != null) {
                Localidad previo = paso.getAntecesor();

                // Buscamos qué carretera une a 'paso' y 'previo' para pintarla
                for (Carretera c : grafo.obtenerCarreterasAdyacentes(paso)) {
                    if (c.getOrigen().equals(previo) || c.getDestino().equals(previo)) {
                        c.setColorActual(Color.RED); 
                        break;
                    }
                }
                paso = previo;
            }
            actualizacionVisual.run(); 
        }

        return distanciasMap;
    }
    
    // MÉTODOS AUXILIARES
    
    // Utilizado en: Recorrido BFS
    private static void prepararGrafo(Grafo grafo) {
        for (Localidad u : grafo.getNodos()) {
            u.setColorActual(Color.WHITE); // Blanco = no visitado
            u.setDistanciaMinima(Double.MAX_VALUE); 
            u.setAntecesor(null);
        }
        for (Carretera c : grafo.getAristas()) {
            c.colorActual = new Color(100, 100, 100, 150); // Gris por defecto
        }
    }
    
    // Método auxiliar para simular el FIND-SET con "Path Compression"
    private static Localidad findSet(Localidad nodo, Map<Localidad, Localidad> padre) {
        if (padre.get(nodo) == nodo) {
            return nodo;
        }
        // Compresión de caminos para que sea ultra rápido
        Localidad raiz = findSet(padre.get(nodo), padre);
        padre.put(nodo, raiz);
        return raiz;
    }
}
