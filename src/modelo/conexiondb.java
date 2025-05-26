package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * Clase encargada de establecer la conexión con la base de datos MySQL del sistema PC Builder.
 * Utiliza JDBC para conectarse a la base de datos configurada localmente.
 * 
 * <p>Esta clase carga el driver de MySQL y proporciona un método estático para obtener una conexión activa.</p>
 * 
 * <b>Nota:</b> Las credenciales están codificadas directamente por simplicidad, pero en producción
 * se recomienda usar variables de entorno o un archivo de configuración externo.
 * 
 * @author  
 * @version 1.0
 * @since 2025
 */



public class conexiondb {
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        }
        
        String url = "jdbc:mysql://localhost:3306/ConfiguradorPC";
        String usuario = "root";
        String contrasena = "Nigga3.44";
        
        return DriverManager.getConnection(url, usuario, contrasena);
    }
    
    
    /**
     * Establece y devuelve una conexión activa a la base de datos.
     * 
     * @return Objeto {@link Connection} conectado a la base de datos.
     * @throws SQLException si ocurre un error durante la carga del driver o la conexión.
     */
    
    
    
    
}