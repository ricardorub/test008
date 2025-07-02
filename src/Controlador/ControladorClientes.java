/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cliente.*;
import Modelo.Nodo;
import Vista.PanelCliente;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author Omara Baylón
 */
public class ControladorClientes {
    private PanelCliente vista;
    private ArbolCliente arbol;
    private ListaSimpleClientes listaEspera;
    private ColaTurnosClientes colaTurnos;
    private PilaModificacionesCliente pilaModificaciones;
    private ConsultasClientes consultas;
    
    public ControladorClientes(PanelCliente vista, ArbolCliente arbol, ListaSimpleClientes listaEspera,
                               ColaTurnosClientes colaTurnos, PilaModificacionesCliente pilaModificaciones,
                               ConsultasClientes consultas) {
        this.vista = vista;
        this.arbol = arbol;
        this.listaEspera = listaEspera;
        this.colaTurnos = colaTurnos;
        this.pilaModificaciones = pilaModificaciones;
        this.consultas = consultas;

        cargarDatosIniciales();
        actualizarTabla(listaEspera.mostrarTodos(), vista.getTableClientes());
        configurarEventos();
    }
    
    private void cargarDatosIniciales() {
        Cliente[] clientes = {
            new Cliente("12345678", "Valentina Vargas", 3),
            new Cliente("23456789", "Bruno Díaz", 5),
            new Cliente("04567890", "Natalia Román", 1),
            new Cliente("45678901", "Ignacio Salas", 2),
            new Cliente("56789012", "Camila Ríos", 4)
        };
        for (Cliente c : clientes) {
            arbol.insertar(c);
            listaEspera.agregarAlFinal(c);
        }
    }
    
    private void configurarEventos() {
        vista.getBtnRegistrar().addActionListener(e -> registrarCliente());
        vista.getBtnTurno().addActionListener(e -> asignarTurno());
        vista.getBtnAtender().addActionListener(e -> atenderCliente());
        vista.getBtnModificar().addActionListener(e -> modificarCliente());
        vista.getBtnOrdenar().addActionListener(e -> mostrarClientesOrdenados());
        vista.getBtnBuscar().addActionListener(e -> buscarClientePorDni());
        vista.getBtnHistorial().addActionListener(e -> mostrarHistorial());
        vista.getBtnClientesEspera().addActionListener(e -> mostrarClientesEnEspera());
    }


    private void registrarCliente() {
        String dni = vista.getTxtDni().getText().trim();
        String nombre = vista.getTxtNombre().getText().trim();
        String antiguedadStr = vista.getTxtAntiguedad().getText().trim();

        if (dni.isEmpty() || nombre.isEmpty() || antiguedadStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int antiguedad;
        try {
            antiguedad = Integer.parseInt(antiguedadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Antigüedad debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente nuevo = new Cliente(dni, nombre, antiguedad);
        arbol.insertar(nuevo);
        listaEspera.agregarAlFinal(nuevo);
        
        actualizarTabla(listaEspera.mostrarTodos(), vista.getTableClientes());   
        vista.limpiarCampos();
        JOptionPane.showMessageDialog(vista, "Cliente registrado con éxito.");
    }
    
    private void asignarTurno() {
        if (listaEspera.estaVacia()) {
            JOptionPane.showMessageDialog(vista, "No hay clientes en espera. Cola Vacia" );
            return;
        }

        Cliente cliente = (Cliente) listaEspera.getInicio().getDato();
        colaTurnos.encolar(cliente);
        listaEspera.eliminarPorDni(cliente.getDni());

        mostrarClientesEnEspera();
        JOptionPane.showMessageDialog(vista, "Turno asignado a: " + cliente.getNombre());
    }
    
    private void atenderCliente() {
        if (colaTurnos.estaVacio()) {
            JOptionPane.showMessageDialog(vista, "No hay clientes en la cola.");
            return;
        }
        
        Cliente cliente = (Cliente) colaTurnos.getFrente();
        colaTurnos.desencolar();

        JOptionPane.showMessageDialog(vista, "Cliente atendido: " + cliente.getNombre());
    }
    
    private void modificarCliente() {
        String dni = vista.getTxtDni().getText();
        Cliente original = (Cliente) arbol.buscar(dni);

        if (original == null) {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado.");
            return;
        }

        pilaModificaciones.push(new Cliente(original));
        
        String nombre = vista.getTxtNombre().getText().trim();
        String antiguedadStr = vista.getTxtAntiguedad().getText().trim();
        
        int antiguedad;
        try {
            antiguedad = Integer.parseInt(antiguedadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Antigüedad debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        original.setNombre(nombre);
        original.setAntiguedad(antiguedad);

        mostrarClientesOrdenados();
        JOptionPane.showMessageDialog(vista, "Cliente modificado.");
    } 
    
    private void mostrarClientesOrdenados() {
        Object[] clientesOrdenados = arbol.obtenerEnOrden();
        actualizarTabla(clientesOrdenados, vista.getTableClientes());
    }
    
    private void mostrarClientesEnEspera() {
        Object[] clientesEnEspera = listaEspera.mostrarTodos();
        actualizarTabla(clientesEnEspera, vista.getTableClientesEspera());
    }
    
    private void buscarClientePorDni() {
        String dni = vista.getTxtBuscar().getText();
        Cliente cliente = (Cliente) arbol.buscar(dni);

        if (cliente != null) {
            Object[] resultado = {cliente};
            actualizarTabla(resultado, vista.getTableClientes()); 
            JOptionPane.showMessageDialog(vista, "Cliente encontrado: " + cliente.getNombre());
        } else {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado con ese DNI.");
        }
        vista.limpiarCampos();
    }
    
    private void mostrarHistorial() {
        Object[] historial = pilaModificaciones.ArreglodeDatos();
        if (historial.length == 0) {
        JOptionPane.showMessageDialog(vista, "Aún no se han realizado modificaciones a ningún cliente, Historial vacío." );
        return;
    }

        actualizarTabla(historial, vista.getTableHistorial());
    }
    
    private void actualizarTabla(Object[] datos, JTable tabla) {
        String[][] filas = new String[datos.length][3];
        for (int i = 0; i < datos.length; i++) {
            Cliente c = (Cliente) datos[i];
            filas[i][0] = c.getDni();
            filas[i][1] = c.getNombre();
            filas[i][2] = String.valueOf(c.getAntiguedad());
        }
        vista.mostrar(filas, tabla);
    }

    private void refrescarTablasClientes() {
        mostrarClientesOrdenados();
        mostrarClientesEnEspera();
    }

}
