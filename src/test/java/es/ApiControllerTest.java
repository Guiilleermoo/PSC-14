package es;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.controller.ApiController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private ApiController apiController;

    @Mock
    private FileWriter fileWriterMock;

    @Mock
    private BufferedWriter bufferedWriter;

    @Test
    void testRegistrarCambios() throws Exception {
        String mensaje = "{\"data\": \"Este es un mensaje de prueba.\"}";
        mvc.perform(MockMvcRequestBuilders.post("/api/registrarCambios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mensaje));

        // Verificar si el mensaje se ha registrado correctamente en el archivo de log
        String contenidoArchivo = new String(Files.readAllBytes(Paths.get("logCambios.txt")));
        assertTrue(contenidoArchivo.contains("Este es un mensaje de prueba."), "El mensaje se registró correctamente en el archivo de log.");
    }
}