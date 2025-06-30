package Controlador;

import Modelo.CheckOut.ArbolCheckOut;
import Modelo.Cliente.Cliente;
import Modelo.Habitacion.Habitacion;
import Modelo.Habitacion.ListaCircularHabitaciones;
import Modelo.Historial.PilaHistorialReserva;
import Modelo.Reservas.ListaDobleReservas;
import Modelo.Reservas.Reserva;
import Vista.PanelCheckOut;

public class ControladorCheckOut {

    private PanelCheckOut vista;
    private ArbolCheckOut arbolCheckOut;
    private ListaDobleReservas listaTotalReservas; 
    private ListaCircularHabitaciones listaHabitaciones;
    private PilaHistorialReserva pilaHistorial;

    private static final double DEFAULT_PRECIO_POR_NOCHE = 50.0; 

    public ControladorCheckOut(PanelCheckOut vista,
                               ArbolCheckOut arbolCheckOut,
                               ListaDobleReservas listaTotalReservas,
                               ListaCircularHabitaciones listaHabitaciones,
                               PilaHistorialReserva pilaHistorial) {
        this.vista = vista;
        this.arbolCheckOut = arbolCheckOut;
        this.listaTotalReservas = listaTotalReservas;
        this.listaHabitaciones = listaHabitaciones;
        this.pilaHistorial = pilaHistorial;
        
        cargarReservasActivasEnArbol();
    }

    private void cargarReservasActivasEnArbol() {
        if (this.listaTotalReservas != null && !this.listaTotalReservas.estaVacia()) {
            Object[] todasLasReservas = this.listaTotalReservas.mostrarTodos();
            for (Object obj : todasLasReservas) {
                if (obj instanceof Reserva) {
                    Reserva reserva = (Reserva) obj;
                    if (reserva.getHabitacion() != null && !reserva.getHabitacion().isDisponible()) {
                         // ArbolCheckOut now keys by Reserva ID internally upon insert
                         this.arbolCheckOut.insertar(new Reserva(reserva)); 
                    }
                }
            }
        }
    }

    // Search by Reservation ID
    public Reserva buscarReservaPorIdParaPanel(int reservaId) { 
        if (reservaId <= 0) { // Basic validation
            System.err.println("ControladorCheckOut: ID de Reserva para búsqueda es inválido: " + reservaId);
            return null;
        }
        return this.arbolCheckOut.buscarPorId(reservaId);
    }

    public Object[] obtenerTodasLasReservasActivasParaPanel() { 
        if (this.arbolCheckOut == null) {
            return new Object[0];
        }
        return this.arbolCheckOut.obtenerTodasLasReservasActivas();
    }

    // Check-out process initiated with Reservation ID (as string from input)
    public boolean realizarCheckOutDesdePanel(String inputId, String fechaSalida) { 
        if (inputId == null || inputId.trim().isEmpty()) {
            System.err.println("ControladorCheckOut: ID de Reserva (input) para check-out es nulo o vacío.");
            return false;
        }

        int reservaId;
        try {
            reservaId = Integer.parseInt(inputId.trim());
            if (reservaId <= 0) {
                System.err.println("ControladorCheckOut: ID de Reserva parseado es inválido: " + reservaId);
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("ControladorCheckOut: Error al parsear ID de Reserva desde input: '" + inputId + "'. " + e.getMessage());
            return false;
        }

        Reserva reservaACheckOut = this.arbolCheckOut.buscarPorId(reservaId);

        if (reservaACheckOut != null) {
            Habitacion habitacionDeReservaOriginal = reservaACheckOut.getHabitacion();

            if (habitacionDeReservaOriginal != null) {
                int numHabInt = habitacionDeReservaOriginal.getNumero(); // Get room number for updating global list
                Object habitacionObjEnListaGlobal = this.listaHabitaciones.buscarPorId(numHabInt); 
                
                if (habitacionObjEnListaGlobal instanceof Habitacion) {
                    Habitacion habitacionEnListaGlobal = (Habitacion) habitacionObjEnListaGlobal;
                    habitacionEnListaGlobal.setDisponible(true); 
                } else {
                    System.err.println("ControladorCheckOut: Habitación " + numHabInt + 
                                       " (de la reserva ID " + reservaId + ") no encontrada en ListaCircularHabitaciones global. Estado de habitación no actualizado.");
                }
            } else {
                 System.err.println("ControladorCheckOut: La reserva con ID " + reservaId + 
                                    " no tiene una habitación asociada en el ArbolCheckOut.");
            }

            // Remove from ArbolCheckOut using Reservation ID
            this.arbolCheckOut.eliminarPorId(reservaId);

            if (this.pilaHistorial != null) {
                this.pilaHistorial.push(new Reserva(reservaACheckOut)); 
            }
            
            return true;
        } else {
            System.err.println("ControladorCheckOut: No se encontró reserva para check-out con ID: " + reservaId);
            return false;
        }
    }

    public String getClienteNombreCompleto(Cliente cliente) {
        return (cliente != null) ? cliente.getNombre() : "";
    }
    
    public String getClienteTelefono(Cliente cliente) {
        return (cliente != null) ? "N/A" : "N/A"; 
    }

    public String getClienteEmail(Cliente cliente) {
        return (cliente != null) ? "N/A" : "N/A"; 
    }
    
    public double getHabitacionPrecioPorNoche(Habitacion habitacion) {
        if (habitacion != null) {
            return DEFAULT_PRECIO_POR_NOCHE; 
        }
        return DEFAULT_PRECIO_POR_NOCHE;
    }
}
