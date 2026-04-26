package itson.org.proyectografos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;
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

    public PanelMapa(List<Localidad> localidades, List<Carretera> carreteras) {        
        this.localidades = localidades;
        this.carreteras = carreteras;
        cargarImagen();
        this.setLayout(new BorderLayout());
        crearTablasNodosYAristas();
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
        
        String[] colsNodos = {"#", "Nombre Localidad", "Coordenada X", "Coordenada Y"};
        DefaultTableModel modelNodos = new DefaultTableModel(colsNodos, 0);
        
        for (int i = 0; i < localidades.size(); i++) {
            Localidad loc = localidades.get(i);
            String nombre = loc.getNombre(); 
            int x = loc.getX();
            int y = loc.getY();
            
            modelNodos.addRow(new Object[]{i + 1, nombre, x, y});
        }
        JTable tablaNodos = new JTable(modelNodos);
        darEstiloTabla(tablaNodos);
        
        JScrollPane scrollNodos = new JScrollPane(tablaNodos);
        scrollNodos.setBorder(BorderFactory.createEmptyBorder());
        scrollNodos.getViewport().setBackground(Color.WHITE);
        aplicarEstiloScroll(scrollNodos); 
        
        JLabel lblAristas = new JLabel("TABLA DE CARRETERAS");
        lblAristas.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblAristas.setForeground(Color.WHITE);
        lblAristas.setAlignmentX(CENTER_ALIGNMENT);
        
        String[] colsAristas = {"#", "Origen", "Destino", "Distancia"};
        DefaultTableModel modelAristas = new DefaultTableModel(colsAristas, 0);
        
        for (int i = 0; i < carreteras.size(); i++) {
            Carretera car = carreteras.get(i);
            
            String origen = car.getOrigen().getNombre();
            String destino = car.getDestino().getNombre();
            double peso = car.getPeso();
            
            modelAristas.addRow(new Object[]{i + 1, origen, destino, peso + " km"});
        }
        JTable tablaAristas = new JTable(modelAristas);
        darEstiloTabla(tablaAristas);
        
        JScrollPane scrollAristas = new JScrollPane(tablaAristas);
        scrollAristas.setBorder(BorderFactory.createEmptyBorder()); 
        scrollAristas.getViewport().setBackground(Color.WHITE);
        aplicarEstiloScroll(scrollAristas); 
        
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
        
        this.add(panelContenedorTablas, BorderLayout.CENTER);
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
        
        if (mostrarTabla) {
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
    
    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
        if (panelContenedorTablas != null) {
            panelContenedorTablas.setVisible(mostrarTabla);
        }
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
    
    private void aplicarEstiloScroll(JScrollPane scroll) {
        scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
           
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return crearBotonInvisible();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return crearBotonInvisible();
            }
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                    return;
                }
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#c9a163"));
                g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2, thumbBounds.width - 4, thumbBounds.height - 4, 10, 10);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.decode("#f3e3b9")); 
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
           
            private JButton crearBotonInvisible() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
    }
}