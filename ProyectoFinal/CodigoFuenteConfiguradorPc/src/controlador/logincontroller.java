package controlador;

import modelo.conexiondb;
import modelo.usuario;
import vista.loginFrame;
import javax.swing.*;
import java.sql.*;

/**
 * Controlador encargado de gestionar la autenticación del usuario
 * desde la interfaz gráfica de inicio de sesión.
 * 
 * <p>Verifica las credenciales en la base de datos y redirige al usuario
 * al panel correspondiente según su rol (admin o usuario estándar).</p>
 * 
 * <p>Además, gestiona los eventos del botón de login.</p>
 * 
 * @author Abdelghaffar EL AKHDAR 
 * @version 1.6
 * @since 2025
 */
public class logincontroller {
    private loginFrame loginFrame;

    /**
     * Constructor que asocia el controlador con la vista de login.
     * 
     * @param frame Vista de inicio de sesión.
     */
    public logincontroller(loginFrame frame) {
        this.loginFrame = frame;
        
        frame.botonLogin.addActionListener(e -> {
            String user = frame.campoUsuario.getText();
            String pass = String.valueOf(frame.campoContrasena.getPassword());
            autenticar(user, pass);
        });
    }

    /**
     * Método privado que se encarga de autenticar al usuario en la base de datos.
     * 
     * @param nombreUsuario Nombre de usuario introducido.
     * @param contrasena Contraseña introducida.
     */
    private void autenticar(String nombreUsuario, String contrasena) {
        try {
            Connection conn = conexiondb.conectar();
            String sql = "SELECT * FROM Usuario WHERE nombre_usuario = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario usuario = new usuario(
                    rs.getInt("id"),
                    nombreUsuario,
                    contrasena,
                    rs.getDouble("creditos"),
                    rs.getString("rol")
                );

                loginFrame.dispose();

                if ("admin".equalsIgnoreCase(usuario.getRol())) {
                    new adminController(usuario);
                } else {
                    new maincontroller(usuario);
                }
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Usuario o contraseña incorrectos");
            }

            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(loginFrame, "Error de conexión");
        }
    }
}
