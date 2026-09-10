package com.saludmovil.ticketera.generator;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class GeneradorTicket {

    private final AtomicInteger correlativo = new AtomicInteger(0);

    public String generar() {

        int numero = correlativo.incrementAndGet();

        int anio = LocalDate.now().getYear();

        return String.format(
                "SM-%d-%05d",
                anio,
                numero
        );
    }
}