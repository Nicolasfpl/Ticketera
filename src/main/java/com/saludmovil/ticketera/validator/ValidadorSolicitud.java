package com.saludmovil.ticketera.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.saludmovil.ticketera.DTO.SolicitudRequestDTO;
import com.saludmovil.ticketera.excepcion.SolicitudInvalidaException;

@Component 
public class ValidadorSolicitud {
    public void validar(
            SolicitudRequestDTO solicitud){

        if (solicitud == null) {
            throw new SolicitudInvalidaException(
                    "La solicitud es obligatoria"
            );
        }
        if (solicitud.getFechaVisita() == null) {
            throw new SolicitudInvalidaException(
                    "La fecha de visita es obligatoria"
            );
        }
        if (solicitud.getFechaVisita().isBefore(LocalDate.now())) {
            throw new SolicitudInvalidaException(
                    "La fecha de visita no puede ser anterior a la fecha de solicitud"
            );
        }
        if (solicitud.getHorario() == null) {
            throw new SolicitudInvalidaException(
                    "El horario de visita es obligatorio"
            );
        }
        if (solicitud.getExamenes() == null || solicitud.getExamenes().isEmpty()) {
            throw new SolicitudInvalidaException(
                    "Debe seleccionar al menos un examen"
            );
        }
        boolean examenVacio = solicitud.getExamenes().stream()
                .anyMatch(examen -> examen == null || examen.isBlank());
        if (examenVacio) {
            throw new SolicitudInvalidaException(
                    "No se pueden seleccionar exámenes vacíos"
            );
        }
    }

}
