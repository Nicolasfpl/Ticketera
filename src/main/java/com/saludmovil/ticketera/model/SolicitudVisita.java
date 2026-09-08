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

    private String numeroticket;
    private Paciente paciente;
    private LocalDate FechaVisita;
    private HorarioVisita Horario;
    private List<String> examenes;
    private EstadoSolicitud estado;
    
}
