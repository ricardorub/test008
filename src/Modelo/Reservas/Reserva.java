/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Reservas;

import Modelo.Cliente.Cliente;
import Modelo.Habitacion.Habitacion;

/**
 *
 * @author USUARIO
 */
public class Reserva {
    int id;
    Cliente cliente;
    Habitacion habitacion;
    String fecha;

    public Reserva(int id, Cliente cliente, Habitacion habitacion, String fecha) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fecha = fecha;
    }
    
    public Reserva(Reserva otraReserva) {
        this.id = otraReserva.id;
        this.cliente = new Cliente(otraReserva.cliente);  
        this.habitacion = new Habitacion(otraReserva.habitacion);  
        this.fecha = otraReserva.fecha;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }    
    
}
