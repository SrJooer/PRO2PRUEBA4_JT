package org.example.pedidos;

public class PedidoInternacional extends Pedido {

    private final double tarifaFija = 20.0;
    private final double costoAduana;
    private final String destino;

    public PedidoInternacional(int id, String cliente, double monto, double costoAduana, String destino) {
        super(id, cliente, monto);
        this.costoAduana = costoAduana;
        this.destino = destino;
    }

    @Override
    public double calcularCostoEnvio() {
        return tarifaFija + costoAduana;
    }

    @Override
    public String getTipo() {
        return "PEDIDO INTERNACIONAL PAIS: " + destino;
    }

    @Override
    public String toString() {
        return super.toString().concat(", País de destino: " + destino);
    }
}
