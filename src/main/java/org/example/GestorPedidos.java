package org.example;

import org.example.pedidos.*;

public class GestorPedidos {

    private Pedido[] pedidos = new Pedido[10];
    int cantidad = 0;

    public void agregarPedido(Pedido p) throws CapacidadException {
        if (cantidad < pedidos.length) {
            pedidos[cantidad] = p;
            cantidad++;
        } else {
            throw new CapacidadException(pedidos.length);
        }
    }

    public Pedido buscarPedidoPorId(int id) throws PedidoNoEncontradoException {
        for (int i = 0; i < cantidad; i++) {
            if (pedidos[i].getId() == id) return pedidos[i];
        }
        throw new PedidoNoEncontradoException(id);
    }

    public void cambiarEstado(int id, EstadoPedido nuevoEstado) throws PedidoNoEncontradoException, TransicionEstadoInvalidaException {
        Pedido p = buscarPedidoPorId(id);
        p.cambiarEstado(nuevoEstado);
    }

    public Pedido[] listarPorEstado(EstadoPedido estado) {
        int contador = 0;
        for (int i = 0; i < cantidad; i++) {
            if (pedidos[i].getEstado() == estado) {
                contador++;
            }
        }

        Pedido[] pedidosFiltrados = new Pedido[contador];

        int indice = 0;
        for (int i = 0; i < cantidad; i++) {
            if (pedidos[i].getEstado() == estado) {
                pedidosFiltrados[indice] = pedidos[i];
                indice++;
            }
        }

        return pedidosFiltrados;
    }
}
