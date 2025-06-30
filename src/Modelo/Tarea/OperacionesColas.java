/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Tarea;

import Modelo.Nodo;

/**
 *
 * @author Alicia Huamani
 */
public class OperacionesColas {

    private final ColaTareas alta;
    private final ColaTareas baja;
    private final ColaTareas media;

    public OperacionesColas() {
        this.alta = new ColaTareas();
        this.media = new ColaTareas();
        this.baja = new ColaTareas();
    }

    public void encolarOrden(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Esta vacio");
        }
        Tarea tarea = (Tarea) obj;

        String prioridad = tarea.getPrioridad();

        if (prioridad.equalsIgnoreCase("Alta")) {
            alta.encolar(tarea);
        } else if (prioridad.equalsIgnoreCase("Media")) {
            media.encolar(tarea);
        }
        if (prioridad.equalsIgnoreCase("Baja")) {
            baja.encolar(tarea);
        }
    }

    public void desencolarOrden() {
        if (!alta.estaVacio()) {
            alta.desencolar();
        } else if (!media.estaVacio()) {
            media.desencolar();
        } else if (!baja.estaVacio()) {
            baja.desencolar();
        }
    }
    
    
    public String [][] RecorrerTotalOrden(){
        int totalTareas = alta.contarNodos() + media.contarNodos() + baja.contarNodos();

        String[][] datos = new String[totalTareas][2];
        int i = 0;

        Nodo actual =alta.getFrente();
        while (actual != null) {
            Tarea t = (Tarea) actual.getDato();
            datos[i][0] = t.getDescripcion();
            datos[i][1] = t.getPrioridad();
            actual = actual.getSiguiente();
            i++;
        }

        actual = media.getFrente();
        while (actual != null) {
            Tarea t = (Tarea) actual.getDato();
            datos[i][0] = t.getDescripcion();
            datos[i][1] = t.getPrioridad();
            actual = actual.getSiguiente();
            i++;
        }

        actual = baja.getFrente();
        while (actual != null) {
            Tarea t = (Tarea) actual.getDato();
            datos[i][0] = t.getDescripcion();
            datos[i][1] = t.getPrioridad();
            actual = actual.getSiguiente();
            i++;
        }
        return datos;
    }
}
