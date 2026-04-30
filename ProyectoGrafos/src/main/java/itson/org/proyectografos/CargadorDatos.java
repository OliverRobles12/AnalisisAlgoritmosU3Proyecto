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
        lista.add(new Localidad("Comitán", 431, 372));           
        lista.add(new Localidad("Chiapa", 267, 292));   
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
        lista.add(new Localidad("Las Rosas", 380, 345));       
        lista.add(new Localidad("Teopisca", 356, 309));    
        
        lista.add(new Localidad("Suchiapa", 229,311));
        lista.add(new Localidad("Mapastepec", 271,521));
        lista.add(new Localidad("Cacahoatán", 421,588));
        lista.add(new Localidad("Yajalón", 385,184));
        lista.add(new Localidad("Pijijiapan", 214,479));
        lista.add(new Localidad("Venustiano Carranza", 329,359));
        lista.add(new Localidad("Pichucalco", 230,117));
        lista.add(new Localidad("Acala", 292,325));
        lista.add(new Localidad("Simojovel de Allende", 327,197));
        lista.add(new Localidad("Bochil", 263,217));
        
        return lista;
    }

  public static List<Carretera> cargarCarreteras(List<Localidad> locs) {
        List<Carretera> carreteras = new ArrayList<>();

        // ---------------------------------------------------------
        // ZONA NORTE Y SELVA
        // ---------------------------------------------------------
        carreteras.add(new Carretera(locs.get(13), locs.get(26),  31.0)); // Reforma (13) - Pichucalco (26)
        carreteras.add(new Carretera(locs.get(26), locs.get(29), 100.0)); // Pichucalco (26) - Bochil (29)
        carreteras.add(new Carretera(locs.get(29), locs.get(28),  48.0)); // Bochil (29) - Simojovel (28)
        carreteras.add(new Carretera(locs.get(29), locs.get(0),   84.0)); // Bochil (29) - Tuxtla Gutiérrez (0)

        // ---------------------------------------------------------
        // ZONA CENTRO Y VALLE
        // ---------------------------------------------------------
        carreteras.add(new Carretera(locs.get(0),  locs.get(11),  22.0)); // Tuxtla Gutiérrez (0) - Berriozábal (11)
        carreteras.add(new Carretera(locs.get(0),  locs.get(4),   15.0)); // Tuxtla Gutiérrez (0) - Chiapa de Corzo (4)
        carreteras.add(new Carretera(locs.get(0),  locs.get(20),  16.0)); // Tuxtla Gutiérrez (0) - Suchiapa (20)
        carreteras.add(new Carretera(locs.get(20), locs.get(10),  60.0)); // Suchiapa (20) - Villaflores (10)
        carreteras.add(new Carretera(locs.get(4),  locs.get(27),  38.0)); // Chiapa de Corzo (4) - Acala (27)
        carreteras.add(new Carretera(locs.get(27), locs.get(25),  45.0)); // Acala (27) - V. Carranza (25)
        carreteras.add(new Carretera(locs.get(25), locs.get(18),  25.0)); // V. Carranza (25) - Las Rosas (18)

        // ---------------------------------------------------------
        // ZONA ALTOS Y RUTA A PALENQUE
        // ---------------------------------------------------------
        carreteras.add(new Carretera(locs.get(4),  locs.get(2),   60.0)); // Chiapa de Corzo (4) - San Cristóbal (2)
        carreteras.add(new Carretera(locs.get(2),  locs.get(19),  35.0)); // San Cristóbal (2) - Teopisca (19)
        carreteras.add(new Carretera(locs.get(2),  locs.get(7),   90.0)); // San Cristóbal (2) - Ocosingo (7)
        carreteras.add(new Carretera(locs.get(7),  locs.get(23),  60.0)); // Ocosingo (7) - Yajalón (23)
        carreteras.add(new Carretera(locs.get(23), locs.get(5),  115.0)); // Yajalón (23) - Palenque (5)

        // ---------------------------------------------------------
        // ZONA FRONTERIZA
        // ---------------------------------------------------------
        carreteras.add(new Carretera(locs.get(19), locs.get(18),  30.0)); // Teopisca (19) - Las Rosas (18)
        carreteras.add(new Carretera(locs.get(19), locs.get(3),   55.0)); // Teopisca (19) - Comitán (3)
        carreteras.add(new Carretera(locs.get(3),  locs.get(16),  20.0)); // Comitán (3) - Las Margaritas (16)
        carreteras.add(new Carretera(locs.get(3),  locs.get(17),  80.0)); // Comitán (3) - Frontera Comalapa (17)
        carreteras.add(new Carretera(locs.get(17), locs.get(14),  55.0)); // Frontera Comalapa (17) - Motozintla (14)

        // ---------------------------------------------------------
        // ZONA COSTA Y SOCONUSCO
        // ---------------------------------------------------------
        carreteras.add(new Carretera(locs.get(11), locs.get(8),   14.0)); // Berriozábal (11) - Ocozocoautla (8)
        carreteras.add(new Carretera(locs.get(8),  locs.get(6),   45.0)); // Ocozocoautla (8) - Cintalapa (6)
        carreteras.add(new Carretera(locs.get(6),  locs.get(15),  65.0)); // Cintalapa (6) - Arriaga (15)
        carreteras.add(new Carretera(locs.get(15), locs.get(9),   25.0)); // Arriaga (15) - Tonalá (9)
        carreteras.add(new Carretera(locs.get(9),  locs.get(24),  75.0)); // Tonalá (9) - Pijijiapan (24)
        carreteras.add(new Carretera(locs.get(24), locs.get(21),  45.0)); // Pijijiapan (24) - Mapastepec (21)
        carreteras.add(new Carretera(locs.get(21), locs.get(12),  60.0)); // Mapastepec (21) - Huixtla (12)
        carreteras.add(new Carretera(locs.get(12), locs.get(14),  90.0)); // Huixtla (12) - Motozintla (14)
        carreteras.add(new Carretera(locs.get(12), locs.get(1),   41.0)); // Huixtla (12) - Tapachula (1)
        carreteras.add(new Carretera(locs.get(1),  locs.get(22),  18.0)); // Tapachula (1) - Cacahoatán (22)

        return carreteras;
    }
}
