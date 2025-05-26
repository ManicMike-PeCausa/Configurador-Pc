package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import modelo.Componente;
import modelo.componenteDAO;

public class adminFrame extends JFrame {
    // Tabla para mostrar componentes
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    
    // Campos de texto
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JComboBox<String> comboTipo;
    
    // Botones
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnRefrescar;
    
    public adminFrame() {
        // Configurar ventana
        setTitle("Panel de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        // Titulo
        JLabel titulo = new JLabel("ADMINISTRADOR - Gestión de Componentes");
        titulo.setBackground(new Color(192, 192, 192));
        titulo.setBounds(200, 10, 400, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        getContentPane().add(titulo);
        
        // Crear tabla
        crearTabla();
        
        // Crear formulario
        crearFormulario();
        
        // Crear botones
        crearBotones();
        
        // Cargar datos
        cargarDatos();
        
        setVisible(true);
    }
    
    private void crearTabla() {
        // Crear modelo de tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");
        
        // Crear tabla
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 50, 750, 200);
        getContentPane().add(scroll);
        
        // Cuando se selecciona una fila
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                // Poner los datos en los campos
                txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtPrecio.setText(modeloTabla.getValueAt(fila, 3).toString());
                txtStock.setText(modeloTabla.getValueAt(fila, 4).toString());
            }
        });
    }
    
    private void crearFormulario() {
        // Labels
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 280, 100, 25);
        getContentPane().add(lblTipo);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 320, 100, 25);
        getContentPane().add(lblNombre);
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 360, 100, 25);
        getContentPane().add(lblDescripcion);
        
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 400, 100, 25);
        getContentPane().add(lblPrecio);
        
        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(20, 440, 100, 25);
        getContentPane().add(lblStock);
        
        // Campos de texto
        comboTipo = new JComboBox<>();
        comboTipo.setBackground(new Color(128, 0, 255));
        comboTipo.addItem("procesador");
        comboTipo.addItem("placa base");
        comboTipo.addItem("memoria ram");
        comboTipo.addItem("tarjeta gráfica");
        comboTipo.addItem("almacenamiento");
        comboTipo.addItem("fuente de poder");
        comboTipo.addItem("caja");
        comboTipo.setBounds(130, 280, 200, 25);
        getContentPane().add(comboTipo);
        
        txtNombre = new JTextField();
        txtNombre.setBackground(new Color(192, 192, 192));
        txtNombre.setBounds(130, 320, 200, 25);
        getContentPane().add(txtNombre);
        
        txtDescripcion = new JTextField();
        txtDescripcion.setBackground(new Color(192, 192, 192));
        txtDescripcion.setBounds(130, 360, 200, 25);
        getContentPane().add(txtDescripcion);
        
        txtPrecio = new JTextField();
        txtPrecio.setBackground(new Color(192, 192, 192));
        txtPrecio.setBounds(130, 400, 200, 25);
        getContentPane().add(txtPrecio);
        
        txtStock = new JTextField();
        txtStock.setBackground(new Color(192, 192, 192));
        txtStock.setBounds(130, 440, 200, 25);
        getContentPane().add(txtStock);
    }
    
    private void crearBotones() {
        // Boton agregar
        btnAgregar = new JButton("Agregar Nuevo");
        btnAgregar.setBounds(400, 320, 150, 30);
        btnAgregar.setBackground(Color.GREEN);
        getContentPane().add(btnAgregar);
        
        // Boton actualizar
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(400, 360, 150, 30);
        btnActualizar.setBackground(Color.ORANGE);
        getContentPane().add(btnActualizar);
        
        // Boton refrescar
        btnRefrescar = new JButton("Refrescar Tabla");
        btnRefrescar.setBackground(new Color(255, 255, 0));
        btnRefrescar.setBounds(400, 400, 150, 30);
        getContentPane().add(btnRefrescar);
        
        // Boton salir
        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setBounds(600, 500, 150, 30);
        btnSalir.setBackground(Color.RED);
        getContentPane().add(btnSalir);
        
        // Acciones de los botones
        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnSalir.addActionListener(e -> {
            dispose();
            new loginFrame();
        });
    }
    
    private void agregar() {
        // Verificar que los campos no esten vacios
        if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Llena todos los campos");
            return;
        }
        
        try {
            // Crear nuevo componente
            Componente nuevo = new Componente();
            nuevo.setNombre(txtNombre.getText());
            nuevo.setDescripcion(txtDescripcion.getText());
            nuevo.setPrecio(Double.parseDouble(txtPrecio.getText()));
            nuevo.setStock(Integer.parseInt(txtStock.getText()));
            nuevo.setTipo(comboTipo.getSelectedItem().toString());
            
            // Guardar en base de datos
            int tipoId = comboTipo.getSelectedIndex() + 1;
            if (componenteDAO.agregarComponente(nuevo, tipoId)) {
                JOptionPane.showMessageDialog(this, "Componente agregado!");
                limpiar();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Verifica los datos");
        }
    }
    
    private void actualizar() {
        // Verificar que haya una fila seleccionada
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un componente");
            return;
        }
        
        try {
            // Obtener el ID
            int id = (int) modeloTabla.getValueAt(fila, 0);
            
            // Crear componente con datos nuevos
            Componente comp = new Componente();
            comp.setId(id);
            comp.setNombre(txtNombre.getText());
            comp.setDescripcion(txtDescripcion.getText());
            comp.setPrecio(Double.parseDouble(txtPrecio.getText()));
            comp.setStock(Integer.parseInt(txtStock.getText()));
            
            // Actualizar en base de datos
            if (componenteDAO.actualizarComponente(comp)) {
                JOptionPane.showMessageDialog(this, "Actualizado!");
                cargarDatos();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Verifica los datos");
        }
    }
    
    private void cargarDatos() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        // Obtener todos los componentes
        List<Componente> lista = componenteDAO.obtenerTodosComponentes();
        
        // Agregar a la tabla
        for (Componente c : lista) {
            Object[] fila = {
                c.getId(),
                c.getNombre(),
                c.gettipo(),
                c.getPrecio(),
                c.getStock()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void limpiar() {
        // Limpiar todos los campos
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        comboTipo.setSelectedIndex(0);
    }
}