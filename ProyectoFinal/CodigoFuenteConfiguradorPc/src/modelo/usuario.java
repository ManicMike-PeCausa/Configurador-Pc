package modelo;


/**
 * Clase que representa un usuario del sistema PC Builder.
 * Gestiona la información de los usuarios incluyendo sus credenciales,
 * créditos disponibles y rol en el sistema.
 * 
 * @author Abdelghaffar EL AKHDAR	
 * @version 1.6
 * @since 2025
 */



public class usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private double creditos;
    private String rol;

    /**
     * Constructor de la clase Usuario.
     * Crea un nuevo usuario con todos sus datos.
     * 
     * @param id Identificador único del usuario
     * @param nombreUsuario Nombre de usuario para el login
     * @param contrasena Contraseña del usuario
     * @param creditos Créditos disponibles para compras
     * @param rol Rol del usuario (cliente o admin)
     */ 
    
    public usuario(int id, String nombreUsuario, String contrasena, double creditos, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.creditos = creditos;
        this.rol = rol;
    }

    /**
     * Obtiene el ID del usuario.
     * @return El identificador único del usuario
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario usado para login
     */
    public String getNombreUsuario() { 
        return nombreUsuario; 
    }
    
    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario
     */
    public String getContrasena() { 
        return contrasena; 
    }
    
    /**
     * Obtiene los créditos disponibles del usuario.
     * @return La cantidad de créditos en euros
     */
    public double getCreditos() { 
        return creditos; 
    }
    
    /**
     * Obtiene el rol del usuario en el sistema.
     * @return El rol (admin o cliente)
     */
    public String getRol() { 
        return rol; 
    }
    
    /**
     * Establece nuevos créditos para el usuario.
     * @param creditos Nueva cantidad de créditos
     */
    public void setCreditos(double creditos) { 
        this.creditos = creditos; 
    }
}