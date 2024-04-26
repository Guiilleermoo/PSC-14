package es;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.model.Trabajador;
import jakarta.persistence.Id;

public class TrabajadorTest {
    @Id
    protected String dni=null;
    protected String nombre=null;
    protected String gmail=null;
    protected String telefono=null;
    protected int sueldo=0; 
    protected String password=null; 
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
    @Test
    public void testEquals() {
        // Crear dos trabajadores con los mismos atributos
        Trabajador trabajador1 = new Trabajador();
        trabajador1.setDni("78955017L");
        trabajador1.setNombre("Unai Aira");
        trabajador1.setGmail("unaiaira@gmail.com");
        trabajador1.setTelefono("666444555");
        trabajador1.setSueldo(1000);
        trabajador1.setPassword("contra");

        Trabajador trabajador2 = new Trabajador();
        trabajador2.setDni("78955017L");
        trabajador2.setNombre("Unai Aira");
        trabajador2.setGmail("unaiaira@gmail.com");
        trabajador2.setTelefono("666444555");
        trabajador2.setSueldo(1000);
        trabajador2.setPassword("contra");

        // Verificar que los dos trabajadores sean iguales
        assertTrue(trabajador1.equals(trabajador2));
        assertTrue(trabajador2.equals(trabajador1));

        // Cambiar un atributo de uno de los trabajadores
        trabajador2.setSueldo(1200);

        // Verificar que los trabajadores ya no sean iguales
        assertFalse(trabajador1.equals(trabajador2));
        assertFalse(trabajador2.equals(trabajador1));
    }

    @Test
    public void testHashCode() {
        // Crear dos trabajadores con los mismos atributos
        Trabajador trabajador1 = new Trabajador();
        trabajador1.setDni("78955017L");
        trabajador1.setNombre("Unai Aira");
        trabajador1.setGmail("unaiaira@gmail.com");
        trabajador1.setTelefono("666444555");
        trabajador1.setSueldo(1000);
        trabajador1.setPassword("contra");

        Trabajador trabajador2 = new Trabajador();
        trabajador2.setDni("78955017L");
        trabajador2.setNombre("Unai Aira");
        trabajador2.setGmail("unaiaira@gmail.com");
        trabajador2.setTelefono("666444555");
        trabajador2.setSueldo(1000);
        trabajador2.setPassword("contra");

        // Verificar que los hash codes de los trabajadores sean iguales
        assertEquals(trabajador1.hashCode(), trabajador2.hashCode());

        // Cambiar un atributo de uno de los trabajadores
        trabajador2.setSueldo(1200);

        // Verificar que los hash codes de los trabajadores ya no sean iguales
        assertNotEquals(trabajador1.hashCode(), trabajador2.hashCode());

    }
    @Test
    public void testToString() {
        String expectedToString = "Trabajador(" +
                "dni=78955017L" +
                ", nombre=Unai Aira" +
                ", gmail=unaiaira@gmail.com" +
                ", telefono=666444555" +
                ", sueldo=1000" +
                ", password=contra" +
                ')';
        assertEquals(expectedToString, trabajador.toString());
    }
    

   

}