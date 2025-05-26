package modelo;

/**
 * Clase que representa un componente de PC en el sistema.
 * Almacena toda la información necesaria de cada componente
 * incluyendo características técnicas para validar compatibilidad.
 * 
 * @author Abdelghaffar EL AKHDAR
 * @version 1.6
 * @since 2025
 */
public class Componente {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String tipo;
    private String socket;
    private String tipoRam;
    private Integer potenciaRecomendada;
    private String tamanoPlaca;

    /**
     * Obtiene el ID único del componente.
     * @return El identificador del componente en la base de datos
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Establece el ID del componente.
     * @param id El nuevo identificador a asignar
     */
    public void setId(int id) { 
        this.id = id; 
    }
    
    /**
     * Obtiene el nombre del componente.
     * @return El nombre comercial del componente
     */
    public String getNombre() { 
        return nombre; 
    }
    
    /**
     * Establece el nombre del componente.
     * @param nombre El nuevo nombre a asignar
     */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    
    /**
     * Obtiene la descripción detallada del componente.
     * @return La descripción del componente
     */
    public String getDescripcion() { 
        return descripcion; 
    }
    
    /**
     * Establece la descripción del componente.
     * @param descripcion La nueva descripción a asignar
     */
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }
    
    /**
     * Obtiene el precio del componente.
     * @return El precio en euros
     */
    public double getPrecio() { 
        return precio; 
    }
    
    /**
     * Establece el precio del componente.
     * @param precio El nuevo precio en euros
     */
    public void setPrecio(double precio) { 
        this.precio = precio; 
    }
    
    /**
     * Obtiene el stock disponible del componente.
     * @return La cantidad en stock
     */
    public int getStock() { 
        return stock; 
    }
    
    /**
     * Establece el stock del componente.
     * @param stock La nueva cantidad en stock
     */
    public void setStock(int stock) { 
        this.stock = stock; 
    }
    
    /**
     * Obtiene el tipo de componente.
     * @return El tipo (procesador, placa base, etc.)
     */
    public String gettipo() { 
        return tipo; 
    }
    
    /**
     * Establece el tipo de componente.
     * @param tipo El nuevo tipo a asignar
     */
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }
    
    /**
     * Obtiene el socket del componente (para CPU y placas base).
     * @return El tipo de socket o null si no aplica
     */
    public String getsocket() { 
        return socket; 
    }
    
    /**
     * Establece el socket del componente.
     * @param socket El nuevo socket a asignar
     */
    public void setSocket(String socket) { 
        this.socket = socket; 
    }
    
    /**
     * Obtiene el tipo de RAM soportado (para placas base y RAM).
     * @return El tipo de RAM o null si no aplica
     */
    public String gettipoRam() { 
        return tipoRam; 
    }
    
    /**
     * Establece el tipo de RAM del componente.
     * @param tipoRam El nuevo tipo de RAM a asignar
     */
    public void setTipoRam(String tipoRam) { 
        this.tipoRam = tipoRam; 
    }
    
    /**
     * Obtiene la potencia recomendada (para fuentes de poder).
     * @return La potencia en watts o null si no aplica
     */
    public Integer getpotenciaRecomendada() { 
        return potenciaRecomendada; 
    }
    
    /**
     * Establece la potencia recomendada del componente.
     * @param potencia La nueva potencia en watts
     */
    public void setPotenciaRecomendada(Integer potencia) { 
        this.potenciaRecomendada = potencia; 
    }
    
    /**
     * Obtiene el tamaño de placa soportado (para cajas).
     * @return El tamaño de placa o null si no aplica
     */
    public String gettamanoPlaca() { 
        return tamanoPlaca; 
    }
    
    /**
     * Establece el tamaño de placa del componente.
     * @param tamanoPlaca El nuevo tamaño a asignar
     */
    public void setTamanoPlaca(String tamanoPlaca) { 
        this.tamanoPlaca = tamanoPlaca; 
    }
}