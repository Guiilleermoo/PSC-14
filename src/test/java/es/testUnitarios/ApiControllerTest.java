package es.testUnitarios;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.controller.ApiController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

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
}