package controlador;

import modelo.usuario;
import vista.adminFrame;

public class adminController {
    private adminFrame frame;
    private usuario admin;

    public adminController(usuario usuario) {
        this.admin = usuario;
        this.frame = new adminFrame();
        frame.setVisible(true);
    }
}