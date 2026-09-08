package com.saludmovil.ticketera.DTO;

import java.time.LocalDate;
import java.util.List;

import com.saludmovil.ticketera.model.HorarioVisita;
import com.saludmovil.ticketera.model.Paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class SolicitudRequestDTO {
    private Paciente paciente;
    private LocalDate fechaVisita;
    private HorarioVisita horario;
    private List<String> examenes;

}
