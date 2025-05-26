package controlador;

import modelo.conexiondb;
import modelo.usuario;
import vista.loginFrame;
import javax.swing.*;
import java.sql.*;

public class logincontroller {
    private loginFrame loginFrame;

    public logincontroller(loginFrame frame) {
        this.loginFrame = frame;
        
        frame.botonLogin.addActionListener(e -> {
            String user = frame.campoUsuario.getText();
            String pass = String.valueOf(frame.campoContrasena.getPassword());
            autenticar(user, pass);
        });
    }

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