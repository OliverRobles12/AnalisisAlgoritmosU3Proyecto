/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.proyectografos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author regin
 */
public class Grafo {
    private List<Localidad> nodos;
    private List<Carretera> aristas;

    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }
}
