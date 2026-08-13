package org.example.pedidos;

public enum EstadoPedido {
    PENDIENTE("Pedido registrado, esperando procesamiento", false, 1),
    PROCESANDO("Pedido siendo preparado", false, 2),
    ENVIANDO("Pedido camino al cliente", false, 3),
    ENTREGADO("Pedido entregado al cliente", true, 0),
    CANCELADO("Pedido cancelado", true, 0);

    private final String description;
    private final boolean esFinal;
    private final int diasEstimados;

    EstadoPedido(String desc, boolean esFinal, int dias) {
        this.description = desc;
        this.esFinal = esFinal;
        this.diasEstimados = dias;
    }

    public boolean esFinal() { return esFinal; }

    public boolean transicionarA(EstadoPedido nuevoEstado) {
        if (this.esFinal) return false;
        boolean valido = false;

        switch (this) {
            case PENDIENTE:
                if (nuevoEstado == PROCESANDO || nuevoEstado == CANCELADO)
                    valido = true;
                break;
            case PROCESANDO:
                if (nuevoEstado == ENVIANDO || nuevoEstado == CANCELADO)
                    valido = true;
                break;
            case ENVIANDO:
                if (nuevoEstado == ENTREGADO)
                    valido = true;
                break;
            default:
                valido = false;
                break;
        }
        return valido;
    }
}
