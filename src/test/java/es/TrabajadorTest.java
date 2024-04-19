package es;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.model.Trabajador;

public class TrabajadorTest {

    private Trabajador trabajador;

    @Before
    public void setUp() {
        trabajador = new Trabajador();
        trabajador.setDni("78955017L");
        trabajador.setNombre("Unai Aira");
        trabajador.setGmail("unaiaira@gmail.com");
        trabajador.setTelefono("666444555");
        trabajador.setSueldo(1000);
        trabajador.setPassword("contra");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("78955017L", trabajador.getDni());
        assertEquals("Unai Aira", trabajador.getNombre());
        assertEquals("unaiaira@gmail.com", trabajador.getGmail());
        assertEquals("666444555", trabajador.getTelefono());
        assertEquals(1000, trabajador.getSueldo());
        assertEquals("contra", trabajador.getPassword());
        
    }

    
}
