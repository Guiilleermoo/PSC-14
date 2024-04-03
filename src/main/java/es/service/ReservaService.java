package es.service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import es.dao.*;
import es.model.*;

public class ReservaService {

    private ReservaRepository reservaRepository;
    private ViajeRepository viajeRepository;
    private ClienteRepository clienteRepository;

    public void crearReserva(Reserva reserva) {
        try {
            reservaRepository.save(reserva);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear la reserva -> %s", e.getMessage()));
        }
    }

    public void crearReserva(int idViaje, String dniCliente, int pasajeros) {
        try {
            Viaje viaje = viajeRepository.findById(idViaje).orElse(null);
    
        if(viaje != null && viaje.getAsientosDisponibles()>=pasajeros) {
            Cliente cliente = clienteRepository.findById(dniCliente).orElse(null);
            if(cliente != null) {
                Reserva reserva = new Reserva();
                reserva.setCliente(cliente);
                reserva.setViaje(viaje);
                reserva.setNumPlazas(pasajeros);
                reservaRepository.save(reserva);

                cliente.getReservas().add(reserva);
                clienteRepository.save(cliente);

                viaje.setAsientosDisponibles(viaje.getAsientosDisponibles()-pasajeros);
                viajeRepository.save(viaje);
                
            } else {
                throw new RuntimeException("Error al buscar el cliente");
            }
        } else {
            throw new RuntimeException("Error al buscar el viaje");
        }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear la reserva -> %s", e.getMessage()));
        }
    }

    public void cancelarReserva(Integer idReserva, String dniCliente) {
        try {
            Reserva reserva = reservaRepository.findById(idReserva).orElse(null);

            if(reserva != null && reserva.getCliente().getDni().equals(dniCliente)) {
                reserva.getCliente().getReservas().remove(reserva);
                clienteRepository.save(reserva.getCliente());

                reserva.getViaje().setAsientosTotales(reserva.getViaje().getAsientosTotales() + reserva.getNumPlazas());
                viajeRepository.save(reserva.getViaje());
                

                reservaRepository.deleteById(idReserva);
            } else {
                throw new RuntimeException("Error al buscar la reserva");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al cancelar la reserva -> %s", e.getMessage()));
        }
    }

    public void actualizarReserva(Reserva reserva) {
        try {
            Reserva reservaDB = reservaRepository.findById(reserva.getIdReserva()).orElse(null);

            if(reservaDB != null) {
                reservaRepository.save(reserva);
            } else {
                throw new RuntimeException("Error al buscar la reserva");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al actualizar la reserva -> %s", e.getMessage()));
        }
    }

    public List<Reserva> obtenerTodasLasReservas() {
        try {
            List<Reserva> reservas = reservaRepository.findAll();
            if(reservas != null) {
                return reservas;
            } else {
                throw new RuntimeException("Error al buscar las reservas");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al obtener las reservas -> %s", e.getMessage()));
        
        }
    }

    public Reserva obtenerReservaPorId(Integer idReserva) {
        try {
            Reserva reserva = reservaRepository.findById(idReserva).orElse(null);
            if(reserva != null) {
                return reserva;
            } else {
                throw new RuntimeException("Error al buscar la reserva");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al obtener la reserva -> %s", e.getMessage()));
        }
    }

    public List<Reserva> buscarReservasPorCliente(Cliente cliente) {
        List<Reserva> reservas = obtenerTodasLasReservas();
        List<Reserva> reservasCliente = new ArrayList<>();

        for(Reserva reserva : reservas) {
            if(reserva.getCliente().equals(cliente)) {
                reservasCliente.add(reserva);
            }
        }

        return reservasCliente;
    }

    public List<Reserva> buscarReservasPorViaje(Viaje viaje) {
        List<Reserva> reservas = obtenerTodasLasReservas();
        List<Reserva> reservasViaje = new ArrayList<>();

        for(Reserva reserva : reservas) {
            if(reserva.getViaje().equals(viaje)) {
                reservasViaje.add(reserva);
            }
        }

        return reservasViaje;
    }

    public List<Reserva> buscarReservasPorFecha(LocalDateTime fecha) {
        List<Reserva> reservas = obtenerTodasLasReservas();
        List<Reserva> reservasFecha = new ArrayList<>();

        for(Reserva reserva : reservas) {
            if(reserva.getViaje().getFecha().equals(fecha)) {
                reservasFecha.add(reserva);
            }
        }

        return reservasFecha;
    }

}
