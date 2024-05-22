package es.testUnitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.controller.ApiController;
import es.dao.ReservaRepository;
import es.model.Reserva;
import es.model.Viaje;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    @Mock
    private ReservaRepository reservaRepository;

    private List<Reserva> reservas;
    private Viaje viaje1;
    private Viaje viaje2;

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private ApiController apiController;

    @Mock
    private FileWriter fileWriterMock;

    @Mock
    private BufferedWriter bufferedWriter;

    private static final String LOG_FILE_PATH = "logCambios.txt";

    @AfterEach
    void cleanup() throws IOException {
        // Eliminar el contenido del archivo después de cada prueba
        Files.write(Paths.get(LOG_FILE_PATH), new byte[0]);
    }

    @Test
    void testRegistrarCambios() throws Exception {
        String mensaje = "{\"data\": \"Este es un mensaje de prueba.\"}";
        mvc.perform(MockMvcRequestBuilders.post("/api/registrarCambios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mensaje));

        // Verificar si el mensaje se ha registrado correctamente en el archivo de log
        String contenidoArchivo = new String(Files.readAllBytes(Paths.get(LOG_FILE_PATH)));
        assertTrue(contenidoArchivo.contains("Este es un mensaje de prueba."), "El mensaje se registró correctamente en el archivo de log.");
    }

    @Test
    public void testGetMapa() {
        viaje1 = new Viaje();
        viaje1.setId(1);
        viaje1.setOrigen("Barcelona");
        viaje1.setDestino("Madrid");
        viaje2 = new Viaje();
        viaje2.setId(2);
        viaje2.setOrigen("Madrid");
        viaje2.setDestino("Barcelona");

        Reserva reserva1 = new Reserva();
        reserva1.setId(1);
        reserva1.setViaje(viaje1); 
        Reserva reserva2 = new Reserva();
        reserva2.setId(2);
        reserva2.setViaje(viaje1);
        Reserva reserva3 = new Reserva();
        reserva3.setId(3);
        reserva3.setViaje(viaje2);

        reservas = Arrays.asList(reserva1, reserva2, reserva3);

        // Simular el comportamiento del repositorio
        when(reservaRepository.findAll()).thenReturn(reservas);

        // Llamar al método del controlador
        ResponseEntity<Map<Viaje, Integer>> response = apiController.getMapa();

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que el mapa devuelto tiene el tamaño correcto
        Map<Viaje, Integer> expectedMap = new HashMap<>();
        expectedMap.put(viaje1, 2);
        expectedMap.put(viaje2, 1);

        assertEquals(expectedMap, response.getBody());
    }

    @Test
    public void testGetMapaThrowsException() {
        // Simular un error en el repositorio
        when(reservaRepository.findAll()).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método del controlador y verificar que lanza una excepción
        Exception exception = assertThrows(RuntimeException.class, () -> {
            apiController.getMapa();
        });

        // Verificar que el mensaje de la excepción es el esperado
        assertEquals("Error al obtener el mapaError en el repositorio", exception.getMessage());
    }
}