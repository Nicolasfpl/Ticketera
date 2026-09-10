package com.saludmovil.ticketera.excepcion;

public class SolicitudInvalidaException 
        extends RuntimeException {

    public SolicitudInvalidaException(String mensaje) {
        super(mensaje);
    }

}
