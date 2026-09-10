package com.saludmovil.ticketera.service;

import org.springframework.stereotype.Service;

import com.saludmovil.ticketera.DTO.SolicitudRequestDTO;
import com.saludmovil.ticketera.DTO.SolicitudResponseDTO;
import com.saludmovil.ticketera.generator.GeneradorTicket;
import com.saludmovil.ticketera.model.EstadoSolicitud;
import com.saludmovil.ticketera.model.SolicitudVisita;
import com.saludmovil.ticketera.validator.ValidadorPaciente;
import com.saludmovil.ticketera.validator.ValidadorSolicitud;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl
        implements SolicitudService {

    private final ValidadorPaciente validadorPaciente;

    private final ValidadorSolicitud validadorSolicitud;

    private final GeneradorTicket generadorTicket;

    @Override
    public SolicitudResponseDTO crearSolicitud(
            SolicitudRequestDTO request) {

        validadorSolicitud.validar(request);

        validadorPaciente.validar(
                request.getPaciente()
        );

        String numeroTicket =
                generadorTicket.generar();

        SolicitudVisita solicitud =
                SolicitudVisita.builder()
                        .numeroTicket(numeroTicket)
                        .paciente(
                                request.getPaciente()
                        )
                        .fechaVisita(
                                request.getFechaVisita()
                        )
                        .horario(
                                request.getHorario()
                        )
                        .examenes(
                                request.getExamenes()
                        )
                        .estado(
                                EstadoSolicitud.RECIBIDA
                        )
                        .build();

        return SolicitudResponseDTO.builder()
                .numeroTicket(
                        solicitud.getNumeroTicket()
                )
                .nombrePaciente(
                        solicitud
                                .getPaciente()
                                .getNombre()
                )
                .fechaVisita(
                        solicitud.getFechaVisita()
                )
                .horario(
                        solicitud.getHorario()
                )
                .examenes(
                        solicitud.getExamenes()
                )
                .estado(
                        solicitud.getEstado()
                )
                .mensaje(
                        "Solicitud registrada correctamente"
                )
                .build();
    }
}