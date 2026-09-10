package com.saludmovil.ticketera.validator;

import org.springframework.stereotype.Component;

import com.saludmovil.ticketera.excepcion.SolicitudInvalidaException;
import com.saludmovil.ticketera.model.Paciente;

@Component 
public class ValidadorPaciente {
    public void validar(Paciente paciente){
        if (paciente ==null) {
            throw new SolicitudInvalidaException(
                    "El paciente es obligatorio"
            );
        }
        if (paciente.getNombre() == null || paciente.getNombre().isBlank()) {
            throw new SolicitudInvalidaException(
                    "El nombre del paciente es obligatorio"
            );
        }
        if (paciente.getRut() == null || paciente.getRut().isBlank()) {
            throw new SolicitudInvalidaException(
                    "El RUT del paciente es obligatorio"
            );
        }
        if (paciente.getTelefono() == null || paciente.getTelefono().isBlank()) {
            throw new SolicitudInvalidaException(
                    "El teléfono del paciente es obligatorio"
            );
        }
        if (paciente.getDireccion() == null || paciente.getDireccion().isBlank()) {
            throw new SolicitudInvalidaException(
                    "La dirección del paciente es obligatoria"
            );
        }
        if (paciente.getComuna() == null || paciente.getComuna().isBlank()) {
            throw new SolicitudInvalidaException(
                    "La comuna del paciente es obligatoria"
            );
        }
    }
}