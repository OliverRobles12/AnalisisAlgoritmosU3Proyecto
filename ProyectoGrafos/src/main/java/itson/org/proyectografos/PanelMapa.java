package itson.org.proyectografos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelMapa extends JPanel {

    private static final Logger LOG = Logger.getLogger(PanelMapa.class.getName());
    private final URL urlImagen = getClass().getResource("/mapa_chiapas_contorno.png");
    
    private BufferedImage imagenFondo;
    private List<Localidad> localidades;
    private List<Carretera> carreteras;
    private boolean mostrarTabla = false;
    private JPanel panelContenedorTablas;
    private boolean mostrarMatriz = false;
    private JPanel panelContenedorMatriz;

    public PanelMapa(List<Localidad> localidades, List<Carretera> carreteras) {        
        this.localidades = localidades;
        this.carreteras = carreteras;
        cargarImagen();
        this.setLayout(new BorderLayout());
        crearTablasNodosYAristas();
        crearTablaMatriz();
    }
    
    private void crearTablasNodosYAristas() {
        JPanel panelTablas = new JPanel();
        panelTablas.setLayout(new BoxLayout(panelTablas, BoxLayout.Y_AXIS));
        
        panelTablas.setBackground(Color.decode("#f3e3b9"));
        panelTablas.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); 
        
        JLabel lblNodos = new JLabel("TABLA DE LOCALIDADES");
        lblNodos.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNodos.setForeground(Color.WHITE);
        lblNodos.setAlignmentX(CENTER_ALIGNMENT);
        
        String[] colsNodos = {"Nombre Localidad", "Coordenada X", "Coordenada Y"};
        DefaultTableModel modelNodos = new DefaultTableModel(colsNodos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        for (int i = 0; i < localidades.size(); i++) {
            Localidad loc = localidades.get(i);
            String nombre = loc.getNombre(); 
            int x = loc.getX();
            int y = loc.getY();
            
            modelNodos.addRow(new Object[]{i, nombre, x, y});
        }
        JTable tablaNodos = new JTable(modelNodos);
        darEstiloTabla(tablaNodos);
        
        JScrollPane scrollNodos = new JScrollPane(tablaNodos);
        scrollNodos.setBorder(BorderFactory.createEmptyBorder());
        scrollNodos.getViewport().setBackground(Color.WHITE); 
        
        JLabel lblAristas = new JLabel("TABLA DE CARRETERAS");
        lblAristas.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblAristas.setForeground(Color.WHITE);
        lblAristas.setAlignmentX(CENTER_ALIGNMENT);
        
        String[] colsAristas = {"Origen", "Destino", "Distancia"};
        DefaultTableModel modelAristas = new DefaultTableModel(colsAristas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        for (int i = 0; i < carreteras.size(); i++) {
            Carretera car = carreteras.get(i);
            
            String origen = car.getOrigen().getNombre();
            String destino = car.getDestino().getNombre();
            double peso = car.getPeso();
            
            modelAristas.addRow(new Object[]{origen, destino, peso + " km"});
        }
        JTable tablaAristas = new JTable(modelAristas);
        darEstiloTabla(tablaAristas);
        
        JScrollPane scrollAristas = new JScrollPane(tablaAristas);
        scrollAristas.setBorder(BorderFactory.createEmptyBorder()); 
        scrollAristas.getViewport().setBackground(Color.WHITE);
        
        panelTablas.add(Box.createVerticalStrut(10)); 
        panelTablas.add(lblNodos);
        panelTablas.add(Box.createVerticalStrut(15));
        panelTablas.add(scrollNodos);
        panelTablas.add(Box.createVerticalStrut(40)); 
        panelTablas.add(lblAristas);
        panelTablas.add(Box.createVerticalStrut(15));
        panelTablas.add(scrollAristas);
        panelTablas.add(Box.createVerticalStrut(10)); 
        
        panelContenedorTablas = panelTablas;
        panelContenedorTablas.setVisible(false); 
        
            }
    
    private void cargarImagen() {
        try {
            if (urlImagen != null) {
                imagenFondo = ImageIO.read(urlImagen);
            }   else {
                System.err.println("No se encontró la imagen en la ruta especificada.");
                throw new IOException("Ruta imagen nula");
            }
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
            imagenFondo = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imagenFondo.createGraphics();
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 800, 600);
            g.dispose();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (mostrarTabla || mostrarMatriz) {
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (imagenFondo != null) {
            g2d.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }

        for (Carretera c : carreteras) {
            c.dibujar(g2d);
        }

        for (Localidad loc : localidades) {
            loc.dibujar(g2d);
        }
    }
    
    public boolean isMostrarTabla() {
        return mostrarTabla;
    }
    
    public void setMostrarTabla(boolean mostrar) {
        this.mostrarTabla = mostrar;
        
        if (mostrarTabla) {
            this.mostrarMatriz = false; 
            if (panelContenedorMatriz != null) {
                this.remove(panelContenedorMatriz); 
            }
            this.add(panelContenedorTablas, BorderLayout.CENTER);
            panelContenedorTablas.setVisible(true);
        } else {
            if (panelContenedorTablas != null) {
                this.remove(panelContenedorTablas);
                panelContenedorTablas.setVisible(false);
            }
        }
        
        this.revalidate(); 
        this.repaint();
    }
    
    private void darEstiloTabla(JTable tabla) {
        tabla.getTableHeader().setBackground(Color.decode("#c9a163"));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.getTableHeader().setReorderingAllowed(false);

        tabla.setRowHeight(30); 
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setSelectionBackground(Color.decode("#c9a163")); 
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(new Color(230, 230, 230)); 
        tabla.setShowVerticalLines(false); 

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        ((DefaultTableCellRenderer)tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }
    
    
    private void crearTablaMatriz() {
        JPanel panelMatriz = new JPanel();
        panelMatriz.setLayout(new BoxLayout(panelMatriz, BoxLayout.Y_AXIS));
        panelMatriz.setBackground(Color.decode("#f3e3b9"));
        panelMatriz.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); 
        
        JLabel lblMatriz = new JLabel("MATRIZ DE ADYACENCIA");
        lblMatriz.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblMatriz.setForeground(Color.WHITE);
        lblMatriz.setAlignmentX(CENTER_ALIGNMENT);
        
        int n = localidades.size();
        
        String[] columnas = new String[n + 1];
        columnas[0] = "Localidad";
        for (int i = 0; i < n; i++) {
            columnas[i + 1] = localidades.get(i).getNombre();
        }
        
        DefaultTableModel modelMatriz = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        String[][] matrizValores = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizValores[i][j] = (i == j) ? "-" : "-";
            }
        }
        
        for (Carretera car : carreteras) {
            int origenIdx = localidades.indexOf(car.getOrigen());
            int destinoIdx = localidades.indexOf(car.getDestino());
            
            if (origenIdx != -1 && destinoIdx != -1) {
                String peso = String.valueOf(car.getPeso());
                matrizValores[origenIdx][destinoIdx] = peso;
                matrizValores[destinoIdx][origenIdx] = peso;
            }
        }
        
        for (int i = 0; i < n; i++) {
            Object[] fila = new Object[n + 1];
            fila[0] = localidades.get(i).getNombre(); 
            for (int j = 0; j < n; j++) {
                fila[j + 1] = matrizValores[i][j];
            }
            modelMatriz.addRow(fila);
        }
        
        JTable tablaMatriz = new JTable(modelMatriz);
        tablaMatriz.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        darEstiloTabla(tablaMatriz);

        tablaMatriz.getColumnModel().getColumn(0).setPreferredWidth(150);
        
        JScrollPane scrollMatriz = new JScrollPane(tablaMatriz);
        scrollMatriz.setBorder(BorderFactory.createEmptyBorder());
        scrollMatriz.getViewport().setBackground(Color.WHITE);
        
        panelMatriz.add(Box.createVerticalStrut(10)); 
        panelMatriz.add(lblMatriz);
        panelMatriz.add(Box.createVerticalStrut(15));
        panelMatriz.add(scrollMatriz);
        panelMatriz.add(Box.createVerticalStrut(10)); 
        
        panelContenedorMatriz = panelMatriz;
        panelContenedorMatriz.setVisible(false); 
        
        tablaMatriz.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 10));
        
    }
    
    public boolean isMostrarMatriz() {
        return mostrarMatriz;
    }
    
    public void setMostrarMatriz(boolean mostrar) {
        this.mostrarMatriz = mostrar;
        
        if (mostrarMatriz) {
            this.mostrarTabla = false; 
            if (panelContenedorTablas != null) {
                this.remove(panelContenedorTablas); 
            }
            this.add(panelContenedorMatriz, BorderLayout.CENTER);
            panelContenedorMatriz.setVisible(true);
        } else {
            if (panelContenedorMatriz != null) {
                this.remove(panelContenedorMatriz);
                panelContenedorMatriz.setVisible(false);
            }
        }
        
        this.revalidate(); 
        this.repaint();    
    }
}