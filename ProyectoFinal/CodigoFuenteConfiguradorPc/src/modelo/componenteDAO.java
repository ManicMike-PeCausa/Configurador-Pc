package modelo;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase DAO (Data Access Object) para gestionar operaciones relacionadas con los componentes
 * de hardware en la base de datos del sistema PC Builder.
 * Proporciona métodos para obtener, agregar, actualizar y modificar el stock de componentes.
 * 
 * Esta clase se conecta a la base de datos utilizando la clase {@code conexiondb}.
 * 
 * @author Abdelghaffar EL AKHDAR
 * @version 1.6
 * @since 2025
 */
public class componenteDAO {

    /**
     * Obtiene una lista de componentes filtrados por tipo desde la base de datos.
     * 
     * @param tipo El nombre del tipo de componente a buscar (por ejemplo: "GPU", "CPU")
     * @return Lista de objetos {@code Componente} que pertenecen al tipo indicado
     */
    public static List<Componente> obtenerComponentesPorTipo(String tipo) {
        List<Componente> lista = new ArrayList<>();
        try {
            Connection con = conexiondb.conectar();
            String sql = "SELECT c.*, tc.nombre as tipo_nombre FROM componente c " +
                         "JOIN tipocomponente tc ON c.tipo_id = tc.id " +
                         "WHERE tc.nombre = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Componente comp = new Componente();
                comp.setId(rs.getInt("id"));
                comp.setNombre(rs.getString("nombre"));
                comp.setDescripcion(rs.getString("descripcion"));
                comp.setPrecio(rs.getDouble("precio"));
                comp.setStock(rs.getInt("stock"));
                comp.setTipo(rs.getString("tipo_nombre"));
                comp.setSocket(rs.getString("socket"));
                comp.setTipoRam(rs.getString("tipo_ram"));
                comp.setPotenciaRecomendada(rs.getObject("potencia_recomendada") != null ? 
                    rs.getInt("potencia_recomendada") : null);
                comp.setTamanoPlaca(rs.getString("tamano_placa"));
                lista.add(comp);
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza el stock disponible de un componente específico.
     * 
     * @param id ID del componente a actualizar
     * @param nuevoStock Nueva cantidad de stock disponible
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario
     */
    public static boolean actualizarStock(int id, int nuevoStock) {
        try {
            Connection con = conexiondb.conectar();
            String sql = "UPDATE componente SET stock = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nuevoStock);
            ps.setInt(2, id);
            int resultado = ps.executeUpdate();
            con.close();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }

    /**
     * Agrega un nuevo componente a la base de datos.
     * 
     * @param comp Objeto {@code Componente} con los datos del nuevo componente
     * @param tipoId Identificador del tipo de componente (clave foránea)
     * @return {@code true} si el componente se insertó correctamente, {@code false} si hubo error
     */
    public static boolean agregarComponente(Componente comp, int tipoId) {
        try {
            Connection con = conexiondb.conectar();
            String sql = "INSERT INTO componente (nombre, descripcion, precio, stock, tipo_id, " +
                         "socket, tipo_ram, potencia_recomendada, tamano_placa) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, comp.getNombre());
            ps.setString(2, comp.getDescripcion());
            ps.setDouble(3, comp.getPrecio());
            ps.setInt(4, comp.getStock());
            ps.setInt(5, tipoId);
            ps.setString(6, comp.getsocket());
            ps.setString(7, comp.gettipoRam());
            ps.setObject(8, comp.getpotenciaRecomendada());
            ps.setString(9, comp.gettamanoPlaca());

            int resultado = ps.executeUpdate();
            con.close();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza los datos de un componente existente.
     * Solo modifica nombre, descripción, precio y stock.
     * 
     * @param comp Objeto {@code Componente} con los datos actualizados
     * @return {@code true} si la actualización fue exitosa, {@code false} si falló
     */
    public static boolean actualizarComponente(Componente comp) {
        try {
            Connection con = conexiondb.conectar();
            String sql = "UPDATE componente SET nombre=?, descripcion=?, precio=?, stock=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, comp.getNombre());
            ps.setString(2, comp.getDescripcion());
            ps.setDouble(3, comp.getPrecio());
            ps.setInt(4, comp.getStock());
            ps.setInt(5, comp.getId());
            int resultado = ps.executeUpdate();
            con.close();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los componentes registrados en la base de datos, incluyendo su tipo.
     * 
     * @return Lista de todos los objetos {@code Componente} disponibles
     */
    public static List<Componente> obtenerTodosComponentes() {
        List<Componente> lista = new ArrayList<>();
        try {
            Connection con = conexiondb.conectar();
            String sql = "SELECT c.*, tc.nombre as tipo_nombre FROM componente c " +
                         "JOIN tipocomponente tc ON c.tipo_id = tc.id";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Componente comp = new Componente();
                comp.setId(rs.getInt("id"));
                comp.setNombre(rs.getString("nombre"));
                comp.setDescripcion(rs.getString("descripcion"));
                comp.setPrecio(rs.getDouble("precio"));
                comp.setStock(rs.getInt("stock"));
                comp.setTipo(rs.getString("tipo_nombre"));
                lista.add(comp);
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
