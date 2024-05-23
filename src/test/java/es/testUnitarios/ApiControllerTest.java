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
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    @Mock
    private ReservaRepository reservaRepository;



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
        // Preparar datos de prueba
        Viaje viaje1 = new Viaje();
        Viaje viaje2 = new Viaje();
        Reserva reserva1 = new Reserva();
        reserva1.setViaje(viaje1);
        Reserva reserva2 = new Reserva();
        reserva2.setViaje(viaje2);
        List<Reserva> reservas = Arrays.asList(reserva1, reserva2);

        // Simular el comportamiento del reservaRepository
        when(reservaRepository.findAll()).thenReturn(reservas);

        // Llamar a la función a probar
        ResponseEntity<String> response = apiController.getMapa();

        // Verificar que la respuesta es la esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mapa ordenado de los viajes más reservados por los clientes", response.getBody());
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