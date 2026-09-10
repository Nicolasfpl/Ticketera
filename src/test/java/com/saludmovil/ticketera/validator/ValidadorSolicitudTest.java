package com.saludmovil.ticketera.validator;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.saludmovil.ticketera.DTO.SolicitudRequestDTO;
import com.saludmovil.ticketera.excepcion.SolicitudInvalidaException;
import com.saludmovil.ticketera.model.HorarioVisita;
import com.saludmovil.ticketera.model.Paciente;

public class ValidadorSolicitudTest {

    private final ValidadorSolicitud validador =
            new ValidadorSolicitud();

    @Test
    void solicitudValidaNoDebeLanzarExcepcion() {

        // Arrange
        Paciente paciente = Paciente.builder()
                .rut("12345678-9")
                .nombre("Maria Perez")
                .telefono("+56912345678")
                .direccion("Los Pinos 123")
                .comuna("Vina del Mar")
                .build();

        SolicitudRequestDTO solicitud =
                SolicitudRequestDTO.builder()
                        .paciente(paciente)
                        .fechaVisita(LocalDate.now().plusDays(2))
                        .horario(HorarioVisita.MANANA)
                        .examenes(List.of(
                                "HEMOGRAMA",
                                "GLICEMIA"
                        ))
                        .build();

        // Act + Assert
        assertDoesNotThrow(
                () -> validador.validar(solicitud)
        );
    }

    @Test
    void fechaPasadaDebeLanzarExcepcion() {

        // Arrange
        SolicitudRequestDTO solicitud =
                SolicitudRequestDTO.builder()
                        .fechaVisita(LocalDate.now().minusDays(1))
                        .horario(HorarioVisita.MANANA)
                        .examenes(List.of("HEMOGRAMA"))
                        .build();

        // Act + Assert
        assertThrows(
                SolicitudInvalidaException.class,
                () -> validador.validar(solicitud)
        );
    }

    @Test
    void fechaActualDebeSerAceptada() {

        // Arrange
        SolicitudRequestDTO solicitud =
                SolicitudRequestDTO.builder()
                        .fechaVisita(LocalDate.now())
                        .horario(HorarioVisita.MANANA)
                        .examenes(List.of("HEMOGRAMA"))
                        .build();

        // Act + Assert
        assertDoesNotThrow(
                () -> validador.validar(solicitud)
        );
    }

    @Test
    void horarioNuloDebeLanzarExcepcion() {

        // Arrange
        SolicitudRequestDTO solicitud =
                SolicitudRequestDTO.builder()
                        .fechaVisita(LocalDate.now().plusDays(1))
                        .horario(null)
                        .examenes(List.of("HEMOGRAMA"))
                        .build();

        // Act + Assert
        assertThrows(
                SolicitudInvalidaException.class,
                () -> validador.validar(solicitud)
        );
    }

    @Test
    void examenesVaciosDebeLanzarExcepcion() {

        // Arrange
        SolicitudRequestDTO solicitud =
                SolicitudRequestDTO.builder()
                        .fechaVisita(LocalDate.now().plusDays(1))
                        .horario(HorarioVisita.TARDE)
                        .examenes(List.of())
                        .build();

        // Act + Assert
        assertThrows(
                SolicitudInvalidaException.class,
                () -> validador.validar(solicitud)
        );
    }

    @Test
    void examenEnBlancoDebeLanzarExcepcion() {

        // Arrange
        SolicitudRequestDTO solicitud =
                SolicitudRequestDTO.builder()
                        .fechaVisita(LocalDate.now().plusDays(1))
                        .horario(HorarioVisita.TARDE)
                        .examenes(List.of(""))
                        .build();

        // Act + Assert
        assertThrows(
                SolicitudInvalidaException.class,
                () -> validador.validar(solicitud)
        );
    }
}