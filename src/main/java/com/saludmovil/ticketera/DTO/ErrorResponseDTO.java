package com.saludmovil.ticketera.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class ErrorResponseDTO {
    private String error;
    private LocalDateTime Fecha;
}
