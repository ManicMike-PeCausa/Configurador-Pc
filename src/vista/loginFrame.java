package vista;

import javax.swing.*;
import java.awt.*;

public class loginFrame extends JFrame {
    public JTextField campoUsuario;
    public JPasswordField campoContrasena;
    public JButton botonLogin;
    
    public loginFrame() {
        setTitle("Login - PC Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 242, 245));
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 30, 80, 25);
        panel.add(lblUsuario);
        
        campoUsuario = new JTextField();
        campoUsuario.setBounds(140, 30, 200, 25);
        panel.add(campoUsuario);
        
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 70, 80, 25);
        panel.add(lblContrasena);
        
        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(140, 70, 200, 25);
        panel.add(campoContrasena);
        
        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBounds(140, 110, 150, 30);
        botonLogin.setBackground(new Color(52, 152, 219));
        botonLogin.setForeground(Color.WHITE);
        panel.add(botonLogin);
    }
}