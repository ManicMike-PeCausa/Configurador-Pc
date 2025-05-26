package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que gestiona el carrito de compra del usuario.
 * Permite agregar componentes validando su compatibilidad
 * y calcula el total de la compra.
 * 
 * @author Abdelghaffar EL AKHDAR
 * @version 1.6
 * @since 2025
 */
public class Carrito {
    /** Lista de componentes en el carrito */
    private List<Componente> componentes = new ArrayList<>();
    
    /** Socket de la placa base para validar compatibilidad */
    private String socketBase = null;
    
    /** Tipo de RAM de la placa base para validar compatibilidad */
    private String tipoRamBase = null;

    /**
     * Agrega un componente al carrito validando compatibilidad.
     * Si es una placa base, establece los parámetros de compatibilidad.
     * 
     * @param comp El componente a agregar
     * @return true si se agregó correctamente, false si hay incompatibilidad
     */
    public boolean agregarComponente(Componente comp) {
        // Verificar si es placa base
        if ("placa base".equalsIgnoreCase(comp.gettipo())) {
            if (socketBase == null) {
                socketBase = comp.getsocket();
                tipoRamBase = comp.gettipoRam();
                componentes.add(comp);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Ya hay una placa base en el carrito.");
                return false;
            }
        }

        // Verificar compatibilidad de socket
        if (socketBase != null && comp.getsocket() != null && !socketBase.equals(comp.getsocket())) {
            JOptionPane.showMessageDialog(null, "Socket incompatible.");
            return false;
        }

        // Verificar compatibilidad de RAM
        if (tipoRamBase != null && comp.gettipoRam() != null && !tipoRamBase.equals(comp.gettipoRam())) {
            JOptionPane.showMessageDialog(null, "RAM incompatible.");
            return false;
        }

        componentes.add(comp);
        return true;
    }

    /**
     * Obtiene la lista de componentes en el carrito.
     * @return Lista de componentes
     */
    public List<Componente> getComponentes() {
        return componentes;
    }
    
    /**
     * Vacía el carrito eliminando todos los componentes.
     * También resetea los parámetros de compatibilidad.
     */
    public void vaciar() {
        componentes.clear();
        socketBase = null;
        tipoRamBase = null;
    }
    
    /**
     * Calcula el precio total de todos los componentes en el carrito.
     * @return El precio total en euros
     */
    public double getTotal() {
        double total = 0;
        for (Componente c : componentes) {
            total += c.getPrecio();
        }
        return total;
    }
}