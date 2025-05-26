package controlador;

import modelo.usuario;
import modelo.Componente;
import modelo.componenteDAO;
import vista.mainframe;
import javax.swing.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class maincontroller {
    private mainframe vista;
    private usuario usuario;
    private Map<String, String> mapeoTipos;
    private List<Componente> componentesActuales;

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

    private void cargarComponentes(String tipo) {
        String tipoDb = mapeoTipos.get(tipo);
        if (tipoDb != null) {
            componentesActuales = componenteDAO.obtenerComponentesPorTipo(tipoDb);
            vista.cargarComponentes(componentesActuales);
        }
    }

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