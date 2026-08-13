package org.example.pedidos;

public abstract class Pedido {

    protected final int id;
    protected final String cliente;
    protected final double monto;
    protected EstadoPedido estado;

    public Pedido(int id, String cliente, double monto) {
        this.id = id;
        this.cliente = cliente;
        this.monto = monto;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public double getMonto() { return monto; }
    public EstadoPedido getEstado() { return estado; }

    public abstract double calcularCostoEnvio();
    public abstract String getTipo();

    public void cambiarEstado(EstadoPedido nuevoEstado) throws TransicionEstadoInvalidaException {
        if (estado.transicionarA(nuevoEstado)) {
            estado = nuevoEstado;
        } else {
            throw new TransicionEstadoInvalidaException(estado, nuevoEstado);
        }
    }

    public String toString() {
        return "ID: " + id
                + ", Tipo: " + getTipo()
                + ", Cliente: " + cliente
                + ", Monto: " + monto
                + ", Estado: " + estado
                + ", Costo de envío: " + calcularCostoEnvio();
    }

}
