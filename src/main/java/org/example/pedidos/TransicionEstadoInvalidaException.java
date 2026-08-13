package org.example.pedidos;

public class TransicionEstadoInvalidaException extends Exception {

    public TransicionEstadoInvalidaException(EstadoPedido estadoActual, EstadoPedido estadoNuevo) {
        super("No se puede cambiar de estado desde " + estadoActual + " a " + estadoNuevo
        + " (estado actual final: " + estadoActual.esFinal() + ")");
    }
}
