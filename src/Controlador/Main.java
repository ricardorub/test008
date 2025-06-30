package Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Modelo.Cliente.ListaSimpleClientes;
import Modelo.Habitacion.ListaCircularHabitaciones;
import Modelo.Reservas.ListaDobleReservas;
import Modelo.Historial.PilaHistorialReserva;
import Modelo.Reservas.ConsultasReservas;
import Vista.PanelReserva;
import Modelo.CheckOut.ArbolCheckOut;    

import Vista.PanelReserva;
import Vista.PanelCheckOut; 
import Vista.PanelPrincipal;

import Modelo.Cliente.Cliente;
import Modelo.Habitacion.Habitacion;
import Modelo.Reservas.Reserva;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        // Initialize data structures
        ListaDobleReservas listaReservas = new ListaDobleReservas();
        PilaHistorialReserva pilaHistorial = new PilaHistorialReserva();
        ListaSimpleClientes listaCliente = new ListaSimpleClientes(); // Keep for other controllers if needed
        ListaCircularHabitaciones cirularHabitaciones = new ListaCircularHabitaciones();
        ArbolCheckOut arbolCheckOut = new ArbolCheckOut();
        // ConsultasReservas consultas = new ConsultasReservas(); // This was duplicated by consultasReservas below. Remove one.
        
        ConsultasReservas consultasReservas = new ConsultasReservas(); // If needed by other controllers
       
        // Populate some data (example)
        Habitacion h101 = new Habitacion(101, "Simple", true); 
        Habitacion h102 = new Habitacion(102, "Doble", true);  
        Habitacion h201 = new Habitacion(201, "Suite", true); 
        cirularHabitaciones.agregarAlFinal(h101);
        cirularHabitaciones.agregarAlFinal(h102);
        cirularHabitaciones.agregarAlFinal(h201);
        
        // Cliente cliente1 = new Cliente("12345678A", "Juan Perez", 5); // Example data
        // Cliente cliente2 = new Cliente("87654321B", "Ana Lopez", 2); // Example data
        // listaCliente.agregarAlFinal(cliente1); // Example data for listaCliente
        // listaCliente.agregarAlFinal(cliente2); // Example data for listaCliente

        // Create PanelPrincipal with necessary dependencies for PanelCheckOut controller
        PanelPrincipal vista = new PanelPrincipal(arbolCheckOut, listaReservas, cirularHabitaciones, pilaHistorial);
        
        // TODO: ControladorReservas and ControladorTareas were associated with PanelReserva.
        // If PanelReserva is opened from PanelPrincipal, its controller setup will need
        // to happen in PanelPrincipal's actionPerformed for the Reserva button, passing these dependencies.
        // Example of what might be needed in PanelPrincipal for Reserva button:
        // PanelReserva panelReserva = new PanelReserva();
        // ControladorReservas ctrlReservas = new ControladorReservas(panelReserva, this.pilaHistorial, this.listaTotalReservas, /*consultas*/, /*listaCliente*/, this.listaHabitaciones);
        // new ControladorTareas(panelReserva); // If ControladorTareas is still relevant
        // panelReserva.setVisible(true);
        // this.dispose();
        
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
}


