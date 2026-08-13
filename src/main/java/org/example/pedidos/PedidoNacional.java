package org.example.pedidos;

public class PedidoNacional extends Pedido {

    private final double distanciaKm;
    private final double tarifaFija = 10.0;
    private final double fraccionFija = 0.1;

    public PedidoNacional(int id, String cliente, double monto, double distanciaKm) {
        super(id, cliente, monto);
        this.distanciaKm = distanciaKm;
    }

    @Override
    public double calcularCostoEnvio() {
        return tarifaFija + (monto * fraccionFija * distanciaKm);
    }

    @Override
    public String getTipo() {
        return "PEDIDO NACIONAL";
    }
}
