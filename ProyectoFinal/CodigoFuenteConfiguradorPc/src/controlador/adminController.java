package controlador;

import modelo.usuario;
import vista.adminFrame;

/**
 * Controlador para la vista del panel de administración.
 * 
 * <p>
 * Este controlador se encarga de inicializar la interfaz de administrador y
 * pasarle el usuario autenticado.
 * </p>
 * 
 * @author Abdelghaffar EL AKHDAR
 * @version 1.6
 * @since 2025
 */
public class adminController {
	private adminFrame frame;
	private usuario admin;

	/**
	 * Constructor que inicializa el frame de administrador.
	 * 
	 * @param usuario Usuario autenticado con rol de administrador.
	 */
	public adminController(usuario usuario) {
		this.admin = usuario;
		this.frame = new adminFrame();
		frame.setVisible(true);
	}
}
