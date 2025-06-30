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
        PanelReserva vista = new PanelReserva();
        ListaDobleReservas listaReservas = new ListaDobleReservas();
        PilaHistorialReserva pilaHistorial = new PilaHistorialReserva();
        ConsultasReservas consultas = new ConsultasReservas();
        ListaSimpleClientes listaCliente = new ListaSimpleClientes();
        ListaCircularHabitaciones cirularHabitaciones = new ListaCircularHabitaciones();
        ArbolCheckOut arbolCheckOut = new ArbolCheckOut();
        
        ConsultasReservas consultasReservas = new ConsultasReservas();
       
        Habitacion h101 = new Habitacion(101, "Simple", true); 
        Habitacion h102 = new Habitacion(102, "Doble", true);  
        Habitacion h201 = new Habitacion(201, "Suite", true); 
        cirularHabitaciones.agregarAlFinal(h101);
        cirularHabitaciones.agregarAlFinal(h102);
        cirularHabitaciones.agregarAlFinal(h201);
        
        new ControladorReservas(vista, pilaHistorial, listaReservas, consultas,listaCliente,cirularHabitaciones);
        new ControladorTareas(vista);

        Cliente cliente1 = new Cliente("12345678A", "Juan Perez", 5);
        Cliente cliente2 = new Cliente("87654321B", "Ana Lopez", 2);
        
         
        /////ver luego
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
}
