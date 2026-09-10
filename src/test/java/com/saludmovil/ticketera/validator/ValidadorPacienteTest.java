package com.saludmovil.ticketera.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.saludmovil.ticketera.excepcion.SolicitudInvalidaException;
import com.saludmovil.ticketera.model.Paciente;

public class ValidadorPacienteTest {

    private final ValidadorPaciente validador =
            new ValidadorPaciente();

    @Test
    void pacienteValidoNoDebeLanzarExcepcion() {

        // Arrange
        Paciente paciente = Paciente.builder()
                .rut("12345678-9")
                .nombre("Maria Perez")
                .telefono("+56912345678")
                .direccion("Los Pinos 123")
                .comuna("Vina del Mar")
                .build();

        // Act + Assert
        assertDoesNotThrow(
                () -> validador.validar(paciente)
        );
    }

    @Test
    void nombreVacioDebeLanzarExcepcion() {

        // Arrange
        Paciente paciente = Paciente.builder()
                .rut("12345678-9")
                .nombre("")
                .telefono("+56912345678")
                .direccion("Los Pinos 123")
                .comuna("Vina del Mar")
                .build();

        // Act + Assert
        assertThrows(
                SolicitudInvalidaException.class,
                () -> validador.validar(paciente)
        );
    }

    @Test
    void rutVacioDebeLanzarExcepcion() {

        // Arrange
        Paciente paciente = Paciente.builder()
                .rut("")
                .nombre("Maria Perez")
                .telefono("+56912345678")
                .direccion("Los Pinos 123")
                .comuna("Vina del Mar")
                .build();

        // Act + Assert
        assertThrows(
                SolicitudInvalidaException.class,
                () -> validador.validar(paciente)
        );
    }
}