package modelo;


/**
* Clase encargada de verificar la compatibilidad entre dos componentes del sistema.
* 
* <p>Actualmente valida compatibilidad entre sockets y tipo de RAM. 
* Si ambos atributos están presentes en ambos componentes, se comparan para determinar si son compatibles.</p>
* 
* <p>Este validador puede utilizarse antes de agregar componentes a un sistema personalizado
* para garantizar que no se produzcan incompatibilidades técnicas.</p>
* 
* <b>Compatibilidades verificadas:</b>
* <ul>
*   <li>{@code socket}: Ambos componentes deben tener el mismo tipo de socket (por ejemplo, CPU y placa base).</li>
*   <li>{@code tipoRam}: Ambos componentes deben usar el mismo tipo de RAM (por ejemplo, DDR4).</li>
* </ul>
* 
* @author Abdelghaffar EL AKHDAR 
* @version 1.6
* @since 2025
*/



public class ValidadorCompatibilidad {
    public static boolean esCompatible(Componente c1, Componente c2) {
        if (c1.getsocket() != null && c2.getsocket() != null) {
            if (!c1.getsocket().equals(c2.getsocket())) {
                return false;
            }
        }
        
        if (c1.gettipoRam() != null && c2.gettipoRam() != null) {
            if (!c1.gettipoRam().equals(c2.gettipoRam())) {
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * Verifica si dos componentes son compatibles según su socket y tipo de RAM.
     * 
     * @param c1 Primer componente a comparar.
     * @param c2 Segundo componente a comparar.
     * @return {@code true} si ambos componentes son compatibles; {@code false} si hay alguna incompatibilidad.
     */
    
    
    
}