/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Tarea.OperacionesColas;
import Modelo.Tarea.Tarea;
import Vista.PanelReserva;
import javax.swing.JOptionPane;

/**
 *
 * @author Alicia Huamani
 */
public class ControladorTareas {

    private PanelReserva vista;
    private OperacionesColas cola;

    public ControladorTareas(PanelReserva vista) {
        this.vista = vista;
        this.cola = new OperacionesColas();

        cola.encolarOrden(new Tarea("Realizar el check-in de nuevos huéspedes", "Alta"));
        cola.encolarOrden(new Tarea("Limpiar las habitaciones del piso 3", "Alta"));
        cola.encolarOrden(new Tarea("Reparar la calefacción en la habitación 205", "Media"));
        cola.encolarOrden(new Tarea("Preparar el desayuno para los huéspedes", "Baja"));

        actualizarTabla();

        vista.getBtnAgregarTarea().addActionListener(e -> agregarTarea());
        vista.getBtnAtenderTarea().addActionListener(e -> desencolarTarea());
    }
   
    private void actualizarTabla() {
        vista.mostrar(cola.RecorrerTotalOrden(),vista.getTableTareasR());
    }

    private void agregarTarea() {
        String decripcion = vista.getTxtDescripcion().getText();
        String prioridad = vista.getComboPrioridad().getSelectedItem().toString();

        if (decripcion.isEmpty() || prioridad.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Completar todos los campos");
        }

        Tarea nueva = new Tarea(decripcion, prioridad);
        cola.encolarOrden(nueva);
        vista.limpiarCampos();
        actualizarTabla();
    }

    private void desencolarTarea() {
        cola.desencolarOrden();
        actualizarTabla();
        JOptionPane.showMessageDialog(vista, "Se Completo la primera tarea");
    }

}
