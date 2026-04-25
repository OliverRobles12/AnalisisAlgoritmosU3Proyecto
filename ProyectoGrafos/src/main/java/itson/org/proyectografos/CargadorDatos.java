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
public class CargadorDatos {

    public static List<Localidad> cargarLocalidades() {
        List<Localidad> lista = new ArrayList<>();

        lista.add(new Localidad("Tuxtla Gutiérrez", 235, 271));   
        lista.add(new Localidad("Tapachula", 399, 632));         
        lista.add(new Localidad("San Cristóbal", 339, 281));    
        lista.add(new Localidad("Comitán", 431, 370));           
        lista.add(new Localidad("Chiapa de Corzo", 267, 292));   
        lista.add(new Localidad("Palenque", 491, 133));          
        lista.add(new Localidad("Cintalapa", 70, 280));        
        lista.add(new Localidad("Ocosingo", 593, 273));          
        lista.add(new Localidad("Ocozocoautla", 167, 224));      
        lista.add(new Localidad("Tonalá", 138, 424));            
        lista.add(new Localidad("Villaflores", 195, 351));      
        lista.add(new Localidad("Berriozábal", 204, 251));      
        lista.add(new Localidad("Huixtla", 351, 600));           
        lista.add(new Localidad("Reforma", 221, 50));          
        lista.add(new Localidad("Motozintla", 390, 557));       
        lista.add(new Localidad("Arriaga", 91, 371));           
        lista.add(new Localidad("Las Margaritas", 509, 344));  
        lista.add(new Localidad("Frontera Comalapa", 439, 467));
        lista.add(new Localidad("Las Rosas", 380, 349));       
        lista.add(new Localidad("Teopisca", 356, 309));    
        
        return lista;
    }

   public static List<Carretera> cargarCarreteras(List<Localidad> locs) {
       List<Carretera> carreteras = new ArrayList<>();

       // Conexiones desde Tuxtla Gutiérrez 
       carreteras.add(new Carretera(locs.get(0), locs.get(4), 15.0));  // Tuxtla - Chiapa de Corzo
       carreteras.add(new Carretera(locs.get(0), locs.get(11), 22.0)); // Tuxtla - Berriozábal
       carreteras.add(new Carretera(locs.get(0), locs.get(10), 75.0)); // Tuxtla - Villaflores

       // Conexiones desde San Cristóbal
       carreteras.add(new Carretera(locs.get(2), locs.get(4), 60.0));  // San Cristóbal - Chiapa de Corzo
       carreteras.add(new Carretera(locs.get(2), locs.get(19), 35.0)); // San Cristóbal - Teopisca
       carreteras.add(new Carretera(locs.get(2), locs.get(7), 90.0));  // San Cristóbal - Ocosingo
       

       // Conexiones zona Comitán 
       carreteras.add(new Carretera(locs.get(3), locs.get(19), 55.0)); // Comitán - Teopisca
       carreteras.add(new Carretera(locs.get(3), locs.get(16), 20.0)); // Comitán - Las Margaritas
       carreteras.add(new Carretera(locs.get(3), locs.get(17), 115.0));// Comitán - Frontera Comalapa
       carreteras.add(new Carretera(locs.get(18), locs.get(3), 45.0)); // Las Rosas - Comitán

       // Conexiones zona Selva y Norte
       carreteras.add(new Carretera(locs.get(7), locs.get(5), 120.0)); // Ocosingo - Palenque
       carreteras.add(new Carretera(locs.get(13), locs.get(8), 210.0));

       // Conexiones zona Costa y Soconusco
       carreteras.add(new Carretera(locs.get(8), locs.get(11), 12.0)); // Conexión Ocozocoautla (8) - Berriozábal (11)
       carreteras.add(new Carretera(locs.get(8), locs.get(6), 45.0));  // Ocozocoautla - Cintalapa
       carreteras.add(new Carretera(locs.get(6), locs.get(15), 65.0)); // Cintalapa - Arriaga
       carreteras.add(new Carretera(locs.get(15), locs.get(9), 25.0)); // Arriaga - Tonalá
       carreteras.add(new Carretera(locs.get(9), locs.get(12), 175.0));// Tonalá - Huixtla (vía Mapastepec)
       carreteras.add(new Carretera(locs.get(12), locs.get(1), 40.0));  // Huixtla - Tapachula
       carreteras.add(new Carretera(locs.get(12), locs.get(14), 60.0)); // Huixtla - Motozintla
       carreteras.add(new Carretera(locs.get(14), locs.get(17), 55.0)); // Motozintla - Frontera Comalapa
       
       return carreteras;
   }
}
