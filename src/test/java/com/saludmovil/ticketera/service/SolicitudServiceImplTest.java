package com.saludmovil.ticketera.service;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.saludmovil.ticketera.DTO.SolicitudRequestDTO;
import com.saludmovil.ticketera.DTO.SolicitudResponseDTO;
import com.saludmovil.ticketera.excepcion.SolicitudInvalidaException;
import com.saludmovil.ticketera.generator.GeneradorTicket;
import com.saludmovil.ticketera.model.EstadoSolicitud;
import com.saludmovil.ticketera.model.HorarioVisita;
import com.saludmovil.ticketera.model.Paciente;
import com.saludmovil.ticketera.validator.ValidadorPaciente;
import com.saludmovil.ticketera.validator.ValidadorSolicitud;

public class SolicitudServiceImplTest {

    private SolicitudServiceImpl servicio;

    @BeforeEach
    void prepararServicio() {

        servicio = new SolicitudServiceImpl(
                new ValidadorPaciente(),
                new ValidadorSolicitud(),
                new GeneradorTicket()
        );
    }

    @Test
    void solicitudValidaDebeCrearTicket() {

        // ARRANGE
        Paciente paciente = Paciente.builder()
                .rut("12345678-9")
                .nombre("Maria Perez")
                .telefono("+56912345678")
                .direccion("Los Pinos 123")
                .comuna("Vina del Mar")
                .build();

        SolicitudRequestDTO request = SolicitudRequestDTO.builder()
                .paciente(paciente)
                .fechaVisita(LocalDate.now().plusDays(2))
                .horario(HorarioVisita.MANANA)
                .examenes(List.of(
                        "HEMOGRAMA",
                        "GLICEMIA"
                ))
                .build();

        // ACT
        SolicitudResponseDTO respuesta =
                servicio.crearSolicitud(request);

        // ASSERT
        assertNotNull(respuesta);

        assertNotNull(
                respuesta.getNumeroTicket()
        );

        assertTrue(
                respuesta.getNumeroTicket()
                        .startsWith(
                                "SM-" + LocalDate.now().getYear()
                        )
        );

        assertEquals(
                EstadoSolicitud.RECIBIDA,
                respuesta.getEstado()
        );

        assertEquals(
                "Maria Perez",
                respuesta.getNombrePaciente()
        );

        assertEquals(
                "Solicitud registrada correctamente",
                respuesta.getMensaje()
        );
    }

    @Test
    void solicitudConNombreVacioDebeLanzarExcepcion() {

        // ARRANGE
        Paciente paciente = Paciente.builder()
                .rut("12345678-9")
                .nombre("")
                .telefono("+56912345678")
                .direccion("Los Pinos 123")
                .comuna("Vina del Mar")
                .build();

        SolicitudRequestDTO request = SolicitudRequestDTO.builder()
                .paciente(paciente)
                .fechaVisita(LocalDate.now().plusDays(2))
                .horario(HorarioVisita.MANANA)
                .examenes(List.of("HEMOGRAMA"))
                .build();

        // ACT + ASSERT
        assertThrows(
                SolicitudInvalidaException.class,
                () -> servicio.crearSolicitud(request)
        );
    }

    @Test
    void solicitudConFechaPasadaDebeLanzarExcepcion() {

        // ARRANGE
        Paciente paciente = Paciente.builder()
                .rut("12345678-9")
                .nombre("Maria Perez")
                .telefono("+56912345678")
                .direccion("Los Pinos 123")
                .comuna("Vina del Mar")
                .build();

        SolicitudRequestDTO request = SolicitudRequestDTO.builder()
                .paciente(paciente)
                .fechaVisita(LocalDate.now().minusDays(1))
                .horario(HorarioVisita.TARDE)
                .examenes(List.of("HEMOGRAMA"))
                .build();

        // ACT + ASSERT
        assertThrows(
                SolicitudInvalidaException.class,
                () -> servicio.crearSolicitud(request)
        );
    }
}