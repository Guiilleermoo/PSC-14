package es.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @PostMapping("/api/registrarCambios")
    public void registrarCambios(@RequestBody String mensaje) {
        String nombreArchivo = "logCambios.txt";
        String[] mensajeArray = mensaje.split("\"");
        String mensajeFinal = mensajeArray[3].toString();
        
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(mensajeFinal + "\n");

            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo de log" + e.getMessage());
        }
    }

}
