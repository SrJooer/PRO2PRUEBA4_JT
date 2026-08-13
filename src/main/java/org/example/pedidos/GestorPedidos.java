package org.example.pedidos;

public class GestorPedidos {

    private final Pedido[] pedidos;

    private int cantidad;

    public GestorPedidos(int size) {
        pedidos = new Pedido[size];
        cantidad = 0;
    }

    public void agregar(Pedido pedido) throws CapacidadException {
        if (cantidad < pedidos.length) {
            pedidos[cantidad] = pedido;
            cantidad++;
        } else {
            throw new CapacidadException(pedidos.length);
        }
    }
}