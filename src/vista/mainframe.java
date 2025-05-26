package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Componente;

public class mainframe extends JFrame {
    // Componentes publicos para el controlador
    public JComboBox<String> comboTipo;
    public JTable tablaComponentes;
    public JButton btnAgregarCarrito;
    public JButton btnVaciarCarrito;
    public JButton btnComprar;
    
    // Labels
    private JLabel lblUsuario;
    private JLabel lblCreditos;
    private JLabel lblTotal;
    
    // Paneles
    private JPanel panelCarrito;
    private DefaultTableModel modeloTabla;
    
    // Lista del carrito
    private List<Componente> carrito;
    private double totalCarrito = 0;
    
    public mainframe() {
        // Configurar ventana
        setTitle("Configurador de PCs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Inicializar carrito
        carrito = new ArrayList<>();
        
        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.DARK_GRAY);
        panelSuperior.setLayout(new BorderLayout());
        
        lblUsuario = new JLabel("Usuario: Invitado");
        lblUsuario.setForeground(Color.WHITE);
        lblCreditos = new JLabel("Creditos: 0.00");
        lblCreditos.setForeground(Color.WHITE);
        
        panelSuperior.add(lblUsuario, BorderLayout.WEST);
        panelSuperior.add(lblCreditos, BorderLayout.EAST);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        // Crear pestañas
        JTabbedPane pestanas = new JTabbedPane();
        
        // Pestaña 1 - Componentes
        JPanel panelComponentes = new JPanel();
        panelComponentes.setLayout(new BorderLayout());
        
        // Panel para el combo
        JPanel panelCombo = new JPanel();
        panelCombo.add(new JLabel("Tipo de componente:"));
        comboTipo = new JComboBox<>();
        comboTipo.addItem("Procesador");
        comboTipo.addItem("Placa Base");
        comboTipo.addItem("Memoria RAM");
        comboTipo.addItem("Tarjeta Gráfica");
        comboTipo.addItem("Almacenamiento");
        comboTipo.addItem("Fuente de Poder");
        comboTipo.addItem("Caja");
        panelCombo.add(comboTipo);
        
        panelComponentes.add(panelCombo, BorderLayout.NORTH);
        
        // Tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");
        
        tablaComponentes = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaComponentes);
        panelComponentes.add(scrollTabla, BorderLayout.CENTER);
        
        // Boton agregar
        JPanel panelBotonAgregar = new JPanel();
        btnAgregarCarrito = new JButton("Agregar al Carrito");
        panelBotonAgregar.add(btnAgregarCarrito);
        panelComponentes.add(panelBotonAgregar, BorderLayout.SOUTH);
        
        // Pestaña 2 - Carrito
        JPanel panelCarritoCompleto = new JPanel();
        panelCarritoCompleto.setLayout(new BorderLayout());
        
        // Label total
        lblTotal = new JLabel("Total: 0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        panelCarritoCompleto.add(lblTotal, BorderLayout.NORTH);
        
        // Panel del carrito
        panelCarrito = new JPanel();
        panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
        JScrollPane scrollCarrito = new JScrollPane(panelCarrito);
        panelCarritoCompleto.add(scrollCarrito, BorderLayout.CENTER);
        
        // Botones del carrito
        JPanel panelBotonesCarrito = new JPanel();
        btnVaciarCarrito = new JButton("Vaciar Carrito");
        btnComprar = new JButton("Comprar");
        panelBotonesCarrito.add(btnVaciarCarrito);
        panelBotonesCarrito.add(btnComprar);
        panelCarritoCompleto.add(panelBotonesCarrito, BorderLayout.SOUTH);
        
        // Pestaña 3 - Creditos
        JPanel panelCreditos = new JPanel();
        panelCreditos.add(new JLabel("Mis Creditos"));
        
        // Agregar pestañas
        pestanas.addTab("Componentes", panelComponentes);
        pestanas.addTab("Carrito", panelCarritoCompleto);
        pestanas.addTab("Creditos", panelCreditos);
        
        add(pestanas, BorderLayout.CENTER);
        
        // Configurar evento del boton vaciar
        configurarEventoAgregarCarrito();
    }
    
    public void setNombreUsuario(String nombre) {
        lblUsuario.setText("Usuario: " + nombre);
    }
    
    public void setCreditos(double creditos) {
        lblCreditos.setText("Creditos: " + creditos);
    }
    
    public void cargarComponentes(List<Componente> lista) {
        modeloTabla.setRowCount(0);
        for (Componente c : lista) {
            Object[] fila = new Object[4];
            fila[0] = c.getNombre();
            fila[1] = c.getDescripcion();
            fila[2] = c.getPrecio();
            fila[3] = c.getStock();
            modeloTabla.addRow(fila);
        }
    }
    
    public void agregarComponenteAlCarrito(Componente comp) {
        carrito.add(comp);
        actualizarCarrito();
    }
    
    private void actualizarCarrito() {
        panelCarrito.removeAll();
        totalCarrito = 0;
        
        for (Componente comp : carrito) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
            JLabel nombreLabel = new JLabel(comp.getNombre() + " - " + comp.getPrecio() + " euros");
            itemPanel.add(nombreLabel, BorderLayout.CENTER);
            
            JButton btnEliminar = new JButton("X");
            btnEliminar.addActionListener(e -> {
                carrito.remove(comp);
                actualizarCarrito();
            });
            itemPanel.add(btnEliminar, BorderLayout.EAST);
            
            panelCarrito.add(itemPanel);
            panelCarrito.add(Box.createVerticalStrut(5));
            
            totalCarrito += comp.getPrecio();
        }
        
        lblTotal.setText("Total: " + totalCarrito);
        panelCarrito.revalidate();
        panelCarrito.repaint();
    }
    
    public void configurarEventoAgregarCarrito() {
        btnVaciarCarrito.addActionListener(e -> {
            carrito.clear();
            actualizarCarrito();
        });
    }
    
    public JComboBox<String> getComboTipo() {
        return comboTipo;
    }
    
    public JTable getTablaComponentes() {
        return tablaComponentes;
    }
    
    public JButton getBtnAgregarCarrito() {
        return btnAgregarCarrito;
    }
}