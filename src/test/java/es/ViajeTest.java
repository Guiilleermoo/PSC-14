package es;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.model.Viaje;

public class ViajeTest {

    private Viaje viaje;

    @Before
    public void setUp() {
        viaje = new Viaje();
        viaje.setId(1);
        viaje.setOrigen("Bilbao");
        viaje.setDestino("Madrid");
        viaje.setFecha("2022-12-31");
        viaje.setDuracion(120);
        viaje.setPrecio(100.0);
        viaje.setOferta(10);
        viaje.setEmpresa("Deusto");
        viaje.setAsientosTotales(100);
        viaje.setAsientosDisponibles(100);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, viaje.getId());
        assertEquals("Bilbao", viaje.getOrigen());
        assertEquals("Madrid", viaje.getDestino());
        assertEquals("2022-12-31", viaje.getFecha());
        assertEquals(120, viaje.getDuracion());
        assertEquals(100.0, viaje.getPrecio(), 0.01);
        assertEquals(10, viaje.getOferta());
        assertEquals("Deusto", viaje.getEmpresa());
        assertEquals(100, viaje.getAsientosTotales());
        assertEquals(100, viaje.getAsientosDisponibles()); 
    }

    @Test
    public void testCalcularPrecioFinal() {
        int precioFinal = viaje.calcularPrecioFinal();

        assertEquals(90, precioFinal); // 100 - 10%
    }
}
