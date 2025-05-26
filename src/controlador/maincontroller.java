package controlador;

import modelo.usuario;
import modelo.Componente;
import modelo.componenteDAO;
import vista.mainframe;
import javax.swing.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador principal para los usuarios estándar.
 * 
 * <p>Gestiona la carga de componentes, su visualización en la interfaz y
 * la interacción del usuario con el carrito de compras.</p>
 * 
 * <p>Se encarga también de mapear los nombres de tipo de componente para
 * facilitar su obtención desde la base de datos.</p>
 * 
 * @author Abdelghaffar EL AKHDAR 
 * @version 1.6
 * @since 2025
 */
public class maincontroller {
    private mainframe vista;
    private usuario usuario;
    private Map<String, String> mapeoTipos;
    private List<Componente> componentesActuales;

    /**
     * Constructor que inicializa la interfaz principal del usuario.
     * 
     * @param usuario Usuario autenticado.
     */
    public maincontroller(usuario usuario) {
        this.usuario = usuario;
        this.vista = new mainframe();
        
        mapeoTipos = new HashMap<>();
        mapeoTipos.put("Procesador", "procesador");
        mapeoTipos.put("Placa Base", "placa base");
        mapeoTipos.put("Memoria RAM", "memoria ram");
        mapeoTipos.put("Tarjeta Gráfica", "tarjeta gráfica");
        mapeoTipos.put("Almacenamiento", "almacenamiento");
        mapeoTipos.put("Fuente de Poder", "fuente de poder");
        mapeoTipos.put("Caja", "caja");
        
        vista.setNombreUsuario(usuario.getNombreUsuario());
        vista.setCreditos(usuario.getCreditos());
        
        cargarComponentes("Procesador");

        vista.getComboTipo().addActionListener(e -> {
            String tipo = (String) vista.getComboTipo().getSelectedItem();
            cargarComponentes(tipo);
        });

        vista.getBtnAgregarCarrito().addActionListener(e -> {
            agregarAlCarrito();
        });

        vista.setVisible(true);
    }

    /**
     * Carga los componentes disponibles de un tipo específico desde la base de datos.
     * 
     * @param tipo Tipo visible en la interfaz (ej. "Procesador", "Placa Base").
     */
    private void cargarComponentes(String tipo) {
        String tipoDb = mapeoTipos.get(tipo);
        if (tipoDb != null) {
            componentesActuales = componenteDAO.obtenerComponentesPorTipo(tipoDb);
            vista.cargarComponentes(componentesActuales);
        }
    }

    /**
     * Agrega el componente seleccionado al carrito, si está disponible.
     */
    private void agregarAlCarrito() {
        int fila = vista.getTablaComponentes().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un componente");
            return;
        }

        Componente comp = componentesActuales.get(fila);

        if (comp.getStock() <= 0) {
            JOptionPane.showMessageDialog(vista, "Sin stock");
            return;
        }

        vista.agregarComponenteAlCarrito(comp);
        JOptionPane.showMessageDialog(vista, comp.getNombre() + " agregado!");
    }
}
