package com.saludmovil.ticketera.DTO;

import java.time.LocalDate;
import java.util.List;

import com.saludmovil.ticketera.model.EstadoSolicitud;
import com.saludmovil.ticketera.model.HorarioVisita;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class SolicitudResponseDTO {

    private String numeroTicket;
    private String nombrePaciente;
    private LocalDate fechaVisita;
    private HorarioVisita horario;
    private List<String> examenes;
    private EstadoSolicitud estado;
    private String mensaje;
    

}
