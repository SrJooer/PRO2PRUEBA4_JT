package org.example.pedidos;

public class PedidoNoEncontradoException extends Exception{
    public PedidoNoEncontradoException(int id) {
        super("No se encontro el pedido con id: " + id);
    }
}
