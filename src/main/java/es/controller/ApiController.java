package es.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.dao.ReservaRepository;
import es.model.Reserva;
import es.model.Viaje;

@RestController
public class ApiController {

    ReservaRepository reservaRepository;

    public ApiController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

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

    @GetMapping("/api/getMapa")
    public ResponseEntity<Map<Viaje, Integer>> getMapa() {
        try {
            Map<Viaje, Integer> mapa = new HashMap<Viaje, Integer>();
            List<Reserva> reservas = reservaRepository.findAll();

            for(Reserva reserva : reservas) {
                if(mapa.containsKey(reserva.getViaje())) {
                    mapa.put(reserva.getViaje(), mapa.get(reserva.getViaje()) + 1);
                } else {
                    mapa.put(reserva.getViaje(), 1);
                }
            }

            return new ResponseEntity<>(mapa, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el mapa" + e.getMessage());
        }
    }

}
