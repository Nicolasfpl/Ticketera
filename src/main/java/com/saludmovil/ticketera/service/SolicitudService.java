package com.saludmovil.ticketera.service;

import com.saludmovil.ticketera.DTO.SolicitudRequestDTO;
import com.saludmovil.ticketera.DTO.SolicitudResponseDTO;

public interface SolicitudService {
    SolicitudResponseDTO crearSolicitud(
            SolicitudRequestDTO request
        // Validar la solicitud
        );
}
