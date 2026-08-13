package org.example;

import org.example.pedidos.*;

import java.util.Scanner;

public class Main {

    private static GestorPedidos gestorPedidos = new GestorPedidos();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = 0;
        do {
            n = mostrarMenu();
            ejecutarAccion(n);
        } while (n != 5);
    }

    private static void ejecutarAccion(int n) {
        switch (n) {
            case 1:
                agregarPedido();
            break;
            case 2:
                buscarPedidoPorId();
            break;
            case 3:
                cambiarEstado();
            break;
            case 4:
                listarPorEstado();
            break;
            default:
                System.out.println("Se ha salido del programa");
            break;
        }
    }

    private static void agregarPedido() {
        System.out.println("[!] Ingrese el id del pedido: ");
        int id = scanner.nextInt();
        System.out.println("[!] Ingrese el nombre del cliente: ");
        String cliente = scanner.next();
        System.out.println("[!] Ingrese el monto del pedido: ");
        double monto = scanner.nextDouble();
        System.out.println("[!] Ingrese el tipo de pedido [NACIONAL/INTERNACIONAL]: ");

        Pedido pedido;

        if (scanner.next().equals("NACIONAL")) {
            System.out.println("[!] Ingrese la distancia en km: ");
            double distanciaKm = scanner.nextDouble();
            pedido = new PedidoNacional(id, cliente, monto, distanciaKm);
        } else {
            System.out.println("[!] Ingrese el costo de aduana: ");
            double costoAduana = scanner.nextDouble();
            System.out.println("[!] Ingrese el país de destino: ");
            String destino = scanner.next();
            pedido = new PedidoInternacional(id, cliente, monto, costoAduana, destino);
        }

        try {
            gestorPedidos.agregarPedido(pedido);
        } catch (CapacidadException e) {
            System.out.println("[!] HUBO UN ERROR AL AGREGAR EL PEDIDO:");
            e.printStackTrace();
        }
    }

    private static void buscarPedidoPorId() {
        System.out.println("Ingrese el id del pedido a buscar: ");
        int id = scanner.nextInt();

        Pedido pedido;

        try {
            pedido = gestorPedidos.buscarPedidoPorId(id);
        } catch (PedidoNoEncontradoException e) {
            System.out.println("[!] HUBO UN ERROR AL BUSCAR EL PEDIDO:");
            e.printStackTrace();
            return;
        }

        System.out.println("[!] PEDIDO ENCONTRADO: ");
        System.out.println(pedido.toString());
    }

    private static void cambiarEstado() {
        System.out.println("[!] Ingrese el id del pedido a cambiar de estado: ");
        int id = scanner.nextInt();
        System.out.println("[!] Ingrese el nuevo estado del pedido: ");
        EstadoPedido nuevoEstado = EstadoPedido.valueOf(scanner.next());

        try {
            gestorPedidos.cambiarEstado(id, nuevoEstado);
        } catch (Exception e) {
            System.out.println("[!] HUBO UN ERROR AL CAMBIAR EL ESTADO DEL PEDIDO:");
            e.printStackTrace();
        }
    }

    private static void listarPorEstado() {
        System.out.println("[1] Listar por PENDIENTE");
        System.out.println("[2] Listar por PROCESANDO");
        System.out.println("[3] Listar por ENVIANDO");
        System.out.println("[4] Listar por ENTREGADO");
        System.out.println("[5] Listar por CANCELADO");
        System.out.println("[!] Ingrese una opcion: ");
        int n = scanner.nextInt();

        switch (n) {
            case 1:
                System.out.println("[!] Listado de pedidos PENDIENTES: ");
                imprimirLista(gestorPedidos.listarPorEstado(EstadoPedido.PENDIENTE));
            break;
            case 2:
                System.out.println("[!] Listado de pedidos PROCESANDO: ");
                imprimirLista(gestorPedidos.listarPorEstado(EstadoPedido.PROCESANDO));
            break;
            case 3:
                System.out.println("[!] Listado de pedidos ENVIANDO: ");
                imprimirLista(gestorPedidos.listarPorEstado(EstadoPedido.ENVIANDO));
            break;
            case 4:
                System.out.println("[!] Listado de pedidos ENTREGADO: ");
                imprimirLista(gestorPedidos.listarPorEstado(EstadoPedido.ENTREGADO));
            break;
            case 5:
                System.out.println("[!] Listado de pedidos CANCELADO: ");
                imprimirLista(gestorPedidos.listarPorEstado(EstadoPedido.CANCELADO));
            break;
            default:
                System.out.println(("[!] No se ha seleccionado ningun estado"));
            break;
        }
    }

    private static void imprimirLista(Pedido[] pedidos) {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString());
        }
    }

    private static int mostrarMenu() {
        System.out.println("-------------- [MENU]  --------------");
        System.out.println("[1] Agregar pedido");
        System.out.println("[2] Buscar pedido por id");
        System.out.println("[3] Cambiar estado de un pedido");
        System.out.println("[4] Listar pedidos por estado");
        System.out.println("[5] Salir");
        System.out.println("-------------- [MENU]  --------------");
        System.out.println("[!] Ingrese una opcion: ");
        int n = scanner.nextInt();
        return n;
    }
}
