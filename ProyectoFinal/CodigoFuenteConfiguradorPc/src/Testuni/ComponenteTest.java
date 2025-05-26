package Testuni;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Componente;

public class ComponenteTest {

    @Test
    public void testGetSetNombre() {
        Componente componente = new Componente();
        componente.setNombre("Intel Core i9");
        assertEquals("Intel Core i9", componente.getNombre());
    }

    @Test
    public void testGetSetPrecio() {
        Componente componente = new Componente();
        componente.setPrecio(499.99);
        assertEquals(499.99, componente.getPrecio(), 0.01); // margen de error para double
    }
}