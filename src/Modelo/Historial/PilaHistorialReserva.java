/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Historial;

import Modelo.Nodo;
import Modelo.Pila;
import Modelo.Reservas.Reserva;

/**
 *
 * @author Alicia Huamani
 */
public class PilaHistorialReserva implements Pila {

    private Nodo cima;

    public PilaHistorialReserva() {
        this.cima = null;
    }

    @Override
    public boolean estaVacio() {
        return cima == null;
    }

    @Override
    public void limpiar() {
        Nodo actual = cima;
        while (actual != null) {
            Nodo siguiente = actual.getSiguiente();
            actual.setSiguiente(null);
            actual = siguiente;
        }
        cima = null;
    }

    @Override
    public Object pop() {
        if (cima==null) {
            throw new IllegalArgumentException("la pila esta vacia");
        }
        Reserva reserva=(Reserva)cima.getDato();        
        cima=cima.getSiguiente();
        return reserva;
    }

    @Override
    public void push(Object obj) {
        Reserva reserva = (Reserva) obj;
        Nodo nuevo = new Nodo(reserva);
        if (cima == null) {
            cima = nuevo;
        } else {
            nuevo.setSiguiente(cima);
            cima = nuevo;
        }
    }
    
    @Override
    public Object[] ArreglodeDatos() {
    int count = 0;
    Nodo actual = cima;
    while (actual != null) {
        count++;
        actual = actual.getSiguiente();
    }

    Object[] array = new Object[count];
    actual = cima;
    for (int i = 0; i < count; i++) {
        array[i] = actual.getDato();
        actual = actual.getSiguiente();
    }
    return array;
}

}
