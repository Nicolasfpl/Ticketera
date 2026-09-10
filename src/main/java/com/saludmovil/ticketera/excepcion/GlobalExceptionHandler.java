package com.saludmovil.ticketera.excepcion;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.saludmovil.ticketera.DTO.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
              SolicitudInvalidaException.class
    )
    public ResponseEntity<ErrorResponseDTO> 
            manejarSolicitudInvalida(
                    SolicitudInvalidaException exception) {

        ErrorResponseDTO error = 
                ErrorResponseDTO.builder() 
                        .error(
                                exception.getMessage()
                        ) 
                        .Fecha(
                                LocalDateTime.now()
                        )    
                        .build();
        
        return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
    }
    

}
