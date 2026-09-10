package com.saludmovil.ticketera.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudVisita {

    private String numeroTicket;

    private Paciente paciente;

    private LocalDate fechaVisita;

    private HorarioVisita horario;

    private List<String> examenes;

    private EstadoSolicitud estado;
}  