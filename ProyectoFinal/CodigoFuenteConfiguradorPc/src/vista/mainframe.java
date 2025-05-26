package vista;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Componente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana principal para el configurador de PCs.
 */
public class mainframe extends JFrame {
    /** ComboBox para seleccionar tipo de componente. */
    public JComboBox<String> comboTipo;

    /** Tabla para mostrar los componentes disponibles. */
    public JTable tablaComponentes;

    /** Botón para agregar componentes al carrito. */
    public JButton btnAgregarCarrito;

    /** Botón para vaciar el carrito. */
    public JButton btnVaciarCarrito;

    /** Botón para realizar la compra. */
    public JButton btnComprar;

    /** Label que muestra el nombre de usuario. */
    private JLabel lblUsuario;

    /** Label que muestra los créditos del usuario. */
    private JLabel lblCreditos;

    /** Label que muestra el total del carrito. */
    private JLabel lblTotal;

    /** Panel que contiene los elementos del carrito. */
    private JPanel panelCarrito;

    /** Modelo de la tabla de componentes. */
    private DefaultTableModel modeloTabla;

    /** Lista de componentes añadidos al carrito. */
    private List<Componente> carrito;

    /** Total acumulado del carrito. */
    private double totalCarrito = 0;

    /**
     * Constructor que inicializa la ventana principal.
     */
    public mainframe() {
        setTitle("Configurador de PCs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        carrito = new ArrayList<>();

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.DARK_GRAY);
        panelSuperior.setLayout(new BorderLayout());

        lblUsuario = new JLabel("Usuario: Invitado");
        lblUsuario.setForeground(Color.WHITE);
        lblCreditos = new JLabel("Creditos: 0.00");
        lblCreditos.setForeground(Color.WHITE);

        panelSuperior.add(lblUsuario, BorderLayout.WEST);
        panelSuperior.add(lblCreditos, BorderLayout.EAST);

        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        JTabbedPane pestanas = new JTabbedPane();

        JPanel panelComponentes = new JPanel();
        panelComponentes.setLayout(new BorderLayout());

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

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");

        tablaComponentes = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaComponentes);
        panelComponentes.add(scrollTabla, BorderLayout.CENTER);

        JPanel panelBotonAgregar = new JPanel();
        
        JButton btnCerrarSesion = new JButton("Cerrar Session");
        btnCerrarSesion.setBackground(new Color(255, 0, 0));
        btnCerrarSesion.addActionListener(e -> {
        	dispose();
        	new loginFrame();
        });
        panelBotonAgregar.add(btnCerrarSesion);
        
        
        
       
        btnAgregarCarrito = new JButton("Agregar al Carrito");
        btnAgregarCarrito.setBackground(new Color(0, 0, 255));
        panelBotonAgregar.add(btnAgregarCarrito);
        panelComponentes.add(panelBotonAgregar, BorderLayout.SOUTH);

        JPanel panelCarritoCompleto = new JPanel();
        panelCarritoCompleto.setLayout(new BorderLayout());

        lblTotal = new JLabel("Total: 0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        panelCarritoCompleto.add(lblTotal, BorderLayout.NORTH);

        panelCarrito = new JPanel();
        panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
        JScrollPane scrollCarrito = new JScrollPane(panelCarrito);
        panelCarritoCompleto.add(scrollCarrito, BorderLayout.CENTER);

        JPanel panelBotonesCarrito = new JPanel();
        btnVaciarCarrito = new JButton("Vaciar Carrito");
        btnVaciarCarrito.setBackground(new Color(255, 128, 128));
        btnComprar = new JButton("Comprar");
        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (carrito.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El carrito está vacío.");
                    return;
                }

                // Pedir nombre de la factura
                String nombreFactura = JOptionPane.showInputDialog("Introduce el nombre de la factura (sin extensión):");
                if (nombreFactura == null || nombreFactura.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nombre de factura no válido.");
                    return;
                }

                // Abrir JFileChooser para seleccionar la carpeta y nombre del archivo
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar factura");
                fileChooser.setSelectedFile(new File(nombreFactura + ".xml"));
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File archivo = fileChooser.getSelectedFile();

                    try {
                        // Crear el documento XML con los datos del carrito
                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                        Document doc = docBuilder.newDocument();
                        Element rootElement = doc.createElement("factura");
                        doc.appendChild(rootElement);

                        // Fecha
                        Element fecha = doc.createElement("fecha");
                        fecha.setTextContent(LocalDate.now().toString());
                        rootElement.appendChild(fecha);

                        // Elementos del carrito
                        Element elementos = doc.createElement("componentes");
                        double total = 0;
                        for (Componente comp : carrito) {
                            Element item = doc.createElement("componente");

                            Element nombre = doc.createElement("nombre");
                            nombre.setTextContent(comp.getNombre());
                            item.appendChild(nombre);

                            Element tipo = doc.createElement("tipo");
                            tipo.setTextContent(comp.gettipo());
                            item.appendChild(tipo);

                            Element precio = doc.createElement("precio");
                            precio.setTextContent(String.valueOf(comp.getPrecio()));
                            item.appendChild(precio);

                            elementos.appendChild(item);
                            total += comp.getPrecio();
                        }
                        rootElement.appendChild(elementos);

                        // Total
                        Element totalElement = doc.createElement("total");
                        totalElement.setTextContent(String.format("%.2f", total));
                        rootElement.appendChild(totalElement);

                        // Guardar XML
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(archivo);
                        transformer.transform(source, result);

                        JOptionPane.showMessageDialog(null, "Factura guardada en:\n" + archivo.getAbsolutePath());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al generar la factura.");
                    }
                }
            }
        });


        
        
        
        
        btnComprar.setBackground(new Color(0, 255, 64));
        panelBotonesCarrito.add(btnVaciarCarrito);
        panelBotonesCarrito.add(btnComprar);
        panelCarritoCompleto.add(panelBotonesCarrito, BorderLayout.SOUTH);

        JPanel panelCreditos = new JPanel();
        panelCreditos.add(new JLabel("Mis Creditos"));

        pestanas.addTab("Componentes", panelComponentes);
        pestanas.addTab("Carrito", panelCarritoCompleto);
        pestanas.addTab("Creditos", panelCreditos);

        getContentPane().add(pestanas, BorderLayout.CENTER);

        configurarEventoAgregarCarrito();
    }

    /**
     * Establece el nombre del usuario mostrado.
     * @param nombre Nombre del usuario.
     */
    public void setNombreUsuario(String nombre) {
        lblUsuario.setText("Usuario: " + nombre);
    }

    /**
     * Establece los créditos del usuario mostrados.
     * @param creditos Cantidad de créditos.
     */
    public void setCreditos(double creditos) {
        lblCreditos.setText("Creditos: " + creditos);
    }

    /**
     * Carga la lista de componentes en la tabla.
     * @param lista Lista de componentes.
     */
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

    /**
     * Agrega un componente al carrito.
     * @param comp Componente a agregar.
     */
    public void agregarComponenteAlCarrito(Componente comp) {
        carrito.add(comp);
        actualizarCarrito();
    }

    /**
     * Actualiza el contenido y el total del carrito.
     */
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

    /**
     * Configura el evento del botón para vaciar el carrito.
     */
    public void configurarEventoAgregarCarrito() {
        btnVaciarCarrito.addActionListener(e -> {
            carrito.clear();
            actualizarCarrito();
        });
    }

    /**
     * Devuelve el combo box de tipos de componentes.
     * @return JComboBox con los tipos.
     */
    public JComboBox<String> getComboTipo() {
        return comboTipo;
    }

    /**
     * Devuelve la tabla de componentes.
     * @return JTable de componentes.
     */
    public JTable getTablaComponentes() {
        return tablaComponentes;
    }

    /**
     * Devuelve el botón de agregar al carrito.
     * @return JButton de agregar.
     */
    public JButton getBtnAgregarCarrito() {
        return btnAgregarCarrito;
    }
    
    
    
    public void generarFacturaXML(List<Componente> carrito, String nombreCliente) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("factura");
            doc.appendChild(root);

            Element cliente = doc.createElement("cliente");
            cliente.setTextContent(nombreCliente);
            root.appendChild(cliente);

            double total = 0.0;

            for (Componente c : carrito) {
                Element item = doc.createElement("componente");

                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(c.getNombre());
                item.appendChild(nombre);

                Element tipo = doc.createElement("tipo");
                tipo.setTextContent(c.gettipo());
                item.appendChild(tipo);

                Element precio = doc.createElement("precio");
                precio.setTextContent(String.valueOf(c.getPrecio()));
                item.appendChild(precio);

                root.appendChild(item);

                total += c.getPrecio();
            }

            Element totalTag = doc.createElement("total");
            totalTag.setTextContent(String.valueOf(total));
            root.appendChild(totalTag);

            // Guardar el XML en disco
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            // Puedes cambiar la ruta si quieres guardar en otro lado
            String nombreArchivo = "factura_" + System.currentTimeMillis() + ".xml";
            StreamResult result = new StreamResult(new File(nombreArchivo));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "Factura guardada como: " + nombreArchivo);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar la factura.");
        }
    }

    
    
    
    
    
    
    
    
    
}
