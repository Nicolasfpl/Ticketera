package com.saludmovil.ticketera.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 

public class Paciente {
    private String rut;
    private String nombre;
    private String telefono;
    private String direccion;
    private String comuna;

}
