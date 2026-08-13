package org.example.pedidos;

public class CapacidadException extends Exception{
    public CapacidadException(int capacidad) {
        super("Capacidad máxima alcanzada (" + capacidad + " pedidos)");
    }
}
