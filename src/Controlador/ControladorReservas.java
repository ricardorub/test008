/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente.Cliente;
import Modelo.Cliente.ListaSimpleClientes;
import Modelo.Habitacion.Habitacion;
import Modelo.Habitacion.ListaCircularHabitaciones;
import Modelo.Historial.PilaHistorialReserva;
import Modelo.Nodo;
import Modelo.Reservas.ConsultasReservas;
import Modelo.Reservas.ListaDobleReservas;
import Modelo.Reservas.Reserva;
import Vista.PanelReserva;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Alicia Huamani
 */
public class ControladorReservas {

    PanelReserva vista;
    ListaDobleReservas lista;
    PilaHistorialReserva pila;
    ConsultasReservas consul;
    ListaSimpleClientes listaCliente;
    ListaCircularHabitaciones cirularHabitaciones;

    public ControladorReservas(PanelReserva vista, PilaHistorialReserva pila, ListaDobleReservas lista, ConsultasReservas consul, ListaSimpleClientes listaCliente, ListaCircularHabitaciones cirularHabitaciones) {
        this.vista = vista;
        this.lista = lista;
        this.pila = pila;
        this.consul = consul;
        this.listaCliente = listaCliente;
        this.cirularHabitaciones = cirularHabitaciones;

        Cliente cliente1 = new Cliente("12345678A", "Juan Pérez", 5);
        Cliente cliente2 = new Cliente("23456789B", "Ana Gómez", 2);
        Cliente cliente3 = new Cliente("34567890C", "Carlos López", 10);
        Cliente cliente4 = new Cliente("45678901D", "Laura Martínez", 1);
        Cliente cliente5 = new Cliente("123", "lala", 1);
        listaCliente.agregarAlFinal(cliente5);

        Habitacion habitacion1 = new Habitacion(101, "Individual", true);
        Habitacion habitacion2 = new Habitacion(102, "Doble", false);
        Habitacion habitacion3 = new Habitacion(201, "Suite", true);
        Habitacion habitacion4 = new Habitacion(202, "Triple", true);
        Habitacion habitacion6 = new Habitacion(203, "segund", false);
        cirularHabitaciones.agregarAlFinal(habitacion6);

        Reserva reserva1 = new Reserva(1, cliente1, habitacion1, "2025-07-01");
        Reserva reserva2 = new Reserva(2, cliente2, habitacion2, "2025-07-05");
        Reserva reserva3 = new Reserva(3, cliente3, habitacion3, "2025-07-10");
        Reserva reserva4 = new Reserva(4, cliente4, habitacion4, "2025-07-15");

        lista.agregarAlFinal(reserva2);
        lista.agregarAlFinal(reserva3);
        lista.agregarAlFinal(reserva4);
        lista.agregarAlFinal(reserva1);

        actualizarTabla(vista.getTablePrincipalR());
        actualizarTabla(vista.getTableConsultaR());

        vista.getBtnAgregarR().addActionListener(e -> agregarReserva());
        vista.getBtnEditar().addActionListener(e -> editarReserva());
        vista.getBtnEliminarR().addActionListener(e -> eliminarReserva());
        vista.getBtnDeshacer().addActionListener(e -> deshacerUltimaModificacion());
        vista.getbtnActualizar().addActionListener(e -> actualizarTabla(pila.ArreglodeDatos(), vista.getTableHistoriallR()));
        vista.getBtnSecuencialR().addActionListener(e -> buscarPorNombreSecuencial());
        vista.getBtnOrdenarR().addActionListener(e -> ordenarPorIdMerge());
        vista.getBtnBinariaR().addActionListener(e -> buscarPorIdBinario());
    }

    private void actualizarTabla(JTable tabla) {
        int totalReservas = lista.contar();
        String[][] datos = new String[totalReservas][6];
        int i = 0;

        Nodo actual = lista.getInicio();
        while (actual != null) {
            Reserva r = (Reserva) actual.getDato();
            datos[i][0] = String.valueOf(r.getId());
            datos[i][1] = r.getCliente().getDni();
            datos[i][2] = r.getCliente().getNombre();
            datos[i][3] = String.valueOf(r.getHabitacion().getNumero());
            datos[i][4] = r.getHabitacion().getTipo();
            datos[i][5] = r.getFecha();
            actual = actual.getSiguiente();
            i++;
        }
        vista.mostrar(datos, tabla);
    }

    private void agregarReserva() {
        Cliente c = listaCliente.buscarPorDni(vista.getTxtDniCliente().getText());

        if (c == null) {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado.");
            return;  
        }

        Habitacion h = (Habitacion) cirularHabitaciones.buscarPorId(Integer.parseInt(vista.getTxtIdHabitacion().getText()));

        if (h == null) {
            JOptionPane.showMessageDialog(vista, "Habitación no encontrada.");
            return;  
        }
        
        Reserva r = new Reserva(Integer.parseInt(vista.getTxtIdReservas().getText()), c, h, vista.getTxtFecha().getText());
        lista.agregarAlFinal(r);

        actualizarTabla(vista.getTablePrincipalR());
    }

    private void editarReserva() {
        int id = Integer.parseInt(vista.getTxtIdReservas().getText());
        Reserva reservaOriginal = (Reserva) lista.buscarPorId(id);

        if (reservaOriginal != null) {
            pila.push(new Reserva(reservaOriginal));

            reservaOriginal.setFecha(vista.getTxtFecha().getText());

            actualizarTabla(vista.getTablePrincipalR());
            actualizarTabla(pila.ArreglodeDatos(), vista.getTableHistoriallR());

            JOptionPane.showMessageDialog(vista, "Reserva editada correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo editar la Reserva");

        }
    }

    private void eliminarReserva() {
        int id = Integer.parseInt(vista.getTxtIdReservas().getText());
        Reserva reservaEliminar = (Reserva) lista.buscarPorId(id);

        if (reservaEliminar != null) {
            pila.push(new Reserva(reservaEliminar));

            lista.eliminarPorId(id);

            actualizarTabla(vista.getTablePrincipalR());
            actualizarTabla(pila.ArreglodeDatos(), vista.getTableHistoriallR());

            JOptionPane.showMessageDialog(vista, "Reserva eliminada correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "Reserva no encontrada.");
        }
    }

    private void buscarPorNombreSecuencial() {
        Reserva encontrada = consul.buscarPorNombreSecuencial(lista, vista.getTxtBusquedaCliente().getText());
        if (encontrada != null) {
            Object[] resultado = new Object[]{encontrada};
            actualizarTabla(resultado, vista.getTableConsultaR());
        } else {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado.");
        }

    }

    private void ordenarPorIdMerge() {
        consul.ordenarPorIdMergeSort(lista);
        actualizarTabla(vista.getTableConsultaR());
    }

    private void buscarPorIdBinario() {
        Reserva encontrada = consul.buscarPorIdBinaria(lista, Integer.parseInt(vista.gettxtidConsultasR().getText()));
        if (encontrada != null) {
            Object[] resultado = new Object[]{encontrada};
            actualizarTabla(resultado, vista.getTableConsultaR());
        } else {
            JOptionPane.showMessageDialog(vista, "Reserva no encontrada por ID.");
        }
    }

    private void deshacerUltimaModificacion() {
        if (!pila.estaVacio()) {
            Reserva reservaRestaurada = (Reserva) pila.pop();

            lista.eliminarPorId(reservaRestaurada.getId());

            lista.agregarAlFinal(reservaRestaurada);

            actualizarTabla(vista.getTablePrincipalR());

            JOptionPane.showMessageDialog(vista, "Reserva restaurada desde historial");
        } else {
            JOptionPane.showMessageDialog(vista, "No hay historial disponible.");
        }
    }

    private void actualizarTabla(Object[] objetos, JTable tabla) {
        String[][] datos = new String[objetos.length][6];
        for (int i = 0; i < objetos.length; i++) {
            Reserva r = (Reserva) objetos[i];
            datos[i][0] = String.valueOf(r.getId());
            datos[i][1] = r.getCliente().getDni();
            datos[i][2] = r.getCliente().getNombre();
            datos[i][3] = String.valueOf(r.getHabitacion().getNumero());
            datos[i][4] = r.getHabitacion().getTipo();
            datos[i][5] = r.getFecha();
        }
        vista.mostrar(datos, tabla);
    }
}
