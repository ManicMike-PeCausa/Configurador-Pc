package modelo;

public class ComponenteDebug {
    public static void main(String[] args) {
        Componente componente = new Componente();

        componente.setNombre("Procesador Ryzen 7");
        componente.setDescripcion("8 núcleos, 16 hilos");
        componente.setPrecio(299.99);
        componente.setStock(10);

        String nombre = componente.getNombre(); // ← aquí pon un breakpoint
        System.out.println("Nombre: " + nombre);
    }
}
