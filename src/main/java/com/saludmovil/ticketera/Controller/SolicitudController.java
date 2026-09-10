package com.saludmovil.ticketera.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saludmovil.ticketera.DTO.SolicitudRequestDTO;
import com.saludmovil.ticketera.DTO.SolicitudResponseDTO;
import com.saludmovil.ticketera.service.SolicitudService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    @PostMapping
    public ResponseEntity<SolicitudResponseDTO>
            crearSolicitud(
                    @RequestBody
                    SolicitudRequestDTO request) {

        SolicitudResponseDTO respuesta =
                solicitudService
                        .crearSolicitud(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }
}